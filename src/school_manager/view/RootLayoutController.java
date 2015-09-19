/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import school_manager.MainApp;
import school_manager.helpers.MainReferenced;

/**
 * FXML Controller class
 *
 * @author abrasha
 */
public class RootLayoutController implements Initializable, MainReferenced {

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

    }

    @FXML
    public void menuLoginClicked() {
    }

    @Override
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
