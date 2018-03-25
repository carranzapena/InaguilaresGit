package sv.com.institucion.dao;

import sv.com.institucion.entidades.Municipio;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class MunicipioDao extends FabricaAbstracta<Municipio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MunicipioDao() {
		super(Municipio.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
