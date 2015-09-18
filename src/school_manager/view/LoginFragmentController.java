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
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author abrasha
 */
public class LoginFragmentController implements Initializable {

    private String[] userTypes = {
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
    
}
