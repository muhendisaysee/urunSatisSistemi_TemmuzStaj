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
public class Satici extends Kullanici{

    public Satici() {
    }

    public Satici(int kullanici_id, String kullanici_ad_soyad, String kullanici_telefon, String kullanici_adres, String kullanici_email, String kullanici_sifre) {
        super(kullanici_id, kullanici_ad_soyad, kullanici_telefon, kullanici_adres, kullanici_email, kullanici_sifre);
    }
    
}
