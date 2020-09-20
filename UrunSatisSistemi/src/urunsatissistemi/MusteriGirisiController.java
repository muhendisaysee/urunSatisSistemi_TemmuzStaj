/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunsatissistemi;

import Entity.Musteri;
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
public class MusteriGirisiController implements Initializable {

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

        String emailText = email.getText();
        String sifreText = sifre.getText();
        System.out.println("E mail : " + emailText);
//        
//      
        if (emailText.equals("")) {
            JOptionPane.showMessageDialog(null, "EMAİL BOŞ GEÇİLEMEZ!"
                    + " YENİDEN DENEYİNİZ!!");
        }

        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();
        boolean girisKontrol = false;
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from musteri");

            while (rs.next()) {
                Musteri musteri = new Musteri(rs.getInt("musteri_id"), rs.getString("musteri_adi_soyadi"), rs.getString("musteri_tel"), rs.getString("musteri_adres"), rs.getString("musteri_email"), rs.getString("musteri_sifre"));
                musteri.setKullanici_id(rs.getInt("musteri_id"));
                musteri.setKullanici_ad_soyad(rs.getString("musteri_adi_soyadi"));
                musteri.setKullanici_adres(rs.getString("musteri_adres"));
                musteri.setKullanici_telefon(rs.getString("musteri_tel"));
                musteri.setKullanici_email(rs.getString("musteri_email"));
                musteri.setKullanici_sifre(rs.getString("musteri_sifre"));
                if (email.getText().equals(rs.getString("musteri_email"))
                        && sifre.getText().equals(rs.getString("musteri_sifre"))) {
                    System.out.println("Sisteme giren müşteri ad, soyad : " + rs.getString("musteri_adi_soyadi"));
                    System.out.println("Sisteme giren müşteri adres : " + rs.getString("musteri_adres"));
                    MusteriIslemlerController.aktifMusteri=musteri;
                    IletisimController.aktifMusteri=musteri;
                    E_BelgeController.aktifMusteri=musteri;
                    BelgeDAO.aktifMusteri=musteri;
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

    public void giris(ActionEvent a) {

        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("MusteriIslemler.fxml"));
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
