
package modelo;

public class Vendedor {
    private int idVendedor;
    private String ciVendedor;
    private String nombres;
    private String telefono;
    private String usuario;

    public Vendedor() {
    }

    public Vendedor(int idVendedor, String ciVendedor, String nombres, String telefono, String usuario) {
        this.idVendedor = idVendedor;
        this.ciVendedor = ciVendedor;
        this.nombres = nombres;
        this.telefono = telefono;
        this.usuario = usuario;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getCiVendedor() {
        return ciVendedor;
    }

    public void setCiVendedor(String ciVendedor) {
        this.ciVendedor = ciVendedor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
}
