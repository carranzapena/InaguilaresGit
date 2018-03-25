package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the institucionprocedencia database table.
 * 
 */
@Entity
@NamedQuery(name="Institucionprocedencia.findAll", query="SELECT i FROM Institucionprocedencia i")
public class Institucionprocedencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idinsitucionprocedencia;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Estudiante
	@OneToMany(mappedBy="institucionprocedencia")
	private List<Estudiante> estudiantes;

	public Institucionprocedencia() {
	}

	public int getIdinsitucionprocedencia() {
		return this.idinsitucionprocedencia;
	}

	public void setIdinsitucionprocedencia(int idinsitucionprocedencia) {
		this.idinsitucionprocedencia = idinsitucionprocedencia;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Estudiante> getEstudiantes() {
		return this.estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Estudiante addEstudiante(Estudiante estudiante) {
		getEstudiantes().add(estudiante);
		estudiante.setInstitucionprocedencia(this);

		return estudiante;
	}

	public Estudiante removeEstudiante(Estudiante estudiante) {
		getEstudiantes().remove(estudiante);
		estudiante.setInstitucionprocedencia(null);

		return estudiante;
	}

}