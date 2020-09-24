package minigmail;

import java.io.Serializable;

public class FiltroCuenta extends Filtro implements Serializable {
    private String direccionCorreo;

    public FiltroCuenta(String direccionCorreo) {
        this.direccionCorreo = direccionCorreo;
    }

    public String getDireccionCorreo() {
        return direccionCorreo;
    }

    public void setDireccionCorreo(String direccionCorreo) {
        this.direccionCorreo = direccionCorreo;
    }

    @Override
    public String toString() {
        return "FiltroCuenta: " + direccionCorreo;
    }
    
}
