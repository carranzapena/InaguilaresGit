package sv.com.institucion.dao;

import sv.com.institucion.entidades.Persona;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class PersonaDao extends FabricaAbstracta<Persona> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonaDao() {
		super(Persona.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
