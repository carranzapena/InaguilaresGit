package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USUARIO")
	private int idUsuario;

	private String contrasenia;

	private String estado;

	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;

	private String usuario;

	private String usuariocrea;

	private String usuraiomodifica;

	//bi-directional many-to-one association to OpcionUsuario
	@OneToMany(mappedBy="usuario")
	private List<OpcionUsuario> opcionUsuarios;

	//bi-directional many-to-one association to Persona
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PERSONA")
	private Persona persona;

	//bi-directional many-to-one association to UsuarioRol
	@OneToMany(mappedBy="usuario")
	private List<UsuarioRol> usuarioRols;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuariocrea() {
		return this.usuariocrea;
	}

	public void setUsuariocrea(String usuariocrea) {
		this.usuariocrea = usuariocrea;
	}

	public String getUsuraiomodifica() {
		return this.usuraiomodifica;
	}

	public void setUsuraiomodifica(String usuraiomodifica) {
		this.usuraiomodifica = usuraiomodifica;
	}

	public List<OpcionUsuario> getOpcionUsuarios() {
		return this.opcionUsuarios;
	}

	public void setOpcionUsuarios(List<OpcionUsuario> opcionUsuarios) {
		this.opcionUsuarios = opcionUsuarios;
	}

	public OpcionUsuario addOpcionUsuario(OpcionUsuario opcionUsuario) {
		getOpcionUsuarios().add(opcionUsuario);
		opcionUsuario.setUsuario(this);

		return opcionUsuario;
	}

	public OpcionUsuario removeOpcionUsuario(OpcionUsuario opcionUsuario) {
		getOpcionUsuarios().remove(opcionUsuario);
		opcionUsuario.setUsuario(null);

		return opcionUsuario;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<UsuarioRol> getUsuarioRols() {
		return this.usuarioRols;
	}

	public void setUsuarioRols(List<UsuarioRol> usuarioRols) {
		this.usuarioRols = usuarioRols;
	}

	public UsuarioRol addUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().add(usuarioRol);
		usuarioRol.setUsuario(this);

		return usuarioRol;
	}

	public UsuarioRol removeUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().remove(usuarioRol);
		usuarioRol.setUsuario(null);

		return usuarioRol;
	}

}