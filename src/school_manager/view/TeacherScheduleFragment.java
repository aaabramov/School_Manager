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
import school_manager.model.schedule.TeacherSchedule;
import school_manager.model.schedule.TeacherLesson;

/**

 @author bepa
 */
public class TeacherScheduleFragment implements MainReferenced {

    private MainApp mainApp;

    public void Initialize(){
        GridPane grid = new GridPane();

        TeacherSchedule schedule;

        List<TeacherLesson> list;
        ScheduleCell scCell = new ScheduleCell();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
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
