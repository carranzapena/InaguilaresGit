package sv.com.institucion.dao;

import sv.com.institucion.entidades.Inscripcion;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class InscripcionDao extends FabricaAbstracta<Inscripcion> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InscripcionDao() {
		super(Inscripcion.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
