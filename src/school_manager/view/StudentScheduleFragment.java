/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.helpers.ScheduleCell;
import school_manager.model.Student;
import school_manager.model.schedule.StudentSchedule;

/**

 @author bepa
 */
public class StudentScheduleFragment implements MainReferenced {

    private MainApp mainApp;
    private Student student;

    public void Initialize(){
        GridPane grid = new GridPane();

        StudentSchedule schedule = DatabaseManager.getStudentScheduleById(student.getGroupId());

        ScheduleCell scCell = new ScheduleCell();

        schedule.getLessonList().forEach((e) -> {
            scCell.setData(e.getSubject().getName(), e.getTeacher().getInitials(), e.getClassroom(), e.getDay(), e.getOrder());
        });
        
        ScrollPane pane = new ScrollPane(grid);
        mainApp.setContent(pane);
    }

    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    public void setStudent(Student student){
        this.student = student;
    }
}
