/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;


public class Ventas_controller implements ActionListener{
    
    Ventas_Form ventas_form;
    ClienteDAO clienteDAO=new ClienteDAO();
    ProductoDAO productoDAO=new ProductoDAO();
    
    Ventas ventas=new Ventas();
    VentasDAO ventasDAO=new VentasDAO();
    DetalleVentas detalleVentas=new DetalleVentas();
    
    double totalPagar;
    double precio;
    int cant;
    DefaultTableModel tablaModel=new DefaultTableModel();
  
    Cliente cliente=new Cliente();
    
    int idProd;
    
    public Ventas_controller(Ventas_Form ventas_form) {
        
        this.ventas_form=ventas_form;
        
        agregarFecha();
        //eventos
        ventas_form.btnBucarCliente.addActionListener(this);
        ventas_form.btnBuscarProd.addActionListener(this);
        ventas_form.btnAgregar.addActionListener(this);
        ventas_form.btnGenerarVenta.addActionListener(this);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(ventas_form.btnBucarCliente==e.getSource()){
           buscarCliente();
       }else if(ventas_form.btnBuscarProd==e.getSource()){
           buscarProducto();
       }else if(ventas_form.btnAgregar==e.getSource()){
           agregarProducto();
       }else if(ventas_form.btnGenerarVenta==e.getSource()){
           if(ventas_form.txtTotalPagar.getText().equals("")){
               JOptionPane.showMessageDialog(ventas_form, "Debe ingresar datos");
           }else{
                guardarVenta();
                guardarDetalle();
                actualizarStock();
                 JOptionPane.showMessageDialog(ventas_form, "Se realizo la venta con exito");
                 nuevo();
           }
       }
       
    }
    
    //metodos del evento
    public void buscarCliente(){
        int res;
        String cod=ventas_form.txtCedula.getText();
        if(ventas_form.txtCedula.getText().equals("")){
            JOptionPane.showMessageDialog(ventas_form, "Debe ingresar el ci del Cliente");
        }else{
            cliente=clienteDAO.listarCedula(cod);
            if(cliente.getCiCliente()!=null){
                ventas_form.txtCliente.setText(cliente.getNombres());
            }else{
                res=JOptionPane.showConfirmDialog(ventas_form, "Cliente no registrado, desea registrar?");
                if(res==0){
                    Cliente_Form cform=new Cliente_Form();
                    VentanaPrincipal_Form.panelPrincipal.add(cform);
                    cform.setVisible(true);
                }
            }
        }
    }
    
    //buscar producto
    public void buscarProducto(){
        idProd=Integer.parseInt(ventas_form.txtCodProd.getText());
        if(ventas_form.txtCodProd.getText().equals("")){
            JOptionPane.showMessageDialog(ventas_form, "Debe ingresar el codigo del producto");
        }else {
            Producto p=productoDAO.listarId(idProd);
            if(p.getIdProducto()!=0){
                ventas_form.txtProducto.setText(p.getNombre());
                ventas_form.txtPrecio.setText(p.getPrecio()+"");
                ventas_form.txtStok.setText(p.getStock()+"");
            }else{
                JOptionPane.showMessageDialog(ventas_form, "Producto no registrado");
            }
        }
    }
    
    ////agregar producto
    public void agregarProducto(){
        double total;
        int item=0;
        tablaModel=(DefaultTableModel) ventas_form.tablaVentas.getModel();
        item++;
        int idProd=Integer.parseInt(ventas_form.txtCodProd.getText());
        String nomProd= ventas_form.txtProducto.getText();
        precio=Double.parseDouble(ventas_form.txtPrecio.getText());
        cant= Integer.parseInt(ventas_form.spCantidad.getValue().toString());
        int stock=Integer.parseInt(ventas_form.txtStok.getText());
        total=cant*precio;
        
        ArrayList lista=new ArrayList();
        if(stock>0){
            lista.add(item);
            lista.add(idProd);
            lista.add(nomProd);
            lista.add(cant);
            lista.add(precio);
            lista.add(total);
            
            Object obj[]=new Object[6];
            obj[0]=lista.get(0);
            obj[1]=lista.get(1);
            obj[2]=lista.get(2);
            obj[3]=lista.get(3);
            obj[4]=lista.get(4);
            obj[5]=lista.get(5);
            
            tablaModel.addRow(obj);
            ventas_form.tablaVentas.setModel(tablaModel);
            calcularTotal();
        }else{
            JOptionPane.showMessageDialog(ventas_form, "Stock producto no disponible");
        }
    }
    
