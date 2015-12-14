/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model.schedule;

import school_manager.model.overviews.LessonOverview;
import school_manager.model.overviews.TeacherOverview;

/**

 @author abrasha
 */
public class StudentLesson extends LessonOverview {
    
    private TeacherOverview teacher;

    public TeacherOverview getTeacher(){
        return teacher;
    }

    public void setTeacher(TeacherOverview teacher){
        this.teacher = teacher;
    }

    protected StudentLesson(Builder builder){
        super(builder);
        this.teacher = builder.teacher;
    }

    public static class Builder extends LessonOverview.Builder<StudentLesson.Builder> {
        
        private TeacherOverview teacher;
        
        public StudentLesson.Builder teacher(TeacherOverview teacher){
            this.teacher = teacher; return this;
        }
        
        @Override
        public StudentLesson build(){
            return new StudentLesson(this);
        }
        
    }
    
}
