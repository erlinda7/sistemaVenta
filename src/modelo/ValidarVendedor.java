package modelo;
import vista.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class ValidarVendedor {
    
    PreparedStatement ps;
    ResultSet rs;
    
    Conexion conexion=new Conexion(); //acceso a nuestra clase conexion
    Connection con;
    
    Vendedor ven=new Vendedor();  //instancia de vendedor 

    Login_Form loginForm;

    public ValidarVendedor(Login_Form loginForm ) {
       
        this.loginForm=this.loginForm;
        
    }
    
    //usuario --->usuario...ci--->contraseña del login  
    public Vendedor validarVendedor(String usuario, String ci){
               
       Vendedor  vendedor=new Vendedor(); //objeto
       String consultaSQL="SELECT * FROM vendedor WHERE usuario=? AND ciVendedor=? "; //de la base de datos columnas
        
        try {
            con=conexion.conectar(); //llamamos al metodo conectar ..se realiza la conexion
            ps=con.prepareStatement(consultaSQL); //prepared statement
            ps.setString(1, usuario);
            ps.setString(2, ci);
            rs=ps.executeQuery();
            
            while(rs.next()){
                //el orden que tiene en la BBDD 
                vendedor.setIdVendedor(rs.getInt(1));
                vendedor.setCiVendedor(rs.getString(2));
                vendedor.setNombres(rs.getString(3));
                vendedor.setTelefono(rs.getString(4));
                vendedor.setUsuario(rs.getString(5));
                
            }
            
        } catch (Exception e) {
        }
        return vendedor;
    }
    
    
    
   
}
