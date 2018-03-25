package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the opcion database table.
 * 
 */
@Entity
@NamedQuery(name="Opcion.findAll", query="SELECT o FROM Opcion o")
public class Opcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_OPCION")
	private int idOpcion;

	private String icono;

	private String nombre;

	private String url;

	//bi-directional many-to-one association to Opcion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PADRE")
	private Opcion opcion;

	//bi-directional many-to-one association to Opcion
	@OneToMany(mappedBy="opcion")
	private List<Opcion> opcions;

	//bi-directional many-to-one association to OpcionRol
	@OneToMany(mappedBy="opcion")
	private List<OpcionRol> opcionRols;

	//bi-directional many-to-one association to OpcionUsuario
	@OneToMany(mappedBy="opcion")
	private List<OpcionUsuario> opcionUsuarios;

	public Opcion() {
	}

	public int getIdOpcion() {
		return this.idOpcion;
	}

	public void setIdOpcion(int idOpcion) {
		this.idOpcion = idOpcion;
	}

	public String getIcono() {
		return this.icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Opcion getOpcion() {
		return this.opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	public List<Opcion> getOpcions() {
		return this.opcions;
	}

	public void setOpcions(List<Opcion> opcions) {
		this.opcions = opcions;
	}

	public Opcion addOpcion(Opcion opcion) {
		getOpcions().add(opcion);
		opcion.setOpcion(this);

		return opcion;
	}

	public Opcion removeOpcion(Opcion opcion) {
		getOpcions().remove(opcion);
		opcion.setOpcion(null);

		return opcion;
	}

	public List<OpcionRol> getOpcionRols() {
		return this.opcionRols;
	}

	public void setOpcionRols(List<OpcionRol> opcionRols) {
		this.opcionRols = opcionRols;
	}

	public OpcionRol addOpcionRol(OpcionRol opcionRol) {
		getOpcionRols().add(opcionRol);
		opcionRol.setOpcion(this);

		return opcionRol;
	}

	public OpcionRol removeOpcionRol(OpcionRol opcionRol) {
		getOpcionRols().remove(opcionRol);
		opcionRol.setOpcion(null);

		return opcionRol;
	}

	public List<OpcionUsuario> getOpcionUsuarios() {
		return this.opcionUsuarios;
	}

	public void setOpcionUsuarios(List<OpcionUsuario> opcionUsuarios) {
		this.opcionUsuarios = opcionUsuarios;
	}

	public OpcionUsuario addOpcionUsuario(OpcionUsuario opcionUsuario) {
		getOpcionUsuarios().add(opcionUsuario);
		opcionUsuario.setOpcion(this);

		return opcionUsuario;
	}

	public OpcionUsuario removeOpcionUsuario(OpcionUsuario opcionUsuario) {
		getOpcionUsuarios().remove(opcionUsuario);
		opcionUsuario.setOpcion(null);

		return opcionUsuario;
	}

}