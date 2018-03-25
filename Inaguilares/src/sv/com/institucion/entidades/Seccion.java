package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seccion database table.
 * 
 */
@Entity
@NamedQuery(name="Seccion.findAll", query="SELECT s FROM Seccion s")
public class Seccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idseccion;

	private String codigo;

	//bi-directional many-to-one association to Gradoseccion
	@OneToMany(mappedBy="seccion")
	private List<Gradoseccion> gradoseccions;

	public Seccion() {
	}

	public int getIdseccion() {
		return this.idseccion;
	}

	public void setIdseccion(int idseccion) {
		this.idseccion = idseccion;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Gradoseccion> getGradoseccions() {
		return this.gradoseccions;
	}

	public void setGradoseccions(List<Gradoseccion> gradoseccions) {
		this.gradoseccions = gradoseccions;
	}

	public Gradoseccion addGradoseccion(Gradoseccion gradoseccion) {
		getGradoseccions().add(gradoseccion);
		gradoseccion.setSeccion(this);

		return gradoseccion;
	}

	public Gradoseccion removeGradoseccion(Gradoseccion gradoseccion) {
		getGradoseccions().remove(gradoseccion);
		gradoseccion.setSeccion(null);

		return gradoseccion;
	}

}