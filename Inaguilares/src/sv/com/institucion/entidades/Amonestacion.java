package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the amonestacion database table.
 * 
 */
@Entity
@NamedQuery(name="Amonestacion.findAll", query="SELECT a FROM Amonestacion a")
public class Amonestacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idamonestacion;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Timestamp fechamodificacion;

	//bi-directional many-to-one association to Expediente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDEXPEDIENTE")
	private Expediente expediente;

	//bi-directional many-to-one association to Falta
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFALTA")
	private Falta falta;

	//bi-directional many-to-one association to UsuarioRol
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSUBDIRECTOR")
	private UsuarioRol usuarioRol;

	public Amonestacion() {
	}

	public int getIdamonestacion() {
		return this.idamonestacion;
	}

	public void setIdamonestacion(int idamonestacion) {
		this.idamonestacion = idamonestacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public boolean getEstadoBooleano() {
		return this.estado.equals("A")?true:false;
	}

	public void setEstadoBooleano(boolean estado) {
		if(estado)
			setEstado("A");
		else
			setEstado("N");
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public Expediente getExpediente() {
		return this.expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public Falta getFalta() {
		return this.falta;
	}

	public void setFalta(Falta falta) {
		this.falta = falta;
	}

	public UsuarioRol getUsuarioRol() {
		return this.usuarioRol;
	}

	public void setUsuarioRol(UsuarioRol usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

}