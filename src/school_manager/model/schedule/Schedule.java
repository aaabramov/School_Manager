/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model.schedule;

import java.util.LinkedList;
import java.util.List;
import school_manager.model.overviews.LessonOverview;

/**

 @author abrasha
 */
public abstract class Schedule<T extends LessonOverview> {

    private final List<T> lessons;

    public Schedule(){
        lessons = new LinkedList<>();
    }

    public abstract void addLesson(T added);

    public List<T> getLessonList(){
        return lessons;
    }
    
    public void format(){
        lessons.sort( (e1, e2) -> {
            return ( e2.getDay().ordinal() - e1.getDay().ordinal());
        });
    }

    public static enum Day {

        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, UNKNOWN
        // MONDAY = 0, TUESDAY = 1, .....
        // to parse INTEGER values to ENUM user DateTimeConverter.parseDayOfWeek(INTEGER);
    }

}
