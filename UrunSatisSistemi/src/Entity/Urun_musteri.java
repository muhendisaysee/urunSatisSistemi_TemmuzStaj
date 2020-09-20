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
public class Urun_musteri {
    private int musteri_id;
    private String musteri_adi_soyadi;
    private String musteri_tel;
    private String musteri_adres;
    private String musteri_email;
    private String musteri_sifre;
    private int urun_id;

    public Urun_musteri() {
    }

    public Urun_musteri(int musteri_id, String musteri_adi_soyadi, String musteri_tel, String musteri_adres, String musteri_email, String musteri_sifre, int urun_id) {
        this.musteri_id = musteri_id;
        this.musteri_adi_soyadi = musteri_adi_soyadi;
        this.musteri_tel = musteri_tel;
        this.musteri_adres = musteri_adres;
        this.musteri_email = musteri_email;
        this.musteri_sifre = musteri_sifre;
        this.urun_id = urun_id;
    }

  
    public int getMusteri_id() {
        return musteri_id;
    }

    public void setMusteri_id(int musteri_id) {
        this.musteri_id = musteri_id;
    }

    public String getMusteri_adi_soyadi() {
        return musteri_adi_soyadi;
    }

    public void setMusteri_adi_soyadi(String musteri_adi_soyadi) {
        this.musteri_adi_soyadi = musteri_adi_soyadi;
    }

    public String getMusteri_tel() {
        return musteri_tel;
    }

    public void setMusteri_tel(String musteri_tel) {
        this.musteri_tel = musteri_tel;
    }

    public String getMusteri_adres() {
        return musteri_adres;
    }

    public void setMusteri_adres(String musteri_adres) {
        this.musteri_adres = musteri_adres;
    }

    public String getMusteri_email() {
        return musteri_email;
    }

    public void setMusteri_email(String musteri_email) {
        this.musteri_email = musteri_email;
    }

    public String getMusteri_sifre() {
        return musteri_sifre;
    }

    public void setMusteri_sifre(String musteri_sifre) {
        this.musteri_sifre = musteri_sifre;
    }

    public int getUrun_id() {
        return urun_id;
    }

    public void setUrun_id(int urun_id) {
        this.urun_id = urun_id;
    }

    
    
    
    
    
}
