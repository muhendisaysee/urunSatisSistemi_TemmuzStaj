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
public class Belge {
    private int belge_id;
    private String belge_baslik;
    private String belge_tutar;
    private String belge_urunler;
    private String belge_kesilenkurum;
    private String belge_turu;

    public Belge() {
    }

    public Belge(int belge_id, String belge_baslik, String belge_tutar, String belge_urunler, String belge_kesilenkurum, String belge_turu) {
        this.belge_id = belge_id;
        this.belge_baslik = belge_baslik;
        this.belge_tutar = belge_tutar;
        this.belge_urunler = belge_urunler;
        this.belge_kesilenkurum = belge_kesilenkurum;
        this.belge_turu = belge_turu;
    }

    public String getBelge_kesilenkurum() {
        return belge_kesilenkurum;
    }

    public void setBelge_kesilenkurum(String belge_kesilenkurum) {
        this.belge_kesilenkurum = belge_kesilenkurum;
    }


    public int getBelge_id() {
        return belge_id;
    }

    public void setBelge_id(int belge_id) {
        this.belge_id = belge_id;
    }

    public String getBelge_baslik() {
        return belge_baslik;
    }

    public void setBelge_baslik(String belge_baslik) {
        this.belge_baslik = belge_baslik;
    }

    public String getBelge_tutar() {
        return belge_tutar;
    }

    public void setBelge_tutar(String belge_tutar) {
        this.belge_tutar = belge_tutar;
    }

    public String getBelge_urunler() {
        return belge_urunler;
    }

    public void setBelge_urunler(String belge_urunler) {
        this.belge_urunler = belge_urunler;
    }

    public String getBelge_turu() {
        return belge_turu;
    }

    public void setBelge_turu(String belge_turu) {
        this.belge_turu = belge_turu;
    }
    
    
    
    
}
