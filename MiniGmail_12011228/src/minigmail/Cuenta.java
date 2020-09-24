package minigmail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.tree.DefaultMutableTreeNode;

public class Cuenta implements Serializable {

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

    private ArrayList<Chat> chats;
    private ArrayList<GrupoContactos> grupos;
    private ArrayList<Tarea> tareas;
    private ArrayList<Filtro> filtros;

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

    public void setDireccionCorreoElectronico(String direccionCorreoElectronico) {
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
        boolean flag = true;
        for (Filtro filtro : filtros) {
            if (filtro instanceof FiltroAsunto) {
                if (recibido.getAsunto().contains(((FiltroAsunto) filtro).getPalabras().toUpperCase())) {
                    flag = false;
                    break;
                }
            } else if (filtro instanceof FiltroCuenta) {
                if (recibido.getEmisor().getDireccionCorreoElectronico().equals(((FiltroCuenta) filtro).getDireccionCorreo())) {
                    flag = false;
                    break;
                }
            } else if (filtro instanceof FiltroHora) {
                int m = recibido.getFechaEnvio().getMinutes();
                int h = recibido.getFechaEnvio().getHours();
                FiltroHora f = (FiltroHora) filtro;
                if ((h > f.getHoraInicio() && h < f.getHoraFinal())
                        ||
                        (h == f.getHoraInicio() && m >= f.getMinutoInicio())
                        ||
                        (h == f.getHoraFinal() && m <= f.getMinutoFinal())
                    ) {
                    if (h <= f.getHoraFinal()) {
                        if (m <= f.getMinutoFinal()) {//Entra al filtro
                            flag = false;
                            break;
                        }
                    }
                }
            }
        }
        if (flag) {
            recibidos.add(recibido);
        } else {
            spam.add(recibido);
        }
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

    public ArrayList<GrupoContactos> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<GrupoContactos> grupos) {
        this.grupos = grupos;
    }

    public ArrayList<Filtro> getFiltros() {
        return filtros;
    }

    public void setFiltros(ArrayList<Filtro> filtros) {
        this.filtros = filtros;
    }

    @Override
    public String toString() {
        return direccionCorreoElectronico;
    }

