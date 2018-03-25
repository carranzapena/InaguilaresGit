package sv.com.institucion.dao;

import javax.persistence.CacheStoreMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import sv.com.institucion.entidades.Usuario;
import sv.com.institucion.fabrica.FabricaAbstracta;

public class UsuarioDao extends FabricaAbstracta<Usuario> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger logger = Logger.getLogger(UsuarioDao.class);

	public UsuarioDao() {
		super(Usuario.class);
	}
	
    public Usuario usuarioValido(Usuario u){        
        Usuario t=new Usuario();
        try{
        Query q=obtenerManejadorEntidades().createQuery("SELECT u FROM Usuario u WHERE u.contrasenia = :contrasenia AND u.usuario = :usuario", Usuario.class)
        		.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        q.setParameter("contrasenia", u.getContrasenia());
        q.setParameter("usuario", u.getUsuario());
        t=(Usuario) q.getSingleResult();
        logger.error("Ocurrio un error en usuarioValido Mensaje: "+t.getIdUsuario());
        }catch(NoResultException ex){
        	t = null;
        }catch(Exception e){
        	e.printStackTrace();
        	logger.error("Ocurrio un error en usuarioValido Mensaje: "+e.getMessage());
        	logger.error("Ocurrio un error en usuarioValido Causa: "+e.getCause());
        	logger.fatal(e.toString());
        }
        
        return t;
    }
    
    public boolean existeUsuario(Usuario u) {
        boolean existe = false;
        try {
            Query q = obtenerManejadorEntidades().createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario",Usuario.class).
            		setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
            q.setParameter("usuario", u.getUsuario());
            q.getSingleResult();
            existe=true;
        } catch (Exception e) {
        	logger.error("Ocurrio un error en existeUsuario Mensaje: "+e.getMessage());
        	logger.error("Ocurrio un error en existeUsuario Causa: "+e.getCause());
        	logger.fatal(e.toString());
            existe=false;
        }
        return existe;
    }
    
    public boolean isActivo(Usuario u){
        boolean isactivo=false;
        String n="A";
        try{
            Query q = obtenerManejadorEntidades().createQuery("select u from Usuario u where u.estado=:estadoVar and u.usuario=:us",Usuario.class)
            		.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
            q.setParameter("estadoVar",n);
            q.setParameter("us", u.getUsuario());
        	logger.error("Parametros: "+u.getUsuario()+"  "+n);
            Usuario us=(Usuario) q.getSingleResult();
            if(us!=null){
                isactivo=true;
            }            
        }catch(NoResultException ex){
        	isactivo = false;
        }catch(Exception e){
            logger.log(Level.ERROR, e.getMessage()+"  Cause: "+e.getCause());
        	logger.error("Ocurrio un error en existeUsuario Mensaje: "+e.getMessage());
        	logger.error("Ocurrio un error en existeUsuario Causa: "+e.getCause());
        }
        return isactivo;
    }


	@Override
	public String obtenerNombreUnidadPersistencia() {
		return "Inaguilares";
	}

}
