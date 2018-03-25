package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grado database table.
 * 
 */
@Entity
@NamedQuery(name="Grado.findAll", query="SELECT g FROM Grado g")
public class Grado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idgrado;

	private String nombre;

	//bi-directional many-to-one association to Gradoseccion
	@OneToMany(mappedBy="grado")
	private List<Gradoseccion> gradoseccions;

	public Grado() {
	}

	public int getIdgrado() {
		return this.idgrado;
	}

	public void setIdgrado(int idgrado) {
		this.idgrado = idgrado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Gradoseccion> getGradoseccions() {
		return this.gradoseccions;
	}

	public void setGradoseccions(List<Gradoseccion> gradoseccions) {
		this.gradoseccions = gradoseccions;
	}

	public Gradoseccion addGradoseccion(Gradoseccion gradoseccion) {
		getGradoseccions().add(gradoseccion);
		gradoseccion.setGrado(this);

		return gradoseccion;
	}

	public Gradoseccion removeGradoseccion(Gradoseccion gradoseccion) {
		getGradoseccions().remove(gradoseccion);
		gradoseccion.setGrado(null);

		return gradoseccion;
	}

}