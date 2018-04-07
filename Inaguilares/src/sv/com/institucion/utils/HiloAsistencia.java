package sv.com.institucion.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sv.com.institucion.dao.ParametrosGeneralesDao;

/**
 *
 * @author Gerardo
 */
public class HiloAsistencia implements Runnable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Logger logger = Logger.getLogger(HiloAsistencia.class);
    
    private String nombre;
    private String directorioMonitorear;
    private File archivosEnDirectorio;
    private File archivoProcesando;
    private File archivoConvertido;
    private Map<String,String> parametros;
    public static ScheduledExecutorService scheduledExecutorService;

    private final static String DELIMITER = ";";
    private final static String XLSX = "xlsx";
    private final static String XLS = "xls";
    private final static String CSV = "csv";
    
    private ParametrosGeneralesDao parametrosGeneralesDao;
    
    public HiloAsistencia(String nombre, String directorioMonitorear,ParametrosGeneralesDao parametrosGeneralesDao, ScheduledExecutorService scheduledExecutorService){
		this.nombre = nombre;
		this.directorioMonitorear = directorioMonitorear;
                this.parametrosGeneralesDao = parametrosGeneralesDao;
                HiloAsistencia.scheduledExecutorService = scheduledExecutorService;
                
    }
     @Override
     public void run() {
         parametros = parametrosGeneralesDao.getAllMapParameters();
         if(this.parametros.get("estadoProdAsincrono").equalsIgnoreCase("H")){
            logger.info("Iniciando Hilo");
            validarDirectorio();
            leerArchivo();
            eliminarArchivo();
            logger.info("Proceso asincrono a dormir");
            Thread.currentThread().setName(nombre);
         }
      }
	
    private void validarDirectorio(){
            archivosEnDirectorio = new File(this.directorioMonitorear);
            if(!archivosEnDirectorio.exists())
                    logger.info("El directorio no existe");
            if(!archivosEnDirectorio.isDirectory())
                    logger.info("No es un directorio valido");
    }

    private void eliminarArchivo(){
            if(archivoProcesando != null && archivoProcesando.exists()){
                    if(archivoProcesando.delete()){
                            if(archivoConvertido!= null && archivoConvertido.exists()){
                                    if(archivoConvertido.delete())
                                            logger.info("archivos eliminados con exito");
                                    else
                                            logger.info("No se pudo eliminar el archivo convertido");
                            }else{
                                    logger.info("Archivo eliminado con exito");
                            }
                    }else{
                            logger.info("No se pudo eliminar el archivo que se procesa");
                    }
            }else{
                    logger.info("No se puede eliminar el archivo ya que no se encuentra");
            }
    }

    private Boolean procesarArchivoCsv(String Finalpath) throws SQLException{
            StringBuilder sb = new StringBuilder();
            Boolean respuesta = false;
            try {
                    sb.append("LOAD DATA LOCAL INFILE '")
                    .append(Finalpath.replace("\\","\\\\")).append("' ")
                    .append("INTO TABLE ASISTENCIA ")
                    .append("FIELDS TERMINATED BY ';' ")
                    .append("LINES TERMINATED BY '\\n' ")
                    .append("IGNORE 1 LINES ")
                    .append("(@CARNET,@FECHA,HORA) ")
                    .append("SET IDEXPEDIENTE = (SELECT ID_EXPEDIENTE FROM EXPEDIENTE WHERE ANIO_ESCOLAR = YEAR(NOW()) ")
                    .append("AND ID_ESTUDIANTE = OBTENERIDESTUDIANTE(@CARNET)), ")
                    .append("IDCATALOGOASISTENCIA = (SELECT IDCATALOGOASISTENCIA FROM CATALOGOASISTENCIA WHERE NOMBRE = ")
                    .append("(CASE WHEN HOUR(NOW()) > 12 THEN 'VESPERTINA' ELSE 'MATUTINA' END)), FECHA = STR_TO_DATE(@FECHA, '%d/%m/%Y')");
                    logger.info(sb.toString());
                    parametrosGeneralesDao.executeQueryImportCsvOnMysql(sb.toString());
                    respuesta = true;
            } catch (Exception e) {
                    e.printStackTrace();
            }finally{
                   sb = null;
            }
            return respuesta;

    }

    private void leerArchivo(){
                    logger.info("Iniciando la lectura del archivo");
                    Boolean respuesta = false;
            try {
                    if(archivosEnDirectorio.listFiles().length > 0){
                    for(File archive: archivosEnDirectorio.listFiles()){
                            archivoProcesando = new File(this.directorioMonitorear+archive.getName());
                            if(archive.getName().contains("xls")||archive.getName().contains("xlsx")){			
                                    this.archivoConvertido = new File(this.directorioMonitorear+compareArchiveNames(archive.getName()));
                                    fromXlsxToCsv(archivoProcesando, this.archivoConvertido);
                                    respuesta = procesarArchivoCsv(this.archivoConvertido.getAbsolutePath());
                            }else if(archive.getName().contains("csv")){
                                    respuesta = procesarArchivoCsv(archivoProcesando.getAbsolutePath());					
                            }else{
                                    logger.info("El archivo posee un formato no valido");
                            }

                            if(respuesta)
                                    logger.info("El archivo se ha procesado satisfactoriamente");
                            else
                                    logger.info("Hubo un problema al procesar el archivo");
                    }
                    }else{
                            logger.info("Archivo no existe");
                    }
            }catch (NullPointerException ne) {
                logger.error("No se ha encontrado la carpeta que contiene los archivos");
            } 
            catch (Exception e) {
            	logger.error("Ha ocurrido un error al momento de leer el archivo: "+e.toString());
            }

    }

    private String compareArchiveNames(String name){
            if(name.contains(XLSX))
                    return name.replace(XLSX, CSV);
            else if(name.contains(XLS))
                    return name.replace(XLS, CSV);
            else
                    return "";
    }

private void fromXlsxToCsv(File inputFile, File outputFile) {
    // For storing data into CSV files
    StringBuilder data = new StringBuilder();

    try {
        FileOutputStream fos = new FileOutputStream(outputFile);
        // Get the workbook object for XLSX file
        XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(inputFile));
        // Get first sheet from the workbook
        XSSFSheet sheet = wBook.getSheetAt(0);
        Row row;
        Cell cell;
        // Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            row = rowIterator.next();

            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {

                cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        data.append(cell.getBooleanCellValue()).append(DELIMITER);

                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        data.append(cell.getNumericCellValue()).append(DELIMITER);

                        break;
                    case Cell.CELL_TYPE_STRING:
                        data.append(cell.getStringCellValue()).append(DELIMITER);
                        break;

                    case Cell.CELL_TYPE_BLANK:
                        data.append("" + DELIMITER);
                        break;
                    default:
                        data.append(cell).append(DELIMITER);

                }
            }
        }
        fos.write(data.toString().getBytes());
        fos.close();
    } catch (Exception ioe) {
        ioe.printStackTrace();
    }
}


	
}
