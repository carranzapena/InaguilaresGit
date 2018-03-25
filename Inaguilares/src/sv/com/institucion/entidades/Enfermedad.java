package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the enfermedad database table.
 * 
 */
@Entity
@NamedQuery(name="Enfermedad.findAll", query="SELECT e FROM Enfermedad e")
public class Enfermedad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idenfermedad;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to ExpedienteMedico
	@OneToMany(mappedBy="enfermedad")
	private List<ExpedienteMedico> expedienteMedicos;

	public Enfermedad() {
	}

	public int getIdenfermedad() {
		return this.idenfermedad;
	}

	public void setIdenfermedad(int idenfermedad) {
		this.idenfermedad = idenfermedad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ExpedienteMedico> getExpedienteMedicos() {
		return this.expedienteMedicos;
	}

	public void setExpedienteMedicos(List<ExpedienteMedico> expedienteMedicos) {
		this.expedienteMedicos = expedienteMedicos;
	}

	public ExpedienteMedico addExpedienteMedico(ExpedienteMedico expedienteMedico) {
		getExpedienteMedicos().add(expedienteMedico);
		expedienteMedico.setEnfermedad(this);

		return expedienteMedico;
	}

	public ExpedienteMedico removeExpedienteMedico(ExpedienteMedico expedienteMedico) {
		getExpedienteMedicos().remove(expedienteMedico);
		expedienteMedico.setEnfermedad(null);

		return expedienteMedico;
	}

}