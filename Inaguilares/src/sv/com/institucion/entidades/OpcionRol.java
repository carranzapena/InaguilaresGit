package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opcion_rol database table.
 * 
 */
@Entity
@Table(name="opcion_rol")
@NamedQuery(name="OpcionRol.findAll", query="SELECT o FROM OpcionRol o")
public class OpcionRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_OPCION_PERFIL")
	private int idOpcionPerfil;

	//bi-directional many-to-one association to Opcion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_OPCION")
	private Opcion opcion;

	//bi-directional many-to-one association to Rol
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_ROL")
	private Rol rol;

	public OpcionRol() {
	}

	public int getIdOpcionPerfil() {
		return this.idOpcionPerfil;
	}

	public void setIdOpcionPerfil(int idOpcionPerfil) {
		this.idOpcionPerfil = idOpcionPerfil;
	}

	public Opcion getOpcion() {
		return this.opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}