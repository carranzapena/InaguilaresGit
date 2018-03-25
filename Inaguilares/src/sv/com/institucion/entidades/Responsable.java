package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the responsable database table.
 * 
 */
@Entity
@NamedQuery(name="Responsable.findAll", query="SELECT r FROM Responsable r")
public class Responsable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idresponsable;

	//bi-directional many-to-one association to Parentesco
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPARENTESCO")
	private Parentesco parentesco;

	//bi-directional many-to-one association to Persona
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PERSONA")
	private Persona persona;

	//bi-directional many-to-one association to ResponsableEstudiante
	@OneToMany(mappedBy="responsable")
	private List<ResponsableEstudiante> responsableEstudiantes;

	public Responsable() {
	}

	public int getIdresponsable() {
		return this.idresponsable;
	}

	public void setIdresponsable(int idresponsable) {
		this.idresponsable = idresponsable;
	}

	public Parentesco getParentesco() {
		return this.parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<ResponsableEstudiante> getResponsableEstudiantes() {
		return this.responsableEstudiantes;
	}

	public void setResponsableEstudiantes(List<ResponsableEstudiante> responsableEstudiantes) {
		this.responsableEstudiantes = responsableEstudiantes;
	}

	public ResponsableEstudiante addResponsableEstudiante(ResponsableEstudiante responsableEstudiante) {
		getResponsableEstudiantes().add(responsableEstudiante);
		responsableEstudiante.setResponsable(this);

		return responsableEstudiante;
	}

	public ResponsableEstudiante removeResponsableEstudiante(ResponsableEstudiante responsableEstudiante) {
		getResponsableEstudiantes().remove(responsableEstudiante);
		responsableEstudiante.setResponsable(null);

		return responsableEstudiante;
	}

}