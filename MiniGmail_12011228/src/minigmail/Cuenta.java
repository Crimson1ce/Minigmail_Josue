package minigmail;

import java.util.Date;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Cuenta {

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
    
    private void initTree(){
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode();
        recibidos = new DefaultMutableTreeNode();
        enviados = new DefaultMutableTreeNode();
        eliminados = new DefaultMutableTreeNode();
        borradores = new DefaultMutableTreeNode();
        spam = new DefaultMutableTreeNode();
        
        modelo_bandejas = new DefaultTreeModel(raiz);

        raiz.add(recibidos);    //0
        raiz.add(enviados);     //1
        raiz.add(eliminados);   //2
        raiz.add(borradores);   //3
        raiz.add(spam);         //4
    }

 }
