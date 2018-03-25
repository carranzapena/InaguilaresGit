package sv.com.institucion.dao;

import sv.com.institucion.entidades.Enfermedad;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class EnfermedadDao extends FabricaAbstracta<Enfermedad> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnfermedadDao() {
		super(Enfermedad.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
