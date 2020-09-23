package server;

import chat.usuario;
import chat.Usuarios;
import java.net.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ServidorP {

    public static void main(String[] args) throws IOException {
        ServerSocket socketServer = null;
        Socket socketCliente = null;
        Usuarios todos = new Usuarios();
        try {
            socketServer = new ServerSocket(4900);
            System.out.println("Server Listo\nEsperando un Usuario");
            
            while (true) {
                socketCliente = socketServer.accept();
                System.out.println("Se conecto un Usuario......");
                todos.getUsers().add(new usuario(todos, socketCliente.getInputStream(), socketCliente.getOutputStream()));
                todos.getUsers().get(todos.getUsers().size() - 1).start();

                
                for (usuario user : todos.getUsers()) {
                    try {
                        if (!user.isAlive()) {//.isInterrupted()) {
                            todos.getUsers().remove(user);
                        }
                    } catch (Exception e) {
                    }
                }

                for (usuario user : todos.getUsers()) {
                    user.setDisponibles(todos);
                    user.enviarDisponibles();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socketServer.close();
        } catch (Exception e) {
        }
    }
}