    private void initTree() {
        recibidos = new ArrayList();
        enviados = new ArrayList();
        eliminados = new ArrayList();
        borradores = new ArrayList();
        spam = new ArrayList();

        bandejas = new ArrayList[]{enviados, recibidos, eliminados, borradores, spam};
        carpetas = new String[]{"Enviados", "Recibidos", "Eliminados", "Borradores", "Spam"};

        chats = new ArrayList<>();
        grupos = new ArrayList<>();
        tareas = new ArrayList<>();
        filtros = new ArrayList<>();
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
                        //e.printStackTrace();
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

    public void cargarRecibidos() {
        File archivo = null;
        FileInputStream fi = null;
        ObjectInputStream oi = null;

        try {

            archivo = new File("./Cuentas/" + direccionCorreoElectronico + "/Recibidos.aaa");

            if (archivo.exists()) {

                fi = new FileInputStream(archivo);
                oi = new ObjectInputStream(fi);
                Correo c;

                recibidos = new ArrayList<>();
                try {
                    while ((c = (Correo) oi.readObject()) != null) {
                        recibidos.add(c);
                    }//fin while
                } catch (EOFException e) {
                } catch (NullPointerException e) {
                    //e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                oi.close();
                fi.close();

            }//Fin if exists

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escribirRecibidos() {
        File archivo = null;
        FileOutputStream fo = null;
        ObjectOutputStream oo = null;

        try {

            archivo = new File("./Cuentas/" + direccionCorreoElectronico + "/Recibidos.aaa");

            fo = new FileOutputStream(archivo, false);
            oo = new ObjectOutputStream(fo);
            try {
                for (Correo recibido : recibidos) {
                    oo.writeObject(recibido);
                    oo.flush();
                }

            } catch (EOFException e) {
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void cargarChats() {
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

    public void escribirChats() {
        try {
            File f = new File("./Cuentas/" + direccionCorreoElectronico + "/Chats.jdf");
            FileOutputStream fo = new FileOutputStream(f, false);
            ObjectOutputStream oo = new ObjectOutputStream(fo);

            for (Chat chat : chats) {
                oo.writeObject(chat);
                oo.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarGrupos() {

        grupos = new ArrayList<>();

        //listar        
        Dba db = new Dba("./Base2.accdb");
        db.conectar();
        try {
            db.query.execute("select DireccionCorreo,NombreGrupo from Grupos");
            ResultSet rs = db.query.getResultSet();
            while (rs.next()) {
                try {
                    if (rs.getString(1).equals(direccionCorreoElectronico)) {
                        grupos.add(new GrupoContactos(rs.getString(2)));
                    }
                } catch (Exception e) {
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        db.desconectar();

        for (GrupoContactos grupo : grupos) {

            try {
                File f = new File("./Cuentas/" + direccionCorreoElectronico + "/Grupos/" + grupo.getNombreGrupo() + ".txt");
                if (f.exists()) {
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String direccion;
                    try {
                        while ((direccion = br.readLine()) != null) {
                            grupo.getContactos().add(direccion);
                        }
                    } catch (EOFException e) {
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void agregarGrupo(String nombre) {

        Dba db = new Dba("./Base2.accdb");
        db.conectar();
        try {
            String n;

            db.query.execute("INSERT INTO Grupos"
                    + " (DireccionCorreo,NombreGrupo)"
                    + " VALUES ('" + direccionCorreoElectronico + "', '" + nombre + "')");
            db.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        db.desconectar();

        grupos.add(new GrupoContactos(nombre));

        File f = new File("./Cuentas/" + direccionCorreoElectronico + "/Grupos/" + grupos.get(grupos.size() - 1).getNombreGrupo() + ".txt");
        try {
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(direccionCorreoElectronico);
            bw.newLine();

            bw.flush();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void escribirGrupos() {
        for (GrupoContactos grupo : grupos) {
            File f = new File("./Cuentas/" + direccionCorreoElectronico + "/Grupos/" + grupo.getNombreGrupo() + ".txt");
            try {
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);

                for (String contacto : grupo.getContactos()) {
                    bw.write(contacto);
                    bw.newLine();
                }

            } catch (Exception e) {
            }
        }
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    public void cargarTareas() {
        try {
            File f = new File("./Cuentas/" + direccionCorreoElectronico + "/Tareas.jos");
            if (f.exists()) {

                FileInputStream fi = new FileInputStream(f);
                ObjectInputStream oi = new ObjectInputStream(fi);
                Tarea tarea;

                tareas = new ArrayList<>();
                try {
                    while ((tarea = (Tarea) oi.readObject()) != null) {
                        tareas.add(tarea);
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

    public void escribirTareas() {
        try {
            File f = new File("./Cuentas/" + direccionCorreoElectronico + "/Tareas.jos");
            FileOutputStream fo = new FileOutputStream(f, false);
            ObjectOutputStream oo = new ObjectOutputStream(fo);

            for (Tarea tarea : tareas) {
                oo.writeObject(tarea);
                oo.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarFiltros() {
        try {
            File f = new File("./Cuentas/" + direccionCorreoElectronico + "/Filtros.kms");
            if (f.exists()) {

                FileInputStream fi = new FileInputStream(f);
                ObjectInputStream oi = new ObjectInputStream(fi);
                Filtro filtro;

                filtros = new ArrayList<>();
                try {
                    while ((filtro = (Filtro) oi.readObject()) != null) {
                        filtros.add(filtro);
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

    public void escribirFiltros() {
        try {
            File f = new File("./Cuentas/" + direccionCorreoElectronico + "/Filtros.kms");
            FileOutputStream fo = new FileOutputStream(f, false);
            ObjectOutputStream oo = new ObjectOutputStream(fo);

            for (Filtro filtro : filtros) {
                oo.writeObject(filtro);
                oo.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
