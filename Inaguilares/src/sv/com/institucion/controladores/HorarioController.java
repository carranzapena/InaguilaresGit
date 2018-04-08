package sv.com.institucion.controladores;


import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.logging.Logger;
import org.primefaces.context.RequestContext;

import lombok.Getter;
import lombok.Setter;
import sv.com.institucion.dao.AsignaturaImpartirDao;
import sv.com.institucion.dao.AulaDao;
import sv.com.institucion.dao.DetalleHorarioDao;
import sv.com.institucion.dao.DiaDao;
import sv.com.institucion.dao.GradoSeccionDao;
import sv.com.institucion.entidades.Asignaturaimpartir;
import sv.com.institucion.entidades.Aula;
import sv.com.institucion.entidades.Detallehorario;
import sv.com.institucion.entidades.Dia;
import sv.com.institucion.entidades.Gradoseccion;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class HorarioController implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(HorarioController.class.getName());
	
	
	/*Dao*/
    private AsignaturaImpartirDao asignaturaImpartirDao;
    private DetalleHorarioDao detalleHorarioDao;
    private AulaDao aulaDao;
    private DiaDao diaDao;
    private GradoSeccionDao gradosecDao;

    /*ENTIDADES*/
    private Asignaturaimpartir asigImpartir;    
    private Detallehorario detalleHorario;
    private Detallehorario selected;     
    private Aula aula;
    private Dia dia;    
    private Gradoseccion gradoseccion;
    
    private Date horaInicio;    
    private Date horaFin;
    
    /*Listas de entidades*/
    private List<Asignaturaimpartir> asignaturasImpartidas;    
    private List<Detallehorario> detallesHorarios;    
    private List<Aula> aulas;    
    private List<Dia> dias;    
    private List<Gradoseccion> grados;
    
    @PostConstruct
    public void init(){
    	inicializarDao();
    	asignaturasImpartidas = asignaturaImpartirDao.obtenerTodos();
    	detallesHorarios = detalleHorarioDao.obtenerTodos();
        aulas = aulaDao.obtenerTodos();
        dias = diaDao.obtenerTodos();
        grados = gradosecDao.obtenerTodos();        
        asigImpartir = new Asignaturaimpartir();
        detalleHorario = new Detallehorario();
        aula = new Aula();
        dia = new Dia();
        gradoseccion = new Gradoseccion();
    }
    
    
    public void prepareHorario(){
        asigImpartir = new Asignaturaimpartir();
        detalleHorario = new Detallehorario();
        aula = new Aula();
        dia = new Dia();
        gradoseccion = new Gradoseccion();
    	detalleHorario = selected;
    	horaInicio = selected.getHorainicio();
    	horaFin = selected.getHorafin();
    	asigImpartir.setIdasignaturaimpartir(selected.getAsignaturaimpartir().getIdasignaturaimpartir());
    	aula.setIdaula(detalleHorario.getAula().getIdaula());
    	dia.setIdidia(detalleHorario.getDia().getIdidia());
    	gradoseccion.setIdgradoseccion(detalleHorario.getGradoseccion().getIdgradoseccion());
    }
    
    public void agregarHorario(){
    	inicializarDao();
        try{
        detalleHorario.setAsignaturaimpartir(asignaturaImpartirDao.obtenerPorId(asigImpartir.getIdasignaturaimpartir()));
        detalleHorario.setAula(aulaDao.obtenerPorId(aula.getIdaula()));
        detalleHorario.setDia(diaDao.obtenerPorId(dia.getIdidia()));
        detalleHorario.setGradoseccion(gradosecDao.obtenerPorId(gradoseccion.getIdgradoseccion()));
        detalleHorario.setHorainicio(new Time(horaInicio.getTime()));
        detalleHorario.setHorafin(new Time(horaFin.getTime()));
        detalleHorarioDao.crearEntidad(detalleHorario);
        FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se guardo satisfactoriamente"));
        this.detallesHorarios = detalleHorarioDao.obtenerTodos();
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo agregarHorario->HorarioController"+e.toString());
            FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error!"));
        } finally {
        	RequestContext.getCurrentInstance().execute("PF('wvhorarios').filter()");
        	RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide()");
            RequestContext.getCurrentInstance().update("messageGlobal");
            this.cancelar();
        }
    }
    
    public void modificarHorario(){
    	inicializarDao();
        try{
        detalleHorario.setAsignaturaimpartir(asignaturaImpartirDao.obtenerPorId(asigImpartir.getIdasignaturaimpartir()));
        detalleHorario.setAula(aulaDao.obtenerPorId(aula.getIdaula()));
        detalleHorario.setDia(diaDao.obtenerPorId(dia.getIdidia()));
        detalleHorario.setGradoseccion(gradosecDao.obtenerPorId(gradoseccion.getIdgradoseccion()));    
        detalleHorario.setHorainicio(new Time(horaInicio.getTime()));
        detalleHorario.setHorafin(new Time(horaFin.getTime()));
        detalleHorarioDao.modificarEntidad(detalleHorario);
        FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","El registro se actualizo satisfactoriamente"));
        this.detallesHorarios = detalleHorarioDao.obtenerTodos();
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo modificarHorario->HorarioController"+e.toString());
            FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error!"));
        } finally {
        	RequestContext.getCurrentInstance().execute("PF('wvhorarios').filter()");
        	RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide()");
            RequestContext.getCurrentInstance().update("messageGlobal");
            this.cancelar();
        }
    }
    
    public void eliminarHorario(){
    	inicializarDao();
        try{
        detalleHorarioDao.eliminarEntidad(selected);
        FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","El registro se eliminado satisfactoriamente"));
        this.detallesHorarios = detalleHorarioDao.obtenerTodos();
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo eliminarHorario->HorarioController"+e.toString());
            FacesContext.getCurrentInstance().addMessage("messageGlobal", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error!"));
        } finally {
        	RequestContext.getCurrentInstance().execute("PF('wvhorarios').filter()");
        	RequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
            RequestContext.getCurrentInstance().update("messageGlobal");
            this.cancelar();
        }
    }
    
    public void cancelar(){
        asigImpartir = new Asignaturaimpartir();
        detalleHorario = new Detallehorario();
        aula = new Aula();
        dia = new Dia();
        gradoseccion = new Gradoseccion();  
        horaInicio = null;
        horaFin = null;
    }
    
    public void inicializarDao(){
        asignaturaImpartirDao = new AsignaturaImpartirDao();
        detalleHorarioDao = new DetalleHorarioDao();
        aulaDao = new AulaDao();
        diaDao = new DiaDao();
        gradosecDao = new GradoSeccionDao();
    	
    }
}   

