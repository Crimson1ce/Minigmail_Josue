package minigmail;

import java.io.Serializable;

public abstract class Filtro implements Serializable {

    public Filtro() {
    }

    @Override
    public abstract String toString();
    
}
