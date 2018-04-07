package sv.com.institucion.dao;

import sv.com.institucion.entidades.Falta;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class FaltaDao extends FabricaAbstracta<Falta> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FaltaDao() {
		super(Falta.class);
	}


	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
