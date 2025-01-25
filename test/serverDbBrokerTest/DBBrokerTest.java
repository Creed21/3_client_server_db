/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverDbBrokerTest;

import java.util.List;
import server.db.DBBroker;
import server.model.Student;

/**
 *
 * @author Korisnik
 */
public class DBBrokerTest {
    public static void main(String[] args) {
        DBBroker dBBroker = DBBroker.getIsntance();
        
        List<Student> students = dBBroker.getAllStudents();
        
        System.out.println(students);
    }
}
