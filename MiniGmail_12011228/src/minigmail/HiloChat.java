package minigmail;

import chat.Pregunta;
import chat.Usuarios;
import chat.usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextArea;

public class HiloChat extends Thread{
    private JTextArea bitacora;
    private JComboBox cbox;
    private JList lista;
    
    private ObjectInputStream entradaObjeto;
    
    

    public HiloChat(JTextArea bitacora, JComboBox cbox, JList lista) {
        super();
        this.bitacora = bitacora;
        this.cbox = cbox;
        this.lista = lista;
        
    }

    public ObjectInputStream getEntradaObjeto() {
        return entradaObjeto;
    }

    public void setEntradaObjeto(ObjectInputStream entradaObjeto) {
        this.entradaObjeto = entradaObjeto;
    }
    
    private void escribirTexto(String texto){
        DateFormat df=
                new SimpleDateFormat("YYYY/MM/DD hh:mm:ss - ");
         bitacora.append(df.format(new Date()) + texto +"\n");
        }
    
    
    @Override
    public void run() {
        try{
            while(true){
                Pregunta respuesta = (Pregunta)entradaObjeto.readObject();
                int opcion = respuesta.getOpcion();
                
                switch(opcion){
                    case 0://Recibir mensaje
                        escribirTexto(respuesta.getMensaje());
                        break;
                    case 1://Establecer los usuarios disponibles
                        Usuarios todos = respuesta.getUsers();
                        break;
                    case 2:
                        ArrayList<String> arr = respuesta.getLista();
                        establecerUsuarios(arr);
                        break;
                    default:
                        javax.swing.JOptionPane.showMessageDialog(null, "error");
                }
                
                //escribirTexto(respuestaServer);
            }        
        } catch (Exception e) {
        }
    }

    private void establecerUsuarios(ArrayList<String> todos) {
        DefaultComboBoxModel model = (DefaultComboBoxModel)cbox.getModel();
        model.removeAllElements();
        
        for (String user : todos) {
            model.addElement(user);
            //System.out.println("HolaUsersCB");
        }
        
        cbox.setModel(model);
        lista.setModel(new DefaultListModel());

    }
    
}
