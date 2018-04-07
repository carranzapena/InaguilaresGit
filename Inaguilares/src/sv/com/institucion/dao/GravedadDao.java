package sv.com.institucion.dao;

import sv.com.institucion.entidades.Gravedad;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class GravedadDao extends FabricaAbstracta<Gravedad> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GravedadDao() {
		super(Gravedad.class);
	}


	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
