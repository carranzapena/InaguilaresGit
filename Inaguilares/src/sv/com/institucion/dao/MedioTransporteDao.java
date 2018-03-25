package sv.com.institucion.dao;

import sv.com.institucion.entidades.MedioTransporte;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class MedioTransporteDao extends FabricaAbstracta<MedioTransporte> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MedioTransporteDao() {
		super(MedioTransporte.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
