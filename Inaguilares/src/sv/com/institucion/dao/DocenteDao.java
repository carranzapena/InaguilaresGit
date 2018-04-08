package sv.com.institucion.dao;

import sv.com.institucion.entidades.Docente;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class DocenteDao extends FabricaAbstracta<Docente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DocenteDao() {
		super(Docente.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
