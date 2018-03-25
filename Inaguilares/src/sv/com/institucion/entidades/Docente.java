package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the docente database table.
 * 
 */
@Entity
@NamedQuery(name="Docente.findAll", query="SELECT d FROM Docente d")
public class Docente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iddocente;

	//bi-directional many-to-one association to Asignaturaimpartir
	@OneToMany(mappedBy="docente")
	private List<Asignaturaimpartir> asignaturaimpartirs;

	//bi-directional many-to-one association to Persona
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PERSONA")
	private Persona persona;

	public Docente() {
	}

	public int getIddocente() {
		return this.iddocente;
	}

	public void setIddocente(int iddocente) {
		this.iddocente = iddocente;
	}

	public List<Asignaturaimpartir> getAsignaturaimpartirs() {
		return this.asignaturaimpartirs;
	}

	public void setAsignaturaimpartirs(List<Asignaturaimpartir> asignaturaimpartirs) {
		this.asignaturaimpartirs = asignaturaimpartirs;
	}

	public Asignaturaimpartir addAsignaturaimpartir(Asignaturaimpartir asignaturaimpartir) {
		getAsignaturaimpartirs().add(asignaturaimpartir);
		asignaturaimpartir.setDocente(this);

		return asignaturaimpartir;
	}

	public Asignaturaimpartir removeAsignaturaimpartir(Asignaturaimpartir asignaturaimpartir) {
		getAsignaturaimpartirs().remove(asignaturaimpartir);
		asignaturaimpartir.setDocente(null);

		return asignaturaimpartir;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}