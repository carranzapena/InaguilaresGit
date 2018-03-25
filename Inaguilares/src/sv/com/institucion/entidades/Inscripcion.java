package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the inscripcion database table.
 * 
 */
@Entity
@NamedQuery(name="Inscripcion.findAll", query="SELECT i FROM Inscripcion i")
public class Inscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idinscripcion;

	@Column(name="ANIO_ESCOLAR")
	private int anioEscolar;

	private Timestamp fecha;

	private Timestamp fechamod;

	private String usuariomod;

	//bi-directional many-to-one association to Estadoinscripcion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDESTADOINSCRIPCION")
	private Estadoinscripcion estadoinscripcion;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDESTUDIANTE")
	private Estudiante estudiante;

	//bi-directional many-to-one association to Modalidad
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTURNO")
	private Modalidad modalidad;

	//bi-directional many-to-one association to InscripcionHorario
	@OneToMany(mappedBy="inscripcion")
	private List<InscripcionHorario> inscripcionHorarios;

	public Inscripcion() {
	}

	public int getIdinscripcion() {
		return this.idinscripcion;
	}

	public void setIdinscripcion(int idinscripcion) {
		this.idinscripcion = idinscripcion;
	}

	public int getAnioEscolar() {
		return this.anioEscolar;
	}

	public void setAnioEscolar(int anioEscolar) {
		this.anioEscolar = anioEscolar;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFechamod() {
		return this.fechamod;
	}

	public void setFechamod(Timestamp fechamod) {
		this.fechamod = fechamod;
	}

	public String getUsuariomod() {
		return this.usuariomod;
	}

	public void setUsuariomod(String usuariomod) {
		this.usuariomod = usuariomod;
	}

	public Estadoinscripcion getEstadoinscripcion() {
		return this.estadoinscripcion;
	}

	public void setEstadoinscripcion(Estadoinscripcion estadoinscripcion) {
		this.estadoinscripcion = estadoinscripcion;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Modalidad getModalidad() {
		return this.modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}

	public List<InscripcionHorario> getInscripcionHorarios() {
		return this.inscripcionHorarios;
	}

	public void setInscripcionHorarios(List<InscripcionHorario> inscripcionHorarios) {
		this.inscripcionHorarios = inscripcionHorarios;
	}

	public InscripcionHorario addInscripcionHorario(InscripcionHorario inscripcionHorario) {
		getInscripcionHorarios().add(inscripcionHorario);
		inscripcionHorario.setInscripcion(this);

		return inscripcionHorario;
	}

	public InscripcionHorario removeInscripcionHorario(InscripcionHorario inscripcionHorario) {
		getInscripcionHorarios().remove(inscripcionHorario);
		inscripcionHorario.setInscripcion(null);

		return inscripcionHorario;
	}

}