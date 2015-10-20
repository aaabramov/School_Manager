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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author bepa
 */
public class AdminParentInsertionFragmentController implements Initializable {

    @FXML
    private TextField tfFname;
    @FXML
    private TextField tfLname;
    @FXML
    private TextField tfPatronymic;
    @FXML
    private TextField tfBDay;
    @FXML
    private TextField tfAdress;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfNotes;
    @FXML
    private TextField tfChildSearch;
    @FXML
    private ListView lvChild;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnClearClicked(){
        tfLname.clear();
        tfFname.clear();
        tfPatronymic.clear();
        tfBDay.clear();
        tfAdress.clear();
        tfPhone.clear();
        tfNotes.clear();
        tfChildSearch.clear();
        lvChild.setItems(null);
    }
    
}
