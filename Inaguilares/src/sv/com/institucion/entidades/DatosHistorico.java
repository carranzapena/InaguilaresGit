package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the datos_historicos database table.
 * 
 */
@Entity
@Table(name="datos_historicos")
@NamedQuery(name="DatosHistorico.findAll", query="SELECT d FROM DatosHistorico d")
public class DatosHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_HISTORICOS")
	private String idHistoricos;

	private String apellidos;

	private String ayo;

	@Column(name="CANTIDAD_FALTAS")
	private int cantidadFaltas;

	@Column(name="CANTIDAD_SANCIONES")
	private int cantidadSanciones;

	private String nie;

	private String nombre;

	private float promedio;

	private String seccion;

	private String usuario;

	//bi-directional many-to-one association to DetalleHistorico
	@OneToMany(mappedBy="datosHistorico")
	private List<DetalleHistorico> detalleHistoricos;

	public DatosHistorico() {
	}

	public String getIdHistoricos() {
		return this.idHistoricos;
	}

	public void setIdHistoricos(String idHistoricos) {
		this.idHistoricos = idHistoricos;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getAyo() {
		return this.ayo;
	}

	public void setAyo(String ayo) {
		this.ayo = ayo;
	}

	public int getCantidadFaltas() {
		return this.cantidadFaltas;
	}

	public void setCantidadFaltas(int cantidadFaltas) {
		this.cantidadFaltas = cantidadFaltas;
	}

	public int getCantidadSanciones() {
		return this.cantidadSanciones;
	}

	public void setCantidadSanciones(int cantidadSanciones) {
		this.cantidadSanciones = cantidadSanciones;
	}

	public String getNie() {
		return this.nie;
	}

	public void setNie(String nie) {
		this.nie = nie;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPromedio() {
		return this.promedio;
	}

	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}

	public String getSeccion() {
		return this.seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<DetalleHistorico> getDetalleHistoricos() {
		return this.detalleHistoricos;
	}

	public void setDetalleHistoricos(List<DetalleHistorico> detalleHistoricos) {
		this.detalleHistoricos = detalleHistoricos;
	}

	public DetalleHistorico addDetalleHistorico(DetalleHistorico detalleHistorico) {
		getDetalleHistoricos().add(detalleHistorico);
		detalleHistorico.setDatosHistorico(this);

		return detalleHistorico;
	}

	public DetalleHistorico removeDetalleHistorico(DetalleHistorico detalleHistorico) {
		getDetalleHistoricos().remove(detalleHistorico);
		detalleHistorico.setDatosHistorico(null);

		return detalleHistorico;
	}

}