/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Student implements Serializable {
    private int id;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private int godinaUpisa;
    private int espBodovi;
    private double prosek;

    public Student() {
    }

    public Student(int id, String ime, String prezime, Date datumRodjenja, int godinaUpisa, int espBodovi, double prosek) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.godinaUpisa = godinaUpisa;
        this.espBodovi = espBodovi;
        this.prosek = prosek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(int godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public int getEspBodovi() {
        return espBodovi;
    }

    public void setEspBodovi(int espBodovi) {
        this.espBodovi = espBodovi;
    }

    public double getProsek() {
        return prosek;
    }

    public void setProsek(double prosek) {
        this.prosek = prosek;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", datumRodjenja=" + datumRodjenja + ", godinaUpisa=" + godinaUpisa + ", espBodovi=" + espBodovi + ", prosek=" + prosek + '}';
    }
    
    
    
}
