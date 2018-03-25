package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipofalta database table.
 * 
 */
@Entity
@NamedQuery(name="Tipofalta.findAll", query="SELECT t FROM Tipofalta t")
public class Tipofalta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtipofalta;

	private String nombre;

	//bi-directional many-to-one association to Falta
	@OneToMany(mappedBy="tipofalta")
	private List<Falta> faltas;

	public Tipofalta() {
	}

	public int getIdtipofalta() {
		return this.idtipofalta;
	}

	public void setIdtipofalta(int idtipofalta) {
		this.idtipofalta = idtipofalta;
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
		falta.setTipofalta(this);

		return falta;
	}

	public Falta removeFalta(Falta falta) {
		getFaltas().remove(falta);
		falta.setTipofalta(null);

		return falta;
	}

}