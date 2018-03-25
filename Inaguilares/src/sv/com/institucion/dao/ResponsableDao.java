package sv.com.institucion.dao;

import sv.com.institucion.entidades.Responsable;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class ResponsableDao extends FabricaAbstracta<Responsable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResponsableDao() {
		super(Responsable.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
