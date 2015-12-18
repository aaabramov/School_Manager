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
public class Schedule<T extends Lesson> {


    private final List<T> lessons;

    public Schedule(){
        lessons = new LinkedList<>();
    }
   
    public void addLesson(T added)
    {
        lessons.add(added);
    }

    public List<T> getLessonList(){
        return lessons;
    }
    public Lesson getLesson(int i)
    {
        if(lessons.size()>i)
        {return lessons.get(i);}
        else 
        {return null;}
    }

    public enum Day {

        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public class Lesson extends SubjectOverview {

        int order;
        String classroom;
        Day day;
        int groupId;
        int teacherId;

        public int getOrder(){
            return order;
        }

        public void setOrder(int order){
            this.order = order;
        }

        public String getClassroom(){
            return classroom;
        }

        public void setClassroom(String classroom){
            this.classroom = classroom;
        }

        public int getDay(){
            int result;
            switch(day)
            {
                case MONDAY:
                {
                    result = 1; 
                    break;
                }
                case TUESDAY:
                {
                    result = 2;
                    break;
                }
                case WEDNESDAY:
                {
                    result = 3;
                    break;
                }
                case THURSDAY:
                {
                    result = 4 ;
                    break;
                }
                case FRIDAY:
                {
                    result = 5 ;
                    break;
                }
                case SATURDAY:
                {
                    result = 6;
                    break;
                }
                case SUNDAY:
                {
                    result = 7;
                    break;
                }
                default:
                {
                    result = 7 ;
                    break;
                }
            }
             return (result-1);    
        }

        public void setDay(Day day){
            this.day = day;
        }
        
        public void setGroup(int id)
        {
            this.groupId=id;
        }
        public int getGroup()
        {
            return this.groupId;
        }
        
        public void setTeacher(int id)
        {
            this.teacherId=id;
        }
        public int getTeacher()
        {
            return this.teacherId;
        }
        public Lesson(int subjectId, String name, String classroom, int order, int day){
            super(name, subjectId);
            this.classroom = classroom;
            this.order = order;
            
            switch(day)
            {
                case 1:
                {
                    this.day =Schedule.Day.MONDAY ;
                    break;
                }
                case 2:
                {
                    this.day =Schedule.Day.TUESDAY ;
                    break;
                }
                case 3:
                {
                    this.day =Schedule.Day.WEDNESDAY ;
                    break;
                }
                case 4:
                {
                    this.day =Schedule.Day.THURSDAY ;
                    break;
                }
                case 5:
                {
                    this.day =Schedule.Day.FRIDAY ;
                    break;
                }
                case 6:
                {
                    this.day =Schedule.Day.SATURDAY ;
                    break;
                }
                case 7:
                {
                    this.day =Schedule.Day.SUNDAY ;
                    break;
                }
                default:
                {
                    this.day =Schedule.Day.SUNDAY ;
                    break;
                }
            }
        }
        
        public Lesson(int subjectId, String name, String classroom, int order, int day,int group,int teacher){
            super(name, subjectId);
            this.classroom = classroom;
            this.order = order;
            this.groupId=group;
            this.teacherId= teacher;
            switch(day)
            {
                case 1:
                {
                    this.day =Schedule.Day.MONDAY ;
                    break;
                }
                case 2:
                {
                    this.day =Schedule.Day.TUESDAY ;
                    break;
                }
                case 3:
                {
                    this.day =Schedule.Day.WEDNESDAY ;
                    break;
                }
                case 4:
                {
                    this.day =Schedule.Day.THURSDAY ;
                    break;
                }
                case 5:
                {
                    this.day =Schedule.Day.FRIDAY ;
                    break;
                }
                case 6:
                {
                    this.day =Schedule.Day.SATURDAY ;
                    break;
                }
                case 7:
                {
                    this.day =Schedule.Day.SUNDAY ;
                    break;
                }
                default:
                {
                    this.day =Schedule.Day.SUNDAY ;
                    break;
                }
            }
        }

    }

}
