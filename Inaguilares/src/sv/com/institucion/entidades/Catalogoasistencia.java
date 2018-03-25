package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the catalogoasistencia database table.
 * 
 */
@Entity
@NamedQuery(name="Catalogoasistencia.findAll", query="SELECT c FROM Catalogoasistencia c")
public class Catalogoasistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcatalogoasistencia;

	private String nombre;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="catalogoasistencia")
	private List<Asistencia> asistencias;

	public Catalogoasistencia() {
	}

	public int getIdcatalogoasistencia() {
		return this.idcatalogoasistencia;
	}

	public void setIdcatalogoasistencia(int idcatalogoasistencia) {
		this.idcatalogoasistencia = idcatalogoasistencia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setCatalogoasistencia(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setCatalogoasistencia(null);

		return asistencia;
	}

}