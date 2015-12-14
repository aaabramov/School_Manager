package school_manager.model.schedule;

import school_manager.model.overviews.GroupOverview;

/**

 @author abrasha
 */
public class StudentSchedule extends Schedule<StudentLesson> {

    GroupOverview group;

    public GroupOverview getGroup(){
        return group;
    }

    public void setGroup(GroupOverview group){
        this.group = group;
    }

    public StudentSchedule(GroupOverview group){
        this.group = group;
    }

    @Override
    public void addLesson(StudentLesson added){
        if (added != null) {
            getLessonList().add(added);
        }
    }

}
