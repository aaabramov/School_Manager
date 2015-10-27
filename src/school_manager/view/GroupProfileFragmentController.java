/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.Group;
import school_manager.model.Teacher;
import school_manager.model.overviews.StudentOverview;

/**
 FXML Controller class

 @author abrasha
 */
public class GroupProfileFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    private Group group;

    @FXML
    Label labelGroupName;
    @FXML
    Label labelCurator;
    @FXML
    ListView<StudentOverview> lvMembers;
    @FXML
    Label labelAbout;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        lvMembers.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                int id = lvMembers.getSelectionModel().getSelectedItem().getId();
                FXMLLoader loader = new FXMLLoader();
                try {
                    loader.setLocation(getClass().getResource("StudentProfileFragment.fxml"));
                    GridPane view = loader.load();
                    
                    mainApp.setContent(view);
                    
                    StudentProfileFragmentController studentProfileController = loader.getController();
                    studentProfileController.setMainApp(mainApp);
                    studentProfileController.setStudent(id);
                } catch (IOException ex){
                    mainApp.getLogger().severe("Error setting student profile");
                }
            }
        });

    }

    public void setGroup(int id){
        this.group = DatabaseManager.getGroupById(id);
        if (group != null) {
            fillInProfile();
        }
    }

    private void fillInProfile(){
        Teacher curator = DatabaseManager.getTeacherById(group.getCuratorId());
        if (curator != null) {
            labelCurator.setText(curator.getInitials());
        }
        labelGroupName.setText(group.getCode());
        labelAbout.setText(group.getNote());
        lvMembers.setItems(FXCollections.observableArrayList(group.getStudents()));
    }

    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

}
