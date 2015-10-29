/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import java.util.LinkedList;
import java.util.List;
import school_manager.model.Schedule.Lesson;
import school_manager.model.overviews.SubjectOverview;

/**

 @author abrasha
 */
public abstract class Schedule<T extends Lesson> {


    private final List<T> lessons;

    public Schedule(){
        lessons = new LinkedList<>();
    }

    public abstract void addLesson(T added);

    public List<T> getLessonList(){
        return lessons;
    }

    public enum Day {

        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public abstract class Lesson extends SubjectOverview {

        int order;
        String classroom;
        Day day;

        public Lesson(int subjectId, String name, String classroom, int order, Day day){
            super(name, subjectId);
            this.classroom = classroom;
            this.order = order;
            this.day = day;
        }

    }

}
