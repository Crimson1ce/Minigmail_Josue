package minigmail;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Cuenta implements Serializable {

    static final int BANDEJA_RECIBIDOS = 0;
    static final int BANDEJA_ENVIADOS = 1;
    static final int BANDEJA_ELIMINADOS = 2;
    static final int BANDEJA_BORRADORES = 3;
    static final int BANDEJA_SPAM = 4;

    private String direccionCorreoElectronico;
    private String nombre;
    private String apellido;
    private String clave;
    private Date fechaDeCreacion;
    private DefaultTreeModel modelo_bandejas;
    private DefaultMutableTreeNode recibidos;
    private DefaultMutableTreeNode enviados;
    private DefaultMutableTreeNode eliminados;
    private DefaultMutableTreeNode borradores;
    private DefaultMutableTreeNode spam;

    private DefaultMutableTreeNode[] nodos;

    public static final long SerialVersionUID = 1L;

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

    public DefaultTreeModel getModelo_bandejas() {
        return modelo_bandejas;
    }

    public void setModelo_bandejas(DefaultTreeModel modelo_bandejas) {
        this.modelo_bandejas = modelo_bandejas;
    }

    public DefaultMutableTreeNode getRecibidos() {
        return recibidos;
    }

    public void setRecibidos(DefaultMutableTreeNode recibidos) {
        this.recibidos = recibidos;
    }

    public void setRecibido(Correo recibido) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(recibido);
        recibidos.add(node);
    }

    public DefaultMutableTreeNode getEnviados() {
        return enviados;
    }

    public void setEnviados(DefaultMutableTreeNode enviados) {
        this.enviados = enviados;
    }

    public DefaultMutableTreeNode getEliminados() {
        return eliminados;
    }

    public void setEliminados(DefaultMutableTreeNode eliminados) {
        this.eliminados = eliminados;
    }

    public DefaultMutableTreeNode getBorradores() {
        return borradores;
    }

    public void setBorradores(DefaultMutableTreeNode borradores) {
        this.borradores = borradores;
    }

    public DefaultMutableTreeNode getSpam() {
        return spam;
    }

    public void setSpam(DefaultMutableTreeNode spam) {
        this.spam = spam;
    }

    @Override
    public String toString() {
        return direccionCorreoElectronico;
    }

    private void initTree() {
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode();
        recibidos = new DefaultMutableTreeNode("Recibidos");
        enviados = new DefaultMutableTreeNode("Enviados");
        eliminados = new DefaultMutableTreeNode("Eliminados");
        borradores = new DefaultMutableTreeNode("Borradores");
        spam = new DefaultMutableTreeNode("Spam");

        nodos = new DefaultMutableTreeNode[]{enviados, recibidos, eliminados, borradores, spam};

        modelo_bandejas = new DefaultTreeModel(raiz);

        raiz.add(recibidos);    //0
        raiz.add(enviados);     //1
        raiz.add(eliminados);   //2
        raiz.add(borradores);   //3
        raiz.add(spam);         //4
    }

    public void cargarCorreos() {

        File archivo = null;
        FileInputStream fi = null;
        ObjectInputStream oi = null;

        try {

            for (DefaultMutableTreeNode nodo : nodos) {
                //int n = 0;
                String p = "./Correos/" + direccionCorreoElectronico;
                archivo = new File(p + "/" + nodo.getUserObject() + ".aaa");
                //n++;
                if (archivo.exists()) {

                    fi = new FileInputStream(archivo);
                    oi = new ObjectInputStream(fi);
                    Correo c = null;
                    
                    try {
                        c = (Correo) oi.readObject();
                        while (!c.equals(null)) {
                            //System.out.println(n);
                            DefaultMutableTreeNode correo = new DefaultMutableTreeNode(c);
                            nodo.add(correo);
                            c = (Correo) oi.readObject();
                        }//fin while
                    } catch (EOFException e) {
                    } catch (NullPointerException e){
                        System.out.println("No hay correos.");
                    } catch (Exception e){
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

            for (DefaultMutableTreeNode nodo : nodos) {
                archivo = new File("./Correos/" + direccionCorreoElectronico + "/" + nodo.getUserObject() + ".aaa");

                fo = new FileOutputStream(archivo);
                oo = new ObjectOutputStream(fo);

                for (int i = 0; i < nodo.getChildCount(); i++) {
                    oo.writeObject((Correo) ((DefaultMutableTreeNode) nodo.getChildAt(i)).getUserObject());
                }

                oo.close();
                fo.close();
            }//fin for

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
