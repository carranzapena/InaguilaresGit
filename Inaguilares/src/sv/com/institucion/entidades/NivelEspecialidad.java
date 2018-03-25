package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nivel_especialidad database table.
 * 
 */
@Entity
@Table(name="nivel_especialidad")
@NamedQuery(name="NivelEspecialidad.findAll", query="SELECT n FROM NivelEspecialidad n")
public class NivelEspecialidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_NIVEL_ESPECIALIAD")
	private int idNivelEspecialiad;

	//bi-directional many-to-one association to Estudiante
	@OneToMany(mappedBy="nivelEspecialidad")
	private List<Estudiante> estudiantes;

	//bi-directional many-to-one association to Especialidad
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_ESPECIALIDAD")
	private Especialidad especialidad;

	//bi-directional many-to-one association to Nivel
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_NIVEL")
	private Nivel nivel;

	public NivelEspecialidad() {
	}

	public int getIdNivelEspecialiad() {
		return this.idNivelEspecialiad;
	}

	public void setIdNivelEspecialiad(int idNivelEspecialiad) {
		this.idNivelEspecialiad = idNivelEspecialiad;
	}

	public List<Estudiante> getEstudiantes() {
		return this.estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Estudiante addEstudiante(Estudiante estudiante) {
		getEstudiantes().add(estudiante);
		estudiante.setNivelEspecialidad(this);

		return estudiante;
	}

	public Estudiante removeEstudiante(Estudiante estudiante) {
		getEstudiantes().remove(estudiante);
		estudiante.setNivelEspecialidad(null);

		return estudiante;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Nivel getNivel() {
		return this.nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

}