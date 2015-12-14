/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import school_manager.model.overviews.LessonOverview;
import school_manager.model.schedule.Schedule;
import school_manager.model.schedule.StudentLesson;

public class ScheduleDayFragmentController <T extends LessonOverview> implements Initializable {

    Schedule.Day day;
    List<T> lessons;
    
    @FXML
    private Label labelDay;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
    public void setLessons(List<T> lessons){
        this.lessons = lessons;
    }
    
    public void setDay(Schedule.Day day){
        this.day = day;
        if (day != null){
            labelDay.setText(day.toString());
        }
    }
    
    public void loadLesson(){


    }
    
    @FXML
    public void buttonAddLessonClicked(){
                
    }

}
