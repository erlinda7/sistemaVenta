
package controlador;

import modelo.*;
import vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Producto_Contr extends MouseAdapter implements ActionListener {

    Producto_Form producto_Form;
    ProductoDAO productoDAO=new ProductoDAO();
    Producto producto=new Producto();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    
    public Producto_Contr( Producto_Form producto_Form) {
        //System.err.println("HOLA HOLA HOLA");
        this.producto_Form=producto_Form;
        
        listar(); //se lista al abrir
        
        //eventos
        this.producto_Form.btnAgregar.addActionListener(this);
        this.producto_Form.btnNuevo.addActionListener(this);
        this.producto_Form.btnActualizar.addActionListener(this);
        this.producto_Form.btnEliminar.addActionListener(this);
        
        this.producto_Form.tablaProducto.addMouseListener(this);//para recargar los campos del formulario mouseClicked
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(producto_Form.btnAgregar==e.getSource()){
             try {
                agregar();
                limpiarTabla(producto_Form.tablaProducto);
                listar();
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo agregar el producto ingrese datos correctos\n precio : 34.7");
            }
        }else if(producto_Form.btnActualizar==e.getSource()){
             try {
                actualizar();
                limpiarTabla(producto_Form.tablaProducto);
                listar();
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo actualizar el producto");
            }
        }else if(producto_Form.btnEliminar==e.getSource()){
             try {
                eliminar();
                limpiarTabla(producto_Form.tablaProducto);
                listar();
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo eliminar el producto");
            }
        }else if(producto_Form.btnNuevo==e.getSource()){
            try {
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo crear nuevo producto");
            }
            
        }
        
    }

    @Override //recargar campos
    public void mouseClicked(MouseEvent e) {
        int fila=producto_Form.tablaProducto.getSelectedRow();
        
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }else{
            id=Integer.parseInt(producto_Form.tablaProducto.getValueAt(fila, 0).toString()); //para actualizar y eliminar
            String nombre=producto_Form.tablaProducto.getValueAt(fila, 1).toString();
            String precio=producto_Form.tablaProducto.getValueAt(fila, 2).toString();
            String stock=producto_Form.tablaProducto.getValueAt(fila, 3).toString();
            
            producto_Form.txtNombre.setText(nombre);
            producto_Form.txtPrecio.setText(precio);
            producto_Form.txtStock.setText(stock);
        }
    }
    
 
    
    
    
    //------------------metodos de los eventos
    
    public void agregar(){ 
        String nombre=producto_Form.txtNombre.getText();
        double precio=Double.parseDouble(producto_Form.txtPrecio.getText());
        int stock=Integer.parseInt(producto_Form.txtStock.getText());
        
        Object obj[]=new Object[3];
        //el id es autoincrement
        obj[0]=nombre;
        obj[1]=precio;
        obj[2]=stock;
        
        productoDAO.insertarProducto(obj);
    }
    
    public void actualizar(){
        int fila=producto_Form.tablaProducto.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }else{
            String nombre=producto_Form.txtNombre.getText();
            double precio=Double.parseDouble(producto_Form.txtPrecio.getText());
            int stock=Integer.parseInt(producto_Form.txtStock.getText());
            Object obj[]=new Object[4];
            obj[0]=nombre;
            obj[1]=precio;
            obj[2]=stock;
            obj[3]=id;
            productoDAO.actualizarProducto(obj);
        }
    }
    
    public void eliminar(){
        int fila=producto_Form.tablaProducto.getSelectedRow();
         if(fila==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }else{
             productoDAO.eliminarProducto(id);
         }
    }
    
       
    public void nuevo(){
        producto_Form.txtNombre.setText("");
        producto_Form.txtPrecio.setText("");
        producto_Form.txtStock.setText("");
        
    }
    
       
   public void limpiarTabla(JTable tabla){
        try {
            
            int filas=tabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                tablaModel.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    public void listar(){
         
        List<Producto> lista=productoDAO.listarProductos();
        tablaModel=(DefaultTableModel) producto_Form.tablaProducto.getModel();
        Object obj[]=new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            obj[0]=lista.get(i).getIdProducto();
            obj[1]=lista.get(i).getNombre();
            obj[2]=lista.get(i).getPrecio();
            obj[3]=lista.get(i).getStock();
            
            tablaModel.addRow(obj);
        }
        producto_Form.tablaProducto.setModel(tablaModel);
    }


    
}
