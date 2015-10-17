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
import javafx.scene.control.TextField;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.Admin;
import school_manager.model.Student;

/**
 * FXML Controller class
 *
 * @author abrasha
 */
public class AdminStudentInsertionFragmentController implements Initializable, MainReferenced {

    MainApp mainApp;
    Admin admin;
    
    @FXML
    private TextField tfFName;
    @FXML
    private TextField tfLName;
    @FXML
    private TextField tfPatronymic;
    @FXML
    private TextField tfIdGroup;
    @FXML
    private TextField tfBDay;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfNotes;
    
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
    
    public void  setAdmin(Admin admin){
        this.admin = admin;
    }
    
    @FXML 
    public void btnConfirmClicked(){
        
        String fname = tfFName.getText();
        String lname = tfLName.getText();
        String patronymic = tfPatronymic.getText();
        int groupId = Integer.valueOf(tfIdGroup.getText());
        String bday = tfBDay.getText();
        String address = tfAddress.getText();
        String phone = tfPhone.getText();
        String notes = tfNotes.getText();
        
        Student added = new Student.Builder()
                .fName(fname)
                .lName(lname)
                .patronymic(patronymic)
                .bday(bday)
                .address(address)
                .phone(phone)
                .notes(notes)
                .id_group(groupId)
                .build();
        
        DatabaseManager.insertStudent(added);
        
    }
    
    @FXML
    public void btnCancelClicked(){
        
        
        
    }
    
    
    
}
