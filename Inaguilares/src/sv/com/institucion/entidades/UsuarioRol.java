package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario_rol database table.
 * 
 */
@Entity
@Table(name="usuario_rol")
@NamedQuery(name="UsuarioRol.findAll", query="SELECT u FROM UsuarioRol u")
public class UsuarioRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idusuariorol;

	//bi-directional many-to-one association to Amonestacion
	@OneToMany(mappedBy="usuarioRol")
	private List<Amonestacion> amonestacions;

	//bi-directional many-to-one association to Rol
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDROL")
	private Rol rol;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUSUARIO")
	private Usuario usuario;

	public UsuarioRol() {
	}

	public int getIdusuariorol() {
		return this.idusuariorol;
	}

	public void setIdusuariorol(int idusuariorol) {
		this.idusuariorol = idusuariorol;
	}

	public List<Amonestacion> getAmonestacions() {
		return this.amonestacions;
	}

	public void setAmonestacions(List<Amonestacion> amonestacions) {
		this.amonestacions = amonestacions;
	}

	public Amonestacion addAmonestacion(Amonestacion amonestacion) {
		getAmonestacions().add(amonestacion);
		amonestacion.setUsuarioRol(this);

		return amonestacion;
	}

	public Amonestacion removeAmonestacion(Amonestacion amonestacion) {
		getAmonestacions().remove(amonestacion);
		amonestacion.setUsuarioRol(null);

		return amonestacion;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}