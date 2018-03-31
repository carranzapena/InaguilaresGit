package sv.com.institucion.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sv.com.institucion.entidades.Expediente;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class ExpedienteDao extends FabricaAbstracta<Expediente> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpedienteDao() {
		super(Expediente.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}
	
	 @SuppressWarnings("unchecked")
	public List<Expediente> getAutoCompleteText(String search){
	        List<Expediente> asistencias = new ArrayList<Expediente>();
	        try {
	            asistencias = obtenerManejadorEntidades().createQuery("select e from Expediente e inner join e.estudiante et "
	                    + "inner join et.persona p where e.anioEscolar = :anio and p.nombres like :valor or p.apellidos like :valor")
	                    .setParameter("anio",Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())))
	                    .setParameter("valor", "%"+search+"%")
	                    .getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return asistencias;
	    }
	     
	     @SuppressWarnings("unchecked")
		public List<Expediente> getAllByYear(){
	        List<Expediente> asistencias = new ArrayList<Expediente>();
	        try {
	            asistencias = obtenerManejadorEntidades().createQuery("select e from Expediente e where e.anioEscolar = :anio")
	                    .setParameter("anio",Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())))
	                    .getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return asistencias;
	    }

}
