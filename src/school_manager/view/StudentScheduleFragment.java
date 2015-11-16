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
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.helpers.ScheduleCell;
import school_manager.model.StudentSchedule;
import school_manager.model.StudentSchedule.StudentLesson;
import school_manager.model.Teacher;

/**
 *
 * @author bepa
 */
public class StudentScheduleFragment implements MainReferenced {
    private MainApp mainApp;
    
    public void Initialize() {
        GridPane grid = new GridPane();
        
        StudentSchedule schedule = DatabaseManager.getScheduleByStudent(
                StudentMenuFragmentController.student.getGroupId());
        
        List<StudentLesson> list = schedule.getLessonList();        
        ScheduleCell scCell = new ScheduleCell();
        
        list.forEach((e) -> {
            //TEACHER OVERVIEW    
            Teacher teacher = DatabaseManager.getTeacherById(e.getTeacherId());
            String str = new String(teacher.getLName() + "\n" + teacher.getFName() + " " + teacher.getPatronymic());
            scCell.setData(e.getName(), str, e.getClassroom(), e.getDay(), e.getOrder());
            grid.add(scCell.getVB(), scCell.getColumn(), scCell.getRow());
        });
        
        ScrollPane pane = new ScrollPane(grid);
        mainApp.setContent(pane);
    }
    
    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
}
