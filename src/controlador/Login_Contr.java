package controlador;

import java.awt.Frame;
import modelo.*;
import vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Login_Contr implements ActionListener
{
 
    Login_Form form_Inicio;//-----1----
    ValidarVendedor vendedorDAO;//---2----para recibir el vendedorDAO ya creado
    Vendedor vendedor;//---2----para guardar el vendedor que se creo en vendedorDAO

    public Login_Contr(Login_Form form_Inicio, ValidarVendedor vendedorDAO) {
        this.form_Inicio = form_Inicio; //-------1------
        this.vendedorDAO = vendedorDAO;
       
        //--------------1--------
        form_Inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form_Inicio.setLocationRelativeTo(null);
        form_Inicio.setResizable(false);
        
        
        //
        form_Inicio.txtUsuario.setText("emp1");
        form_Inicio.txtContrasenia.setText("123");
        //
        
        form_Inicio.setVisible(true);
        //-------1--------
        
        //evento  ---2---
        form_Inicio.btnAceptar.addActionListener(this);
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        validar();
    }

    
    
    
  //metodos de eventos
    public void validar(){
        String usuario=form_Inicio.txtUsuario.getText();
        String contrasenia=form_Inicio.txtContrasenia.getText();
        
        if(usuario.equals("") || contrasenia.equals("")){ //vacio
            JOptionPane.showMessageDialog(form_Inicio, "Ingrese sus datos,campos vacios");
        }else{
            
            vendedor=vendedorDAO.validarVendedor(usuario, contrasenia); //ci =contraseña
            
            if(vendedor.getUsuario()!=null && vendedor.getCiVendedor()!=null){
                VentanaPrincipal_Form ventanPrincipal=new VentanaPrincipal_Form();
                VentanaPrincipal_Contr ventanaPrincipal_Contr=new VentanaPrincipal_Contr(ventanPrincipal);
                 form_Inicio.dispose();
            }else{
                JOptionPane.showMessageDialog(form_Inicio, "Ingrese  datos validos");
            }
        }
    }
}
