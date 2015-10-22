/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import school_manager.MainApp;
import school_manager.helpers.MainReferenced;
import school_manager.model.User;

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
    @FXML
    public Label nameLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        splitPane.widthProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            menuPane.setMaxWidth(newValue.doubleValue() * 0.2);
            contentPane.setMaxWidth(newValue.doubleValue() * 0.8);
        });
        

    }

    @FXML
    public void menuLoginClicked() {
        
    }
    
    @FXML
    public void btnLogoutClicked(){
        mainApp.logOut();
    }
    
    @FXML
    public void imgLogoClicked(){
        mainApp.LogoClicled();
    }
    
    @FXML
    public void menuLoginAsAdminClicked(){
        mainApp.setAccountInfo(new User(0, 0, User.AccType.ADMIN));
        mainApp.setStatus("Forward login as admin");
    }
    
    @FXML
    public void menuLoginAsStudentClicked(){
        mainApp.setAccountInfo(new User(0, 0, User.AccType.STUDENT));
        mainApp.setStatus("Forward login as student");
    }
    
    @FXML
    public void menuLoginAsTeacherClicked(){
        mainApp.setAccountInfo(new User(0, 0, User.AccType.TEACHER));
        mainApp.setStatus("Forward login as teacher");
    }
    
    @FXML
    public void menuLoginAsParentClicked(){
        mainApp.setAccountInfo(new User(0, 0, User.AccType.PARENT));
        mainApp.setStatus("Forward login as parent");
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

    public SplitPane getSplitPane() {
        return splitPane;
    }

    public Label getLabelStatus() {
        return statusLabel;
    }

}
