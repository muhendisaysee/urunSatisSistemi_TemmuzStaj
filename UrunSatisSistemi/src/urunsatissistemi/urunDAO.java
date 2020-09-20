/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunsatissistemi;

import Entity.Satici;
import Entity.Urun;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnection;

/**
 *
 * @author ayse
 */
public class urunDAO {

    static Satici aktifSatici = new Satici();
    DBConnection db = DBConnection.getDBConnection();
    Connection con = db.connect();

  

    public void saticiUrunGoster() {
 
        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();

        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from urun");

            while (rs.next()) {

                Urun urun = new Urun(rs.getInt("urun_id"), rs.getString("urun_adi"),
                        rs.getString("urun_kategorisi"), rs.getString("urun_fiyati"));

                SaticiUrunDuzenleController.saticiUrunler.add(urun);
                SaticiIslemlerController.saticiUrunler.add(urun);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int urun_idCek(String urun_adi, String urun_kategorisi, String urun_fiyati) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select urun_id from urun where urun_adi='" + urun_adi + "' and urun_kategorisi='" + urun_kategorisi + "' and urun_fiyati='" + urun_fiyati + "'");
            while (rs.next()) {
                return rs.getInt("urun_id");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

  
}
