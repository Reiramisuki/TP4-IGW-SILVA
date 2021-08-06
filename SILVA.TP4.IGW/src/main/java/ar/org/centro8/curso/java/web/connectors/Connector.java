package ar.org.centro8.curso.java.web.connectors;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
   private static String driver = "org.mariadb.jdbc.Driver";
    private static String vendor = "mariadb";
//    private static String server = "freedb.tech";
//    private static String port = "3306";
//    private static String db = "freedbtech_negocioRopaWeb";
//    private static String params = "?serverTimezone=UTC";
//    private static String user = "freedbtech_negocioRopaWeb";
//    private static String pass = "centro8";
    private static String server="localhost";
    private static String port="3307";
    private static String db="negocioWebRopa";
    private static String params="?serverTimezone=UTC";
    private static String user="root";
    private static String pass="123456";
    private static String url = "jdbc:" + vendor + "://" + server + ":" + port + "/" + db + params;

    private static Connection conn = null;

    private Connector() {
    }

    public synchronized static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, pass);
            }
        } catch (SQLException e) {
            System.out.println("Problema de conexión");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver");
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}