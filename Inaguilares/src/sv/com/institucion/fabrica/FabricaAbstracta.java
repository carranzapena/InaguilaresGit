package sv.com.institucion.fabrica;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class FabricaAbstracta<T>  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Class<T> clase;
	
	public FabricaAbstracta(Class<T> clase){
		this.clase = clase;
	}
	
	public EntityManager obtenerManejadorEntidades() {
		EntityManagerFactory fabricaManejadoresEntidad = Persistence
				.createEntityManagerFactory(obtenerNombreUnidadPersistencia());
		return fabricaManejadoresEntidad.createEntityManager();
	}

	public T crearEntidad(T entidad) {
		EntityManager em = obtenerManejadorEntidades();
		em.getTransaction().begin();
		em.persist(entidad);
		em.flush();
		em.getTransaction().commit();
		return entidad;
	}

	public void modificarEntidad(T entidad) {
		EntityManager em = obtenerManejadorEntidades();
		em.getTransaction().begin();
		em.merge(entidad);
		em.getTransaction().commit();
		em.close();
	}

	public void eliminarEntidad(T entidad) {
		EntityManager em = obtenerManejadorEntidades();
		em.getTransaction().begin();
		em.remove(em.merge(entidad));
		em.getTransaction().commit();
		em.close();
	}
	  
	@SuppressWarnings("unchecked")
	public List<T> obtenerTodos() {
		EntityManager em = obtenerManejadorEntidades();
		try {
			String jpql = "select a from " + this.clase.getSimpleName() + " a";
			System.out.println(jpql);
			Query ojpql = em.createQuery(jpql);
			return ojpql.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}
		
	public T obtenerPorId(Integer id) {
		return obtenerManejadorEntidades().find(clase, id);
	}
	
	public void eliminarPorId(Integer id) {
		T entity = obtenerPorId(id);
		eliminarEntidad(entity);
	}
	
	public abstract String obtenerNombreUnidadPersistencia();	

}
