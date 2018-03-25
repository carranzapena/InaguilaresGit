package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estadoinscripcion database table.
 * 
 */
@Entity
@NamedQuery(name="Estadoinscripcion.findAll", query="SELECT e FROM Estadoinscripcion e")
public class Estadoinscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idestadoinscripcion;

	private String nombre;

	//bi-directional many-to-one association to Inscripcion
	@OneToMany(mappedBy="estadoinscripcion")
	private List<Inscripcion> inscripcions;

	public Estadoinscripcion() {
	}

	public int getIdestadoinscripcion() {
		return this.idestadoinscripcion;
	}

	public void setIdestadoinscripcion(int idestadoinscripcion) {
		this.idestadoinscripcion = idestadoinscripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Inscripcion> getInscripcions() {
		return this.inscripcions;
	}

	public void setInscripcions(List<Inscripcion> inscripcions) {
		this.inscripcions = inscripcions;
	}

	public Inscripcion addInscripcion(Inscripcion inscripcion) {
		getInscripcions().add(inscripcion);
		inscripcion.setEstadoinscripcion(this);

		return inscripcion;
	}

	public Inscripcion removeInscripcion(Inscripcion inscripcion) {
		getInscripcions().remove(inscripcion);
		inscripcion.setEstadoinscripcion(null);

		return inscripcion;
	}

}