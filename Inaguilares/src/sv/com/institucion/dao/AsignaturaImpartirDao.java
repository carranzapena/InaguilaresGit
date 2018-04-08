package sv.com.institucion.dao;

import sv.com.institucion.entidades.Asignaturaimpartir;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class AsignaturaImpartirDao extends FabricaAbstracta<Asignaturaimpartir> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AsignaturaImpartirDao() {
		super(Asignaturaimpartir.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
