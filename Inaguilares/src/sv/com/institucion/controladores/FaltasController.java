package sv.com.institucion.controladores;


import java.io.Serializable;
import java.util.ArrayList;
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
import sv.com.institucion.dao.FaltaDao;
import sv.com.institucion.dao.GravedadDao;
import sv.com.institucion.dao.TipoFaltaDao;
import sv.com.institucion.entidades.Falta;
import sv.com.institucion.entidades.Gravedad;
import sv.com.institucion.entidades.Tipofalta;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class FaltasController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(FaltasController.class.getName());
    boolean banderaEditar;
    
    private Falta falta;
    private Falta selected;
    private List<Falta> faltas;
    private Tipofalta tipoFalta;
    private List<Falta> faltasFiltradas;
    private List<Tipofalta> tiposFalta;
    private List<Gravedad> gravedades;
    private Integer idGravedad;
    private  FacesMessage message;
    private String tipoOperacion = "";

   
    private FaltaDao faltaDao;
    private TipoFaltaDao tipoFaltaDao;
    private GravedadDao gravedadDao;


    @PostConstruct
    public void init() {
    	inicializarDao();
        tipoFalta = new Tipofalta();
        faltas = new ArrayList<Falta>();
        this.setFaltas(faltaDao.obtenerTodos());
        this.setTiposFalta(tipoFaltaDao.obtenerTodos());
        this.setGravedades(gravedadDao.obtenerTodos());
        falta = new Falta();
        banderaEditar=false;
    }

     
    public void save() {
        try {
        	inicializarDao();
            tipoFaltaDao.crearEntidad(this.tipoFalta);
            this.getFalta().setGravedad(gravedadDao.obtenerPorId(this.idGravedad));
            this.getFalta().setTipofalta(tipoFaltaDao.obtenerPorId(this.tipoFalta.getIdtipofalta()));
            System.err.println("ID-FALTA:"+this.falta.getIdfalta()+" ID-TIPO-FALTA: "+this.getFalta().getTipofalta().getIdtipofalta()+"-ID_GRAVEDAD:"+this.getFalta().getGravedad().getIdgravedad());
            faltaDao.crearEntidad(falta);
            this.faltas = faltaDao.obtenerTodos();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro almacenado");
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo save->FaltasController"+e.toString());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo almacenar");
        } finally {
            this.limpiar();
            RequestContext.getCurrentInstance().execute("PF('wvtableFaltas').filter();");
            RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide();");
            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
            RequestContext.getCurrentInstance().update("messageGlobal");
        }
    }
    public void preparatedEliminar(){
        try{
            this.falta=faltaDao.obtenerPorId(selected.getIdfalta());
            RequestContext.getCurrentInstance().execute("PF('dlgEliminar').show();");
        }catch(Exception e){
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo preparatedEliminar->FaltasController"+e.toString());
        }
    }
    public void eliminar() {
        try {
        	inicializarDao();
            faltaDao.eliminarEntidad(falta);
            this.setFaltas(faltaDao.obtenerTodos());
             message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro eliminado");
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo eliminar ->FaltasController"+e.toString());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo almacenar");
        } finally {
            this.limpiar();
            this.banderaEditar=false;
            RequestContext.getCurrentInstance().execute(" PF('wvtableFaltas').filter();");
            RequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide();");
            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
            RequestContext.getCurrentInstance().update("messageGlobal");
        }

    }

    public void editar() {
        try {
        	inicializarDao();
            System.out.println(this.getFalta().getGravedad().getIdgravedad());
             this.tipoFalta.setIdtipofalta(this.getFalta().getTipofalta().getIdtipofalta());
            tipoFaltaDao.modificarEntidad(this.tipoFalta);
            this.getFalta().setGravedad(gravedadDao.obtenerPorId(this.idGravedad));
            faltaDao.modificarEntidad(this.getFalta());
            this.setFaltas(faltaDao.obtenerTodos());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro editado");
        } catch (Exception e) {
           	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo editar ->FaltasController"+e.toString());
             message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo almacenar");
        } finally {
            this.limpiar();
            RequestContext.getCurrentInstance().execute("PF('wvtableFaltas').filter();");
            RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide();");
            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
            RequestContext.getCurrentInstance().update("messageGlobal");
        }

    }
    public void prepareEdit(){
        try{
        	inicializarDao();
            System.out.println("ID FALTA: "+selected.getIdfalta());
            this.setFalta(faltaDao.obtenerPorId(selected.getIdfalta()));
            this.setIdGravedad(this.falta.getGravedad().getIdgravedad());
            System.out.println("NOMBRE_FALTA: "+this.getFalta().getTipofalta().getNombre());
            this.setTipoFalta(this.falta.getTipofalta());
            this.setTiposFalta(tipoFaltaDao.obtenerTodos());
            this.banderaEditar=true;
             tipoOperacion = "Editar";
            RequestContext.getCurrentInstance().update("dlgEditar");
              RequestContext.getCurrentInstance().execute(" PF('dlgModificar').show();");            
        }catch(Exception e){
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo prepareEdit->FaltasController"+e.toString());
        }
    }
    
    public void prepareSave(){
        try{
            this.falta = new Falta();
            this.tipoFalta = new Tipofalta();
            this.banderaEditar=false;
            tipoOperacion = "Agregar";
            RequestContext.getCurrentInstance().update("dlgEditar");
                          RequestContext.getCurrentInstance().execute(" PF('dlgModificar').show();");   
        }catch(Exception e){
        	LOG.log(Level.SEVERE, "Ha ocurrido un error en el metodo prepareSave->FaltasController"+e.toString());
        }
    }
    
    private void inicializarDao(){
    	tipoFaltaDao = new TipoFaltaDao();
    	gravedadDao = new GravedadDao();
    	faltaDao = new FaltaDao();
    }
    
    
    public void validarTipoOperacion(){
        System.out.println("Entre");
        if(this.banderaEditar)
            editar();
        else
            save();
    }

    public void limpiar() {
    	this.idGravedad = null;
        this.falta = new Falta();
        this.tipoFalta = new Tipofalta();
    }
    

}
