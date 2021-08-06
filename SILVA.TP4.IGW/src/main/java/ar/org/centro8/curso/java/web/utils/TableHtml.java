package ar.org.centro8.curso.java.web.utils;

import ar.org.centro8.curso.java.web.entities.Cliente;
import static java.lang.System.out;
import java.lang.reflect.Field;
import java.util.List;

public class TableHtml<E> {

    public String getTable(List<E> list) {
        String table = "";
        if (list == null || list.isEmpty()) {
            return table;
        }
        table += "<table border=0,1>";
        E e = list.get(0);
        Field[] campos = e.getClass().getDeclaredFields();
        table += "<tr>";
        for (Field f : campos) {
            table += "<th>" + f.getName() + "</th>";
        }
        table += "</tr>";
//        out.println(new TableHtml<Cliente>().getTable(list));
        for (E o : list) {
            table += "<tr>";
            for (Field f : campos) {
                table += "<td>";
                String method = "get"
                        + f
                                .getName()
                                .substring(0, 1)
                                .toUpperCase()
                        + f
                                .getName()
                                .substring(1);
                //table+=method;
                try {
                    table += e.getClass().getMethod(method, null).invoke(o, null);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                table += "</td>";
            }
            table += "</tr>";
        }
        table += "</table>";
        return table;
    }

    public String getTable(List<E> list, String linkDelete) {
        String table = "";
        if (list == null || list.isEmpty()) {
            return table;
        }
        table += "<table border=1>";
        E e = list.get(0);
        Field[] campos = e.getClass().getDeclaredFields();
        table += "<tr>";
        for (Field f : campos) {
            table += "<th>" + f.getName() + "</th>";
        }
        table += "<th>Eliminar</th>";
        table += "</tr>";
        for (E o : list) {
            table += "<tr>";
            for (Field f : campos) {
                table += "<td>";
                String method = "get"
                        + f
                                .getName()
                                .substring(0, 1)
                                .toUpperCase()
                        + f
                                .getName()
                                .substring(1);
                //table+=method;
                try {
                    table += e.getClass().getMethod(method, null).invoke(o, null);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                table += "</td>";
            }
            try {
                table += "<td><a href='" + linkDelete + "?id="
                        + e.getClass().getMethod("getId", null).invoke(o, null)
                        + "'>Borrar</></td>";
            } catch (Exception ex) {
                System.out.println(ex);
            }
            table += "</tr>";
        }
        table += "</table>";
        return table;
    }
}
