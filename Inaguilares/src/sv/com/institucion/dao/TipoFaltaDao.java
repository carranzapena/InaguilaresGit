package sv.com.institucion.dao;

import sv.com.institucion.entidades.Tipofalta;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class TipoFaltaDao extends FabricaAbstracta<Tipofalta> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TipoFaltaDao() {
		super(Tipofalta.class);
	}


	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
