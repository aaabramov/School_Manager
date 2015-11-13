/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import school_manager.MainApp;
import school_manager.helpers.MainReferenced;
import school_manager.helpers.ScheduleCell;

/**
 *
 * @author bepa
 */
public class StudentScheduleFragment implements MainReferenced {
    private MainApp mainApp;
    
    public void Initialize() {
        GridPane grid = new GridPane();
        
        //TODO обработка дня и номера урока
        
        ObservableList<ScheduleCell> listVB = FXCollections.observableArrayList(
            //Monday
            new ScheduleCell("Subject1", "Teacher1", "Class", 0, 0), 
            new ScheduleCell("Subject2", "Teacher1", "Class", 0, 1), 
            new ScheduleCell("Subject3", "Teacher1", "Class", 0, 2), 
            new ScheduleCell("Subject4", "Teacher1", "Class", 0, 3), 
            new ScheduleCell("Subject5", "Teacher1", "Class", 0, 4), 
            new ScheduleCell("Subject6", "Teacher1", "Class", 0, 5), 
            //Tuesday
            new ScheduleCell("Subject1", "Teacher2", "Class", 1, 0), 
            new ScheduleCell("Subject2", "Teacher2", "Class", 1, 1), 
            new ScheduleCell("Subject3", "Teacher2", "Class", 1, 2), 
            new ScheduleCell("Subject4", "Teacher2", "Class", 1, 3), 
            new ScheduleCell("Subject5", "Teacher2", "Class", 1, 4), 
            new ScheduleCell("Subject6", "Teacher2", "Class", 1, 5),
            //Wednesday
            new ScheduleCell("Subject1", "Teacher3", "Class", 2, 0), 
            new ScheduleCell("Subject2", "Teacher3", "Class", 2, 1), 
            new ScheduleCell("Subject3", "Teacher3", "Class", 2, 2), 
            new ScheduleCell("Subject4", "Teacher3", "Class", 2, 3), 
            new ScheduleCell("Subject5", "Teacher3", "Class", 2, 4), 
            new ScheduleCell("Subject6", "Teacher3", "Class", 2, 5),
            //Thuesday
            new ScheduleCell("Subject1", "Teacher4", "Class", 3, 0), 
            new ScheduleCell("Subject2", "Teacher4", "Class", 3, 1), 
            new ScheduleCell("Subject3", "Teacher4", "Class", 3, 2), 
            new ScheduleCell("Subject4", "Teacher4", "Class", 3, 3), 
            new ScheduleCell("Subject5", "Teacher4", "Class", 3, 4), 
            new ScheduleCell("Subject6", "Teacher4", "Class", 3, 5),
            //Friday
            new ScheduleCell("Subject1", "Teacher5", "Class", 4, 0), 
            new ScheduleCell("Subject2", "Teacher5", "Class", 4, 1), 
            new ScheduleCell("Subject3", "Teacher5", "Class", 4, 2), 
            new ScheduleCell("Subject4", "Teacher5", "Class", 4, 3), 
            new ScheduleCell("Subject5", "Teacher5", "Class", 4, 4), 
            new ScheduleCell("Subject6", "Teacher5", "Class", 4, 5)
        );
        
        listVB.forEach((e) -> {
            grid.add(e.getVB(), e.getColumn(), e.getRow());
        });
        
        mainApp.setContent(grid);
    }
    
    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
}
