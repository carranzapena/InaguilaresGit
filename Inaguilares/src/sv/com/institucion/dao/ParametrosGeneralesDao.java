package sv.com.institucion.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import sv.com.institucion.entidades.ParametrosGenerales;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class ParametrosGeneralesDao extends FabricaAbstracta<ParametrosGenerales> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParametrosGeneralesDao() {
		super(ParametrosGenerales.class);
	}
	
	public int executeQueryImportCsvOnMysql(String sql){
		int respuesta = 0;
      try {
    	  EntityManager em = obtenerManejadorEntidades();
    	  EntityTransaction trans = em.getTransaction();
    	  trans.begin();
    	  respuesta = em.createNativeQuery(sql).executeUpdate();
    	  trans.commit();
        return respuesta;
      } catch (Exception e) {
           e.printStackTrace();
      }
      return respuesta;
  }
  
  @SuppressWarnings("unchecked")
public Map<String,String> getAllMapParameters(){
      List<ParametrosGenerales> parametros = new ArrayList<ParametrosGenerales>();
      Map<String,String> parametrosMapa = new HashMap<String,String>();
      try {
          parametros = obtenerManejadorEntidades().createNamedQuery("ParametrosGenerale.findAll")
                  .setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH)
                  .getResultList();
          for (ParametrosGenerales parametro : parametros) {
              parametrosMapa.put(parametro.getLlave(), parametro.getValor());
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
      return parametrosMapa;
  }

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
