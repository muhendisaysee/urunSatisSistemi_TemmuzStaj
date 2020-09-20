/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunsatissistemi;


import Entity.Satici;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author ayse
 */
public class SaticiGirisiController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField email;
    @FXML
    PasswordField sifre;
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void girisKontrol(ActionEvent a) throws SQLException {

        
        String emailText =email.getText();
       String sifreText = sifre.getText();
       System.out.println("E mail : "+ emailText);
//        
//      
        if(emailText.equals("")){
                   JOptionPane.showMessageDialog(null, "EMAİL BOŞ GEÇİLEMEZ!"
                       + " YENİDEN DENEYİNİZ!!");
        }
 
//        else if(sifreText.equals("")){
//              JOptionPane.showMessageDialog(null, "ŞİFRE BOŞ GEÇİLEMEZ!"
//                        + " YENİDEN DENEYİNİZ!!");
//        }
//        else if(emailText.equals("")|| sifreText.equals("")){
//             JOptionPane.showMessageDialog(null, " BOŞ ALAN İLE GEÇİLEMEZ!"
//                        + " YENİDEN DENEYİNİZ!!");
//        }
//        else{
            
        else{
        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();
        boolean girisKontrol = false;
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from satici");

            while (rs.next()) {
                Satici satici = new Satici(rs.getInt("satici_id"), rs.getString("satici_adi_soyadi"), rs.getString("satici_tel"), rs.getString("satici_adres"), rs.getString("satici_email"), rs.getString("satici_sifre"));
                satici.setKullanici_id(rs.getInt("satici_id"));
                satici.setKullanici_ad_soyad(rs.getString("satici_adi_soyadi"));
                satici.setKullanici_adres(rs.getString("satici_adres"));
                satici.setKullanici_telefon(rs.getString("satici_tel"));
                satici.setKullanici_email(rs.getString("satici_email"));
                satici.setKullanici_sifre(rs.getString("satici_sifre"));
                
                if (email.getText().equals(rs.getString("satici_email"))
                        && sifre.getText().equals(rs.getString("satici_sifre"))) {
                    System.out.println("Sisteme giren satici ad, soyad : " + rs.getString("satici_adi_soyadi"));
                    System.out.println("Sisteme giren satici adres : " + rs.getString("satici_adres"));
                    /*--------------------------------------------------*/
                    SaticiUrunDuzenleController.aktifSatici=satici;
                    urunDAO.aktifSatici=satici;
                    SaticiIslemlerController.aktifSatici=satici;
                    /*--------------------------------------------------*/

                    girisKontrol = true;

                    giris(a);
                }
            }
            
            if (girisKontrol == false && !emailText.equals("") && !sifreText.equals("")) {
                JOptionPane.showMessageDialog(null, "Hatali sifre veya kullanici adi !"
                        + " YENİDEN DENEYİNİZ!!");
            }
        } catch (SQLException ex) {
            System.err.println("Hata");
        }
        }

//    }
    }

    public void giris(ActionEvent a) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("SaticiIslemler.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void geri(ActionEvent a) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
