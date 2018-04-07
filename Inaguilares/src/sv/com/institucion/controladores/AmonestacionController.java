/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.institucion.controladores;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import lombok.Getter;
import lombok.Setter;
import sv.com.institucion.dao.AmonestacionDao;
import sv.com.institucion.dao.ExpedienteDao;
import sv.com.institucion.dao.FaltaDao;
import sv.com.institucion.entidades.Amonestacion;
import sv.com.institucion.entidades.Expediente;
import sv.com.institucion.entidades.Falta;

/**
 *
 * @author Gerardo
 */
@Getter	
@Setter
@ManagedBean
@ViewScoped
public class AmonestacionController implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(AmonestacionController.class.getName());
    
    //DAO
    private FaltaDao faltaDao;    
    private ExpedienteDao expedienteDao;    
    private AmonestacionDao amonestacionDao;
    
    
    private boolean banderaEditar;
    private List<Amonestacion> amonestaciones;
    private List<Amonestacion> amonestacionesFiltradas;
    private List<Expediente> expedientes;
    private List<Falta> faltas;
    private Amonestacion amonestacion;
    private Amonestacion selected;
    private Date fecha;
    private String estado;
    private String carnet;
    private String tipoOperacion;
    private  FacesMessage message;
    private Integer idExpediente;
    private Integer idFalta;
    private Date fechaAhora;

    
    
    @PostConstruct
    public void init(){
    	amonestacionDao = new AmonestacionDao();
        amonestacion = new Amonestacion();
        amonestaciones = new ArrayList<Amonestacion>();
        fechaAhora = new Date();
        this.setAmonestaciones(amonestacionDao.obtenerTodos());
        
    }
    
    
    public List<Expediente> completeText(String query){
    	expedienteDao = new ExpedienteDao();
        try{
            if(query != null && !query.equalsIgnoreCase("")){
                return expedienteDao.getAutoCompleteText(query.trim());
            }            
        }catch(Exception e){
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo completeText->AmonestacionController"+e.toString());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se ha podido autocompletar");
            RequestContext.getCurrentInstance().update("formAmonestacion");
        }
        return null;
    }
    
    public void searchByFilter(){
    	amonestacionDao = new AmonestacionDao();
       try{ 
            if((this.carnet != null && !this.carnet.equalsIgnoreCase("")) ||
                this.fecha != null || (this.estado != null && !this.estado.equalsIgnoreCase(""))){
            	this.amonestacionesFiltradas = amonestacionDao.getByFilter(carnet, fecha, estado);
              this.setAmonestaciones(this.amonestacionesFiltradas);
              if(this.amonestaciones.size() > 0)
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se filtro existosamente");
              else
                  message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "No se encontraron registros con este filtro");
            }else{
              message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ningun parametro se ha llenado para realizar el filtro");  
            }
            System.out.println(carnet+"---"+fecha+"---"+estado);
       }catch(Exception e){
    	   LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo searchByFilter->AmonestacionController"+e.toString());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo Filtrar");
       }finally{
           this.limpiar();
           RequestContext.getCurrentInstance().execute("PF('wvtableAmonestacion').filter();");
           FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
           RequestContext.getCurrentInstance().update("messageGlobal");
       }
    }
    
    public void mostrarDetalles(){
        try {
            this.limpiar();
            this.setAmonestacion(selected);
            RequestContext.getCurrentInstance().execute("PF('dlgDetalles').show();");  
        } catch (Exception e) {
             LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo mostrarDetalles->AmonestacionController"+e.toString());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedieron mostrar los detalles");
            RequestContext.getCurrentInstance().update("formAmonestacion");
        }
    }
    
    public void preEditar(){
    	expedienteDao = new ExpedienteDao();
    	faltaDao = new FaltaDao();
        try {
            this.setAmonestacion(selected);
            this.setIdExpediente(this.amonestacion.getExpediente().getIdExpediente());
            this.setIdFalta(this.amonestacion.getFalta().getIdfalta());
            this.setFaltas(faltaDao.obtenerTodos());
            this.setExpedientes(expedienteDao.getAllByYear());
            this.banderaEditar = true;
            this.tipoOperacion = "Editar";
            RequestContext.getCurrentInstance().update("dlgEditar");
            RequestContext.getCurrentInstance().execute(" PF('dlgModificar').show();");  
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo preEditar->AmonestacionController"+e.toString());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo preEditar");
            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
            RequestContext.getCurrentInstance().update("messageGlobal");
        }
           
    }
        
    public void cambiarEstado(Amonestacion a){
    	amonestacionDao = new AmonestacionDao();
        try {
           this.amonestacion = a;
           amonestacionDao.modificarEntidad(this.amonestacion);
           message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Amonestacion se cambio de estado");
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo anular->AmonestacionController"+e.toString());
             message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo activar la amonestacion");
        }finally{
            this.limpiar();
            RequestContext.getCurrentInstance().execute("PF('wvtableAmonestacion').filter();");
            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
            RequestContext.getCurrentInstance().update("messageGlobal");
        }
    }
    

        
    public void prepareSave(){
    	faltaDao = new FaltaDao();
    	expedienteDao = new ExpedienteDao();
        try{
            this.amonestacion = new Amonestacion();
            this.estado = "A";
            this.idExpediente = null;
            this.idFalta = null;
            this.setFaltas(faltaDao.obtenerTodos());
            this.setExpedientes(expedienteDao.getAllByYear());
            this.banderaEditar=false;
            tipoOperacion = "Agregar";
            RequestContext.getCurrentInstance().update("dlgEditar");
            RequestContext.getCurrentInstance().execute(" PF('dlgModificar').show();");   
        }catch(Exception e){
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo prepareSave->AmonestacionController"+e.toString());
        }
    }
    
    public void editar() {
    	expedienteDao = new ExpedienteDao();
    	faltaDao = new FaltaDao();
    	amonestacionDao = new AmonestacionDao();
        try {
            this.amonestacion.setExpediente(expedienteDao.obtenerPorId(this.idExpediente));
            this.amonestacion.setFalta(faltaDao.obtenerPorId(this.idFalta));
            amonestacionDao.modificarEntidad(this.amonestacion);            
            this.setAmonestaciones(amonestacionDao.obtenerTodos());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro editado");
        } catch (Exception e) {
        	 LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo editar->AmonestacionController"+e.toString());
             message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo almacenar");
        } finally {
            this.limpiar();
            RequestContext.getCurrentInstance().execute("PF('wvtableAmonestacion').filter();");
            RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide();");
            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
            RequestContext.getCurrentInstance().update("messageGlobal");
        }

    }
    
    
    public void save() {
    	expedienteDao = new ExpedienteDao();
    	amonestacionDao = new AmonestacionDao();
    	faltaDao = new FaltaDao();
        try {
            this.amonestacion.setExpediente(expedienteDao.obtenerPorId(this.idExpediente));
            this.amonestacion.setFalta(faltaDao.obtenerPorId(this.idFalta));
            this.amonestacion.setFecha(new Date());
            this.amonestacion.setEstado("A");
            amonestacionDao.crearEntidad(this.amonestacion);
            this.setAmonestaciones(amonestacionDao.obtenerTodos());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro almacenado");
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo save->AmonestacionController"+e.toString());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo almacenar");
        } finally {
            this.limpiar();
            RequestContext.getCurrentInstance().execute("PF('wvtableAmonestacion').filter();");
            RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide();");
            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
            RequestContext.getCurrentInstance().update("messageGlobal");

        }
    }
   
    public void validarTipoOperacion(){
        System.out.println("Entre");
        if(this.banderaEditar)
            editar();
        else
            save();
    }
    
    
    public void limpiar() {
           this.amonestacion = new Amonestacion();         
           this.idFalta = null;
           this.idExpediente = null;
           this.fecha = null;
           this.carnet = null;
           this.estado = null;
           amonestacionDao = null;
           expedienteDao = null;
           faltaDao = null;
    }

    
    
}
