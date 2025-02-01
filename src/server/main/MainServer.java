/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.main;

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
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("server is up and running and waiting for client request");
        
        Socket client = serverSocket.accept();
        System.out.println("klijent se konektovao: " + client);
        
        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        
        while (true) {
            System.out.println("(server) cekam da mi klijent posalje operaciju"
                    + "da bih znao sta da radim");
            String operation = ois.readUTF();

            // ovo ce da postane kontroler
            if(operation.equals("getAllStudents")) {
                List<Student> students = DBBroker.getIsntance().getAllStudents();

                // send Response
                oos.writeObject(students);
                oos.flush();
                System.out.println("poslao sam studente klijentu");
            } 
            if(operation.equals("getAllProfesors")) {
                List<Profesor> profesors = DBBroker.getIsntance().getAllProfesors();
                oos.writeObject(profesors);
                oos.flush();
                System.out.println("poslao sam profesore klijentu");
            }
            if(operation.equals("addProfesor")) { 
                Profesor profesor = (Profesor) ois.readObject();
                boolean success = DBBroker.getIsntance().addProfesor(profesor);
                oos.writeBoolean(success);
                oos.flush();
            }
            if(operation.equals("Exit")) {
                System.out.println("klijent se diskonektovao");
            }
        }
    }
}
