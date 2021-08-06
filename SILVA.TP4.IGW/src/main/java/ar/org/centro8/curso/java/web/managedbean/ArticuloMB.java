/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.org.centro8.curso.java.web.managedbean;

import ar.org.centro8.curso.java.web.connectors.Connector;
import ar.org.centro8.curso.java.web.entities.Articulo;
import ar.org.centro8.curso.java.web.repositories.interfaces.I_ArticuloRepository;
import ar.org.centro8.curso.java.web.repositories.jdbc.ArticuloRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author lilia
 */
@Named()
@SessionScoped()
public class ArticuloMB implements Serializable{
    private I_ArticuloRepository ar=new ArticuloRepository(Connector.getConnection());
    private Articulo articulo=new Articulo();
    private String mensaje="";
    private String buscarDescripcion="";
    
    // AGREGAMOS EL METODO addMessage() PARA PODER INCLUIR VENTANAS EMERGENTES DESDE
    // LA VISTA DE ARTICULOS. ESTO NOS PERMITE LUEGO UTILIZAR DIRECTAMENTE EL METODO
    // addMessage(). ESTA SERIA COMO LA DECLARACION DEL METODO addMessage(), PARA QUE
    // DESPUES PODAMOS USARLO EN EL METODO save()
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        
        //UTILIZAMOS LA VARIABLE FacesContext,GENERAMOS UNA INSTANCIA DE ESA VARIABLE Y
        // AGREGAMOS MENSAJES.
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public void save(){
        try {
            ar.save(articulo);
            if(articulo.getId()>0){
                mensaje="Se guardo el articulo id="+articulo.getId();
                addMessage(FacesMessage.SEVERITY_INFO, "Info Message", mensaje);
            }
            else{
                mensaje="Ocurrio un error!";
                // OBSERVAR QUE ACA ES MENSAJE DE ERROR, NO DE INFORMACION.
                addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", mensaje);
            }
            articulo=new Articulo();
        } catch (Exception e) {
            mensaje="Ocurrio un error!";
            addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", mensaje);
        }
    }
    
    public List<Articulo>getAll(){
        return ar.getAll();
    }
    
    public List<Articulo>getLikeDescripcion(){
        return ar.getLikeDescripcion(buscarDescripcion);
    }
    
    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getBuscarDescripcion() {
        return buscarDescripcion;
    }

    public void setBuscarDescripcion(String buscarDescripcion) {
        this.buscarDescripcion = buscarDescripcion;
    }
   
}
