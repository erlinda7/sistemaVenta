/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Erlinda
 */
public class VentanaPrincipal_Form extends javax.swing.JFrame {

    /**
     * Creates new form Form_Ventas
     */
    public VentanaPrincipal_Form() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemAyuda = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemGenerarVenta = new javax.swing.JMenuItem();
        jmenu3 = new javax.swing.JMenu();
        itemCliente = new javax.swing.JMenuItem();
        itemProducto = new javax.swing.JMenuItem();
        itemVendedor = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        itemReportes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(204, 204, 255));
        panelPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 737, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menu.png"))); // NOI18N
        jMenu1.setText("Menu");

        itemAyuda.setText("Ayuda");
        jMenu1.add(itemAyuda);

        itemSalir.setText("Salir");
        jMenu1.add(itemSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventas.png"))); // NOI18N
        jMenu2.setText("Ventas");

        itemGenerarVenta.setText("Generar Venta");
        jMenu2.add(itemGenerarVenta);

        jMenuBar1.add(jMenu2);

        jmenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mantenimiento.png"))); // NOI18N
        jmenu3.setText("Mantenimiento");

        itemCliente.setText("Cliente");
        jmenu3.add(itemCliente);

        itemProducto.setText("Producto");
        jmenu3.add(itemProducto);

        itemVendedor.setText("Vendedor");
        jmenu3.add(itemVendedor);

        jMenuBar1.add(jmenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reportes.png"))); // NOI18N
        jMenu4.setText("Reportes");

        itemReportes.setText("Reporte de Ventas");
        jMenu4.add(itemReportes);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemAyuda;
    public javax.swing.JMenuItem itemCliente;
    public javax.swing.JMenuItem itemGenerarVenta;
    public javax.swing.JMenuItem itemProducto;
    public javax.swing.JMenuItem itemReportes;
    public javax.swing.JMenuItem itemSalir;
    public javax.swing.JMenuItem itemVendedor;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenu jmenu3;
    public static javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
