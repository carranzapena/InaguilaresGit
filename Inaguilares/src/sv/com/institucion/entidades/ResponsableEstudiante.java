package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the responsable_estudiante database table.
 * 
 */
@Entity
@Table(name="responsable_estudiante")
@NamedQuery(name="ResponsableEstudiante.findAll", query="SELECT r FROM ResponsableEstudiante r")
public class ResponsableEstudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ESUDENT_RESP")
	private int idEsudentResp;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDESTUDIANTE")
	private Estudiante estudiante;

	//bi-directional many-to-one association to Responsable
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDRESPONSABLE")
	private Responsable responsable;

	public ResponsableEstudiante() {
	}

	public int getIdEsudentResp() {
		return this.idEsudentResp;
	}

	public void setIdEsudentResp(int idEsudentResp) {
		this.idEsudentResp = idEsudentResp;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Responsable getResponsable() {
		return this.responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

}