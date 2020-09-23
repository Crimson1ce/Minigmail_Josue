package chat;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuarios implements Serializable {
    private ArrayList<usuario> users = new ArrayList();
    public Usuarios() {
    }

    public ArrayList<usuario> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<usuario> users) {
        this.users = users;
    }
}