    public void calcularTotal(){
        totalPagar=0;
        for (int i = 0; i < ventas_form.tablaVentas.getRowCount(); i++) {
            cant=Integer.parseInt(ventas_form.tablaVentas.getValueAt(i, 3).toString());
            precio=Double.parseDouble(ventas_form.tablaVentas.getValueAt(i, 4).toString());
            totalPagar=totalPagar + (cant*precio);
        }
        ventas_form.txtTotalPagar.setText(totalPagar+"");
    }
    
    
    //////////77
    public void agregarFecha(){
        Calendar cal=new GregorianCalendar();
        int anio=cal.get(Calendar.YEAR);
        int mes=cal.get(Calendar.MONTH)+1;
        int dia=cal.get(Calendar.DAY_OF_MONTH);
        ventas_form.txtFecha.setText(anio+"-"+mes+"-"+dia);
    }
    
    public void guardarVenta(){
        int idVendedor=1;
        int idCliente=cliente.getIdCliente();
        String fecha=ventas_form.txtFecha.getText();
        double monto=totalPagar;
        
        ventas.setIdCliente(idCliente);
        ventas.setIdVendedor(idVendedor);
        ventas.setFechaVenta(fecha);
        ventas.setMonto(monto);
        
        ventasDAO.agregarVentas(ventas);
    }
    
    public void guardarDetalle(){
        int idVen=Integer.parseInt(ventasDAO.idVentas());
              
        for (int i = 0; i < ventas_form.tablaVentas.getRowCount(); i++) {
            int idProd=Integer.parseInt(ventas_form.tablaVentas.getValueAt(i, 1).toString());
            int cant=Integer.parseInt(ventas_form.tablaVentas.getValueAt(i, 3).toString());
            double precio=Double.parseDouble(ventas_form.tablaVentas.getValueAt(i, 4).toString());
            
            detalleVentas.setIdVenta(idVen);
            detalleVentas.setIdProducto(idProd);
            detalleVentas.setCantidad(cant);
            detalleVentas.setPrecioVenta(precio);
            
            ventasDAO.agregarDetalleVentas(detalleVentas);
        }
    }
    
    ////////////77777
    public void actualizarStock(){
        for (int i = 0; i < tablaModel.getRowCount(); i++) {
            Producto producto=new Producto();
            idProd=Integer.parseInt(ventas_form.tablaVentas.getValueAt(i, 1).toString());
            cant=Integer.parseInt(ventas_form.tablaVentas.getValueAt(i, 3).toString());
            producto=productoDAO.listarId(idProd);
            int stockActual=producto.getStock()- cant;
            
            productoDAO.modificarStock(stockActual, idProd);
        }
    }
    
    

    
    public void nuevo(){
        limpiarTabla();
        ventas_form.txtCedula.setText("");
        ventas_form.txtCliente.setText("");
        ventas_form.txtCodProd.setText("");
        ventas_form.txtProducto.setText("");
        ventas_form.txtCliente.setText("");
        ventas_form.txtVendedor.setText("");
        ventas_form.spCantidad.setValue(1);
        ventas_form.txtPrecio.setText("");
        ventas_form.txtStok.setText("");
        ventas_form.txtFecha.setText("");
    }
        public void limpiarTabla(){
        for (int i = 0; i < tablaModel.getRowCount(); i++) {
            tablaModel.removeRow(i);
            i=i-1;
        }
    }
}
