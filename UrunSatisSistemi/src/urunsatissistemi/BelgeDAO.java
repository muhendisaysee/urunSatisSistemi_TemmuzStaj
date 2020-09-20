/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunsatissistemi;

import Entity.Belge;
import Entity.Musteri;
import Entity.Urun;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnection;

/**
 *
 * @author ayse
 */
public class BelgeDAO {

    static Musteri aktifMusteri = new Musteri();

    DBConnection db = DBConnection.getDBConnection();
    Connection con = db.connect();
    
       public void musteriBelgeGoster() {
 
        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from belge where musteri_id="+aktifMusteri.getKullanici_id());

            while (rs.next()) {

                Belge belge=new Belge(rs.getInt("belge_id"), rs.getString("belge_baslik"),
                        rs.getString("belge_tutar"), rs.getString("belge_urunler"),
                        rs.getString("belge_kesilenkurum"), rs.getString("belge_turu"));
                E_BelgeController.Musteri_belgeler.add(belge);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
       
       public void arananBelgeGoster(int belge_id) {
 
        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();

        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from belge where belge_id="+belge_id);

            while (rs.next()) {

                Belge belge=new Belge(rs.getInt("belge_id"), rs.getString("belge_baslik"),
                        rs.getString("belge_tutar"), rs.getString("belge_urunler"),
                        rs.getString("belge_kesilenkurum"), rs.getString("belge_turu"));
                E_BelgeController.aranan_belge.add(belge);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
