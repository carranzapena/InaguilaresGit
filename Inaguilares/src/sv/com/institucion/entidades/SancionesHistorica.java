package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sanciones_historicas database table.
 * 
 */
@Entity
@Table(name="sanciones_historicas")
@NamedQuery(name="SancionesHistorica.findAll", query="SELECT s FROM SancionesHistorica s")
public class SancionesHistorica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SANCACIONES_H")
	private int idSancacionesH;

	private String descripcion;

	private String gravedad;

	@Column(name="TIPO_FALTA")
	private String tipoFalta;

	//bi-directional many-to-one association to DetalleHistorico
	@OneToMany(mappedBy="sancionesHistorica")
	private List<DetalleHistorico> detalleHistoricos;

	public SancionesHistorica() {
	}

	public int getIdSancacionesH() {
		return this.idSancacionesH;
	}

	public void setIdSancacionesH(int idSancacionesH) {
		this.idSancacionesH = idSancacionesH;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getGravedad() {
		return this.gravedad;
	}

	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}

	public String getTipoFalta() {
		return this.tipoFalta;
	}

	public void setTipoFalta(String tipoFalta) {
		this.tipoFalta = tipoFalta;
	}

	public List<DetalleHistorico> getDetalleHistoricos() {
		return this.detalleHistoricos;
	}

	public void setDetalleHistoricos(List<DetalleHistorico> detalleHistoricos) {
		this.detalleHistoricos = detalleHistoricos;
	}

	public DetalleHistorico addDetalleHistorico(DetalleHistorico detalleHistorico) {
		getDetalleHistoricos().add(detalleHistorico);
		detalleHistorico.setSancionesHistorica(this);

		return detalleHistorico;
	}

	public DetalleHistorico removeDetalleHistorico(DetalleHistorico detalleHistorico) {
		getDetalleHistoricos().remove(detalleHistorico);
		detalleHistorico.setSancionesHistorica(null);

		return detalleHistorico;
	}

}