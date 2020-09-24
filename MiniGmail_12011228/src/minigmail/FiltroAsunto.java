package minigmail;

import java.io.Serializable;

public class FiltroAsunto extends Filtro implements Serializable {
    private String palabras;

    public FiltroAsunto(String palabras) {
        this.palabras = palabras;
    }

    public String getPalabras() {
        return palabras;
    }

    public void setPalabras(String palabras) {
        this.palabras = palabras;
    }

    @Override
    public String toString() {
        return "FiltroAsunto: " + palabras;
    }
    
}
