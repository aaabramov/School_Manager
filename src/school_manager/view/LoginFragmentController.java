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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;



/**
 * FXML Controller class
 *
 * @author abrasha
 */
public class LoginFragmentController implements Initializable, MainReferenced {
    
    MainApp mainApp;

    @FXML
    private Button buttonOK;
    @FXML
    private Button buttonCancel;
    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField tfPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    @FXML
    public void okClicked() {

        String login = tfLogin.getText();
        String password = tfPassword.getText();
        
        if (!login.isEmpty() && !password.isEmpty()){
            
            if (DatabaseManager.authorize(login, password)){
                
                mainApp.setAccountInfo(DatabaseManager.getAccountInfoByLogin(login));
                mainApp.setStatus("Authorization failed.");
                
            } else {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Authorization failed");
                alert.setContentText("Wrong login or password!");
                alert.showAndWait();
                
            }
            
        }
        
        

    }

    @FXML
    public void cancelClicked() {
            
        tfPassword.clear();
        tfLogin.clear();
        
    }
    
    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
