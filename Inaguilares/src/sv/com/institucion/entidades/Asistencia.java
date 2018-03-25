package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the asistencia database table.
 * 
 */
@Entity
@NamedQuery(name="Asistencia.findAll", query="SELECT a FROM Asistencia a")
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idasistencia;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Timestamp fechamodificacion;

	private Time hora;

	//bi-directional many-to-one association to Catalogoasistencia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCATALOGOASISTENCIA")
	private Catalogoasistencia catalogoasistencia;

	//bi-directional many-to-one association to Expediente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDEXPEDIENTE")
	private Expediente expediente;

	public Asistencia() {
	}

	public int getIdasistencia() {
		return this.idasistencia;
	}

	public void setIdasistencia(int idasistencia) {
		this.idasistencia = idasistencia;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Catalogoasistencia getCatalogoasistencia() {
		return this.catalogoasistencia;
	}

	public void setCatalogoasistencia(Catalogoasistencia catalogoasistencia) {
		this.catalogoasistencia = catalogoasistencia;
	}

	public Expediente getExpediente() {
		return this.expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

}