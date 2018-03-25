package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the parentesco database table.
 * 
 */
@Entity
@NamedQuery(name="Parentesco.findAll", query="SELECT p FROM Parentesco p")
public class Parentesco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idparentesco;

	private String nombre;

	//bi-directional many-to-one association to Responsable
	@OneToMany(mappedBy="parentesco")
	private List<Responsable> responsables;

	public Parentesco() {
	}

	public int getIdparentesco() {
		return this.idparentesco;
	}

	public void setIdparentesco(int idparentesco) {
		this.idparentesco = idparentesco;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Responsable> getResponsables() {
		return this.responsables;
	}

	public void setResponsables(List<Responsable> responsables) {
		this.responsables = responsables;
	}

	public Responsable addResponsable(Responsable responsable) {
		getResponsables().add(responsable);
		responsable.setParentesco(this);

		return responsable;
	}

	public Responsable removeResponsable(Responsable responsable) {
		getResponsables().remove(responsable);
		responsable.setParentesco(null);

		return responsable;
	}

}