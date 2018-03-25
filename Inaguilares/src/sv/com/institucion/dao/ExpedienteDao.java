package sv.com.institucion.dao;

import sv.com.institucion.entidades.Expediente;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class ExpedienteDao extends FabricaAbstracta<Expediente> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpedienteDao() {
		super(Expediente.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
