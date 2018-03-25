package sv.com.institucion.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametros_generales database table.
 * 
 */
@Entity
@Table(name="parametros_generales")
@NamedQuery(name="ParametrosGenerale.findAll", query="SELECT p FROM ParametrosGenerale p")
public class ParametrosGenerale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idparametro;

	private String descripcion;

	private String llave;

	private String valor;

	public ParametrosGenerale() {
	}

	public int getIdparametro() {
		return this.idparametro;
	}

	public void setIdparametro(int idparametro) {
		this.idparametro = idparametro;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLlave() {
		return this.llave;
	}

	public void setLlave(String llave) {
		this.llave = llave;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}