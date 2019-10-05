
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


public class Cliente_Contr extends MouseAdapter implements ActionListener {

    Cliente_Form clientes_Form;
    ClienteDAO  clienteDAO=new ClienteDAO();
    Cliente cliente=new Cliente();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    
    public Cliente_Contr( Cliente_Form clientes_Form) {
        //System.err.println("HOLA HOLA HOLA");
        this.clientes_Form=clientes_Form;
        
        listar(); //se lista al abrir
        
        //eventos
        this.clientes_Form.btnAgregar.addActionListener(this);
        this.clientes_Form.btnNuevo.addActionListener(this);
        this.clientes_Form.btnactualizar.addActionListener(this);
        this.clientes_Form.btnEliminar.addActionListener(this);
        
        this.clientes_Form.tablaCliente.addMouseListener(this);//para recargar los campos del formulario mouseClicked
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(clientes_Form.btnAgregar==e.getSource()){
             try {
                agregar();
                limpiarTabla(clientes_Form.tablaCliente);
                listar();
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo agreagar el cliente");
            }
        }else if(clientes_Form.btnactualizar==e.getSource()){
             try {
                actualizar();
                limpiarTabla(clientes_Form.tablaCliente);
                listar();
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo actualizar el cliente");
            }
        }else if(clientes_Form.btnEliminar==e.getSource()){
             try {
                eliminar();
                limpiarTabla(clientes_Form.tablaCliente);
                listar();
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo eliminar el cliente");
            }
        }else if(clientes_Form.btnNuevo==e.getSource()){
            try {
                nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se pudo crear nuevo cliente");
            }
            
        }
        
    }

    @Override //recargar campos
    public void mouseClicked(MouseEvent e) {
        int fila=clientes_Form.tablaCliente.getSelectedRow();
        
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }else{
            id=Integer.parseInt(clientes_Form.tablaCliente.getValueAt(fila, 0).toString()); //para actualizar y eliminar
            String ci=clientes_Form.tablaCliente.getValueAt(fila, 1).toString();
            String nom=clientes_Form.tablaCliente.getValueAt(fila, 2).toString();
            String dir=clientes_Form.tablaCliente.getValueAt(fila, 3).toString();
            
            clientes_Form.txtCedula.setText(ci);
            clientes_Form.txtNombre.setText(nom);
            clientes_Form.txtDireccion.setText(dir);
        }
    }
    
 
    
    
    
    //------------------metodos de los eventos
    
    public void agregar(){ 
        
        String ci=clientes_Form.txtCedula.getText();
        String nom=clientes_Form.txtNombre.getText();
        String dir=clientes_Form.txtDireccion.getText();
        
        Object obj[]=new Object[3];
        //el id es autoincrement
        obj[0]=ci;
        obj[1]=nom;
        obj[2]=dir;
        
        clienteDAO.insertarCliente(obj);
    }
    
    public void actualizar(){
        int fila=clientes_Form.tablaCliente.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }else{
            String ci=clientes_Form.txtCedula.getText();
            String nom=clientes_Form.txtNombre.getText();
            String dir=clientes_Form.txtDireccion.getText();
            Object obj[]=new Object[4];
            obj[0]=ci;
            obj[1]=nom;
            obj[2]=dir;
            obj[3]=id;
            clienteDAO.actualizarCliente(obj);
        }
    }
    
    public void eliminar(){
        int fila=clientes_Form.tablaCliente.getSelectedRow();
         if(fila==-1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }else{
             clienteDAO.eliminarCliente(id);
         }
    }
    
       
    public void nuevo(){
        clientes_Form.txtCedula.setText("");
        clientes_Form.txtNombre.setText("");
        clientes_Form.txtDireccion.setText("");
        
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
        List<Cliente> lista=clienteDAO.listarClientes();
        tablaModel=(DefaultTableModel) clientes_Form.tablaCliente.getModel();
        Object obj[]=new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            obj[0]=lista.get(i).getIdCliente();
            obj[1]=lista.get(i).getCiCliente();
            obj[2]=lista.get(i).getNombres();
            obj[3]=lista.get(i).getDireccion();
            
            tablaModel.addRow(obj);
        }
        clientes_Form.tablaCliente.setModel(tablaModel);
    }


    
}
