package sv.com.institucion.dao;

import sv.com.institucion.entidades.Estadoinscripcion;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class EstadoInscripcionDao extends FabricaAbstracta<Estadoinscripcion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EstadoInscripcionDao() {
		super(Estadoinscripcion.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}
}
