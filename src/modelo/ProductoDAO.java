
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    Connection con;
    Conexion conexion=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    
     public List listarProductos(){
        
             
        List<Producto> listaProd=new ArrayList<>();
        String consultaSql="select * from producto";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(consultaSql);
            rs=ps.executeQuery();
            
            while (rs.next()) {                
                Producto prod=new Producto();
                prod.setIdProducto(rs.getInt(1));
                prod.setNombre(rs.getString(2));
                prod.setPrecio(rs.getDouble(3));
                prod.setStock(rs.getInt(4));
                
                listaProd.add(prod);
            }
            
        } catch (Exception e) {
        }
        return listaProd;
    }   
     
    public int insertarProducto(Object[] obj){
  
        int resp=0;
        String consultaSql="insert into producto(nombre,precio,stock) values(?,?,?)";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(consultaSql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            resp=ps.executeUpdate();
        } catch (Exception e) {
        }
        return resp;
    } 
    
    
    public int actualizarProducto(Object[] obj){
        int resp=0;
        String consultaSql="update producto set nombre=?, precio=?, stock=? where idProducto=?";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(consultaSql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            ps.setObject(4, obj[3]); //idProducto
            
            resp=ps.executeUpdate();
        } catch (Exception e) {
        }
        return resp;
    }
    
    public void eliminarProducto(int idProd){
        String consultaSql="delete from producto where idProducto=?";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(consultaSql);
            ps.setInt(1, idProd);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    
    //////////////////7777
    public Producto listarId(){
        Producto p=new Producto();
        String sql="select * from producto where IdProducto";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                p.setIdProducto(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
            }
        } catch (Exception e) {
        }
        return p;
    }
}
