package sv.com.institucion.dao;

import sv.com.institucion.entidades.ExpedienteMedico;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class ExpedienteMedicoDao extends FabricaAbstracta<ExpedienteMedico> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpedienteMedicoDao() {
		super(ExpedienteMedico.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
