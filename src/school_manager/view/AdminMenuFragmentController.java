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
import javafx.scene.control.Accordion;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.Admin;

/**
 * FXML Controller class
 *
 * @author bepa
 */
public class AdminMenuFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    private Admin admin;

    @FXML
    private Accordion accordionMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accordionMenu.setExpandedPane(accordionMenu.getPanes().get(0));
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.admin = DatabaseManager.getAdminById(mainApp.getAccountInfo().getId());
        if (admin != null) {
            mainApp.setUserName(admin.toString());
            DatabaseManager.refreshAdminData(admin.getId());
        } else {
            mainApp.setStatus("Error getting admin from db");
        }
    }

    @FXML
    public void buttonAddStudentClicked() {

        FXMLLoader loader = new FXMLLoader();

        try {

            loader.setLocation(getClass().getResource("AdminStudentInsertionFragment.fxml"));
            GridPane pane = (GridPane) loader.load();

            AdminStudentInsertionFragmentController studentInsertionController = loader.getController();
            studentInsertionController.setMainApp(mainApp);
            studentInsertionController.setAdmin(admin);

            mainApp.setContent(pane);
            mainApp.setStatus("Setting student insertion form is set");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            mainApp.setStatus("Error setting student insertion form.");
        }
    }

    @FXML
    public void buttonAddParentClicked() {

        FXMLLoader loader = new FXMLLoader();

        try {

            loader.setLocation(getClass().getResource("AdminParentInsertionFragment.fxml"));
            GridPane pane = (GridPane) loader.load();

            AdminParentInsertionFragmentController parentInsertionController = loader.getController();
            parentInsertionController.setMainApp(mainApp);

            mainApp.setContent(pane);
            mainApp.setStatus("Parent insertion form is set");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            mainApp.setStatus("Error setting parent insertion form.");
        }
    }

    @FXML
    public void buttonAddTeacherClicked() {

        FXMLLoader loader = new FXMLLoader();

        try {

            loader.setLocation(getClass().getResource("AdminTeacherInsertionFragment.fxml"));
            GridPane pane = (GridPane) loader.load();

            AdminTeacherInsertionFragmentController teacherInsertionController = loader.getController();
            teacherInsertionController.setMainApp(mainApp);

            mainApp.setContent(pane);
            mainApp.setStatus("Teacher insertion form is set");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            mainApp.setStatus("Error setting teacher insertion form.");
        }
    }

    @FXML
    public void buttonAddGroupClicked() {

        FXMLLoader loader = new FXMLLoader();

        try {

            loader.setLocation(getClass().getResource("AdminGroupInsertionFragment.fxml"));
            GridPane pane = (GridPane) loader.load();

            AdminGroupInsertionFragmentController groupInsertionController = loader.getController();
            groupInsertionController.setMainApp(mainApp);

            mainApp.setContent(pane);
            mainApp.setStatus("Teacher insertion form is set");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            mainApp.setStatus("Error setting group insertion form");
        }
    }
    
    @FXML
    public void buttonAddScheduleClicked(){
        FXMLLoader loader = new FXMLLoader();
        
        try{
            
            loader.setLocation(getClass().getResource("AdminScheduleInsertionFragment.fxml"));
            GridPane pane = (GridPane) loader.load();
            
            AdminScheduleInsertionFragmentController adminScheduleInsertionFragmentController = loader.getController();
            adminScheduleInsertionFragmentController.setMainApp(mainApp);
            
            mainApp.setContent(pane);
            mainApp.setStatus("Schedule insertion form is set");
            
        }catch(IOException e){
            System.out.println(e.getMessage());
            mainApp.setStatus("Error setting schedule insertion form");
        }
    }
}
