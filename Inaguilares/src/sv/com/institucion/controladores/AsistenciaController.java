package sv.com.institucion.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import sv.com.institucion.dao.AsistenciaDao;
import sv.com.institucion.entidades.Asistencia;


/**
 * @author Gerardo Garzona
 *
 */
@Getter
@Setter
@ManagedBean
@ViewScoped
public class AsistenciaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger LOG = Logger.getLogger(AsistenciaController.class);
    private Date fecha;
    private Date hoy;
    private Asistencia asistencia;
    private List<Asistencia> asistencias;
    private AsistenciaDao asistenciaDao;
    

    @PostConstruct
    public void init() {      	
    	try {
    		asistencia = new Asistencia();
    		asistencias = new ArrayList<Asistencia>();
    		asistenciaDao = new AsistenciaDao();
    		this.setAsistencias(asistenciaDao.getAllByDate(new Date()));
    		asistencia = new Asistencia();
    		fecha = null;
    		hoy = new Date();			
		} catch (Exception e) {
			LOG.error("Ha ocurrido un error en init->AsistenciaController"+e.toString());
		}
    }

    public void searchByDate(){
    	FacesContext facesContext = FacesContext.getCurrentInstance();
    	try {
    		this.setAsistencias(asistenciaDao.getAllByDate(this.fecha));
    		if(this.getAsistencias().size() > 0)
    			facesContext.addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_INFO, "Busqueda", "Datos buscado satisfactoriamente"));
    		else
    			facesContext.addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_WARN, "Busqueda", "No se encontraron datos"));			
		} catch (Exception e) {
			LOG.error("Ha ocurrido un error en searchByDate->AsistenciaController"+e.toString());
			facesContext.addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Busqueda", "Ocurrio un error al obtener los datos"));	
		}finally{
			RequestContext.getCurrentInstance().update("messageGlobal");
		}
    }
 


    public void limpiar() {
        this.asistencia = new Asistencia();
    }

}
