/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import java.util.List;
import school_manager.model.overviews.StudentOverview;

public class Group {

    private final int idGroup;

    private final int idCurator;
    private final String code;
    private final List<StudentOverview> students;
    private String notes;

    private Group(Group.Builder builder){
        this.idGroup = builder.idGroup;
        this.code = builder.code;
        this.students = builder.list;
        this.idCurator = builder.idCurator;
        this.notes = builder.notes;
    }

    public int getIdGroup(){
        return idGroup;
    }

    public int getIdCurator(){
        return idCurator;
    }

    public void addNote(String note){
        notes += "; " + note;
    }

    public String getNote(){
        return notes;
    }

    public int getCuratorId(){
        return idCurator;
    }

    public List<StudentOverview> getStudents(){
        return students;
    }

    public String getCode(){
        return code;
    }

    @Override
    public String toString(){
        return code;
    }

    public static class Builder {

        int idGroup;
        String code;
        private List<StudentOverview> list;
        int idCurator;
        String notes;

        public Group.Builder idGroup(int idGroup){
            this.idGroup = idGroup;
            return this;
        }

        public Group.Builder code(String code){
            this.code = code;
            return this;
        }

        public Group.Builder list(List<StudentOverview> list){
            this.list = list;
            return this;
        }

        public Group.Builder notes(String notes){
            this.notes = notes;
            return this;
        }

        public Group.Builder idCurator(int idCurator){
            this.idCurator = idCurator;
            return this;
        }

        public Group build(){
            return (new Group(this));
        }
    }

}
