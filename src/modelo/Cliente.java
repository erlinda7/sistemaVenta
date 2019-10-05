
package modelo;

public class Cliente {
    
    private int idCliente;
    private String ciCliente;
    private String nombres;
    private String direccion;

    public Cliente() {
    }

    public Cliente(int idCliente, String ciCliente, String nombres, String direccion) {
        this.idCliente = idCliente;
        this.ciCliente = ciCliente;
        this.nombres = nombres;
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCiCliente() {
        return ciCliente;
    }

    public void setCiCliente(String ciCliente) {
        this.ciCliente = ciCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
}