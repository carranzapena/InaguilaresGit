package sv.com.institucion.dao;

import sv.com.institucion.entidades.Gradoseccion;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class GradoSeccionDao extends FabricaAbstracta<Gradoseccion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GradoSeccionDao() {
		super(Gradoseccion.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
