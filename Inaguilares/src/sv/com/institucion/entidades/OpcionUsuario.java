package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opcion_usuario database table.
 * 
 */
@Entity
@Table(name="opcion_usuario")
@NamedQuery(name="OpcionUsuario.findAll", query="SELECT o FROM OpcionUsuario o")
public class OpcionUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_OPCION_USUARIO")
	private int idOpcionUsuario;

	//bi-directional many-to-one association to Opcion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_OPCION")
	private Opcion opcion;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

	public OpcionUsuario() {
	}

	public int getIdOpcionUsuario() {
		return this.idOpcionUsuario;
	}

	public void setIdOpcionUsuario(int idOpcionUsuario) {
		this.idOpcionUsuario = idOpcionUsuario;
	}

	public Opcion getOpcion() {
		return this.opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}