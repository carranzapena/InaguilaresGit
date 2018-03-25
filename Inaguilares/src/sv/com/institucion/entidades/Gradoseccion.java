package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gradoseccion database table.
 * 
 */
@Entity
@NamedQuery(name="Gradoseccion.findAll", query="SELECT g FROM Gradoseccion g")
public class Gradoseccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idgradoseccion;

	//bi-directional many-to-one association to Detallehorario
	@OneToMany(mappedBy="gradoseccion")
	private List<Detallehorario> detallehorarios;

	//bi-directional many-to-one association to Grado
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGRADO")
	private Grado grado;

	//bi-directional many-to-one association to Seccion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSECCION")
	private Seccion seccion;

	public Gradoseccion() {
	}

	public int getIdgradoseccion() {
		return this.idgradoseccion;
	}

	public void setIdgradoseccion(int idgradoseccion) {
		this.idgradoseccion = idgradoseccion;
	}

	public List<Detallehorario> getDetallehorarios() {
		return this.detallehorarios;
	}

	public void setDetallehorarios(List<Detallehorario> detallehorarios) {
		this.detallehorarios = detallehorarios;
	}

	public Detallehorario addDetallehorario(Detallehorario detallehorario) {
		getDetallehorarios().add(detallehorario);
		detallehorario.setGradoseccion(this);

		return detallehorario;
	}

	public Detallehorario removeDetallehorario(Detallehorario detallehorario) {
		getDetallehorarios().remove(detallehorario);
		detallehorario.setGradoseccion(null);

		return detallehorario;
	}

	public Grado getGrado() {
		return this.grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public Seccion getSeccion() {
		return this.seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

}