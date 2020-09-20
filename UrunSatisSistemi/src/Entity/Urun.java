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
public class Urun {
    private int urun_id;
    private String urun_adi;
    private String urun_kategorisi;
    private String urun_fiyati;

    public Urun() {
    }

    public Urun(int urun_id, String urun_adi, String urun_kategorisi, String urun_fiyati) {
        this.urun_id = urun_id;
        this.urun_adi = urun_adi;
        this.urun_kategorisi = urun_kategorisi;
        this.urun_fiyati = urun_fiyati;
    }

    public int getUrun_id() {
        return urun_id;
    }

    public void setUrun_id(int urun_id) {
        this.urun_id = urun_id;
    }

    public String getUrun_adi() {
        return urun_adi;
    }

    public void setUrun_adi(String urun_adi) {
        this.urun_adi = urun_adi;
    }

    public String getUrun_kategorisi() {
        return urun_kategorisi;
    }

    public void setUrun_kategorisi(String urun_kategorisi) {
        this.urun_kategorisi = urun_kategorisi;
    }

    public String getUrun_fiyati() {
        return urun_fiyati;
    }

    public void setUrun_fiyati(String urun_fiyati) {
        this.urun_fiyati = urun_fiyati;
    }
    
    
    
}
