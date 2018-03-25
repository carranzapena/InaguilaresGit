package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the asignatura database table.
 * 
 */
@Entity
@NamedQuery(name="Asignatura.findAll", query="SELECT a FROM Asignatura a")
public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idasignatura;

	private String codigo;

	private String nombre;

	//bi-directional many-to-one association to Asignaturaimpartir
	@OneToMany(mappedBy="asignatura")
	private List<Asignaturaimpartir> asignaturaimpartirs;

	public Asignatura() {
	}

	public int getIdasignatura() {
		return this.idasignatura;
	}

	public void setIdasignatura(int idasignatura) {
		this.idasignatura = idasignatura;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Asignaturaimpartir> getAsignaturaimpartirs() {
		return this.asignaturaimpartirs;
	}

	public void setAsignaturaimpartirs(List<Asignaturaimpartir> asignaturaimpartirs) {
		this.asignaturaimpartirs = asignaturaimpartirs;
	}

	public Asignaturaimpartir addAsignaturaimpartir(Asignaturaimpartir asignaturaimpartir) {
		getAsignaturaimpartirs().add(asignaturaimpartir);
		asignaturaimpartir.setAsignatura(this);

		return asignaturaimpartir;
	}

	public Asignaturaimpartir removeAsignaturaimpartir(Asignaturaimpartir asignaturaimpartir) {
		getAsignaturaimpartirs().remove(asignaturaimpartir);
		asignaturaimpartir.setAsignatura(null);

		return asignaturaimpartir;
	}

}