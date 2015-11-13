/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import school_manager.model.Schedule.Lesson;
import school_manager.model.StudentSchedule.StudentLesson;

/**

 @author abrasha
 */
public class StudentSchedule extends Schedule<StudentLesson> {

    private final int groupId;
    
    public StudentSchedule(int groupId){
        this.groupId = groupId;
    }
    
    @Override
    public void addLesson(StudentLesson added){
        if (added != null)
            getLessonList().add(added);
    }

    public class StudentLesson extends Lesson {

        int teacherId;
        
        public StudentLesson(int subjectId, String name, String classroom, int teacherId, int order, int day){
            super(subjectId, name, classroom, order, day);
            this.teacherId = teacherId;
        }

    }

}
