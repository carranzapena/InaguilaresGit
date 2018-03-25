package sv.com.institucion.dao;

import sv.com.institucion.entidades.Institucionprocedencia;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class InstitucionProcedenciaDao extends FabricaAbstracta<Institucionprocedencia> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InstitucionProcedenciaDao() {
		super(Institucionprocedencia.class);
	}

	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
