package sv.com.institucion.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import sv.com.institucion.entidades.Usuario;

public class CustomUtils {
	
	/**
	 * Valida si ub objeto esta nulo o vacio.
	 * 
	 * @param obj
	 * @return
	 */
	public static Boolean isEmpty(Object obj){
		if(obj != null){
			if(obj instanceof String){
					if(!"".equalsIgnoreCase((String) obj))
						return false;					
					else
						return true;					
			}else
				return false;
			
		}else
			return true;
	}

	
	 /* Retorna un hash a partir de un tipo y un texto */
    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    /* Retorna un hash MD5 a partir de un texto */
    public static String md5(String txt) {
        return CustomUtils.getHash(txt, "MD5");
    }
 
    /* Retorna un hash SHA1 a partir de un texto */
    public static String sha1(String txt) {
        return CustomUtils.getHash(txt, "SHA1");
    }
    
    
    public static Usuario obtenerUsuarioSesion(){
		FacesContext context2 = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
        return (Usuario)session.getAttribute("usuario");
    }
    
    public static String obtenerNie(String nombre,String apellido,Integer id){
        String username="";
        Calendar now = new GregorianCalendar();
        now.setTime( new Date());
        String ayo= String.valueOf(now.get(Calendar.YEAR)) ;         
        username= nombre.substring(0,1)+apellido.substring(0, 1)+ ayo+id;
        return username;
    }
    
    public static Integer obtenerAnioActual(){
    	Calendar now = new GregorianCalendar();
        now.setTime( new Date());
        return now.get(Calendar.YEAR);     
    }

}
