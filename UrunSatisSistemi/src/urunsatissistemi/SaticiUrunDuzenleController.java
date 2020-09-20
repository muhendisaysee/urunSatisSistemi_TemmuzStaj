/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunsatissistemi;

import Entity.Satici;
import Entity.Urun;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author ayse
 */
public class SaticiUrunDuzenleController implements Initializable {

    public static List<Urun> saticiUrunler = new ArrayList();
    /**
     * Initializes the controller class.
     */
    @FXML
    TextField urun_ismi;
    @FXML
    TextField urun_fiyati;
    @FXML
    public ComboBox<String> urun_kategorisi = new ComboBox();
    urunDAO urunDAO = new urunDAO();
    /*----------------------------------------*/
    @FXML
    private TableView<Urun> urunTableView;
    @FXML
    private TableColumn<Urun, Long> urun_idColumn;
    @FXML
    private TableColumn<Urun, String> urun_adiColumn;
    @FXML
    private TableColumn<Urun, String> urun_fiyatiColumn;
    @FXML
    private TableColumn<Urun, String> urun_kategorisiColumn;
    /**
     * ****************************************************************
     */

    @FXML
    TextField guncellenecek_urunID;
    @FXML
    public ComboBox<String> guncellenecek_parametre = new ComboBox();
    static Satici aktifSatici = new Satici();
    @FXML
    TextField guncelDeger;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        saticiUrunler.removeAll(saticiUrunler);
        urunDAO.saticiUrunGoster();
        urunTableView.getItems().setAll(saticiUrunler);
        urun_idColumn.setCellValueFactory(new PropertyValueFactory<Urun, Long>("urun_id"));
        urun_adiColumn.setCellValueFactory(new PropertyValueFactory<Urun, String>("urun_adi"));
        urun_kategorisiColumn.setCellValueFactory(new PropertyValueFactory<Urun, String>("urun_kategorisi"));
        urun_fiyatiColumn.setCellValueFactory(new PropertyValueFactory<Urun, String>("urun_fiyati"));

        urun_kategorisi.getItems().add("e-Arşiv");
        urun_kategorisi.getItems().add("e-Fatura");
        urun_kategorisi.getItems().add("e-Defter");
        urun_kategorisi.getItems().add("e-İrsaliye");
        urun_kategorisi.getItems().add("e-Makbuz");
        /*------------------------------------------------*/
        guncellenecek_parametre.getItems().add("Ürün ismi");
        guncellenecek_parametre.getItems().add("Ürün Fiyatı");
        guncellenecek_parametre.getItems().add("Ürünün kategorisi");
    }

    public void urun_ekle() {

        DBConnection db = DBConnection.getDBConnection();
        Connection con = db.connect();

        String urun_ismiText = urun_ismi.getText();
        String urun_fiyatiText = urun_fiyati.getText();
        String urun_kategorisiText = urun_kategorisi.getValue();

        if (urun_ismiText.equals("") || urun_fiyatiText.equals("")) {
            JOptionPane.showMessageDialog(null, "BOŞ ALAN BIRAKMAYINIZ!");
        } else {
            try {
                Statement st = con.createStatement();
                st.executeUpdate("insert into urun(urun_adi,urun_kategorisi,urun_fiyati) values('" +
                        urun_ismiText + "','" + urun_kategorisiText + "','" + urun_fiyatiText + "')");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "BİR SORUN OLUŞTU!");
                System.out.println(e.getMessage());
            }
            saticiUrunler.removeAll(saticiUrunler);
            urunDAO.saticiUrunGoster();
            urunTableView.getItems().setAll(saticiUrunler);
        }
    }

    public void urun_guncelle() {
        String guncellenecekText = guncellenecek_urunID.getText();
        String secilen_parametreText = guncellenecek_parametre.getValue();
        String guncelDegerText = guncelDeger.getText();
        if (secilen_parametreText.equals("Ürün ismi")) {
            DBConnection db = DBConnection.getDBConnection();
            Connection con = db.connect();
            try {
                Statement st = con.createStatement();
                st.executeUpdate("update urun set urun_adi='" + guncelDegerText + "' where urun_id=" + guncellenecekText);
          } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else if (secilen_parametreText.equals("Ürün Fiyatı")) {
            DBConnection db = DBConnection.getDBConnection();
            Connection con = db.connect();
            try {
                Statement st = con.createStatement();
                st.executeUpdate("update urun set urun_fiyati='" + guncelDegerText + "' where urun_id=" + guncellenecekText);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else if (secilen_parametreText.equals("Ürünün kategorisi")) {
            DBConnection db = DBConnection.getDBConnection();
            Connection con = db.connect();
            try {
                Statement st = con.createStatement();
                st.executeUpdate("update urun set urun_kategorisi='" + guncelDegerText + "' where urun_id=" + guncellenecekText);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        saticiUrunler.removeAll(saticiUrunler);
        urunDAO.saticiUrunGoster();
        urunTableView.getItems().setAll(saticiUrunler);
    }

    public void urun_sil() {
        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();

        ObservableList<Urun> selectedRows, allbilet;
        allbilet = urunTableView.getItems();
        selectedRows = urunTableView.getSelectionModel().getSelectedItems();
        for (Urun urun : selectedRows) {
            allbilet.remove(urun);
            try {
                Statement st = c.createStatement();
                st.executeUpdate("delete from urun where urun_id="
                        + urun.getUrun_id());
                st.executeUpdate("delete from urun_musteri where urun_id=" + urun.getUrun_id());

            } catch (SQLException exception) {
                exception.getMessage();
            }
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

    public void musteri(ActionEvent a) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("SaticiMusteriyiDuzenle.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
