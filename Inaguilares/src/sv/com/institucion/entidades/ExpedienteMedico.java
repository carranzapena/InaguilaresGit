package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the expediente_medico database table.
 * 
 */
@Entity
@Table(name="expediente_medico")
@NamedQuery(name="ExpedienteMedico.findAll", query="SELECT e FROM ExpedienteMedico e")
public class ExpedienteMedico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDEXPEDIENTE_MEDICO")
	private int idexpedienteMedico;

	//bi-directional many-to-one association to Enfermedad
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDENFERMEDAD")
	private Enfermedad enfermedad;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDESTUDIANTE")
	private Estudiante estudiante;

	public ExpedienteMedico() {
	}

	public int getIdexpedienteMedico() {
		return this.idexpedienteMedico;
	}

	public void setIdexpedienteMedico(int idexpedienteMedico) {
		this.idexpedienteMedico = idexpedienteMedico;
	}

	public Enfermedad getEnfermedad() {
		return this.enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

}