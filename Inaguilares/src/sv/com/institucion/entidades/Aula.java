package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the aula database table.
 * 
 */
@Entity
@NamedQuery(name="Aula.findAll", query="SELECT a FROM Aula a")
public class Aula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idaula;

	private String codigo;

	//bi-directional many-to-one association to Detallehorario
	@OneToMany(mappedBy="aula")
	private List<Detallehorario> detallehorarios;

	public Aula() {
	}

	public int getIdaula() {
		return this.idaula;
	}

	public void setIdaula(int idaula) {
		this.idaula = idaula;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Detallehorario> getDetallehorarios() {
		return this.detallehorarios;
	}

	public void setDetallehorarios(List<Detallehorario> detallehorarios) {
		this.detallehorarios = detallehorarios;
	}

	public Detallehorario addDetallehorario(Detallehorario detallehorario) {
		getDetallehorarios().add(detallehorario);
		detallehorario.setAula(this);

		return detallehorario;
	}

	public Detallehorario removeDetallehorario(Detallehorario detallehorario) {
		getDetallehorarios().remove(detallehorario);
		detallehorario.setAula(null);

		return detallehorario;
	}

}