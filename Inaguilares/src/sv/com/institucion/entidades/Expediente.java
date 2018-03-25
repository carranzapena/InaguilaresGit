package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the expediente database table.
 * 
 */
@Entity
@NamedQuery(name="Expediente.findAll", query="SELECT e FROM Expediente e")
public class Expediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_EXPEDIENTE")
	private int idExpediente;

	@Column(name="ANIO_ESCOLAR")
	private int anioEscolar;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fechacreacion;

	//bi-directional many-to-one association to Amonestacion
	@OneToMany(mappedBy="expediente")
	private List<Amonestacion> amonestacions;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="expediente")
	private List<Asistencia> asistencias;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_ESTUDIANTE")
	private Estudiante estudiante;

	public Expediente() {
	}

	public int getIdExpediente() {
		return this.idExpediente;
	}

	public void setIdExpediente(int idExpediente) {
		this.idExpediente = idExpediente;
	}

	public int getAnioEscolar() {
		return this.anioEscolar;
	}

	public void setAnioEscolar(int anioEscolar) {
		this.anioEscolar = anioEscolar;
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

	public Date getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public List<Amonestacion> getAmonestacions() {
		return this.amonestacions;
	}

	public void setAmonestacions(List<Amonestacion> amonestacions) {
		this.amonestacions = amonestacions;
	}

	public Amonestacion addAmonestacion(Amonestacion amonestacion) {
		getAmonestacions().add(amonestacion);
		amonestacion.setExpediente(this);

		return amonestacion;
	}

	public Amonestacion removeAmonestacion(Amonestacion amonestacion) {
		getAmonestacions().remove(amonestacion);
		amonestacion.setExpediente(null);

		return amonestacion;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setExpediente(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setExpediente(null);

		return asistencia;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

}