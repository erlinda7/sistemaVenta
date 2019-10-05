
package controlador;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import vista.*;
public class VentanaPrincipal_Contr {

    VentanaPrincipal_Form ventanaPrincipal;
    JPanel panelPrinc;
    public VentanaPrincipal_Contr(VentanaPrincipal_Form ventanaPrincipal) {
        
       this.ventanaPrincipal=ventanaPrincipal;
       panelPrinc=ventanaPrincipal.panelPrincipal;
       
       ventanaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
       ventanaPrincipal.setTitle("Sistema de Ventas");
       ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       ventanaPrincipal.setVisible(true);
       
       
       //eventos
       ventanaPrincipal.itemSalir.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
       });
       
       ventanaPrincipal.itemGenerarVenta.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Ventas_Form ventas_Form=new Ventas_Form();
               ventas_Form.setTitle("Ventas");
               centarFrameInterno(ventas_Form);
           }
       });
       
       ventanaPrincipal.itemCliente.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Cliente_Form clientes_Form=new Cliente_Form();
               clientes_Form.setTitle("Cliente");
               centarFrameInterno(clientes_Form);
               
               Cliente_Contr cliente_Contr=new Cliente_Contr(clientes_Form); //llamamos al controlador
           }
       });
       
       ventanaPrincipal.itemProducto.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Producto_Form producto_Form=new Producto_Form();
               producto_Form.setTitle("Producto");
               centarFrameInterno(producto_Form); 
               
               Producto_Contr producto_Contr=new Producto_Contr(producto_Form);
           }
       });
       
       this.ventanaPrincipal.itemVendedor.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                Vendedor_Form vendedor_Form=new Vendedor_Form();
                vendedor_Form.setTitle("Vendedor");
                centarFrameInterno(vendedor_Form);
                
                Vendedor_Contr vendedor_Contr=new Vendedor_Contr(vendedor_Form);
           }
       });
    }
    
    public void centarFrameInterno(JInternalFrame frameInterno){
        panelPrinc.add(frameInterno);
        Dimension dim_panelP=panelPrinc.getSize();
        Dimension dim_frameInt=frameInterno.getSize();
        frameInterno.setLocation((dim_panelP.width - dim_frameInt.height)/2, (dim_panelP.height - dim_frameInt.width)/2);
        frameInterno.show();
    }
    
    
    
}
