package sv.com.institucion.controladores;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import sv.com.institucion.dao.UsuarioDao;
import sv.com.institucion.entidades.Usuario;
import sv.com.institucion.utils.CustomUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  FacesMessage message;
	private Usuario usuario;
	public static final Logger logger = Logger.getLogger(Login.class);
//	private MenuModel model;
	private UsuarioDao usuarioDao;
	
    @PostConstruct
    public void init() {    	
        cleanForm();
    }

    public void cleanForm() {
        this.usuario = new Usuario();
    }
	
	
	public void loginUser(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session =(HttpSession) facesContext.getExternalContext().getSession(false);
		usuarioDao = new UsuarioDao();
		try {
			logger.info("Iniciando el login user con usuario: "+this.usuario.getUsuario()+" Contrasenia: "+this.usuario.getContrasenia());
                //verificar si esta en estado activo********************************
                if (usuarioDao.isActivo(usuario)) {
                       this.usuario.setContrasenia(CustomUtils.md5(this.usuario.getContrasenia()).toUpperCase());
	                    this.setUsuario(usuarioDao.usuarioValido(this.usuario));
	                    if (this.usuario != null) {
	                    	session.setAttribute("usuario", this.usuario);
	        				facesContext.getExternalContext().redirect("Vistas/inscripcion.xhtml");
	                    }else 
	                    	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Usuario y password Invalido");
                }else 
                	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Usuario desactivado o no existe");                           
		} catch (Exception e) {
			logger.error("Ha ocurrido un error en el metodo loginUser: "+e.toString());
			message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "No se ha podido iniciar sesion");
		}finally{
			if(message != null)
            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
			message = null;
			cleanForm();
		}
	}
	
	  public void logout() {
		  FacesContext facesContext = FacesContext.getCurrentInstance();
	        try {
	        	HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
	                    .getExternalContext().getSession(false);
	        	session.invalidate();
	        	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	        	 String url=FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/faces/login.xhtml?faces-redirect=true";
	        	facesContext.getExternalContext().redirect(url);
	        } catch (IOException ex) {
	        	logger.error("Ha ocurrido un error en el metodo logout: "+ex.toString());
	        }
	        
	    }
}
