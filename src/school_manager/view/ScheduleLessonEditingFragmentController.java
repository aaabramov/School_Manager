/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import school_manager.helpers.DatabaseManager;
import school_manager.model.schedule.Lesson;
import school_manager.model.overviews.SubjectOverview;
import school_manager.model.overviews.TeacherOverview;

public class ScheduleLessonEditingFragmentController implements Initializable {

    private Lesson lesson;
    
    @FXML
    private Label labelOrder;
    @FXML
    private ComboBox<SubjectOverview> cbSubject;
    @FXML
    private ComboBox<TeacherOverview> cbTeacher;
    @FXML
    private TextField tfClassroom;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
    public void setLesson(int lessonId){
        lesson = DatabaseManager.getLessonById(lessonId);
        fillInData();
    }
    
    public void fillInData(){
        labelOrder.setText(Integer.toString(lesson.getIdLesson()));
        cbSubject.setValue(lesson.getSubject());
        cbTeacher.setValue(lesson.getTeacher());
    }
    
}
