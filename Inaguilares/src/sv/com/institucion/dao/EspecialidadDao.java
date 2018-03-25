package sv.com.institucion.dao;

import sv.com.institucion.entidades.Especialidad;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class EspecialidadDao extends FabricaAbstracta<Especialidad> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EspecialidadDao() {
		super(Especialidad.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
