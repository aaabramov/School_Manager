/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import school_manager.model.Schedule.Lesson;
import school_manager.model.TeacherSchedule.TeacherLesson;

/**

 @author abrasha
 */
public class TeacherSchedule extends Schedule<TeacherLesson> {

    private int teacherId;

    @Override
    public void addLesson(TeacherLesson added){
        if (added != null) {
            getLessonList().add(added);
        }
    }

    public class TeacherLesson extends Lesson {

        int groupId;
        String groupCode;
        public TeacherLesson(int subjectId, String name, String classroom, int groupId,String groupCode, int order, int day){
            super(subjectId, name, classroom, order, day);
            this.groupId = groupId;
            this.groupCode=groupCode;
        }

    }

}
