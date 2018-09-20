/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.models.LoginModel;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String nombre;
    private String pass;
    private String correo;

    public String validaLogin() throws Exception {
        LoginModel log = new LoginModel();
        UsuarioEntity u = log.login(correo, pass);

        if (u != null) {
            int tipo = Integer.parseInt(u.getIdTipo().toString());
            if (tipo == 1) {
                return "admingen";
            } else if (tipo == 2) {
                return "emp";
            } else if (tipo == 3) {
                return "admindep";
            } else if (tipo == 4) {
                return "president";
            }
        }
        return "index";

    }

    public LoginBean() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
