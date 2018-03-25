package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estudiante database table.
 * 
 */
@Entity
@NamedQuery(name="Estudiante.findAll", query="SELECT e FROM Estudiante e")
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ESTUDIANTE")
	private int idEstudiante;

	@Column(name="ANIO_ESTUDIO_ANTERIOR")
	private int anioEstudioAnterior;

	private String nie;

	@Column(name="REPITE_GRADO")
	private String repiteGrado;

	//bi-directional many-to-one association to Institucionprocedencia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_INSTITUCION_PROCEDENCIA")
	private Institucionprocedencia institucionprocedencia;

	//bi-directional many-to-one association to NivelEspecialidad
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	@JoinColumn(name="ID_NIVEL_ESPECIALIDAD")
	private NivelEspecialidad nivelEspecialidad;

	//bi-directional many-to-one association to Persona
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_PERSONA")
	private Persona persona;

	//bi-directional many-to-one association to Expediente
	@OneToMany(mappedBy="estudiante")
	private List<Expediente> expedientes;

	//bi-directional many-to-one association to ExpedienteMedico
	@OneToMany(mappedBy="estudiante")
	private List<ExpedienteMedico> expedienteMedicos;

	//bi-directional many-to-one association to Inscripcion
	@OneToMany(mappedBy="estudiante")
	private List<Inscripcion> inscripcions;

	//bi-directional many-to-one association to ResponsableEstudiante
	@OneToMany(mappedBy="estudiante")
	private List<ResponsableEstudiante> responsableEstudiantes;

	public Estudiante() {
	}

	public int getIdEstudiante() {
		return this.idEstudiante;
	}

	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public int getAnioEstudioAnterior() {
		return this.anioEstudioAnterior;
	}

	public void setAnioEstudioAnterior(int anioEstudioAnterior) {
		this.anioEstudioAnterior = anioEstudioAnterior;
	}

	public String getNie() {
		return this.nie;
	}

	public void setNie(String nie) {
		this.nie = nie;
	}

	public String getRepiteGrado() {
		return this.repiteGrado;
	}

	public void setRepiteGrado(String repiteGrado) {
		this.repiteGrado = repiteGrado;
	}

	public Institucionprocedencia getInstitucionprocedencia() {
		return this.institucionprocedencia;
	}

	public void setInstitucionprocedencia(Institucionprocedencia institucionprocedencia) {
		this.institucionprocedencia = institucionprocedencia;
	}

	public NivelEspecialidad getNivelEspecialidad() {
		return this.nivelEspecialidad;
	}

	public void setNivelEspecialidad(NivelEspecialidad nivelEspecialidad) {
		this.nivelEspecialidad = nivelEspecialidad;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Expediente> getExpedientes() {
		return this.expedientes;
	}

	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}

	public Expediente addExpediente(Expediente expediente) {
		getExpedientes().add(expediente);
		expediente.setEstudiante(this);

		return expediente;
	}

	public Expediente removeExpediente(Expediente expediente) {
		getExpedientes().remove(expediente);
		expediente.setEstudiante(null);

		return expediente;
	}

	public List<ExpedienteMedico> getExpedienteMedicos() {
		return this.expedienteMedicos;
	}

	public void setExpedienteMedicos(List<ExpedienteMedico> expedienteMedicos) {
		this.expedienteMedicos = expedienteMedicos;
	}

	public ExpedienteMedico addExpedienteMedico(ExpedienteMedico expedienteMedico) {
		getExpedienteMedicos().add(expedienteMedico);
		expedienteMedico.setEstudiante(this);

		return expedienteMedico;
	}

	public ExpedienteMedico removeExpedienteMedico(ExpedienteMedico expedienteMedico) {
		getExpedienteMedicos().remove(expedienteMedico);
		expedienteMedico.setEstudiante(null);

		return expedienteMedico;
	}

	public List<Inscripcion> getInscripcions() {
		return this.inscripcions;
	}

	public void setInscripcions(List<Inscripcion> inscripcions) {
		this.inscripcions = inscripcions;
	}

	public Inscripcion addInscripcion(Inscripcion inscripcion) {
		getInscripcions().add(inscripcion);
		inscripcion.setEstudiante(this);

		return inscripcion;
	}

	public Inscripcion removeInscripcion(Inscripcion inscripcion) {
		getInscripcions().remove(inscripcion);
		inscripcion.setEstudiante(null);

		return inscripcion;
	}

	public List<ResponsableEstudiante> getResponsableEstudiantes() {
		return this.responsableEstudiantes;
	}

	public void setResponsableEstudiantes(List<ResponsableEstudiante> responsableEstudiantes) {
		this.responsableEstudiantes = responsableEstudiantes;
	}

	public ResponsableEstudiante addResponsableEstudiante(ResponsableEstudiante responsableEstudiante) {
		getResponsableEstudiantes().add(responsableEstudiante);
		responsableEstudiante.setEstudiante(this);

		return responsableEstudiante;
	}

	public ResponsableEstudiante removeResponsableEstudiante(ResponsableEstudiante responsableEstudiante) {
		getResponsableEstudiantes().remove(responsableEstudiante);
		responsableEstudiante.setEstudiante(null);

		return responsableEstudiante;
	}

}