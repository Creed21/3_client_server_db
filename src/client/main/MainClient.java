/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import server.model.Profesor;
import server.model.Student;

/**
 *
 * @author Korisnik
 */
public class MainClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("CLIENT: automatski se pokrene jedna nit kada se pokrene main metoda"
                +Thread.currentThread());
        
        Socket socket = new Socket("localhost", 5555);
        
        System.out.println("Konektovao sam se na server");
        
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        // ovo cudo mi sluzi da unesem na tastaturi podatke
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true) {
            System.out.println("\nodaberi koju operaciju saljes serveru (iskopiraj ime operacije)");
            System.out.println("1. getAllStudents");
            System.out.println("2. getAllProfesors");
            System.out.println("3. addProfesor");
            System.out.println("0. Exit");
            System.out.print("vas izbor: ");
            
            String operation = br.readLine();
            // da bi ja na neki nacin reko serveru sta hocu od njega
            // ja treba da mu kazem koju operaciju / akciju / metodu da on isoristi
            

            switch(operation) {
                case "getAllStudents": {
                    oos.writeUTF("getAllStudents");
                    oos.flush();

                    List<Student> students = (List<Student>) ois.readObject();

                    System.out.println("primio sam studente sa servera:");
                    System.out.println(students);
                    break;
                }
                case "getAllProfesors" : {
                    oos.writeUTF("getAllProfesors");
                    oos.flush();

                    List<Profesor> profesors = (List<Profesor>) ois.readObject();
                    System.out.println("primio sma profesore sa servera:");
                    System.out.println(profesors);
                    break;
                }
                case "addProfesor": {
                    oos.writeUTF("addProfesor");
                    oos.flush();

                    Profesor profesor = new Profesor();
                    profesor.setIme("aca");
                    profesor.setPrezime("janko");
                    profesor.setDatumRodjenja(new Date(1995 - 1900, 10, 29));

                    oos.writeObject(profesor);
                    oos.flush();

                    boolean success = ois.readBoolean();

                    if(success)
                        System.out.println("profesor je uspesno dodat");
                    else
                        System.out.println("GRESKA pri dodavanju profesora");
                }
                case "Exit" : {
                    oos.writeUTF("Exit");
                    oos.flush();

                    socket.close();
                }
            }
        }
    }
}
