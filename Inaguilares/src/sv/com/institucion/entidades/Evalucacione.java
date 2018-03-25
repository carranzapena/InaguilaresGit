package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the evalucaciones database table.
 * 
 */
@Entity
@Table(name="evalucaciones")
@NamedQuery(name="Evalucacione.findAll", query="SELECT e FROM Evalucacione e")
public class Evalucacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_EVALUCACION_HISTORICA")
	private String idEvalucacionHistorica;

	private int ayo;

	private String materia;

	private float nota;

	private String periodo;

	//bi-directional many-to-one association to DetalleHistorico
	@OneToMany(mappedBy="evalucacione")
	private List<DetalleHistorico> detalleHistoricos;

	public Evalucacione() {
	}

	public String getIdEvalucacionHistorica() {
		return this.idEvalucacionHistorica;
	}

	public void setIdEvalucacionHistorica(String idEvalucacionHistorica) {
		this.idEvalucacionHistorica = idEvalucacionHistorica;
	}

	public int getAyo() {
		return this.ayo;
	}

	public void setAyo(int ayo) {
		this.ayo = ayo;
	}

	public String getMateria() {
		return this.materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public float getNota() {
		return this.nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public List<DetalleHistorico> getDetalleHistoricos() {
		return this.detalleHistoricos;
	}

	public void setDetalleHistoricos(List<DetalleHistorico> detalleHistoricos) {
		this.detalleHistoricos = detalleHistoricos;
	}

	public DetalleHistorico addDetalleHistorico(DetalleHistorico detalleHistorico) {
		getDetalleHistoricos().add(detalleHistorico);
		detalleHistorico.setEvalucacione(this);

		return detalleHistorico;
	}

	public DetalleHistorico removeDetalleHistorico(DetalleHistorico detalleHistorico) {
		getDetalleHistoricos().remove(detalleHistorico);
		detalleHistorico.setEvalucacione(null);

		return detalleHistorico;
	}

}