/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunsatissistemi;

import Entity.Belge;
import Entity.Musteri;
import Entity.Urun;
import Entity.Urun_musteri;
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
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import static urunsatissistemi.MusteriIslemlerController.Musteri_iuyum_urunler;
import static urunsatissistemi.MusteriIslemlerController.aktifMusteri;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author ayse
 */
public class E_BelgeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public ComboBox<String> belge_turu1 = new ComboBox();
    @FXML
    public ComboBox<String> guncellenecek_parametre = new ComboBox();
    static Musteri aktifMusteri = new Musteri();
    public static List<Belge> Musteri_belgeler = new ArrayList();
    public static List<Belge> aranan_belge = new ArrayList();
    urunDAO urunDAO = new urunDAO();
    BelgeDAO belge = new BelgeDAO();
    musteriDAO musteri = new musteriDAO();
    @FXML
    private TableView<Belge> belgeTableView;
    @FXML
    private TableColumn<Belge, Long> belge_idColumn;
    @FXML
    private TableColumn<Belge, String> belge_baslikColumn;
    @FXML
    private TableColumn<Belge, String> belge_urunlerColumn;
    @FXML
    private TableColumn<Belge, String> belge_tutarColumn;
    @FXML
    private TableColumn<Belge, String> belge_kesilenKurumColumn;
    @FXML
    private TableColumn<Belge, String> belge_turuColumn;
    @FXML
    private TableView<Belge> aranan_belgeTableView;
    @FXML
    private TableColumn<Belge, Long> aranan_belge_idColumn;
    @FXML
    private TableColumn<Belge, String> aranan_belge_baslikColumn;
    @FXML
    private TableColumn<Belge, String> aranan_belge_urunlerColumn;
    @FXML
    private TableColumn<Belge, String> aranan_belge_tutarColumn;
    @FXML
    private TableColumn<Belge, String> aranan_belge_kesilenKurumColumn;
    @FXML
    private TableColumn<Belge, String> aranan_belge_turuColumn;
    @FXML
    TextField aranan_belge_id;
    @FXML
    TextField belge_baslik;
    @FXML
    TextField belge_tutar;
    @FXML
    TextField belge_urunler;
    @FXML
    TextField belge_kesilenK;

    @FXML
    TextField guncellenecek_belgeID;

    @FXML
    TextField guncelDeger;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        belge_turu1.getItems().add("e-Arşiv");
        belge_turu1.getItems().add("e-Fatura");
        belge_turu1.getItems().add("e-Defter");
        belge_turu1.getItems().add("e-İrsaliye");
        belge_turu1.getItems().add("e-Makbuz");

        guncellenecek_parametre.getItems().add("Belge Başlık");
        guncellenecek_parametre.getItems().add("Belge tutar");
        guncellenecek_parametre.getItems().add("Belge Ürünler");
        guncellenecek_parametre.getItems().add("Belge Kesilen Kurum");
        guncellenecek_parametre.getItems().add("Belge türü");

        Musteri_belgeler.removeAll(Musteri_belgeler);
        belge.musteriBelgeGoster();
        belgeTableView.getItems().setAll(Musteri_belgeler);

        belge_idColumn.setCellValueFactory(new PropertyValueFactory<Belge, Long>("belge_id"));
        belge_baslikColumn.setCellValueFactory(new PropertyValueFactory<Belge, String>("belge_baslik"));
        belge_urunlerColumn.setCellValueFactory(new PropertyValueFactory<Belge, String>("belge_urunler"));
        belge_kesilenKurumColumn.setCellValueFactory(new PropertyValueFactory<Belge, String>("belge_kesilenkurum"));
        belge_tutarColumn.setCellValueFactory(new PropertyValueFactory<Belge, String>("belge_tutar"));
        belge_turuColumn.setCellValueFactory(new PropertyValueFactory<Belge, String>("belge_turu"));

        aranan_belge_idColumn.setCellValueFactory(new PropertyValueFactory<Belge, Long>("belge_id"));
        aranan_belge_baslikColumn.setCellValueFactory(new PropertyValueFactory<Belge, String>("belge_baslik"));
        aranan_belge_urunlerColumn.setCellValueFactory(new PropertyValueFactory<Belge, String>("belge_urunler"));
        aranan_belge_kesilenKurumColumn.setCellValueFactory(new PropertyValueFactory<Belge, String>("belge_kesilenkurum"));
        aranan_belge_tutarColumn.setCellValueFactory(new PropertyValueFactory<Belge, String>("belge_tutar"));
        aranan_belge_turuColumn.setCellValueFactory(new PropertyValueFactory<Belge, String>("belge_turu"));
    }

    public void belge_ara() {
        
      
        aranan_belge.removeAll(aranan_belge);
        belge.arananBelgeGoster(Integer.valueOf(aranan_belge_id.getText()));
        aranan_belgeTableView.getItems().setAll(aranan_belge);
    }

    public void e_belge(ActionEvent a) {
        int urun_id = musteri.urun_id_bul(aktifMusteri.getKullanici_id());
        if (belge_turu1.getValue().equals("e-Arşiv")) {
            if (urun_id == 17) {
                String belge_baslikText = belge_baslik.getText();
                String belge_tutarText = belge_tutar.getText();
                String belge_urunleriText = belge_urunler.getText();
                String belge_kesilenKText = belge_kesilenK.getText();
                String belge_turuText = belge_turu1.getValue();

                DBConnection db = DBConnection.getDBConnection();
                Connection con = db.connect();

                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("insert into belge(belge_baslik,belge_tutar,belge_urunler,belge_kesilenkurum,belge_turu,musteri_id) "
                            + "values('" + belge_baslikText + "','" + belge_tutarText + "','" + belge_urunleriText + "','" + belge_kesilenKText 
                            + "','" + belge_turuText + "','" + aktifMusteri.getKullanici_id() + "')");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            } else if (belge_turu1.getValue().equals("e-Arşiv") && urun_id != 17) {
                JOptionPane.showMessageDialog(null, "E-ARŞİV ÜRÜNÜNÜZ YOK!");
            }
        } else if (belge_turu1.getValue().equals("e-Fatura")) {
            if (urun_id == 18) {
                String belge_baslikText = belge_baslik.getText();
                String belge_tutarText = belge_tutar.getText();
                String belge_urunleriText = belge_urunler.getText();
                String belge_kesilenKText = belge_kesilenK.getText();
                String belge_turuText = belge_turu1.getValue();

                DBConnection db = DBConnection.getDBConnection();
                Connection con = db.connect();

                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("insert into belge(belge_baslik,belge_tutar,belge_urunler,belge_kesilenkurum,belge_turu,musteri_id) values('" + belge_baslikText + "','" + belge_tutarText + "','" + belge_urunleriText + "','" + belge_kesilenKText + "','" + belge_turuText + "','" + aktifMusteri.getKullanici_id() + "')");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            } else if (belge_turu1.getValue().equals("e-Fatura") && urun_id != 18) {
                JOptionPane.showMessageDialog(null, "E-FATURA ÜRÜNÜNÜZ YOK!");
            }
        } else if (belge_turu1.getValue().equals("e-Defter")) {
            if (urun_id == 19) {
                String belge_baslikText = belge_baslik.getText();
                String belge_tutarText = belge_tutar.getText();
                String belge_urunleriText = belge_urunler.getText();
                String belge_kesilenKText = belge_kesilenK.getText();
                String belge_turuText = belge_turu1.getValue();

                DBConnection db = DBConnection.getDBConnection();
                Connection con = db.connect();

                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("insert into belge(belge_baslik,belge_tutar,belge_urunler,belge_kesilenkurum,belge_turu,musteri_id) values('" + belge_baslikText + "','" + belge_tutarText + "','" + belge_urunleriText + "','" + belge_kesilenKText + "','" + belge_turuText + "','" + aktifMusteri.getKullanici_id() + "')");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            } else if (belge_turu1.getValue().equals("e-Defter") && urun_id != 19) {
                JOptionPane.showMessageDialog(null, "E-DEFTER ÜRÜNÜNÜZ YOK!");
            }
        } else if (belge_turu1.getValue().equals("e-İrsaliye")) {
            if (urun_id == 20) {
                String belge_baslikText = belge_baslik.getText();
                String belge_tutarText = belge_tutar.getText();
                String belge_urunleriText = belge_urunler.getText();
                String belge_kesilenKText = belge_kesilenK.getText();
                String belge_turuText = belge_turu1.getValue();

                DBConnection db = DBConnection.getDBConnection();
                Connection con = db.connect();

                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("insert into belge(belge_baslik,belge_tutar,belge_urunler,belge_kesilenkurum,belge_turu,musteri_id) values('" + belge_baslikText + "','" + belge_tutarText + "','" + belge_urunleriText + "','" + belge_kesilenKText + "','" + belge_turuText + "','" + aktifMusteri.getKullanici_id() + "')");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            } else if (belge_turu1.getValue().equals("e-İrsaliye") && urun_id != 20) {
                JOptionPane.showMessageDialog(null, "E-İrsaliye ÜRÜNÜNÜZ YOK!");
            }
        } else if (belge_turu1.getValue().equals("e-Makbuz")) {
            if (urun_id == 21) {
                String belge_baslikText = belge_baslik.getText();
                String belge_tutarText = belge_tutar.getText();
                String belge_urunleriText = belge_urunler.getText();
                String belge_kesilenKText = belge_kesilenK.getText();
                String belge_turuText = belge_turu1.getValue();

                DBConnection db = DBConnection.getDBConnection();
                Connection con = db.connect();

                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("insert into belge(belge_baslik,belge_tutar,belge_urunler,belge_kesilenkurum,belge_turu,musteri_id) values('" + belge_baslikText + "','" + belge_tutarText + "','" + belge_urunleriText + "','" + belge_kesilenKText + "','" + belge_turuText + "','" + aktifMusteri.getKullanici_id() + "')");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            } else if (belge_turu1.getValue().equals("e-Makbuz") && urun_id != 21) {
                JOptionPane.showMessageDialog(null, "E-Makbuz ÜRÜNÜNÜZ YOK!");
            }
        }
        Musteri_belgeler.removeAll(Musteri_belgeler);
        belge.musteriBelgeGoster();
        belgeTableView.getItems().setAll(Musteri_belgeler);
    }

    public void belge_guncelle() {
        String guncellenecek_belgeIdText = guncellenecek_belgeID.getText();
        String guncellenecek_parametreText = guncellenecek_parametre.getValue();
        String guncelDegerText = guncelDeger.getText();
        DBConnection db = DBConnection.getDBConnection();
        Connection con = db.connect();
        try {
            Statement st = con.createStatement();
            if (guncellenecek_parametreText.equals("Belge Başlık")) {
                st.executeUpdate("update belge set belge_baslik='" + guncelDegerText + "' where belge_id='" + guncellenecek_belgeIdText + "'");
            } else if (guncellenecek_parametreText.equals("Belge tutar")) {
                st.executeUpdate("update belge set belge_tutar='" + guncelDegerText + "' where belge_id='" + guncellenecek_belgeIdText + "'");
            } else if (guncellenecek_parametreText.equals("Belge Ürünler")) {
                st.executeUpdate("update belge set belge_urunler='" + guncelDegerText + "' where belge_id='" + guncellenecek_belgeIdText + "'");
            } else if (guncellenecek_parametreText.equals("Belge Kesilen Kurum")) {
                st.executeUpdate("update belge set belge_kesilenkurum='" + guncelDegerText + "' where belge_id='" + guncellenecek_belgeIdText + "'");
            } else if (guncellenecek_parametreText.equals("Belge türü")) {
                st.executeUpdate("update belge set belge_turu='" + guncelDegerText + "' where belge_id='" + guncellenecek_belgeIdText + "'");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Musteri_belgeler.removeAll(Musteri_belgeler);
        belge.musteriBelgeGoster();
        belgeTableView.getItems().setAll(Musteri_belgeler);
    }

    public void belgeSil() {

        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();

        ObservableList<Belge> selectedRows, allMusteri_belge;
        allMusteri_belge = belgeTableView.getItems();
        selectedRows = belgeTableView.getSelectionModel().getSelectedItems();
        for (Belge belge : selectedRows) {
            allMusteri_belge.remove(belge);
            try {
                Statement st = c.createStatement();
                st.executeUpdate("delete from belge where belge_id="
                        + belge.getBelge_id());

            } catch (SQLException exception) {
                exception.getMessage();
            }
        }
    }
}
