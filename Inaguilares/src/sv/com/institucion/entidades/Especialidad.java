package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especialidad database table.
 * 
 */
@Entity
@NamedQuery(name="Especialidad.findAll", query="SELECT e FROM Especialidad e")
public class Especialidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idespecialidad;

	private String nombre;

	//bi-directional many-to-one association to NivelEspecialidad
	@OneToMany(mappedBy="especialidad")
	private List<NivelEspecialidad> nivelEspecialidads;

	public Especialidad() {
	}

	public int getIdespecialidad() {
		return this.idespecialidad;
	}

	public void setIdespecialidad(int idespecialidad) {
		this.idespecialidad = idespecialidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<NivelEspecialidad> getNivelEspecialidads() {
		return this.nivelEspecialidads;
	}

	public void setNivelEspecialidads(List<NivelEspecialidad> nivelEspecialidads) {
		this.nivelEspecialidads = nivelEspecialidads;
	}

	public NivelEspecialidad addNivelEspecialidad(NivelEspecialidad nivelEspecialidad) {
		getNivelEspecialidads().add(nivelEspecialidad);
		nivelEspecialidad.setEspecialidad(this);

		return nivelEspecialidad;
	}

	public NivelEspecialidad removeNivelEspecialidad(NivelEspecialidad nivelEspecialidad) {
		getNivelEspecialidads().remove(nivelEspecialidad);
		nivelEspecialidad.setEspecialidad(null);

		return nivelEspecialidad;
	}

}