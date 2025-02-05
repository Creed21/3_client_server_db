/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.main;

import server.controller.Controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import server.db.DBBroker;
import server.db.DBBroker;
import server.model.Profesor;
import server.model.Student;

/**
 *
 * @author Korisnik
 */

public class MainServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        System.out.println("SERVER: automatski se pokrene jedna nit kada se pokrene main metoda"
                +Thread.currentThread());
        
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("server is up and running and waiting for client request");
        
        Socket client = serverSocket.accept();
        System.out.println("klijent se konektovao: " + client);
        
        Controller controller = new Controller(client);
        
        while (true) {
            controller.comunicate();
        }
    }
}
