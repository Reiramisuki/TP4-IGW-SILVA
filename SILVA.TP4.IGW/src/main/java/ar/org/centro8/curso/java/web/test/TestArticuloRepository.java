/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.org.centro8.curso.java.web.test;

import ar.org.centro8.curso.java.web.connectors.Connector;
import ar.org.centro8.curso.java.web.entities.Articulo;
import ar.org.centro8.curso.java.web.enums.Temporada;
import ar.org.centro8.curso.java.web.enums.Tipo;
import ar.org.centro8.curso.java.web.repositories.interfaces.I_ArticuloRepository;
import ar.org.centro8.curso.java.web.repositories.jdbc.ArticuloRepository;


/**
 *
 * @author lilia
 */
public class TestArticuloRepository {
    public static void main(String[] args) {
        //I_ArticuloRepository ar=ArticuloRepositoryFactory.getArticuloRepository();
        I_ArticuloRepository ar=new ArticuloRepository(Connector.getConnection());
        
        ar.save(null);
        ar.save(new Articulo("Bota de Cuero", Tipo.CALZADO, "Rojo", "36", 0, 0, 0, 0, 0, Temporada.VERANO));
        ar.save(new Articulo("Remera", Tipo.ROPA, "Rojo", "L", 0, 0, 0, 0, 0, Temporada.VERANO));
        ar.save(new Articulo("Blusa", Tipo.ROPA, "Rojo", "M", 0, 0, 0, 0, 0, Temporada.VERANO));
        ar.save(new Articulo("Sandalia", Tipo.CALZADO, "Rojo", "36", 0, 0, 0, 0, 0, Temporada.VERANO));
        ar.save(null);
        
        ar.remove(ar.getById(30));
        
        Articulo articulo=ar.getById(28);
        if(articulo!=null && articulo.getId()!=0){
            articulo.setColor("Negro");
            ar.update(articulo);
        }
        
        ar.getAll().forEach(System.out::println);
        System.out.println("*********************************************");
        ar.getLikeDescripcion("bl").forEach(System.out::println);
        
    }
}
