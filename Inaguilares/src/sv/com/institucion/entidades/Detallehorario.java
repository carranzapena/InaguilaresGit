package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the detallehorario database table.
 * 
 */
@Entity
@NamedQuery(name="Detallehorario.findAll", query="SELECT d FROM Detallehorario d")
public class Detallehorario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iddetallehorario;

	private Time horafin;

	private Time horainicio;

	//bi-directional many-to-one association to Asignaturaimpartir
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDASIGNATURAIMPARTIR")
	private Asignaturaimpartir asignaturaimpartir;

	//bi-directional many-to-one association to Aula
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDAULA")
	private Aula aula;

	//bi-directional many-to-one association to Dia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDDIA")
	private Dia dia;

	//bi-directional many-to-one association to Gradoseccion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGRADOSECCION")
	private Gradoseccion gradoseccion;

	//bi-directional many-to-one association to InscripcionHorario
	@OneToMany(mappedBy="detallehorario")
	private List<InscripcionHorario> inscripcionHorarios;

	public Detallehorario() {
	}

	public int getIddetallehorario() {
		return this.iddetallehorario;
	}

	public void setIddetallehorario(int iddetallehorario) {
		this.iddetallehorario = iddetallehorario;
	}

	public Time getHorafin() {
		return this.horafin;
	}

	public void setHorafin(Time horafin) {
		this.horafin = horafin;
	}

	public Time getHorainicio() {
		return this.horainicio;
	}

	public void setHorainicio(Time horainicio) {
		this.horainicio = horainicio;
	}

	public Asignaturaimpartir getAsignaturaimpartir() {
		return this.asignaturaimpartir;
	}

	public void setAsignaturaimpartir(Asignaturaimpartir asignaturaimpartir) {
		this.asignaturaimpartir = asignaturaimpartir;
	}

	public Aula getAula() {
		return this.aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Dia getDia() {
		return this.dia;
	}

	public void setDia(Dia dia) {
		this.dia = dia;
	}

	public Gradoseccion getGradoseccion() {
		return this.gradoseccion;
	}

	public void setGradoseccion(Gradoseccion gradoseccion) {
		this.gradoseccion = gradoseccion;
	}

	public List<InscripcionHorario> getInscripcionHorarios() {
		return this.inscripcionHorarios;
	}

	public void setInscripcionHorarios(List<InscripcionHorario> inscripcionHorarios) {
		this.inscripcionHorarios = inscripcionHorarios;
	}

	public InscripcionHorario addInscripcionHorario(InscripcionHorario inscripcionHorario) {
		getInscripcionHorarios().add(inscripcionHorario);
		inscripcionHorario.setDetallehorario(this);

		return inscripcionHorario;
	}

	public InscripcionHorario removeInscripcionHorario(InscripcionHorario inscripcionHorario) {
		getInscripcionHorarios().remove(inscripcionHorario);
		inscripcionHorario.setDetallehorario(null);

		return inscripcionHorario;
	}

}