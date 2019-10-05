
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentasDAO {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int res=0;
    
    public String idVentas(){
        String idVenta="";
        String sql="select max(idVenta) from venta";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                idVenta=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return idVenta;
    }
    
    public int agregarVentas(Ventas vent){
        Ventas ventas=new Ventas();
        String sql="insert into Venta (idCliente, idVendedor, fechaVenta, monto) values(?,?,?,?)";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, vent.getIdCliente());
            ps.setInt(2, vent.getIdVendedor());
            ps.setString(3, vent.getFechaVenta());
            ps.setDouble(4, vent.getMonto());
            
            res=ps.executeUpdate();
            
        } catch (Exception e) {
        }
        return res;
    }
    
    public int agregarDetalleVentas(DetalleVentas detVentas){
        String sql="insert into detalleventa(idVenta, idProducto,cantidad,precioVenta) values(?,?,?,?)";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, detVentas.getIdVenta());
            ps.setInt(2, detVentas.getIdProducto());
            ps.setInt(3, detVentas.getCantidad());
            ps.setDouble(4, detVentas.getPrecioVenta());
            res=ps.executeUpdate();
        } catch (Exception e) {
        }
        return res;
    }
}
