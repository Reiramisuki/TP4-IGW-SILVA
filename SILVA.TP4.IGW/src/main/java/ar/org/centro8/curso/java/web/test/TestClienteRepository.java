package ar.org.centro8.curso.java.web.test;

import ar.org.centro8.curso.java.web.connectors.Connector;
import ar.org.centro8.curso.java.web.entities.Cliente;
import ar.org.centro8.curso.java.web.repositories.jdbc.ClienteRepository;
import ar.org.centro8.curso.java.web.enums.TipoDocumento;
import ar.org.centro8.curso.java.web.repositories.interfaces.I_ClienteRepository;


/**
 *
 * @author lilia
 */
public class TestClienteRepository {

    public static void main(String[] args) {
        I_ClienteRepository cr= new ClienteRepository(Connector.getConnection());
//        I_ClienteRepository cr = ClienteRepositoryFactory.getClienteRepository();
        cr.save(new Cliente("Jorge", "Gordon", 36, "Venesuela 20", "gomez@mail", "222222", TipoDocumento.DNI, "88888883"));
        cr.save(new Cliente("Juan", "Gomez", 26, "Rivadavia 133", "perez@mail", "33333333", TipoDocumento.DNI, "88999993"));
        cr.save(new Cliente("Maria", "Vidal", 35, "Rioja", "vidal@mail", "33333322", TipoDocumento.DNI, "8899922"));
        cr.save(null);
        cr.remove(cr.getById(3));

        Cliente cliente = cr.getById(2);
        if (cliente != null && cliente.getId() != 0) {
            cliente.setTelefono("11111111");
            cr.update(cliente);
        }
        System.out.println("****Lista de todos los clientes****");
        cr.getAll().forEach(System.out::println);
        System.out.println("****Lista de clientes con apellido 'Go...'****");
        cr.getLikeApellido("Go").forEach(System.out::println);
        System.out.println("****Lista de clientes con documento '123...'****");
        cr.getLikeNumeroDocumento("123").forEach(System.out::println);
    }

}
