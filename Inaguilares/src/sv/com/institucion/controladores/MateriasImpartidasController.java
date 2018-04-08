package sv.com.institucion.controladores;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import lombok.Getter;
import lombok.Setter;
import sv.com.institucion.dao.AsignaturaDao;
import sv.com.institucion.dao.AsignaturaImpartirDao;
import sv.com.institucion.dao.DocenteDao;
import sv.com.institucion.entidades.Asignatura;
import sv.com.institucion.entidades.Asignaturaimpartir;
import sv.com.institucion.entidades.Docente;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class MateriasImpartidasController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*Dao*/
    private AsignaturaDao asignaturaDao;
    private DocenteDao docenteDao;
    private AsignaturaImpartirDao asignaturaImpartirDao;

    /*Entidades*/
    private Asignatura asignaturaSeleccionada;
    private Asignaturaimpartir asignaturaImpartirSeleccionada;
    private Docente docenteSeleccionado;
    
    /*Listas de entidades*/
    private List<Asignaturaimpartir> asignaturasImpartidas;
    private List<Docente> docentes;
    private List<Asignatura> asignaturas;

    @PostConstruct
    public void init() {
    	inicializarDao();
        asignaturasImpartidas = asignaturaImpartirDao.obtenerTodos(); 
        docentes = docenteDao.obtenerTodos();
        asignaturas = asignaturaDao.obtenerTodos();
        asignaturaSeleccionada = new Asignatura();
        asignaturaImpartirSeleccionada = new Asignaturaimpartir();
        docenteSeleccionado = new Docente();
    }
    
    public void prepareMaterias(){
    	inicializarDao();
    	docenteSeleccionado = new Docente();
    	asignaturaSeleccionada = new Asignatura();
    	asignaturaSeleccionada = asignaturaDao.obtenerPorId(asignaturaImpartirSeleccionada.getAsignatura().getIdasignatura());
    	docenteSeleccionado = docenteDao.obtenerPorId(asignaturaImpartirSeleccionada.getDocente().getIddocente());
    }

    public void nuevaAsignaturaImpartida() {
    	inicializarDao();
        try {
            asignaturaImpartirSeleccionada.setAsignatura(asignaturaSeleccionada);
            asignaturaImpartirSeleccionada.setDocente(docenteSeleccionado);
            asignaturaImpartirDao.crearEntidad(asignaturaImpartirSeleccionada);
            this.asignaturasImpartidas = asignaturaImpartirDao.obtenerTodos();
            FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se guardó satisfactoriamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error!"));
        } finally {
            this.cancelar();
            RequestContext.getCurrentInstance().update("messageGlobal");
        }
    }

    public void modificarAsignaturaImpartida() {
    	inicializarDao();
        try {
            asignaturaImpartirSeleccionada.setAsignatura(asignaturaSeleccionada);
            asignaturaImpartirSeleccionada.setDocente(docenteSeleccionado);
            asignaturaImpartirDao.modificarEntidad(asignaturaImpartirSeleccionada);
            FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se actualizó satisfactoriamente"));
            this.asignaturasImpartidas = asignaturaImpartirDao.obtenerTodos();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error!"));
        } finally {
            this.cancelar();
            RequestContext.getCurrentInstance().update("messageGlobal");
        }
    }

    public void eliminarAsignaturaImpartida() {
    	inicializarDao();
        try {
            asignaturaImpartirDao.eliminarEntidad(asignaturaImpartirSeleccionada);
            FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se eliminó satisfactoriamente"));
            this.asignaturasImpartidas = asignaturaImpartirDao.obtenerTodos();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error!"));
        } finally {
            this.cancelar();
            RequestContext.getCurrentInstance().update("messageGlobal");
        }
    }

    public void cancelar() {
        asignaturaImpartirSeleccionada = new Asignaturaimpartir();
        asignaturaSeleccionada = new Asignatura();
        docenteSeleccionado = new Docente();
    }
    
    public void inicializarDao(){
        asignaturaDao = new AsignaturaDao();
        docenteDao = new DocenteDao();
        asignaturaImpartirDao = new AsignaturaImpartirDao();
    }
}
