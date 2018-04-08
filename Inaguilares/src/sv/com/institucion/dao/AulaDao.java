package sv.com.institucion.dao;

import sv.com.institucion.entidades.Aula;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class AulaDao extends FabricaAbstracta<Aula> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AulaDao() {
		super(Aula.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
