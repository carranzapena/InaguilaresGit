///*
// * Copyright 2009-2014 PrimeTek.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package org.primefaces.omega.view.menu;
//
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import org.apache.log4j.Logger;
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.Node;
//import org.primefaces.model.menu.DefaultMenuItem;
//import org.primefaces.model.menu.DefaultMenuModel;
//import org.primefaces.model.menu.DefaultSubMenu;
//import org.primefaces.model.menu.MenuModel;
//import org.primefaces.omega.view.Login;
//
//@Getter
//@Setter
//@ManagedBean
//public class MenuView implements Serializable {
//    
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	public static final Logger logger = Logger.getLogger(MenuView.class);
//	private MenuModel model;
//    
//    @ManagedProperty(value = "#{login}")
//    private Login login;
//
//    @PostConstruct
//	public void init() {
//		if(login.getModel() == null)
//			ConstruirMenu();
//		else
//			model = login.getModel();
//	}
//
//    private void ConstruirMenu(){
//    	try {
//    		model = new DefaultMenuModel();
//    		Document doc = login.getDocXml();
//    		Map<String, DefaultSubMenu> primerNivel = new HashMap<String, DefaultSubMenu>();
//    		Map<String, DefaultSubMenu> segundoNivel = new HashMap<String, DefaultSubMenu>();
//    		Map<String, String> mapaId = new HashMap<String, String>();		    		
//			List<Node> accesos = doc.selectNodes("/peticionSeguridad/privilegios/roles/rol/accesos/acceso");
//    		for (Node n : accesos) {
//    			Element acceso = (Element) n;
//    			if(acceso.attribute("type").getText().equalsIgnoreCase("MENU")){
//    				 DefaultSubMenu menu = new DefaultSubMenu(acceso.elementText("nombreAcceso"));
//    				 menu.setId(acceso.elementText("nombreAcceso")+acceso.elementText("id"));
//    				 menu.setIcon(acceso.elementText("icono"));
//    				 primerNivel.put(acceso.elementText("id"), menu);				
//    			}else if(acceso.attribute("type").getText().equalsIgnoreCase("SUBMENU")){
//    				DefaultSubMenu subMenu = new DefaultSubMenu(acceso.elementText("nombreAcceso"));
//    				subMenu.setId(acceso.elementText("nombreAcceso")+acceso.elementText("id"));
//    				subMenu.setIcon(acceso.elementText("icono"));
//    				segundoNivel.put(acceso.elementText("id"), subMenu);
//    				mapaId.put(acceso.elementText("id"), acceso.elementText("idPadre"));
//    			}else{
//    				DefaultMenuItem item = new DefaultMenuItem(acceso.elementText("nombreAcceso"));
//    				item.setIcon(acceso.elementText("icono"));
//    				item.setId(acceso.elementText("nombreAcceso")+acceso.elementText("id"));
//    				item.setUrl(acceso.elementText("url"));
//    				if(primerNivel.get(acceso.elementText("idPadre")) != null){
//    					primerNivel.get(acceso.elementText("idPadre")).addElement(item);
//    				}else if(segundoNivel.get(acceso.elementText("idPadre")) != null){
//    					segundoNivel.get(acceso.elementText("idPadre")).addElement(item);
//    					primerNivel.get(mapaId.get(acceso.elementText("idPadre"))).addElement(segundoNivel.get(acceso.elementText("idPadre")));
//    				}else{
//    					model.addElement(item);
//    				}
//    			}
//    		}
//    		for (Map.Entry<String, DefaultSubMenu> entry : primerNivel.entrySet()) {
//    			model.addElement(entry.getValue());
//    		}
//    		login.setModel(model);
//		} catch (Exception e) {
//			logger.error("Ocurrio un error al construir el menu: "+e.toString());
//		}
//    	    	
//    }
//    
//}
