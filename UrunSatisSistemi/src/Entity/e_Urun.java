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
public class e_Urun {
   protected Long id;
   protected String e_urunBaslik;
   protected String e_urunTarih;
   protected String e_urunTutar;
   protected String e_urunKurum;

    public e_Urun(Long id, String e_urunBaslik, String e_urunTarih, String e_urunTutar, String e_urunKurum) {
        this.id = id;
        this.e_urunBaslik = e_urunBaslik;
        this.e_urunTarih = e_urunTarih;
        this.e_urunTutar = e_urunTutar;
        this.e_urunKurum = e_urunKurum;
    }

    public e_Urun() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getE_urunBaslik() {
        return e_urunBaslik;
    }

    public void setE_urunBaslik(String e_urunBaslik) {
        this.e_urunBaslik = e_urunBaslik;
    }

    public String getE_urunTarih() {
        return e_urunTarih;
    }

    public void setE_urunTarih(String e_urunTarih) {
        this.e_urunTarih = e_urunTarih;
    }

    public String getE_urunTutar() {
        return e_urunTutar;
    }

    public void setE_urunTutar(String e_urunTutar) {
        this.e_urunTutar = e_urunTutar;
    }

    public String getE_urunKurum() {
        return e_urunKurum;
    }

    public void setE_urunKurum(String e_urunKurum) {
        this.e_urunKurum = e_urunKurum;
    }
   
   
   
}
