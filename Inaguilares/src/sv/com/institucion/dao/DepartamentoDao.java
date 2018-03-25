package sv.com.institucion.dao;

import sv.com.institucion.entidades.Departamento;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class DepartamentoDao extends FabricaAbstracta<Departamento> {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepartamentoDao() {
		super(Departamento.class);
	}


	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}
}
