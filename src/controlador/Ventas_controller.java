/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;


public class Ventas_controller implements ActionListener{
    
    Ventas_Form ventas_form;
    ClienteDAO clienteDAO=new ClienteDAO();
    ProductoDAO productoDAO=new ProductoDAO();

    public Ventas_controller(Ventas_Form ventas_form) {
        
        this.ventas_form=ventas_form;
        //eventos
        ventas_form.btnBucarCliente.addActionListener(this);
        ventas_form.btnBuscarProd.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(ventas_form.btnBucarCliente==e.getSource()){
           buscarCliente();
       }else if(ventas_form.btnBuscarProd==e.getSource()){
           buscarProducto();
       }
       
    }
    
    //metodos del evento
    public void buscarCliente(){
        int res;
        String cod=ventas_form.txtCedula.getText();
        if(ventas_form.txtCedula.getText().equals("")){
            JOptionPane.showMessageDialog(ventas_form, "Debe ingresar el ci del Cliente");
        }else{
            Cliente cliente=clienteDAO.listarCedula(cod);
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
        int id=Integer.parseInt(ventas_form.txtCodProd.getText());
        if(ventas_form.txtCodProd.getText().equals("")){
            JOptionPane.showMessageDialog(ventas_form, "Debe ingresar el codigo del producto");
        }else {
            Producto p=productoDAO.listarId(id);
            if(p.getIdProducto()!=0){
                ventas_form.txtProducto.setText(p.getNombre());
                ventas_form.txtPrecio.setText(p.getPrecio()+"");
                ventas_form.txtStok.setText(p.getStock()+"");
            }else{
                JOptionPane.showMessageDialog(ventas_form, "Producto no registrado");
            }
        }
    }
}
