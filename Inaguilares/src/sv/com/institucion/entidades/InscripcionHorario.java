package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inscripcion_horario database table.
 * 
 */
@Entity
@Table(name="inscripcion_horario")
@NamedQuery(name="InscripcionHorario.findAll", query="SELECT i FROM InscripcionHorario i")
public class InscripcionHorario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_INSCRIPCION_HORARIO")
	private int idInscripcionHorario;

	//bi-directional many-to-one association to Evaluacion
	@OneToMany(mappedBy="inscripcionHorario")
	private List<Evaluacion> evaluacions;

	//bi-directional many-to-one association to Detallehorario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_DETALLE_HORARIO")
	private Detallehorario detallehorario;

	//bi-directional many-to-one association to Inscripcion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_INSCRIPCION")
	private Inscripcion inscripcion;

	public InscripcionHorario() {
	}

	public int getIdInscripcionHorario() {
		return this.idInscripcionHorario;
	}

	public void setIdInscripcionHorario(int idInscripcionHorario) {
		this.idInscripcionHorario = idInscripcionHorario;
	}

	public List<Evaluacion> getEvaluacions() {
		return this.evaluacions;
	}

	public void setEvaluacions(List<Evaluacion> evaluacions) {
		this.evaluacions = evaluacions;
	}

	public Evaluacion addEvaluacion(Evaluacion evaluacion) {
		getEvaluacions().add(evaluacion);
		evaluacion.setInscripcionHorario(this);

		return evaluacion;
	}

	public Evaluacion removeEvaluacion(Evaluacion evaluacion) {
		getEvaluacions().remove(evaluacion);
		evaluacion.setInscripcionHorario(null);

		return evaluacion;
	}

	public Detallehorario getDetallehorario() {
		return this.detallehorario;
	}

	public void setDetallehorario(Detallehorario detallehorario) {
		this.detallehorario = detallehorario;
	}

	public Inscripcion getInscripcion() {
		return this.inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}

}