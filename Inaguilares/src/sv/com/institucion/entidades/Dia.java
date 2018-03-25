package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dia database table.
 * 
 */
@Entity
@NamedQuery(name="Dia.findAll", query="SELECT d FROM Dia d")
public class Dia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ididia;

	private String nombre;

	//bi-directional many-to-one association to Detallehorario
	@OneToMany(mappedBy="dia")
	private List<Detallehorario> detallehorarios;

	public Dia() {
	}

	public int getIdidia() {
		return this.ididia;
	}

	public void setIdidia(int ididia) {
		this.ididia = ididia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Detallehorario> getDetallehorarios() {
		return this.detallehorarios;
	}

	public void setDetallehorarios(List<Detallehorario> detallehorarios) {
		this.detallehorarios = detallehorarios;
	}

	public Detallehorario addDetallehorario(Detallehorario detallehorario) {
		getDetallehorarios().add(detallehorario);
		detallehorario.setDia(this);

		return detallehorario;
	}

	public Detallehorario removeDetallehorario(Detallehorario detallehorario) {
		getDetallehorarios().remove(detallehorario);
		detallehorario.setDia(null);

		return detallehorario;
	}

}