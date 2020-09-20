/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author ayse
 */
public class Kullanici {
    protected int kullanici_id;
    protected String kullanici_ad_soyad;
    protected String kullanici_telefon;
    protected String kullanici_adres;
    protected String kullanici_email;
    protected String kullanici_sifre;

    public Kullanici() {
    }

    public Kullanici(int kullanici_id, String kullanici_ad_soyad, String kullanici_telefon, String kullanici_adres, String kullanici_email, String kullanici_sifre) {
        this.kullanici_id = kullanici_id;
        this.kullanici_ad_soyad = kullanici_ad_soyad;
        this.kullanici_telefon = kullanici_telefon;
        this.kullanici_adres = kullanici_adres;
        this.kullanici_email = kullanici_email;
        this.kullanici_sifre = kullanici_sifre;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getKullanici_ad_soyad() {
        return kullanici_ad_soyad;
    }

    public void setKullanici_ad_soyad(String kullanici_ad_soyad) {
        this.kullanici_ad_soyad = kullanici_ad_soyad;
    }

    public String getKullanici_telefon() {
        return kullanici_telefon;
    }

    public void setKullanici_telefon(String kullanici_telefon) {
        this.kullanici_telefon = kullanici_telefon;
    }

    public String getKullanici_adres() {
        return kullanici_adres;
    }

    public void setKullanici_adres(String kullanici_adres) {
        this.kullanici_adres = kullanici_adres;
    }

    public String getKullanici_email() {
        return kullanici_email;
    }

    public void setKullanici_email(String kullanici_email) {
        this.kullanici_email = kullanici_email;
    }

    public String getKullanici_sifre() {
        return kullanici_sifre;
    }

    public void setKullanici_sifre(String kullanici_sifre) {
        this.kullanici_sifre = kullanici_sifre;
    }
    
    
    
}
