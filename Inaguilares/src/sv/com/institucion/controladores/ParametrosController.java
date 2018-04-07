package sv.com.institucion.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import lombok.Getter;
import lombok.Setter;
import sv.com.institucion.dao.ParametrosGeneralesDao;
import sv.com.institucion.entidades.ParametrosGenerales;
import sv.com.institucion.utils.HiloAsistencia;

/**
 * @author Gerardo Garzona
 *
 */
@Getter
@Setter
@ManagedBean
@ViewScoped
public class ParametrosController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ParametrosController.class.getName());
    boolean banderaEditar;
    private ParametrosGenerales parametro;
    private ParametrosGenerales selected;
    private List<ParametrosGenerales> parametros;
    private List<ParametrosGenerales> parametrosFiltrados;
    private  FacesMessage message;
    private String tipoOperacion = "";
    private final static String RA = "RUTA_ARCHIVO_ASYNC";
    private final static String UT = "UNIDAD_TIEMPO_ASYNC";
    private final static String PA = "PERIODO_AYNC";
    private final static String CT = "UNIDAD_TIEMPO_ASYNC";
    private HiloAsistencia hl;

    private ParametrosGeneralesDao parametrosGeneralesDao;
    
    @ManagedProperty(value="#{login}")
    private Login login;
    

    @PostConstruct
    public void init() {
        try{
        parametrosGeneralesDao = new ParametrosGeneralesDao();
        parametros = new ArrayList<ParametrosGenerales>();
        this.setParametros(parametrosGeneralesDao.obtenerTodos());
        if(!login.isBanderaHilo()){
            System.err.println("Entre a ejecutar el hilo");
            startThread();
            login.setBanderaHilo(true);
        }
        parametro = new ParametrosGenerales();
        banderaEditar=false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//    public void validarProcesoAsincrono(List<ParametrosGenerales> parametros){
//        Boolean estado = false;
//        try {
//            for (ParametrosGenerales p : parametros) {
//                if(p.getLlave().equalsIgnoreCase("estadoProdAsincrono")){
//                    estado = p.getValor().equalsIgnoreCase("H") ? true : false;
//                    contador = p.getValor().equalsIgnoreCase("D") ? 0 : contador;
//                    if(estado && contador == 0){
//                        startThread();
//                        contador ++;
//                        break;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private int nextPK() {
//        int nextId = 0;
//        nextId = parametrosGeneralesDao.lastInsert();
//        return nextId + 1;
//    }
    
   
    public void save() {
    	parametrosGeneralesDao = new ParametrosGeneralesDao();
        try {            
            parametrosGeneralesDao.crearEntidad(this.parametro);
            this.parametros = parametrosGeneralesDao.obtenerTodos();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro almacenado");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo almacenar");
        } finally {
            this.limpiar();
            RequestContext.getCurrentInstance().execute("PF('wvtableParametros').filter();");
            RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide();");
            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
            RequestContext.getCurrentInstance().update("messageGlobal");
        }
    }
    public void preparatedEliminar(){
        try{
            this.parametro=selected;
            RequestContext.getCurrentInstance().execute("PF('dlgEliminar').show();");
        }catch(Exception e){
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo preparatedEliminar->ParametrosController"+e.toString());
        }
    }
    public void eliminar() {
    	parametrosGeneralesDao = new ParametrosGeneralesDao();
        try {            
            parametrosGeneralesDao.eliminarEntidad(this.parametro);
            this.setParametros(parametrosGeneralesDao.obtenerTodos());
             message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro eliminado");
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo save->ParametrosController"+e.toString());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo almacenar");
        } finally {
            this.limpiar();
            this.banderaEditar=false;
            RequestContext.getCurrentInstance().execute(" PF('wvtableParametros').filter();");
            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
            RequestContext.getCurrentInstance().update("messageGlobal");

        }

    }

    public void editar() {
    	parametrosGeneralesDao = new ParametrosGeneralesDao();
        try {
             parametrosGeneralesDao.modificarEntidad(this.getParametro()); 
             System.out.println("Entre a editar");
             System.out.println(this.getParametro().getLlave());
            if(this.getParametro().getLlave().equalsIgnoreCase(CT) || this.getParametro().getLlave().equalsIgnoreCase(PA)){
                System.out.println("Entre a editar thread"+this.getParametro().getLlave());
                HiloAsistencia.scheduledExecutorService.shutdown();
                HiloAsistencia.scheduledExecutorService.shutdownNow();
                startThread();
            }
            this.setParametros(parametrosGeneralesDao.obtenerTodos());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro editado");
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo editar->ParametrosController"+e.toString());
             message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo almacenar");
        } finally {
            this.limpiar();
            RequestContext.getCurrentInstance().execute("PF('wvtableParametros').filter();");
            RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide();");
            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
            RequestContext.getCurrentInstance().update("messageGlobal");
        }

    }
    public void prepareEdit(){
        try{
            this.setParametro(selected);
            this.setParametros(parametrosGeneralesDao.obtenerTodos());
            this.banderaEditar=true;
            this.tipoOperacion = "Editar";
            RequestContext.getCurrentInstance().update("dlgEditar");
            RequestContext.getCurrentInstance().execute(" PF('dlgModificar').show();");            
        }catch(Exception e){
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo prepareEdit->ParametrosController"+e.toString());
        }
    }
    
    public void prepareSave(){
        try{
            this.parametro = new ParametrosGenerales();
            this.banderaEditar=false;
            this.tipoOperacion = "Agregar";
            RequestContext.getCurrentInstance().update("dlgEditar");
                          RequestContext.getCurrentInstance().execute(" PF('dlgModificar').show();");   
        }catch(Exception e){
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo prepareSave->ParametrosController"+e.toString());
        }
    }
    
     public void startThread(){
    	 parametrosGeneralesDao = new ParametrosGeneralesDao();
      try{
        Map<String,String> parametros = parametrosGeneralesDao.getAllMapParameters();
        ScheduledExecutorService  scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        hl = new HiloAsistencia("HiloCSV",parametros.get(RA),parametrosGeneralesDao,scheduledExecutorService);
        System.out.println(devolverUnidad(parametros.get(UT)));
        scheduledExecutorService.scheduleAtFixedRate(hl,  0 , Integer.parseInt(parametros.get(PA)) , devolverUnidad(parametros.get(UT)));
      }catch(Exception e){
      	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo startThread->ParametrosController"+e.toString());
      }
    }
     
      public TimeUnit devolverUnidad(String tipo){
        if(tipo.equalsIgnoreCase("SEGUNDOS"))
            return TimeUnit.SECONDS;
        else if(tipo.equalsIgnoreCase("MINUTOS"))
            return TimeUnit.MINUTES;
        else
           return TimeUnit.HOURS;
    }
    
    
    public void validarTipoOperacion(){
        if(this.banderaEditar)
            editar();
        else
            save();
    }

    public void limpiar() {
        this.parametro = new ParametrosGenerales();
    }       
}