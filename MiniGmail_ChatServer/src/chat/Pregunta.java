package chat;

import java.io.Serializable;
import java.util.ArrayList;

public class Pregunta implements Serializable {
    private int opcion;
    private Usuarios users;
    private String mensaje;
    private ArrayList lista;

    public Pregunta(int opcion, Usuarios users) {
        this.opcion = opcion;
        this.users = users;
    }

    public Pregunta(int opcion, String mensaje) {
        this.opcion = opcion;
        this.mensaje = mensaje;
    }

    public Pregunta(int opcion, ArrayList lista) {
        this.opcion = opcion;
        this.lista = lista;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public Usuarios getUsers() {
        return users;
    }

    public void setUsers(Usuarios users) {
        this.users = users;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList getLista() {
        return lista;
    }

    public void setLista(ArrayList lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return opcion + "";
    }
    
}
