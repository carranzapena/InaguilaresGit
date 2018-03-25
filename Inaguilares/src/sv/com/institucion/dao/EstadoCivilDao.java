package sv.com.institucion.dao;

import sv.com.institucion.entidades.EstadoCivil;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class EstadoCivilDao extends FabricaAbstracta<EstadoCivil> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EstadoCivilDao() {
		super(EstadoCivil.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
