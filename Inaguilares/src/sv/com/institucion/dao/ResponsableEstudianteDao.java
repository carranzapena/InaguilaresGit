package sv.com.institucion.dao;

import javax.persistence.CacheStoreMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import sv.com.institucion.entidades.ResponsableEstudiante;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class ResponsableEstudianteDao extends FabricaAbstracta<ResponsableEstudiante> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger logger = Logger.getLogger(ResponsableEstudiante.class);

	public ResponsableEstudianteDao() {
		super(ResponsableEstudiante.class);
	}
	
    public ResponsableEstudiante obtenerResponsableEstudiantePorAlumno(Integer idAlumno){        
    	ResponsableEstudiante t=new ResponsableEstudiante();
        try{
        Query q=obtenerManejadorEntidades().createQuery("SELECT r FROM ResponsableEstudiante r WHERE r.estudiante.idEstudiante = :idEstudiante", ResponsableEstudiante.class)
        		.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        q.setParameter("idEstudiante", idAlumno);
        t=(ResponsableEstudiante) q.getSingleResult();
        }catch(NoResultException ex){
        	t = null;
        	ex.printStackTrace();
        }catch(Exception e){
        	e.printStackTrace();
        	logger.error("Ocurrio un error en obtenerResponsableEstudiantePorAlumno Mensaje: "+e.getMessage());
        	logger.error("Ocurrio un error en obtenerResponsableEstudiantePorAlumno Causa: "+e.getCause());
        	logger.fatal(e.toString());
        }
        
        return t;
    }
	
	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
