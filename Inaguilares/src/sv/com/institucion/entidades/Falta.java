package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the falta database table.
 * 
 */
@Entity
@NamedQuery(name="Falta.findAll", query="SELECT f FROM Falta f")
public class Falta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idfalta;

	private Timestamp fechahora;

	//bi-directional many-to-one association to Amonestacion
	@OneToMany(mappedBy="falta")
	private List<Amonestacion> amonestacions;

	//bi-directional many-to-one association to Gravedad
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGRAVEDAD")
	private Gravedad gravedad;

	//bi-directional many-to-one association to Tipofalta
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPOFALTA")
	private Tipofalta tipofalta;

	public Falta() {
	}

	public int getIdfalta() {
		return this.idfalta;
	}

	public void setIdfalta(int idfalta) {
		this.idfalta = idfalta;
	}

	public Timestamp getFechahora() {
		return this.fechahora;
	}

	public void setFechahora(Timestamp fechahora) {
		this.fechahora = fechahora;
	}

	public List<Amonestacion> getAmonestacions() {
		return this.amonestacions;
	}

	public void setAmonestacions(List<Amonestacion> amonestacions) {
		this.amonestacions = amonestacions;
	}

	public Amonestacion addAmonestacion(Amonestacion amonestacion) {
		getAmonestacions().add(amonestacion);
		amonestacion.setFalta(this);

		return amonestacion;
	}

	public Amonestacion removeAmonestacion(Amonestacion amonestacion) {
		getAmonestacions().remove(amonestacion);
		amonestacion.setFalta(null);

		return amonestacion;
	}

	public Gravedad getGravedad() {
		return this.gravedad;
	}

	public void setGravedad(Gravedad gravedad) {
		this.gravedad = gravedad;
	}

	public Tipofalta getTipofalta() {
		return this.tipofalta;
	}

	public void setTipofalta(Tipofalta tipofalta) {
		this.tipofalta = tipofalta;
	}

}