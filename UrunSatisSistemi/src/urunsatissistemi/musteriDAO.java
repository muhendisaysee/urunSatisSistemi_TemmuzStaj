/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunsatissistemi;

import Entity.Musteri;
import Entity.Urun;
import Entity.Urun_musteri;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnection;

/**
 *
 * @author ayse
 */
public class musteriDAO {

    public void urunleriGoster() {

        Urun_musteri urun_musteri;
        DBConnection db = DBConnection.getDBConnection();
        Connection con = db.connect();
        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from musteri");

            while (rs.next()) {
                int urun_id = urun_id_bul(rs.getInt("musteri_id"));
                System.out.println("urun id : " + urun_id);
                urun_musteri = new Urun_musteri(rs.getInt("musteri_id"),
                        rs.getString("musteri_adi_soyadi"), rs.getString("musteri_tel"),
                        rs.getString("musteri_adres"), rs.getString("musteri_email"), 
                        rs.getString("musteri_sifre"), urun_id);
                SaticiMusteriyiDuzenleController.musteri_ve_urun.add(urun_musteri);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
   public void musteriBul(String musteri_tel) {

        Urun_musteri urun_musteri;

        DBConnection db = DBConnection.getDBConnection();
        Connection con = db.connect();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from musteri where musteri_tel='"+musteri_tel+"'");
            while (rs.next()) {
                int urun_id = urun_id_bul(rs.getInt("musteri_id"));
                System.out.println("urun id : " + urun_id);
                urun_musteri = new Urun_musteri(rs.getInt("musteri_id"), rs.getString("musteri_adi_soyadi"), 
                rs.getString("musteri_tel"), rs.getString("musteri_adres"), rs.getString("musteri_email"), rs.getString("musteri_sifre"), urun_id);
                SaticiMusteriyiDuzenleController.aranan_musteri_ve_urun.add(urun_musteri);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public int urun_id_bul(int musteri_id) {
        DBConnection db = DBConnection.getDBConnection();
        Connection con = db.connect();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select urun_id from urun_musteri where musteri_id=" + musteri_id);

            while (rs.next()) {
                // System.out.println(rs.getInt("urun_id"));
                return rs.getInt("urun_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int urunlerin_id_bul(int musteri_id) {
        DBConnection db = DBConnection.getDBConnection();
        Connection con = db.connect();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select urun_id from urun_musteri where musteri_id=" + musteri_id);

            while (rs.next()) {
                // System.out.println(rs.getInt("urun_id"));
                MusteriurunleriGoster(rs.getInt("urun_id"));
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void MusteriurunleriGoster(int urun_id) {
        DBConnection db = DBConnection.getDBConnection();
        Connection con = db.connect();
        System.out.println("Bana gelen id : "+urun_id);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from urun where urun_id=" + urun_id);

            while (rs.next()) {
                  Urun urun = new Urun(rs.getInt("urun_id"), rs.getString("urun_adi"), rs.getString("urun_kategorisi"), rs.getString("urun_fiyati"));

                  MusteriIslemlerController.Musteri_iuyum_urunler.add(urun);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public int id_bul(String musteri_tel) {

        DBConnection db = DBConnection.getDBConnection();
        Connection con = db.connect();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select musteri_id from musteri where musteri_tel='" + musteri_tel + "'");

            while (rs.next()) {

                System.out.println("Müsteri id : " + rs.getInt("musteri_id"));
                return rs.getInt("musteri_id");
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
        return 0;
    }

}
