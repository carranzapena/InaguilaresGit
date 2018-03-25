package sv.com.institucion.dao;

import sv.com.institucion.entidades.Parentesco;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class ParentescoDao extends FabricaAbstracta<Parentesco> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParentescoDao() {
		super(Parentesco.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
