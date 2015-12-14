/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.Student;

/**
 FXML Controller class

 @author abrasha
 */
public class StudentProfileFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    private Student student;
    @FXML
    private Label labelInitials;
    @FXML
    private Label labelForm;
    @FXML
    private Label labelAddress;
    @FXML
    private Label labelPhone;
    @FXML
    private Label labelNotes;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        labelForm.setOnMouseClicked(e -> {

            int id = student.getGroupId();
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(getClass().getResource("GroupProfileFragment.fxml"));
                GridPane view = loader.load();

                mainApp.setContent(view);

                GroupProfileFragmentController gpfc = loader.getController();
                gpfc.setMainApp(mainApp);
                gpfc.setGroup(id);

            } catch (IOException ex) {
                mainApp.getLogger().severe("Error setting group profile");
            }

        });

    }

    public void setStudent(int id){
        this.student = DatabaseManager.getStudentById(id);
        if (student != null) {
            fillInProfile();
        }
    }

    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    private void fillInProfile(){
        labelInitials.setText(student.getLName() + ' ' + student.getFName());
        labelForm.setText(DatabaseManager.getGroupOverviewByStudent(student.getId()).getCode());
        labelAddress.setText(student.getAddress());
        labelPhone.setText(student.getPhone());
        labelNotes.setText(student.getNotes());
    }

}
