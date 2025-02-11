/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.model.Profesor;
import server.model.Student;

/**
 *
 * @author Korisnik
 */
public class DBBroker {
    
    private static DBBroker instance;
    
    private String url;
    private String username;
    private String password;
    
    private Connection connection;
    
    // private konstruktor
    private DBBroker() {
        url = "jdbc:mysql://localhost:3306/test";
        username = "root";
        password = "";
        
    }
    
    // javna metoda koja vraca instancu
    public static DBBroker getIsntance() {
        if(instance == null)
            instance = new DBBroker();
        return instance;
    }
    
    public boolean connect() {
        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Uspena konekcija nad bazom!");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean closeConnection() {
        if(connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    // vrati sve studente
    public List<Student> getAllStudents() throws RuntimeException {
        List<Student> students = new ArrayList<>();
        
        String query = "SELECT * FROM student";
        
       
        try(Statement statement = connection.createStatement()) {
            // ovo je kao red u tabeli iz baze
            // kao da smo ucitali tabelu u javu
            // i sada treba da parsiramo te podatke i da od njih napravimo java objekte
            ResultSet resultSet = statement.executeQuery(query);
            
            // na pocetku kada dobijem result set kao da je brojac(kursos) postavljen na poziciju -1
            // i da bi mi mogli da pristupimo prvom redu, moramo da se pomerimo na poziciju 0
            // tu nam pomaze metoda while + next() -> pomera kursor na novi red ( dok ima redova)
            while(resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setIme(resultSet.getString("ime"));
                student.setPrezime(resultSet.getString("prezime"));
                student.setDatumRodjenja(resultSet.getDate("datum_rodjenja"));
                student.setGodinaUpisa(resultSet.getInt("godina_upisa"));
                student.setEspBodovi(resultSet.getInt("esp_bodovi"));
                student.setProsek(resultSet.getDouble("prosek"));
                
                // provera 
                // system out print line -> debagovanje za siromasne xD
                System.out.println("izvuko sam studenta iz baze: " + student);
                
                students.add(student);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        return students;
    }
    
    public boolean updateStudent(Student student) {
        String query = "UPDATE student SET ime = ?, prezime = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, student.getIme());
            ps.setString(2, student.getPrezime());
            ps.setInt(3, student.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
     public boolean deleteStudent(int id) {
        String query = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        return false;
    }
    
    // pitanje za intervju: objasni finally, final //, finalize()
    public List<Profesor> getAllProfesors() {
        List<Profesor> profesors = new ArrayList<>();
        String upit = "select * from profesor";
        
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(upit);
            
            while(resultSet.next()) {
                Profesor profesor = new Profesor();
                profesor.setId(resultSet.getInt("id"));
                profesor.setIme(resultSet.getString("ime"));
                profesor.setIme(resultSet.getString("prezime"));
                profesor.setDatumRodjenja(resultSet.getDate("datum_rodjenja"));
                
                profesors.add(profesor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } 
        
        return profesors;
    }
    
    // insert new profesor in db
    public boolean addProfesor(Profesor profesor) {
        String query = "insert into profesor(ime, prezime, datum_rodjenja)"
                + " values(?, ?, ?)";
        
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, profesor.getIme());
            preparedStatement.setString(2, profesor.getPrezime());
            preparedStatement.setDate(3, new java.sql.Date(profesor.getDatumRodjenja().getTime()));
            
            int row = preparedStatement.executeUpdate();
            
//            if(row == 1) { // uspesno smo dodali red
//                return true;
//            } else {
//                return false;
//            }
            return row > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
}
