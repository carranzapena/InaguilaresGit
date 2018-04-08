package sv.com.institucion.dao;

import sv.com.institucion.entidades.Asignatura;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class AsignaturaDao extends FabricaAbstracta<Asignatura> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AsignaturaDao() {
		super(Asignatura.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
