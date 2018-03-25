package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the detalle_historico database table.
 * 
 */
@Entity
@Table(name="detalle_historico")
@NamedQuery(name="DetalleHistorico.findAll", query="SELECT d FROM DetalleHistorico d")
public class DetalleHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DETALLE_HISTORICO")
	private String idDetalleHistorico;

	private Timestamp fecha;

	//bi-directional many-to-one association to DatosHistorico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_HISTORICOS")
	private DatosHistorico datosHistorico;

	//bi-directional many-to-one association to Evalucacione
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_EVALUCACION_HISTORICA")
	private Evalucacione evalucacione;

	//bi-directional many-to-one association to SancionesHistorica
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_SANCACIONES_H")
	private SancionesHistorica sancionesHistorica;

	public DetalleHistorico() {
	}

	public String getIdDetalleHistorico() {
		return this.idDetalleHistorico;
	}

	public void setIdDetalleHistorico(String idDetalleHistorico) {
		this.idDetalleHistorico = idDetalleHistorico;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public DatosHistorico getDatosHistorico() {
		return this.datosHistorico;
	}

	public void setDatosHistorico(DatosHistorico datosHistorico) {
		this.datosHistorico = datosHistorico;
	}

	public Evalucacione getEvalucacione() {
		return this.evalucacione;
	}

	public void setEvalucacione(Evalucacione evalucacione) {
		this.evalucacione = evalucacione;
	}

	public SancionesHistorica getSancionesHistorica() {
		return this.sancionesHistorica;
	}

	public void setSancionesHistorica(SancionesHistorica sancionesHistorica) {
		this.sancionesHistorica = sancionesHistorica;
	}

}