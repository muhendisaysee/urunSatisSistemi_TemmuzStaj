/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunsatissistemi;

import Entity.Urun;
import Entity.Urun_musteri;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static urunsatissistemi.SaticiIslemlerController.saticiUrunler;
import static urunsatissistemi.SaticiUrunDuzenleController.saticiUrunler;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author ayse
 */
public class SaticiMusteriyiDuzenleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField musteri_adi_soyadi;
    @FXML
    TextField musteri_tel;
    @FXML
    TextField musteri_adres;
    @FXML
    TextField musteri_email;
    @FXML
    TextField musteri_sifre;
    @FXML
    TextField guncel_deger;
    @FXML
    TextField guncellenecek_id;
    public static List<Urun_musteri> musteri_ve_urun = new ArrayList();
    public static List<Urun_musteri> aranan_musteri_ve_urun = new ArrayList();
    @FXML
    public ComboBox<String> urun_kategorisi = new ComboBox();
    @FXML
    public ComboBox<String> guncellenecek_parametre = new ComboBox();
    @FXML
    private TableView<Urun_musteri> urun_musteriTableView;
    @FXML
    private TableColumn<Urun_musteri, Long> musteri_idColumn;
    @FXML
    private TableColumn<Urun_musteri, String> musteri_adi_soyadiColumn;
    @FXML
    private TableColumn<Urun_musteri, String> musteri_telColumn;
    @FXML
    private TableColumn<Urun_musteri, String> musteri_adresColumn;
    @FXML
    private TableColumn<Urun_musteri, String> musteri_emailColumn;
    @FXML
    private TableColumn<Urun_musteri, String> musteri_sifreColumn;
    @FXML
    private TableColumn<Urun_musteri, Long> urun_idColumn;
    @FXML
    private TableView<Urun_musteri> aranan_urun_musteriTableView;
    @FXML
    private TableColumn<Urun_musteri, Long> aranan_musteri_idColumn;
    @FXML
    private TableColumn<Urun_musteri, String> aranan_musteri_adi_soyadiColumn;
    @FXML
    private TableColumn<Urun_musteri, String> aranan_musteri_telColumn;
    @FXML
    private TableColumn<Urun_musteri, String> aranan_musteri_adresColumn;
    @FXML
    private TableColumn<Urun_musteri, String> aranan_musteri_emailColumn;
    @FXML
    private TableColumn<Urun_musteri, String> aranan_musteri_sifreColumn;
    @FXML
    private TableColumn<Urun_musteri, Long> aranan_urun_idColumn;
    @FXML
    TextField aranan_musteri_telefon;
    musteriDAO musteriDAO = new musteriDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        urun_kategorisi.getItems().add("e-Arşiv");
        urun_kategorisi.getItems().add("e-Fatura");
        urun_kategorisi.getItems().add("e-Defter");
        urun_kategorisi.getItems().add("e-İrsaliye");
        urun_kategorisi.getItems().add("e-Makbuz");

        guncellenecek_parametre.getItems().add("Müşteri Adı Soyadı");
        guncellenecek_parametre.getItems().add("Müşteri Telefon");
        guncellenecek_parametre.getItems().add("Müşteri Adres");
        guncellenecek_parametre.getItems().add("Müşteri E-Mail");
        guncellenecek_parametre.getItems().add("Müşteri Şifre");

        musteri_ve_urun.removeAll(musteri_ve_urun);
        musteriDAO.urunleriGoster();
        urun_musteriTableView.getItems().setAll(musteri_ve_urun);

        musteri_idColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, Long>("musteri_id"));
        musteri_adi_soyadiColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, String>("musteri_adi_soyadi"));
        musteri_telColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, String>("musteri_tel"));
        musteri_adresColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, String>("musteri_adres"));
        musteri_emailColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, String>("musteri_email"));
        musteri_sifreColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, String>("musteri_sifre"));
        urun_idColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, Long>("urun_id"));

        aranan_musteri_idColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, Long>("musteri_id"));
        aranan_musteri_adi_soyadiColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, String>("musteri_adi_soyadi"));
        aranan_musteri_telColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, String>("musteri_tel"));
        aranan_musteri_adresColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, String>("musteri_adres"));
        aranan_musteri_emailColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, String>("musteri_email"));
        aranan_musteri_sifreColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, String>("musteri_sifre"));
        aranan_urun_idColumn.setCellValueFactory(new PropertyValueFactory<Urun_musteri, Long>("urun_id"));
    }

    public void musteriEkle() {
        String musteri_adi_soyadiText = musteri_adi_soyadi.getText();
        String musteri_adresText = musteri_adres.getText();
        String musteri_telText = musteri_tel.getText();
        String musteri_sifreText = musteri_sifre.getText();
        String musteri_emailText = musteri_email.getText();
        String urun_ismiText = urun_kategorisi.getValue();
        DBConnection db = DBConnection.getDBConnection();
        Connection con = db.connect();
        
        if(musteri_adi_soyadiText.equals("") || musteri_adresText.equals("") || musteri_telText.equals("") || musteri_sifreText.equals("") || musteri_emailText.equals("")){
                JOptionPane.showMessageDialog(null, "BOŞ ALAN BIRAKMAYINIZ!"
                       + " YENİDEN DENEYİNİZ!!");
        }
        else{
            
            
        try {
            Statement st = con.createStatement();
            st.executeUpdate("insert into musteri(musteri_adi_soyadi,musteri_tel,musteri_adres,musteri_email,musteri_sifre) values('" 
                    + musteri_adi_soyadiText + "','" + musteri_telText + "','" + musteri_adresText + "','" + musteri_emailText + "','" 
                    + musteri_sifreText + "')");
            int id = musteriDAO.id_bul(musteri_telText);
            Statement st2 = con.createStatement();
            if (urun_ismiText.equals("e-Arşiv")) {
                st2.executeUpdate("insert into urun_musteri(urun_id,musteri_id) values(17," + id + ")");
            } else if (urun_ismiText.equals("e-Fatura")) {
                st2.executeUpdate("insert into urun_musteri(urun_id,musteri_id) values(18," + id + ")");
            } else if (urun_ismiText.equals("e-Defter")) {
                st2.executeUpdate("insert into urun_musteri(urun_id,musteri_id) values(19," + id + ")");
            } else if (urun_ismiText.equals("e-İrsaliye")) {
                st2.executeUpdate("insert into urun_musteri(urun_id,musteri_id) values(20," + id + ")");
            } else if (urun_ismiText.equals("e-Makbuz")) {
                st2.executeUpdate("insert into urun_musteri(urun_id,musteri_id) values(21," + id + ")");
            }
            musteri_ve_urun.removeAll(musteri_ve_urun);
            musteriDAO.urunleriGoster();
            urun_musteriTableView.getItems().setAll(musteri_ve_urun);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("HATA!!!");
        }

        }
    }
    public void musteri_ara(){
        
        String musteri_telText=aranan_musteri_telefon.getText();
        aranan_musteri_ve_urun.removeAll(aranan_musteri_ve_urun);
        musteriDAO.musteriBul(musteri_telText);
        aranan_urun_musteriTableView.getItems().setAll(aranan_musteri_ve_urun);
        
    }
    public void musteri_sil() {
        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();

        ObservableList<Urun_musteri> selectedRows, allMusteri_urun;
        allMusteri_urun = urun_musteriTableView.getItems();
        selectedRows = urun_musteriTableView.getSelectionModel().getSelectedItems();
        for (Urun_musteri urun : selectedRows) {
            allMusteri_urun.remove(urun);
            try {
                Statement st = c.createStatement();
                st.executeUpdate("delete from urun_musteri where musteri_id="
                        + urun.getMusteri_id());
                st.executeUpdate("delete from musteri where musteri_id=" + urun.getMusteri_id());

            } catch (SQLException exception) {
                exception.getMessage();
            }
        }

    }

    public void musteri_guncelle() {
        DBConnection db = DBConnection.getDBConnection();
        Connection con = db.connect();

        String guncellenecek_parametreText = guncellenecek_parametre.getValue();
        String guncellenecek_musteri_idText = guncellenecek_id.getText();
        String guncelText = guncel_deger.getText();

        try {
            Statement st = con.createStatement();
            if (guncellenecek_parametreText.equals("Müşteri Adı Soyadı")) {

                st.executeUpdate("update musteri set musteri_adi_soyadi='" + guncelText + "' where musteri_id='" + guncellenecek_musteri_idText + "'");
            } else if (guncellenecek_parametreText.equals("Müşteri Telefon")) {
                st.executeUpdate("update musteri set musteri_tel='" + guncelText + "' where musteri_id='" + guncellenecek_musteri_idText + "'");
            } else if (guncellenecek_parametreText.equals("Müşteri Adres")) {
                st.executeUpdate("update musteri set musteri_adres='" + guncelText + "' where musteri_id='" + guncellenecek_musteri_idText + "'");
            } else if (guncellenecek_parametreText.equals("Müşteri E-Mail")) {
                st.executeUpdate("update musteri set musteri_email='" + guncelText + "' where musteri_id='" + guncellenecek_musteri_idText + "'");
            } else if (guncellenecek_parametreText.equals("Müşteri Şifre")) {
                st.executeUpdate("update musteri set musteri_sifre='" + guncelText + "' where musteri_id='" + guncellenecek_musteri_idText + "'");

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        musteri_ve_urun.removeAll(musteri_ve_urun);
        musteriDAO.urunleriGoster();
        urun_musteriTableView.getItems().setAll(musteri_ve_urun);
    }

    public void geri(ActionEvent a) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("SaticiUrunDuzenle.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void cikis(ActionEvent a) {
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
