/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import school_manager.MainApp;
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

    private final String[] userTypes = {
        "Student", "Teacher", "Parent", "Administator"
    };

    @FXML
    private ComboBox<String> typeOfUserComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeOfUserComboBox.setItems(FXCollections.observableArrayList(userTypes));
        typeOfUserComboBox.setValue(userTypes[0]);

    }

    @FXML
    public void okClicked() {

        VBox menuPane;
        FXMLLoader loader = new FXMLLoader();

        try {

            loader.setLocation(getClass().getResource("ParentMenuFragment.fxml"));
            menuPane = (VBox) loader.load();
            mainApp.setMenu(menuPane);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void cancelClicked() {

    }
    
    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
