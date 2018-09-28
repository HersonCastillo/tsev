/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author wecp123
 */
public class JsfUtils {
    public static void addErrorMesages(String id, String msj){
        FacesContext.getCurrentInstance().addMessage(id, 
                    new FacesMessage(msj));
        
    }
    
    public static void addFlashMesages(String id, Object msj){
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put(id, msj);
        
    }
    
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
