package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    
    Connection con;
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //referencia al driver
                                            //url, usuario, password
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas?useTimezone=true&serverTimezone=America/La_Paz", "root", "root");
            System.out.println("Conexion existosa");
        } catch (Exception e) {
                System.out.println("Conexion fallida");
        }
        return con;
    }
}
