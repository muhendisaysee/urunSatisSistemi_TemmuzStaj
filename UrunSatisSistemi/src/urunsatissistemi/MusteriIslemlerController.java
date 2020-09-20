/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunsatissistemi;

import Entity.Musteri;
import Entity.Satici;
import Entity.Urun;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static urunsatissistemi.SaticiUrunDuzenleController.saticiUrunler;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author ayse
 */
public class MusteriIslemlerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static Musteri aktifMusteri = new Musteri();
    public static List<Urun> Musteri_iuyum_urunler = new ArrayList();
    urunDAO urunDAO = new urunDAO();
    musteriDAO musteri = new musteriDAO();
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    

        Musteri_iuyum_urunler.removeAll(Musteri_iuyum_urunler);
        musteri.urunlerin_id_bul(aktifMusteri.getKullanici_id());
        urunTableView.getItems().setAll(Musteri_iuyum_urunler);

        urun_idColumn.setCellValueFactory(new PropertyValueFactory<Urun, Long>("urun_id"));
        urun_adiColumn.setCellValueFactory(new PropertyValueFactory<Urun, String>("urun_adi"));
        urun_kategorisiColumn.setCellValueFactory(new PropertyValueFactory<Urun, String>("urun_kategorisi"));
        urun_fiyatiColumn.setCellValueFactory(new PropertyValueFactory<Urun, String>("urun_fiyati"));

    }

    public void iletisim(ActionEvent a) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("Iletisim.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void belge_duzenle(ActionEvent a) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("E_Belge.fxml"));
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
