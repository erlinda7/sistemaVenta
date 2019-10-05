
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    Connection con;
    Conexion conexion=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    
    public List listarClientes(){
        
        List<Cliente> listaCl=new ArrayList<>();
        String consultaSql="select * from cliente";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(consultaSql);
            rs=ps.executeQuery();
            
            while (rs.next()) {                
                Cliente cl=new Cliente();
                cl.setIdCliente(rs.getInt(1));
                cl.setCiCliente(rs.getString(2));
                cl.setNombres(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                
                listaCl.add(cl);
            }
            
        } catch (Exception e) {
        }
        return listaCl;
    }
    
    public int insertarCliente(Object[] obj){
        int resp=0;
        String consultaSql="insert into cliente(ciCliente,nombres,direccion) values(?,?,?)";
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
    
    public int actualizarCliente(Object[] obj){
        int resp=0;
        String consultaSql="update cliente set ciCliente=?, nombres=?, direccion=? where idCliente=?";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(consultaSql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            ps.setObject(4, obj[3]); //id
            
            resp=ps.executeUpdate();
        } catch (Exception e) {
        }
        return resp;
    }
    
    
    public void eliminarCliente(int idCli){
        String consultaSql="delete from cliente where idCliente=?";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(consultaSql);
            ps.setInt(1, idCli);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}


