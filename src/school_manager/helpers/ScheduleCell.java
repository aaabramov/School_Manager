/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.helpers;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author bepa
 */
public class ScheduleCell {
    private final VBox vb;
    private Label lbl;
    private final int day; //Monday, ...
    private final int lesson; //first, second, ...
    public ScheduleCell(String textSub, String textTeach, String textClass, int Day, int Lesson){
        
        vb = new VBox(NewL(textSub, 0), NewL(textTeach, 1), NewL(textClass, 2));
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
    
    public int getRow(){
        return lesson;
    }
    
    public int getColumn(){
        return day;
    }
}
