/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import school_manager.MainApp;
import school_manager.helpers.MainReferenced;
import school_manager.model.Teacher;

/**
 * FXML Controller class
 *
 * @author bepa
 */
public class TeacherProfileFragmentController implements Initializable, MainReferenced {

    MainApp mainApp;
    Teacher teacher;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }
    
}
