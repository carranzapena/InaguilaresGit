package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nivel database table.
 * 
 */
@Entity
@NamedQuery(name="Nivel.findAll", query="SELECT n FROM Nivel n")
public class Nivel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_NIVEL")
	private int idNivel;

	private String nombre;

	//bi-directional many-to-one association to NivelEspecialidad
	@OneToMany(mappedBy="nivel")
	private List<NivelEspecialidad> nivelEspecialidads;

	public Nivel() {
	}

	public int getIdNivel() {
		return this.idNivel;
	}

	public void setIdNivel(int idNivel) {
		this.idNivel = idNivel;
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
		nivelEspecialidad.setNivel(this);

		return nivelEspecialidad;
	}

	public NivelEspecialidad removeNivelEspecialidad(NivelEspecialidad nivelEspecialidad) {
		getNivelEspecialidads().remove(nivelEspecialidad);
		nivelEspecialidad.setNivel(null);

		return nivelEspecialidad;
	}

}