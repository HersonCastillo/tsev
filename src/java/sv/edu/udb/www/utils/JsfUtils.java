package sv.edu.udb.www.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class JsfUtils {
   
    public static void addErrorMessage(String id, String message){
         FacesContext.getCurrentInstance().addMessage(id, 
                    new FacesMessage(message));
    }
    
    public static void addFlashMessage(String key, Object obj){
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put(key, obj);
    }
    
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