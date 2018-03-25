package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the medio_transporte database table.
 * 
 */
@Entity
@Table(name="medio_transporte")
@NamedQuery(name="MedioTransporte.findAll", query="SELECT m FROM MedioTransporte m")
public class MedioTransporte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MEDIOT")
	private int idMediot;

	private String nombre;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="medioTransporte")
	private List<Persona> personas;

	public MedioTransporte() {
	}

	public int getIdMediot() {
		return this.idMediot;
	}

	public void setIdMediot(int idMediot) {
		this.idMediot = idMediot;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Persona> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public Persona addPersona(Persona persona) {
		getPersonas().add(persona);
		persona.setMedioTransporte(this);

		return persona;
	}

	public Persona removePersona(Persona persona) {
		getPersonas().remove(persona);
		persona.setMedioTransporte(null);

		return persona;
	}

}