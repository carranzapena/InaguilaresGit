package sv.com.institucion.dao;

import sv.com.institucion.entidades.Nivel;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class NivelDao extends FabricaAbstracta<Nivel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NivelDao() {
		super(Nivel.class);
	}
	
	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}
}
