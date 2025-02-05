/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.service;

import java.util.List;
import server.db.DBBroker;
import server.model.Student;

/**
 *
 * @author Korisnik
 */
public class StudentService {
    
    public List<Student> getAllStudents() {
        DBBroker.getIsntance().connect();
        List<Student> students =  DBBroker.getIsntance().getAllStudents();
        DBBroker.getIsntance().closeConnection();
        
        return students;
    }
    
    public boolean updateStudent(Student student) {
        DBBroker.getIsntance().connect();
        // 
        if(student.getId() == 0) {
            return false;
        }
            
        boolean success = DBBroker.getIsntance().updateStudent(student);
        DBBroker.getIsntance().closeConnection();
        
        return success;
    }
    
    public boolean deleteStudent(Student student) {
        DBBroker.getIsntance().connect();
        boolean success = DBBroker.getIsntance().deleteStudent(student.getId());
        DBBroker.getIsntance().closeConnection();
        
        return success;
    }
    
    // bussiness logic = poslovna logika
//    public boolean addStudent(Student student) {
//        find student in db (vec postoji student sa istim brojem indeksa
//          baci gresku klijentu (broj indeksa vec postoji)
//    
//        ako student ne postoji u bazi
//        onda uradi insert i commit
//        i vrati klijentu poruku da je student sacuvan
//    }
    
}
