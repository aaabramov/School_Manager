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
import javafx.geometry.Orientation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.overviews.GroupOverview;

/**
 FXML Controller class

 @author abrasha
 */
public class AdminScheduleEditingFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    private GroupOverview currentGroup;

    @FXML
    private ComboBox<GroupOverview> cbGroups;

    @FXML
    private VBox vbWeek;

    /**
     Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        cbGroups.valueProperty().addListener((observable, oldValue, newValue) -> {

        });
        
        for (int i = 0; i < 5; i++) {
            loadDay();
        }
    }

    public void setScheduleFor(int groupId){
        currentGroup = DatabaseManager.getGroupOverviewById(groupId);
    }

    public void loadDay(){

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("ScheduleDayFragment.fxml"));

            VBox dayPane = loader.load();
            ScheduleDayFragmentController dayController = loader.getController();

            if (vbWeek.getChildren().size() != 0) {
                vbWeek.getChildren().add(new Separator(Orientation.HORIZONTAL));
            }
            
            vbWeek.getChildren().add(dayPane);
        } catch (IOException e) {

        }

    }

    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

}
