package chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class usuario extends Thread implements Serializable {

    //private Socket socket;
    private String nombre;

    private ObjectOutputStream salidaObjeto;
    private ObjectInputStream entradaObjeto;

    private String preguntaCliente;
    private String respuestaServer;

    private Object preguntaObjeto;
    private Object respuestaObjeto;

    private Usuarios disponibles;
    private ArrayList<String> lista;

    public usuario(Usuarios disponibles, InputStream is, OutputStream os) {
        super();
        this.disponibles = disponibles;

        try {
            //Salida y entrada de objetos
            salidaObjeto
                    = new ObjectOutputStream(os);
            entradaObjeto
                    = new ObjectInputStream(is);

            nombre = (String) ((Pregunta) entradaObjeto.readObject()).getMensaje();

        } catch (IOException | ClassNotFoundException e) {
        }

    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public void run() {
        try {

            OUTER:
            while (true) {

                int opcion = -1;

                Pregunta pregunta = null;
                try {
                    preguntaObjeto = entradaObjeto.readObject();
                    System.out.println("Se recibió la pregunta");
                    pregunta = (Pregunta) preguntaObjeto;
                    opcion = pregunta.getOpcion();
                } catch (IOException | ClassNotFoundException e) {
                }

                switch (opcion) {
                    case 0: // Enviar Mensaje

                        try {
                            //Enviar mensaje a todos los otros usuarios en la lista de amigos
                            for (usuario temp : disponibles.getUsers()) {
                                if (!temp.equals(this)) {
                                    boolean flag = false;
                                    for (String string : lista) {
                                        if (string.equals(temp.getNombre())) {
                                            flag = true;
                                            break;
                                        }
                                    }
                                    if (flag) {
                                        //Se envía el mensaje a los sockets
                                        temp.salidaObjeto.writeObject(new Pregunta(0, pregunta.getMensaje()));
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case 1: // Cambiar lista de amigos (receptores)
                        System.out.println("revisar");
                        break;
                    case 2:
                        try {
                            lista = pregunta.getLista();
                        } catch (Exception e) {
                        }
                        break;
                    case -1:
                        break OUTER;
                }//Fin switch

            }

            entradaObjeto.close();
            salidaObjeto.close();
        } catch (SocketException e) {
        } catch (IOException ex) {
        }
    }

    public Usuarios getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(Usuarios disponibles) {
        this.disponibles = disponibles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void enviarDisponibles() {
        try {

            ArrayList<String> n = new ArrayList();
            for (usuario user : disponibles.getUsers()) {
                n.add(user.getNombre());
            }
            //Enviamos los amigos disponibles
            salidaObjeto.writeObject(new Pregunta(2, n));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
