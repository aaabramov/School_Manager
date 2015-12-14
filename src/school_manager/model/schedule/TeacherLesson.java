/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model.schedule;

import school_manager.model.overviews.GroupOverview;
import school_manager.model.overviews.LessonOverview;

/**

 @author abrasha
 */
public class TeacherLesson extends LessonOverview {

    private GroupOverview group;

    protected TeacherLesson(Builder builder){
        super(builder);
        this.group = builder.group;
    }

    public static class Builder extends LessonOverview.Builder<TeacherLesson.Builder> {

        private GroupOverview group;

        public TeacherLesson.Builder group(GroupOverview group){
            this.group = group;
            return this;
        }

        @Override
        public TeacherLesson build(){
            return (new TeacherLesson(this));
        }

    }
}
