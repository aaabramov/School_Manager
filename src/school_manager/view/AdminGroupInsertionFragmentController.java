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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import school_manager.MainApp;
import school_manager.helpers.MainReferenced;

/**
 * FXML Controller class
 *
 * @author bepa
 */
public class AdminGroupInsertionFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    
    @FXML
    private TextField tfCode;
    @FXML
    private ComboBox cbCurator;
    @FXML
    private TextField tfNotes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    @FXML
    private void btnClearClicked(){
        tfCode.clear();
        cbCurator.setValue(null);
        tfNotes.clear();
    }
    
    @FXML
    private void btnConfirmClicked(){
        
    }
}
