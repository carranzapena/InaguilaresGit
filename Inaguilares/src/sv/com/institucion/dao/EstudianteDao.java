package sv.com.institucion.dao;

import sv.com.institucion.entidades.Estudiante;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class EstudianteDao extends FabricaAbstracta<Estudiante> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EstudianteDao() {
		super(Estudiante.class);
	}


	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
