/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunsatissistemi;

import Entity.Musteri;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author ayse
 */
public class YeniKayitController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField adSoyad;
    @FXML
    TextField adres;
    @FXML
    TextField email;
    @FXML
    TextField telefon;
    @FXML
    TextField sifre;
    @FXML
    public ComboBox<String> musteri_satici = new ComboBox();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        musteri_satici.getItems().add("Müşteri");
        musteri_satici.getItems().add("Satıcı");

    }

    public void yeni_kaydet() {

        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();

        String adSoyadText = adSoyad.getText();
        String adresText = adres.getText();
        String emailText = email.getText();
        String telefonText = telefon.getText();
        String sifreText = sifre.getText();
        String musteri_satici_text = musteri_satici.getValue();
        if (adSoyadText.equals("") || adresText.equals("") || emailText.equals("") || telefonText.equals("") || sifreText.equals("")) {
            JOptionPane.showMessageDialog(null, " BOŞ ALAN BIRAKMAYINIZ!"
                    + " YENİDEN DENEYİNİZ!!");
        } else {
            if (musteri_satici_text.equals("Müşteri")) {
                try {
                    Statement st = c.createStatement();
                    st.executeUpdate("insert into musteri(musteri_adi_soyadi,musteri_tel,musteri_adres,musteri_email,musteri_sifre) values('" + adSoyadText + "','" + telefonText + "','" + adresText + "','" + emailText + "','" + sifreText + "')");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else if (musteri_satici_text.equals("Satıcı")) {
                try {
                    Statement st = c.createStatement();
                    st.executeUpdate("insert into satici(satici_adi_soyadi,satici_tel,satici_adres,satici_email,satici_sifre) values('" + adSoyadText + "','" + telefonText + "','" + adresText + "','" + emailText + "','" + sifreText + "')");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

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
