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
    private LoginModel loginModel;

    private String error = "";
    private String usuario;
    private String password;

    public String validaLogin() throws Exception {
        UsuarioEntity u = loginModel.login(usuario, password);
        if (u != null) {
            HttpServletRequest request = JsfUtils.getRequest();
            request.getSession().setAttribute("user", u.getIdCiudadano().getNombre());
            request.getSession().setAttribute("id", u.getId());
            request.getSession().setAttribute("tipo", u.getIdTipo().getId());
            if (null != u.getIdTipo().getId()) {
                this.error = "";
                switch (u.getIdTipo().getId()) {
                    case 1:
                        return "/AdministradorGeneral/admingen?faces-redirect=true";
                    case 2:
                        return "/EmpleadoRNPN/listaCiudadanos?faces-redirect=true";
                    case 3:
                        return "/admin/admindep?faces-redirect=true";
                    case 4:
                        return "/presidente/president?faces-redirect=true";
                    default:
                        break;
                }
            }
        }
        this.error = "Error";
        return null;

    }

    public String logOut() {
        JsfUtils.getRequest().getSession().invalidate();
        return "/login?faces-redirect=true";
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
