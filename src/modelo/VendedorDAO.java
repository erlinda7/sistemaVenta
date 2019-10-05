
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {
    
    Connection con;
    Conexion conexion=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    
    public List listarVendedor(){
        
        List<Vendedor> listaVend=new ArrayList<>();
        String consultaSql="select * from vendedor";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(consultaSql);
            rs=ps.executeQuery();
            
            while (rs.next()) {                
                Vendedor vend=new Vendedor();
                vend.setIdVendedor(rs.getInt(1));
                vend.setCiVendedor(rs.getString(2));
                vend.setNombres(rs.getString(3));
                vend.setTelefono(rs.getString(4));
                vend.setUsuario(rs.getString(4));
                
                listaVend.add(vend);
            }
            
        } catch (Exception e) {
        }
        return listaVend;
    }
    
    public int insertarVendedor(Object[] obj){
        int resp=0;
        String consultaSql="insert into vendedor(ciVendedor,nombres,telefono,usuario) values(?,?,?,?)";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(consultaSql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            ps.setObject(4, obj[3]);
            resp=ps.executeUpdate();
        } catch (Exception e) {
        }
        return resp;
    }
    
    public int actualizarVendedor(Object[] obj){
        int resp=0;
        String consultaSql="update vendedor set ciVendedor=?, nombres=?, telefono=?, usuario=? where idVendedor=?";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(consultaSql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            ps.setObject(4, obj[3]);
            ps.setObject(5, obj[4]); //id
            
            resp=ps.executeUpdate();
        } catch (Exception e) {
        }
        return resp;
    }
    
    
    public void eliminarVendedor(int idVend){
        String consultaSql="delete from vendedor where idVendedor=?";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(consultaSql);
            ps.setInt(1, idVend);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}



