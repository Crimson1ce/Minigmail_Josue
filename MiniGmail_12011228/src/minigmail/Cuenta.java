package minigmail;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Cuenta implements Serializable {
//
//    static final int BANDEJA_ENVIADOS = 0;
//    static final int BANDEJA_RECIBIDOS = 1;
//    static final int BANDEJA_ELIMINADOS = 2;
//    static final int BANDEJA_BORRADORES = 3;
//    static final int BANDEJA_SPAM = 4;

    private String direccionCorreoElectronico;
    private String nombre;
    private String apellido;
    private String clave;
    private Date fechaDeCreacion;
    private ArrayList<Correo> recibidos;
    private ArrayList<Correo> enviados;
    private ArrayList<Correo> eliminados;
    private ArrayList<Correo> borradores;
    private ArrayList<Correo> spam;

    private ArrayList<Correo>[] bandejas;
    private String[] carpetas;

    private ArrayList<Cuenta> cuentasSpam;
    private ArrayList<Date[]> fechasSpam;
    private ArrayList<String> asuntoSpam;

    private ArrayList<Chat> chats;

    public static final long serialVersionUID = 3L;

    public Cuenta() {
        initTree();
    }

    public Cuenta(String direccionCorreoElectronico, String nombre, String apellido, String clave, Date fechaDeCreacion) {
        initTree();
        this.direccionCorreoElectronico = direccionCorreoElectronico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getDireccionCorreoElectronico() {
        return direccionCorreoElectronico;
    }

    public void setdireccionCorreoElectronico(String direccionCorreoElectronico) {
        this.direccionCorreoElectronico = direccionCorreoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    protected ArrayList<Correo> getRecibidos() {
        return recibidos;
    }

    public void setRecibidos(ArrayList<Correo> recibidos) {
        this.recibidos = recibidos;
    }

    public void setRecibido(Correo recibido) {
        recibidos.add(recibido);
    }

    public ArrayList<Correo> getEnviados() {
        return enviados;
    }

    public void setEnviados(ArrayList<Correo> enviados) {
        this.enviados = enviados;
    }

    public ArrayList<Correo> getEliminados() {
        return eliminados;
    }

    public void setEliminados(ArrayList<Correo> eliminados) {
        this.eliminados = eliminados;
    }

    public ArrayList<Correo> getBorradores() {
        return borradores;
    }

    public void setBorradores(ArrayList<Correo> borradores) {
        this.borradores = borradores;
    }

    public ArrayList<Correo> getSpam() {
        return spam;
    }

    public void setSpam(ArrayList<Correo> spam) {
        this.spam = spam;
    }

    public ArrayList<Correo>[] getBandejas() {
        return bandejas;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public String toString() {
        return direccionCorreoElectronico;
    }

    private void initTree() {
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode();
        recibidos = new ArrayList();
        enviados = new ArrayList();
        eliminados = new ArrayList();
        borradores = new ArrayList();
        spam = new ArrayList();

        bandejas = new ArrayList[]{enviados, recibidos, eliminados, borradores, spam};
        carpetas = new String[]{"Enviados", "Recibidos", "Eliminados", "Borradores", "Spam"};

        cuentasSpam = new ArrayList<>();
        fechasSpam = new ArrayList<>();
        asuntoSpam = new ArrayList<>();
        
        chats = new ArrayList<>();
    }

    public void cargarCorreos() {

        File archivo = null;
        FileInputStream fi = null;
        ObjectInputStream oi = null;

        try {

            for (int i = 0; i < 5; i++) {

                archivo = new File("./Cuentas/" + direccionCorreoElectronico + "/" + carpetas[i] + ".aaa");

                if (archivo.exists()) {

                    fi = new FileInputStream(archivo);
                    oi = new ObjectInputStream(fi);
                    Correo c;

                    try {
                        while ((c = (Correo) oi.readObject()) != null) {
                            bandejas[i].add(c);
                        }//fin while
                    } catch (EOFException e) {
                    } catch (NullPointerException e) {
//                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    oi.close();
                    fi.close();

                }//Fin if exists

            }//fin for

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void escribirCorreos() {

        File archivo = null;
        FileOutputStream fo = null;
        ObjectOutputStream oo = null;

        try {

            for (int i = 0; i < 5; i++) {

                archivo = new File("./Cuentas/" + direccionCorreoElectronico + "/" + carpetas[i] + ".aaa");

                try {
                    fo = new FileOutputStream(archivo);

                    oo = new ObjectOutputStream(fo);

                    for (Correo correo : bandejas[i]) {
                        oo.writeObject(correo);
                        oo.flush();
                    }

                    oo.close();
                    fo.close();
                } catch (FileNotFoundException e) {
                }
            }//fin for

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Cuenta> getCuentasSpam() {
        return cuentasSpam;
    }

    public void setCuentasSpam(ArrayList<Cuenta> cuentasSpam) {
        this.cuentasSpam = cuentasSpam;
    }

    public ArrayList<Date[]> getFechasSpam() {
        return fechasSpam;
    }

    public void setFechasSpam(ArrayList<Date[]> fechasSpam) {
        this.fechasSpam = fechasSpam;
    }

    public ArrayList<String> getAsuntoSpam() {
        return asuntoSpam;
    }

    public void setAsuntoSpam(ArrayList<String> asuntoSpam) {
        this.asuntoSpam = asuntoSpam;
    }

    public void cargarChat() {
        try {
            File f = new File("./Cuentas/" + direccionCorreoElectronico + "/Chats.jdf");
            if (f.exists()) {
                FileInputStream fi = new FileInputStream(f);
                ObjectInputStream oi = new ObjectInputStream(fi);
                Chat chat;
                
                chats = new ArrayList<>();
                try {
                    while ((chat = (Chat) oi.readObject()) != null) {
                        chats.add(chat);
                    }
                } catch (EOFException e) {
                }
                oi.close();
                fi.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void escribirChats(){
        try {
            File f = new File("./Cuentas/" + direccionCorreoElectronico + "/Chats.jdf");
            FileOutputStream fo = new FileOutputStream(f, false);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            
            for (Chat chat : chats) {
                oo.writeObject(chat);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
