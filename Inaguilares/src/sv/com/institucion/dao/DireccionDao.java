package sv.com.institucion.dao;


import sv.com.institucion.entidades.Direccion;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class DireccionDao extends FabricaAbstracta<Direccion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DireccionDao() {
		super(Direccion.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
