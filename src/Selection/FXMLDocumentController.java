package Selection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author dale_876101
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Button button;
    @FXML
    private Label label;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/Galaga/GalagaFXML.fxml"));
           Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
           Scene scene = new Scene(loader.load());
           stage.setScene(scene);
        }catch(IOException e){}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
