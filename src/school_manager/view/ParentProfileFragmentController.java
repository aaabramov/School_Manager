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
import school_manager.model.Parent;

/**
 * FXML Controller class
 *
 * @author abrasha
 */
public class ParentProfileFragmentController implements Initializable, MainReferenced {

    MainApp mainApp;
    Parent parent;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public  void setParent(Parent parent){
        this.parent = parent;
    }
    
}
