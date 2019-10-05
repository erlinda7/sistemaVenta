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

    public Ventas_controller(Ventas_Form ventas_form) {
        
        this.ventas_form=ventas_form;
        ventas_form.btnBucarCliente.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       buscarCliente();
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
                    VentanaPrincipal_Contr vpc=new VentanaPrincipal_Contr();
                    vpc.centarFrameInterno(cform);
                }
            }
        }
    }
}
