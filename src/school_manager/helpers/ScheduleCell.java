/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.helpers;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import school_manager.model.Schedule;

/**
 *
 * @author bepa
 */
public class ScheduleCell {
    private VBox vb;
    private Label lbl;
    private int day; //Monday, ...
    private int lesson; //first, second, ...
    
    public ScheduleCell(){
        vb = new VBox();
        lbl = new Label();
        day = -1;
        lesson = -1;
    }
    
    public ScheduleCell(String textSub, String textTeachorGroup, String textClass, int Day, int Lesson){
        
        vb = new VBox(NewL(textSub, 0), NewL(textTeachorGroup, 1), NewL(textClass, 2));
        vb.setId("grid-vbox");
        day = Day;
        lesson = Lesson;
    }
    
    public VBox getVB(){
        return vb;
    }
    
    private Label NewL(String text, int type){
        lbl = new Label(text);
        switch(type){
            case 0:
                lbl.setId("grid-subject");
                break;
            case 1:
                lbl.setId("grid-teacher");                
                break;
            case 2:
                lbl.setId("grid-class");
                break;
            default:
                break;
        }
        return lbl;
    }
    
    public void setData(String textSub, String textTeach, String textClass, Schedule.Day Day, int Lesson){
        vb = new VBox(NewL(textSub, 0), NewL(textTeach, 1), NewL(textClass, 2));
        vb.setId("grid-vbox");
        switch(Day){
            case MONDAY:
                day = 0;
                break;
            case TUESDAY:
                day = 1;
                break;
            case WEDNESDAY:
                day = 2;
                break;
            case THURSDAY:
                day = 3;
                break;
            case FRIDAY:
                day = 4;
                break;
            default:
                day = -1;
                break;
        }
        lesson = Lesson;
    }
    
    public void setData(String textSub, String textTeach, String textClass, int Day, int Lesson){
        vb = new VBox(NewL(textSub, 0), NewL(textTeach, 1), NewL(textClass, 2));
        vb.setId("grid-vbox");
        day = Day;
        lesson = Lesson;
    }
    
    public int getRow(){
        return lesson;
    }
    
    public int getColumn(){
        return day;
    }
}
