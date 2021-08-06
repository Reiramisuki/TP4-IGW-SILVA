/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.org.centro8.curso.java.web.managedbean;

import ar.org.centro8.curso.java.web.connectors.Connector;
import ar.org.centro8.curso.java.web.entities.Cliente;
import ar.org.centro8.curso.java.web.repositories.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.web.repositories.jdbc.ClienteRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author lilia
 */
@Named()
@SessionScoped()
public class ClienteMB implements Serializable {

    private I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());
    private Cliente cliente = new Cliente();
    private String mensaje = "";
    private String buscarApellido = "";
    private String buscarNumeroDocumento = "";
    private String buscarApellidoNumeroDocumento = "";

    public void save() {
        try {
            cr.save(cliente);
            if (cliente.getId() > 0) {
                mensaje = "Se guardo el cliente id: " + cliente.getId();
            } else {
                mensaje = "Ocurrio un error!";
            }
            cliente = new Cliente();
        } catch (Exception e) {
            mensaje = "Ocurrio un error!";
        }
    }

    public List<Cliente> getAll() {
        return cr.getAll();
    }

    public List<Cliente> getLikeApellido() {
        return cr.getLikeApellido(buscarApellido);
    }

    public List<Cliente> getLikeNumeroDocumento() {
        return cr.getLikeNumeroDocumento(buscarNumeroDocumento);
    }

    public List<Cliente> getLikeApellidoNumeroDocumento() {
        return cr.getLikeApellidoNumeroDocumento(buscarApellido, buscarNumeroDocumento);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getBuscarApellido() {
        return buscarApellido;
    }

    public void setBuscarApellido(String buscarApellido) {
        this.buscarApellido = buscarApellido;
    }

    public String getBuscarNumeroDocumento() {
        return buscarNumeroDocumento;
    }

    public void setBuscarNumeroDocumento(String buscarNumeroDocumento) {
        this.buscarNumeroDocumento = buscarNumeroDocumento;
    }

    public String getBuscarApellidoNumeroDocumento() {
        return buscarApellidoNumeroDocumento;
    }

    public void setBuscarApellidoNumeroDocumento(String buscarApellidoNumeroDocumento) {
        this.buscarApellidoNumeroDocumento = buscarApellidoNumeroDocumento;
    }

}
