/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import school_manager.MainApp;
import school_manager.model.Teacher;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author bepa
 */
public class TeacherMenuFragmentController implements Initializable {

    MainApp mainApp;
    Teacher teacher;
    
    @FXML
    private Button buttonProfile;
    
    @FXML
    private Button buttonMyLesson;
    
    @FXML
    private Button buttonSchedule;
    
    @FXML
    private Button buttonClasses;
    
    @FXML
    private Button buttonSettings;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
