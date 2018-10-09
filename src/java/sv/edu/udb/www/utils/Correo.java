/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author kevin
 */
public class Correo {
    
    private String usuario;
    private String clave;
    private String destinatario;
    private String asunto;
    private String mensaje;
    private String rutaAdjunto;
    private String nombreAdjunto;
    
    public Correo() {
        this.usuario = "a1299r5@gmail.com";
        this.clave = "vaoiidnqnqxdipob";
        this.destinatario = "";
        this.asunto = "";
        this.mensaje = "";
        this.rutaAdjunto = "";
        this.nombreAdjunto = "";
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRutaAdjunto() {
        return rutaAdjunto;
    }

    public void setRutaAdjunto(String rutaAdjunto) {
        this.rutaAdjunto = rutaAdjunto;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    public boolean enviarCorreo(){
        try{
            Properties p = new Properties();
            p.put("mail.smtp.host","smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", usuario);
            p.setProperty("mail.smtp.auth", "true");
            Session sesion = Session.getDefaultInstance(p,null);
            MimeMultipart cuerpo = new MimeMultipart();
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensaje,"text/html");
            cuerpo.addBodyPart(texto);
            
            if(!rutaAdjunto.isEmpty()){
                BodyPart adjunto = new MimeBodyPart();
                adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaAdjunto)));
                adjunto.setFileName(nombreAdjunto);
                cuerpo.addBodyPart(adjunto);
            }
            
            MimeMessage correo = new MimeMessage(sesion);
            correo.setFrom(new InternetAddress(usuario));
            correo.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            correo.setSubject(asunto);
            correo.setContent(cuerpo);
            Transport t = sesion.getTransport("smtp");
            t.connect(usuario,clave);
            t.sendMessage(correo, correo.getAllRecipients());
            t.close();
            
            return true;
            
        }catch(MessagingException ex){
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
