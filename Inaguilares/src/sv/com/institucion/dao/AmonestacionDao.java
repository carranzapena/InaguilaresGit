package sv.com.institucion.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CacheStoreMode;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import sv.com.institucion.entidades.Amonestacion;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class AmonestacionDao extends FabricaAbstracta<Amonestacion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger logger = Logger.getLogger(Amonestacion.class);

	public AmonestacionDao() {
		super(Amonestacion.class);
	}
	
    @SuppressWarnings("unchecked")
	public List<Amonestacion> getAllByDate(Date fechaEvaluar){
        List<Amonestacion> amonestaciones = new ArrayList<Amonestacion>();
        try {
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");            
            amonestaciones = obtenerManejadorEntidades().createQuery("Select a from Amonestacion a where a.fecha = :fecha")
                    .setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH)
                    .setParameter("fecha", fecha.parse(fecha.format(fechaEvaluar))).getResultList();
        } catch (Exception e) {
        	logger.error("Ha ocurrido un error en el metodo getAllByDate->AmonestacionDao"+e.toString());
        }
        return amonestaciones;
    }
    
  
    
    
      @SuppressWarnings("unchecked")
	public List<Amonestacion> getByFilter(String carnet, Date fecha, String estado){
        List<Amonestacion> amonestaciones = new ArrayList<Amonestacion>();
        StringBuilder sb = new StringBuilder();
        Boolean carnetB = false;
        Boolean estadoB = false;
        Boolean fechaB = false;
        try {
            sb.append("select a from Amonestacion a inner join a.expediente e inner join ")
                    .append("e.estudiante et where ");
            if(carnet != null && !"".equalsIgnoreCase(carnet)){
                sb.append("et.nie = :carnet").append(fecha != null || (estado != null && !estado.equalsIgnoreCase("")) ? " AND " : "");
                carnetB = true;
            }if(fecha != null){
                sb.append("a.fecha = :fecha").append(estado != null && !estado.equalsIgnoreCase("") ? " AND " : "");
                fechaB = true;
            }if(estado != null && !"".equalsIgnoreCase(estado)){
                 sb.append("a.estado = :estado");
                estadoB= true;
            }
            System.out.println(sb.toString());
            Query q = obtenerManejadorEntidades().createQuery(sb.toString());
            if(carnetB == true)
                q.setParameter("carnet", carnet);
            if(estadoB == true)
                q.setParameter("estado", estado);
            if(fechaB == true){
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                q.setParameter("fecha", formato.parse(formato.format(fecha)));                
            }                                           
            amonestaciones =  q.getResultList();
        } catch (Exception e) {
        	logger.error("Ha ocurrido un error en el metodo getByFilter->AmonestacionDao"+e.toString());
        }
        return amonestaciones;
    }

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
