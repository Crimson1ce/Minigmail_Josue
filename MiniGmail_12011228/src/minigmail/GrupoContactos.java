package minigmail;

import java.io.Serializable;
import java.util.ArrayList;

public class GrupoContactos implements Serializable{
    private String nombreGrupo;
    private ArrayList<String> contactos;

    public GrupoContactos(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
        this.contactos = new ArrayList<>();
    }

    public GrupoContactos(String nombreGrupo, ArrayList<String> contactos) {
        this.nombreGrupo = nombreGrupo;
        this.contactos = contactos;
    }

    public ArrayList<String> getContactos() {
        return contactos;
    }

    public void setContactos(ArrayList<String> contactos) {
        this.contactos = contactos;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    @Override
    public String toString() {
        return nombreGrupo;
    }
    
}
