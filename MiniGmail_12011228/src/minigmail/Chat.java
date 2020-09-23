package minigmail;

import java.io.File;
import java.io.Serializable;

public class Chat implements Serializable {
    private Cuenta yo;
    private Cuenta persona;
    private String historial;

    public Chat(Cuenta yo, Cuenta persona, String historial) {
        this.yo = yo;
        this.persona = persona;
        this.historial = historial;
    }

    public Cuenta getYo() {
        return yo;
    }

    public void setYo(Cuenta yo) {
        this.yo = yo;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public Cuenta getPersona() {
        return persona;
    }

    public void setPersona(Cuenta persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return persona.getNombre() + " " + persona.getApellido();
    }
    
}
