
package ar.org.centro8.curso.java.web.services;

import ar.org.centro8.curso.java.web.connectors.Connector;
import ar.org.centro8.curso.java.web.entities.Cliente;
import ar.org.centro8.curso.java.web.enums.TipoDocumento;
import ar.org.centro8.curso.java.web.repositories.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.web.repositories.jdbc.ClienteRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lilia
 */
@Path("/cliente/v1")
public class ClienteService {

    private I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String info() {
        return "Servicio de clientes V1 se encuentra activo";
    }

    @GET
    @Path("/save")
    @Produces(MediaType.TEXT_PLAIN)
    public String save(@QueryParam("nombre") String nombre,
            @QueryParam("apellido") String apellido,
            @QueryParam("edad") int edad,
            @QueryParam("direccion") String direccion,
            @QueryParam("email") String email,
            @QueryParam("telefono") String telefono,
            @QueryParam("tipoDocumento") TipoDocumento tipoDocumento,
            @QueryParam("numeroDocumento") String numeroDocumento
    ) {
        Cliente cliente = new Cliente(nombre, apellido, edad, direccion, email, telefono, tipoDocumento, numeroDocumento);
        cr.save(cliente);
        return cliente.getId() + "";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        return new Gson().toJson(cr.getAll());
    }

    @GET
    @Path("/remove")
    @Produces(MediaType.TEXT_PLAIN)
    public String remove(@QueryParam("id") int id) {
        boolean result = false;
        try {
            Cliente cliente = cr.getById(id);
            if (cliente.getId() != 0) {
                cr.remove(cliente);
                result = true;
            }
        } catch (Exception e) {
        }
        return result + "";
    }

    @GET
    @Path("/byId")
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@QueryParam("id") int id) {
        Cliente cliente = cr.getById(id);
        if (cliente.getId() != 0) {
            return new Gson().toJson(cr.getById(id));
        } else {
            List<String> respuesta = new ArrayList<>();
            respuesta.add("No encontrado");
            return new Gson().toJson(respuesta);
        }
    }

    @GET
    @Path("/likeApellido")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLikeApellido(@QueryParam("apellido") String apellido) {
        return new Gson().toJson(cr.getLikeApellido(apellido));
    }

    @GET
    @Path("/byDocumento")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLikeNumeroDocumento(
            @QueryParam("numeroDocumento") String numeroDocumento) {
        return new Gson().toJson(cr.getLikeNumeroDocumento(numeroDocumento));
    }

}
