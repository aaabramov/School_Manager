/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model.overviews;

import school_manager.model.schedule.Lesson;
import school_manager.model.schedule.Schedule;
import school_manager.model.schedule.Schedule.Day;
import school_manager.model.schedule.StudentLesson;

/**

 @author abrasha
 */
public class LessonOverview {

    private SubjectOverview subject;
    private Day day;
    private String classroom;
    private int order;
    private int idLesson;

    protected LessonOverview(LessonOverview.Builder builder){
        this.subject = builder.subject;
        this.day = builder.day;
        this.classroom = builder.classroom;
        this.order = builder.order;
        this.idLesson = builder.idLesson;
    }

    public static class Builder<T extends LessonOverview.Builder> {

        private SubjectOverview subject;
        private Schedule.Day day;
        private String classroom;
        private int order;
        private int idLesson;

        public T day(Schedule.Day day){
            this.day = day;
            return (T) this;
        }

        public T order(int order){
            this.order = order;
            return (T) this;
        }

        public T idLesson(int id){
            this.idLesson = id;
            return (T) this;

        }

        public T classroom(String classroom){
            this.classroom = classroom;
            return (T) this;
        }

        public T subject(SubjectOverview subject){
            this.subject = subject;
            return (T) this;
        }

        public LessonOverview build(){
            return (new LessonOverview(this));
        }

    }

    public SubjectOverview getSubject(){
        return subject;
    }

    public void setSubject(SubjectOverview subject){
        this.subject = subject;
    }

    public Day getDay(){
        return day;
    }

    public void setDay(Day day){
        this.day = day;
    }

    public String getClassroom(){
        return classroom;
    }

    public void setClassroom(String classroom){
        this.classroom = classroom;
    }

    public int getOrder(){
        return order;
    }

    public void setOrder(int order){
        this.order = order;
    }

    public int getIdLesson(){
        return idLesson;
    }

    public void setIdLesson(int idLesson){
        this.idLesson = idLesson;
    }

}
