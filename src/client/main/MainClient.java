/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import server.model.Student;

/**
 *
 * @author Korisnik
 */
public class MainClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 5555);
        
        System.out.println("Konektovao sam se na server");
        
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        // get all studentsa
        // insert new student
        
        // da bi ja na neki nacin reko serveru sta hocu od njega
        // ja treba da mu kazem koju operaciju / akciju / metodu da on isoristi
        oos.writeUTF("getAllStudents");
        oos.flush();
        
        List<Student> students = (List<Student>) ois.readObject();
        
        System.out.println("primio sam studente sa servera:");
        System.out.println(students);
    }
}
