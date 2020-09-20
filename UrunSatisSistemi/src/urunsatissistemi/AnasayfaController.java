/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urunsatissistemi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ayse
 */
public class AnasayfaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void musteriGiris(ActionEvent a){
           try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("MusteriGirisi.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
               System.out.println(ex.getMessage());
        }
        
    }
    public void saticiGiris(ActionEvent a){
           try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("SaticiGirisi.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
               System.out.println(ex.getMessage());
        }
        
    }
    public void yeniKayit(ActionEvent a){
           try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("YeniKayit.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
               System.out.println(ex.getMessage());
        }
    }
}
