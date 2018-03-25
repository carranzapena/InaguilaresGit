package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the asignaturaimpartir database table.
 * 
 */
@Entity
@NamedQuery(name="Asignaturaimpartir.findAll", query="SELECT a FROM Asignaturaimpartir a")
public class Asignaturaimpartir implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idasignaturaimpartir;

	//bi-directional many-to-one association to Asignatura
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDASIGNATURA")
	private Asignatura asignatura;

	//bi-directional many-to-one association to Docente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDDOCENTE")
	private Docente docente;

	//bi-directional many-to-one association to Detallehorario
	@OneToMany(mappedBy="asignaturaimpartir")
	private List<Detallehorario> detallehorarios;

	public Asignaturaimpartir() {
	}

	public int getIdasignaturaimpartir() {
		return this.idasignaturaimpartir;
	}

	public void setIdasignaturaimpartir(int idasignaturaimpartir) {
		this.idasignaturaimpartir = idasignaturaimpartir;
	}

	public Asignatura getAsignatura() {
		return this.asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Docente getDocente() {
		return this.docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public List<Detallehorario> getDetallehorarios() {
		return this.detallehorarios;
	}

	public void setDetallehorarios(List<Detallehorario> detallehorarios) {
		this.detallehorarios = detallehorarios;
	}

	public Detallehorario addDetallehorario(Detallehorario detallehorario) {
		getDetallehorarios().add(detallehorario);
		detallehorario.setAsignaturaimpartir(this);

		return detallehorario;
	}

	public Detallehorario removeDetallehorario(Detallehorario detallehorario) {
		getDetallehorarios().remove(detallehorario);
		detallehorario.setAsignaturaimpartir(null);

		return detallehorario;
	}

}