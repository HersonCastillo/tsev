/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.models.LoginModel;
import sv.edu.udb.www.utils.JsfUtils;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private LoginModel usuariosModel;


    private String usuario;
    private String password;
    

    public String validaLogin() throws Exception {
        UsuarioEntity u = usuariosModel.login(usuario, password);
        System.out.println("hola");
        if (u != null) {
            HttpServletRequest request= JsfUtils.getRequest();
            request.getSession().setAttribute("user", usuario);
            request.getSession().setAttribute("tipo",u.getIdTipo());
            if (null != u.getIdTipo().getId()) 
            switch (u.getIdTipo().getId()) {
                case 1:
                    return "/AdministradorGeneral/admingen?faces-redirect=true";
                case 2:
                    return "/empleado/emp?faces-redirect=true";
                case 3:
                    return "/admin/admindep?faces-redirect=true";
                case 4:
                    return "/presidente/president?faces-redirect=true";
                default:
                    break;
            }
        }
        return "index";

    }

    public LoginBean() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

}
