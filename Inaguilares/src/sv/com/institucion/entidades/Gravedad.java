package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;

import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * The persistent class for the gravedad database table.
 * 
 */
@EqualsAndHashCode
@Entity
@NamedQuery(name="Gravedad.findAll", query="SELECT g FROM Gravedad g")
public class Gravedad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idgravedad;

	private String nombre;

	//bi-directional many-to-one association to Falta
	@OneToMany(mappedBy="gravedad")
	private List<Falta> faltas;

	public Gravedad() {
	}

	public int getIdgravedad() {
		return this.idgravedad;
	}

	public void setIdgravedad(int idgravedad) {
		this.idgravedad = idgravedad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Falta> getFaltas() {
		return this.faltas;
	}

	public void setFaltas(List<Falta> faltas) {
		this.faltas = faltas;
	}

	public Falta addFalta(Falta falta) {
		getFaltas().add(falta);
		falta.setGravedad(this);

		return falta;
	}

	public Falta removeFalta(Falta falta) {
		getFaltas().remove(falta);
		falta.setGravedad(null);

		return falta;
	}

}