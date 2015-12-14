/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model.schedule;

import school_manager.model.overviews.TeacherOverview;

/**

 @author abrasha
 */
public class TeacherSchedule extends Schedule<TeacherLesson> {

    TeacherOverview teacher;

    public TeacherOverview getTeacher(){
        return teacher;
    }

    public void setTeacher(TeacherOverview teacher){
        this.teacher = teacher;
    }

    public TeacherSchedule(TeacherOverview teacher){
        this.teacher = teacher;
    }

    @Override
    public void addLesson(TeacherLesson added){
        if (added != null) {
            getLessonList().add(added);
        }
    }

}
