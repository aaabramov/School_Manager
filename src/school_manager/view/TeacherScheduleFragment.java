/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.util.List;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import school_manager.MainApp;
import school_manager.helpers.MainReferenced;
import school_manager.helpers.ScheduleCell;
import school_manager.model.schedule.Schedule;
import school_manager.model.schedule.TeacherSchedule;
import school_manager.model.schedule.TeacherLesson;

/**

 @author bepa
 */
public class TeacherScheduleFragment implements MainReferenced {

    private MainApp mainApp;
    private final String[] Days = new String[]{"Monday", "Tuesday", "Wednesday", "Thuesday", "Friday"};
    
    public void Initialize(){
        GridPane grid = new GridPane();

        TeacherSchedule schedule;

        List<TeacherLesson> list;
        ScheduleCell scCell = new ScheduleCell();

        scCell.setData(null, null, null, 0, 0);        
        grid.add(scCell.getVB(), scCell.getColumn(), scCell.getRow());
        
        for(int i = 1; i < 6; i++){
            scCell.setData(Days[i-1], null, null, i, 0);        
            grid.add(scCell.getVB(), scCell.getColumn(), scCell.getRow());
        }
        for(int j = 1; j <7; j++){
            scCell.setData(null, Integer.toString(j), null, 0, j);        
            grid.add(scCell.getVB(), scCell.getColumn(), scCell.getRow());
        }
        
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 7; j++) {
                scCell.setData("Subject", "TeacherOrGroup", "Classroom", i, j);
                grid.add(scCell.getVB(), scCell.getColumn(), scCell.getRow());
            }
        }

//        list.forEach((e) -> {
//            //TEACHER OVERVIEW    
//            scCell.setData(e.getName(), "value", e.getClassroom(), e.getDay(), e.getOrder());
//            grid.add(scCell.getVB(), scCell.getColumn(), scCell.getRow());
//        });
        ScrollPane pane = new ScrollPane(grid);
        mainApp.setContent(pane);
    }

    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
}
