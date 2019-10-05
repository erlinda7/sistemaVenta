
package modelo;

import java.util.Date;

public class Ventas {
    private int idVenta;
    private int idCliente;
    private int idVendedor;
    private Date fechaVenta;
    private double monto;

    public Ventas() {
    }

    public Ventas(int idVenta, int idCliente, int idVendedor, Date fechaVenta, double monto) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.fechaVenta = fechaVenta;
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    
}
