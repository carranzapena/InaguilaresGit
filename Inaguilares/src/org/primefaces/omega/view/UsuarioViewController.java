//package org.primefaces.omega.view;
//
//import java.io.Serializable;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.faces.context.FacesContext;
//
//import org.apache.log4j.Logger;
//import org.primefaces.context.RequestContext;
//
//import sv.com.bh.entidades.Usuario;
//import sv.com.bh.modelo.UsuarioDao;
//import sv.com.bh.utils.AutenticacionLDAP;
//import sv.com.bh.utils.CustomUtils;
//import lombok.Getter;
//import lombok.Setter;
//
//
//@Getter
//@Setter
//@ManagedBean
//@ViewScoped
//public class UsuarioViewController implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	public static final Logger logger = Logger.getLogger(UsuarioViewController.class);
//	
//	private Usuario usuario;
//	private List<Usuario> usuarios;
//	private List<Usuario> usuariosBackUp;
//	private UsuarioDao usuarioDao;
//	private Boolean banderaEditar;
//	private String tipoOperacion;
//	private  FacesMessage message;
//	
//	@PostConstruct
//	public void init(){
//		usuario = new Usuario();
//		usuarios = new ArrayList<Usuario>();
//		usuarioDao = new UsuarioDao();
//		usuario.setEstado("A");
//		try {
//			usuarios = usuarioDao.obtenerTodosPorFiltro(usuario, true,"=");
//		} catch (SQLException e) {
//			logger.info("Ha ocurrido un error al precargar la informacion de UsuarioViewController: "+e.toString());
//			e.printStackTrace();
//		}finally{
//			usuario = null;
//			usuarioDao = null;			
//		}
//		
//	}
//	
//    public void prepareSave(){
//        try{
//            this.usuario = new Usuario();
//            this.banderaEditar=false;
//            this.tipoOperacion = "Agregar";
//            RequestContext.getCurrentInstance().update("dlgEditar");
//            RequestContext.getCurrentInstance().reset("formModificar");
//            RequestContext.getCurrentInstance().execute(" PF('dlgModificar').show();");   
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void save(){
//    	usuarioDao = new UsuarioDao();
//    	try {
//    		this.usuario.setUserMod(CustomUtils.obtenerUsuarioSesion().getIdentificador());
//    		this.usuario.setDateMod(new Timestamp(new Date().getTime()));
//        	if(!CustomUtils.isEmpty(this.usuario.getContrasenia()))
//          	  this.usuario.setContrasenia(CustomUtils.md5(this.usuario.getContrasenia()).toUpperCase());
//    		usuarioDao.insertarEntidad(this.usuario);
//    		init();
//    		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro almacenado");
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.info("Ha ocurrido un error al guardar la informacion de UsuarioViewController: "+e.toString());
//			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo almacenar");
//		}finally{
//			this.limpiar();
//            RequestContext.getCurrentInstance().execute("PF('WVUsuarios').filter();");
//            RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide();");
//            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
//            RequestContext.getCurrentInstance().update("formUsuario");
//		}
//    	
//    }
//    
//    public void prepareEdit(Integer idParametro){
//    	usuarioDao = new UsuarioDao();
//    	Usuario usuarioFiltro = new Usuario();
//        try{
//            this.setUsuario(usuarioDao.obtenerPorId(idParametro, true));
//            usuarioFiltro.setEstado("A");
//            this.setUsuarios(usuarioDao.obtenerTodosPorFiltro(usuarioFiltro, true,"="));
//            this.banderaEditar=true;
//            this.tipoOperacion = "Editar";
//            RequestContext.getCurrentInstance().update("dlgEditar");
//            RequestContext.getCurrentInstance().execute("PF('dlgModificar').show();");            
//        }catch(Exception e){
//        	 logger.info("Ha ocurrido un error al prepararEdit de UsuarioViewController: "+e.toString());
//        }
//    }
//    
//    public void editar() {
//    	usuarioDao = new UsuarioDao();
//        try {
//        	if(!CustomUtils.isEmpty(this.usuario.getContrasenia()))
//        	  this.usuario.setContrasenia(CustomUtils.md5(this.usuario.getContrasenia()).toUpperCase());
//        	 this.usuario.setUserMod(CustomUtils.obtenerUsuarioSesion().getIdentificador());
//        	 usuarioDao.actualizarEntidad(this.usuario);
//             System.out.println("Entre a editar");
//            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro editado");
//        } catch (Exception e) {
//        	 logger.info("Ha ocurrido un error al editar la informacion de UsuarioViewController: "+e.toString());
//             message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo editar");
//        } finally {
//            this.limpiar();
//            init();
//            RequestContext.getCurrentInstance().execute("PF('WVUsuarios').filter();");
//            RequestContext.getCurrentInstance().execute("PF('dlgModificar').hide();");
//            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
//            RequestContext.getCurrentInstance().update("formUsuario");
//        }
//
//    }
//    
//    public void getUserName(){
//		try {
//			if(!CustomUtils.isEmpty(usuario) && 
//					!CustomUtils.isEmpty(usuario.getIdentificador()) && 
//					!CustomUtils.isEmpty(usuario.getTipoSesion()) && 
//					usuario.getTipoSesion().equalsIgnoreCase("LDAP"))
//				usuario.setNombre(AutenticacionLDAP.getNameOfUser(usuario.getIdentificador()));
//				RequestContext.getCurrentInstance().update("formModificar");
//		} catch (Exception e) {
//			logger.info("Ha ocurrido un error al momento de obtener el nombre de Usuario LDAP UsuarioViewController: "+e.toString());
//			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo obtener el nombre del usuario LDAP");
//			FacesContext.getCurrentInstance().addMessage("messagesM", message);
//		}
//    	
//    }
//    
//    public void eliminar() {
//    	usuarioDao = new UsuarioDao();
//        try {            
//        	 usuarioDao.eliminarPorId(this.usuario.getId());
//             message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro eliminado");
//        } catch (Exception e) {
//            e.printStackTrace();
//            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo eliminar el registro");
//        } finally {
//        	this.limpiar();
//            init();
//            this.banderaEditar=false;
//            FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
//            RequestContext.getCurrentInstance().execute("PF('WVUsuarios').filter();");
//        }
//
//    }
//
//    
//    public void getBackUpUsers(Integer idUsuario){
//    	usuarioDao = new UsuarioDao();
//    	Usuario usuarioFiltro = new Usuario();
//        try{
//            usuarioFiltro.setEstado("I");
//            usuarioFiltro.setId(idUsuario);
//            this.setUsuario(this.usuarioDao.obtenerPorId(idUsuario, false));
//            this.setUsuariosBackUp(usuarioDao.obtenerTodosPorFiltro(usuarioFiltro, false,"<>"));
//            RequestContext.getCurrentInstance().update("dlgBackUp");
//            RequestContext.getCurrentInstance().execute("PF('WvBackUp').show();");            
//        }catch(Exception e){
//        	 logger.info("Ha ocurrido un error al getBackUpUsers de UsuarioViewController: "+e.toString());
//        }
//    }
//    
//    public void preparatedEliminar(Integer id){
//    	usuarioDao = new UsuarioDao();
//        try{
//        	this.setUsuario(usuarioDao.obtenerPorId(id, false));
//        	 RequestContext.getCurrentInstance().execute("PF('dlgEliminar').show();");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    
//    public void validarTipoOperacion(){
//        if(this.banderaEditar)
//            editar();
//        else
//            save();
//    }
//    
//    public void limpiar() {
//    	this.usuario = new Usuario();
//    }
//
//}
