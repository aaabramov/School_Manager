/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import school_manager.MainApp;

/**
 * FXML Controller class
 *
 * @author abrasha
 */
public class RootLayoutController implements Initializable {

    MainApp mainApp;
    
    @FXML
    private VBox contentPane;
    @FXML
    private VBox menuPane;
    @FXML
    private Label statusLabel;
    @FXML
    private Button buttonSearch;
    @FXML
    private Button buttonLogout;
    @FXML
    private SplitPane splitPane;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        FXMLLoader loader = new FXMLLoader();
        try {
            
            loader.setLocation(getClass().getResource("LoginFragment.fxml"));
            BorderPane pane = (BorderPane)loader.load();
            contentPane.getChildren().add(pane);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
        
    public VBox getContentPane() {
        return contentPane;
    }

    public VBox getMenuPane() {
        return menuPane;
    }

    public Label getLabelStatus() {
        return statusLabel;
    }
    
}
