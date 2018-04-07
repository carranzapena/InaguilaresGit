package sv.com.institucion.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CacheStoreMode;
import org.apache.log4j.Logger;
import sv.com.institucion.entidades.Asistencia;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class AsistenciaDao extends FabricaAbstracta<Asistencia> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger logger = Logger.getLogger(AsistenciaDao.class);

	public AsistenciaDao() {
		super(Asistencia.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Asistencia> getAllByDate(Date fechaEvaluar) throws Exception{
        List<Asistencia> asistencias = new ArrayList<Asistencia>();
        try {
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            asistencias = obtenerManejadorEntidades().createQuery("SELECT a FROM Asistencia a where a.fecha = :fecha")
                    .setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH)
                    .setParameter("fecha", fecha.parse(fecha.format(fechaEvaluar))).getResultList();
        } catch (Exception e) {
        	logger.error("Ha ocurrido un error en el metodo getAllByDate->AsistenciaDao: "+e.toString());
        	throw new Exception(e.toString());
        }
        return asistencias;
    }

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
