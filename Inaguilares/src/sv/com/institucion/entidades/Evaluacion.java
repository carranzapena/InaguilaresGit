package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the evaluacion database table.
 * 
 */
@Entity
@NamedQuery(name="Evaluacion.findAll", query="SELECT e FROM Evaluacion e")
public class Evaluacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idevaluacion;

	@Temporal(TemporalType.DATE)
	private Date fechaevaluacion;

	private Timestamp fechamodificacion;

	private int idinscripcionhorario;

	private int idtipoevaluacion;

	private BigDecimal nota;

	//bi-directional many-to-one association to InscripcionHorario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDHORARIO")
	private InscripcionHorario inscripcionHorario;

	//bi-directional many-to-one association to Periodo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPERIODO")
	private Periodo periodo;

	public Evaluacion() {
	}

	public int getIdevaluacion() {
		return this.idevaluacion;
	}

	public void setIdevaluacion(int idevaluacion) {
		this.idevaluacion = idevaluacion;
	}

	public Date getFechaevaluacion() {
		return this.fechaevaluacion;
	}

	public void setFechaevaluacion(Date fechaevaluacion) {
		this.fechaevaluacion = fechaevaluacion;
	}

	public Timestamp getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public int getIdinscripcionhorario() {
		return this.idinscripcionhorario;
	}

	public void setIdinscripcionhorario(int idinscripcionhorario) {
		this.idinscripcionhorario = idinscripcionhorario;
	}

	public int getIdtipoevaluacion() {
		return this.idtipoevaluacion;
	}

	public void setIdtipoevaluacion(int idtipoevaluacion) {
		this.idtipoevaluacion = idtipoevaluacion;
	}

	public BigDecimal getNota() {
		return this.nota;
	}

	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}

	public InscripcionHorario getInscripcionHorario() {
		return this.inscripcionHorario;
	}

	public void setInscripcionHorario(InscripcionHorario inscripcionHorario) {
		this.inscripcionHorario = inscripcionHorario;
	}

	public Periodo getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

}