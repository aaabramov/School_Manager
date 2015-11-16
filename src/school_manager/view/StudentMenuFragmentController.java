/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.Student;

/**
 FXML Controller class

 @author abrasha
 */
public class StudentMenuFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    static Student student;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
    }

    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        this.student = DatabaseManager.getStudentById(mainApp.getAccountInfo().getId());
        if (student != null) {
            mainApp.setUserName(student.toString());
        } else {
            mainApp.setStatus("Error getting student from db");
        }
    }

    @FXML
    public void btnMyProfileClicked(){
        FXMLLoader loader = new FXMLLoader();
        GridPane studentProfilePane;

        try {
            loader.setLocation(getClass().getResource("StudentProfileFragment.fxml"));
            studentProfilePane = (GridPane) loader.load();

            StudentProfileFragmentController studentProfileController = loader.getController();
            studentProfileController.setMainApp(mainApp);
            studentProfileController.setStudent(student.getId());

            mainApp.setContent(studentProfilePane);
            mainApp.setStatus("Student prifile set.");
        } catch (IOException e) {
            mainApp.getLogger().log(Level.SEVERE, "Error setting student profile", e);
            mainApp.setStatus("Error setting student profile...");
        }
    }

    @FXML
    public void btnMyGroupClicked(){
        FXMLLoader loader = new FXMLLoader();
        GridPane groupProfilePane;

        try {
            loader.setLocation(getClass().getResource("GroupProfileFragment.fxml"));
            groupProfilePane = (GridPane) loader.load();

            GroupProfileFragmentController groupProfileController = loader.getController();
            groupProfileController.setMainApp(mainApp);
            groupProfileController.setGroup(student.getId());

            mainApp.setContent(groupProfilePane);
            mainApp.setStatus("Group prifile set.");
        } catch (IOException e) {
            mainApp.getLogger().log(Level.SEVERE, "Error setting group profile", e);
            mainApp.setStatus("Error setting group profile...");
        }
    }

    @FXML
    public void btnScheduleClicked(){
        StudentScheduleFragment studentScheduleFragment = new StudentScheduleFragment();
        studentScheduleFragment.setMainApp(mainApp);
        studentScheduleFragment.Initialize();
        mainApp.setStatus("Scheldue set");
    }

    @FXML
    public void btnHomeworkClicked(){

    }

    @FXML
    public void btnProgressClicked(){

    }

    @FXML
    public void btnMyTeachersClicked(){

    }

    @FXML
    public void btnMySettingsClicked(){

    }

}
