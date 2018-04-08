package sv.com.institucion.dao;

import sv.com.institucion.entidades.Detallehorario;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class DetalleHorarioDao extends FabricaAbstracta<Detallehorario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DetalleHorarioDao() {
		super(Detallehorario.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
