/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model.schedule;

import school_manager.model.overviews.GroupOverview;
import school_manager.model.overviews.SubjectOverview;
import school_manager.model.overviews.TeacherOverview;

/**

 @author abrasha
 */
public class Lesson {

    private GroupOverview group;
    private TeacherOverview teacher;
    private SubjectOverview subject;
    private Schedule.Day day;
    private String classroom;
    private int order;
    private int idLesson;

    private Lesson(Builder builder){
        this.group = builder.group;
        this.teacher = builder.teacher;
        this.subject = builder.subject;
        this.day = builder.day;
        this.classroom = builder.classroom;
        this.order = builder.order;
        this.idLesson = builder.idLesson;
    }

    public static class Builder {

        private GroupOverview group;
        private TeacherOverview teacher;
        private SubjectOverview subject;
        private Schedule.Day day;
        private String classroom;
        private int order;
        private int idLesson;

        public Lesson.Builder group(GroupOverview group){
            this.group = group;
            return this;
        }

        public Lesson.Builder teacher(TeacherOverview teacher){
            this.teacher = teacher;
            return this;
        }

        public Lesson.Builder subject(SubjectOverview subject){
            this.subject = subject;
            return this;
        }

        public Lesson.Builder day(Schedule.Day day){
            this.day = day;
            return this;
        }

        public Lesson.Builder order(int order){
            this.order = order;
            return this;
        }

        public Lesson.Builder idLesson(int id){
            this.idLesson = id;
            return this;
        }

        public Lesson.Builder classroom(String classroom){
            this.classroom = classroom;
            return this;
        }

        public Lesson build(){
            return new Lesson(this);
        }

    }

    public GroupOverview getGroup(){
        return group;
    }

    public void setGroup(GroupOverview group){
        this.group = group;
    }

    public TeacherOverview getTeacher(){
        return teacher;
    }

    public void setTeacher(TeacherOverview teacher){
        this.teacher = teacher;
    }

    public SubjectOverview getSubject(){
        return subject;
    }

    public void setSubject(SubjectOverview subject){
        this.subject = subject;
    }

    public Schedule.Day getDay(){
        return day;
    }

    public void setDay(Schedule.Day day){
        this.day = day;
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
    
    public String getClassroom(){
        return classroom;
    }
    
    public void setClassroom(String classroom){
        this.classroom = classroom;
    }
}
