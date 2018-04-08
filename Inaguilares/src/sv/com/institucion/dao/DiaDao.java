package sv.com.institucion.dao;

import sv.com.institucion.entidades.Dia;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class DiaDao extends FabricaAbstracta<Dia> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DiaDao() {
		super(Dia.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
