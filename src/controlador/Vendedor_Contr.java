
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


public class Vendedor_Contr extends MouseAdapter implements ActionListener {

    Vendedor_Form vendedor_Form;
    VendedorDAO  vendedorDAO=new VendedorDAO();
    Vendedor vendedor=new Vendedor();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    
    public Vendedor_Contr( Vendedor_Form vendedor_Form) {
        //System.err.println("HOLA HOLA HOLA");
        this.vendedor_Form=vendedor_Form;
        
        listar(); //se lista al abrir
        
        //eventos
        this.vendedor_Form.btnAgregar.addActionListener(this);
        this.vendedor_Form.btnNuevo.addActionListener(this);
        this.vendedor_Form.btnactualizar.addActionListener(this);
        this.vendedor_Form.btnEliminar.addActionListener(this);
        
        this.vendedor_Form.tablaVendedor.addMouseListener(this);//para recargar los campos del formulario mouseClicked
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(vendedor_Form.btnAgregar==e.getSource()){
             try {
                agregar();
                limpiarTabla(vendedor_Form.tablaVendedor);
                listar();
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo agreagar el vendedor");
            }
        }else if(vendedor_Form.btnactualizar==e.getSource()){
             try {
                actualizar();
                limpiarTabla(vendedor_Form.tablaVendedor);
                listar();
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo actualizar el vendedor");
            }
        }else if(vendedor_Form.btnEliminar==e.getSource()){
             try {
                eliminar();
                limpiarTabla(vendedor_Form.tablaVendedor);
                listar();
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo eliminar el vendedor");
            }
        }else if(vendedor_Form.btnNuevo==e.getSource()){
            try {
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo crear nuevo vendedor");
            }
            
        }
        
    }

    @Override //recargar campos
    public void mouseClicked(MouseEvent e) {
        int fila=vendedor_Form.tablaVendedor.getSelectedRow();
        
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }else{
            id=Integer.parseInt(vendedor_Form.tablaVendedor.getValueAt(fila, 0).toString()); //para actualizar y eliminar
            String ci=vendedor_Form.tablaVendedor.getValueAt(fila, 1).toString();
            String nom=vendedor_Form.tablaVendedor.getValueAt(fila, 2).toString();
            String telf=vendedor_Form.tablaVendedor.getValueAt(fila, 3).toString();
            String usua=vendedor_Form.tablaVendedor.getValueAt(fila, 4).toString();
            
            vendedor_Form.txtCedula.setText(ci);
            vendedor_Form.txtNombres.setText(nom);
            vendedor_Form.txtTelefono.setText(telf);
            vendedor_Form.txtUsuario.setText(usua);
        }
    }
    
 
    
    
    
    //------------------metodos de los eventos
    
    public void agregar(){ 
        
        String ci=vendedor_Form.txtCedula.getText();
        String nom=vendedor_Form.txtNombres.getText();
        String telf=vendedor_Form.txtTelefono.getText();
        String usua=vendedor_Form.txtUsuario.getText();
        
        Object obj[]=new Object[4];
        //el id es autoincrement
        obj[0]=ci;
        obj[1]=nom;
        obj[2]=telf;
        obj[3]=usua;
        
        vendedorDAO.insertarVendedor(obj);
    }
    
    public void actualizar(){
        int fila=vendedor_Form.tablaVendedor.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }else{
            String ci=vendedor_Form.txtCedula.getText();
            String nom=vendedor_Form.txtNombres.getText();
            String telf=vendedor_Form.txtTelefono.getText();
            String usua=vendedor_Form.txtUsuario.getText();
            Object obj[]=new Object[5];
            obj[0]=ci;
            obj[1]=nom;
            obj[2]=telf;
            obj[3]=usua;
            obj[4]=id;
            vendedorDAO.actualizarVendedor(obj);
        }
    }
    
    public void eliminar(){
        int fila=vendedor_Form.tablaVendedor.getSelectedRow();
         if(fila==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }else{
             vendedorDAO.eliminarVendedor(id);
         }
    }
    
       
    public void nuevo(){
        vendedor_Form.txtCedula.setText("");
        vendedor_Form.txtNombres.setText("");
        vendedor_Form.txtTelefono.setText("");
        vendedor_Form.txtUsuario.setText("");
        
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
        List<Vendedor> lista=vendedorDAO.listarVendedor();
        tablaModel=(DefaultTableModel) vendedor_Form.tablaVendedor.getModel();
        Object obj[]=new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            obj[0]=lista.get(i).getIdVendedor();
            obj[1]=lista.get(i).getCiVendedor();
            obj[2]=lista.get(i).getNombres();
            obj[3]=lista.get(i).getTelefono();
            obj[4]=lista.get(i).getUsuario();
            
            tablaModel.addRow(obj);
        }
        vendedor_Form.tablaVendedor.setModel(tablaModel);
    }


    
}

