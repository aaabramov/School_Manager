/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import java.util.Map;



public class Group {

    private final int idGroup;
    private final int idCurator;
    private String notes;
    private Map<String, Integer> list;
    private final String code;
    
    
    public Group(Group.Builder builder) {
        this.idGroup = builder.idGroup;
        this.code = builder.code;
        this.list = builder.list;
        this.idCurator = builder.idCurator;
        this.notes = builder.notes;
    }

    public void addNote(String note){
        notes += "; " + note;
    }

    public String getNote(){
        return notes;
    }

    public void removeNotes(){
        notes = "";
    }

    public int getTeacher(){
        return idCurator;
    }

    public Map<String, Integer> getList(){
        return list;
    }

    public String getCode(){
        return code;
    }

    public static class Builder {

        int idGroup;
        String code;
        private Map<String, Integer> list;
        int idCurator;
        String notes;

        public Group.Builder idGroup(int idGroup){
            this.idGroup = idGroup; return this;
        }

        public Group.Builder code(String code){
            this.code = code; return this;
        }

        public Group.Builder list(Map<String, Integer> list){
            this.list = list; return this;
        }

        public Group.Builder notes(String notes){
            this.notes = notes; return this;
        }

        public Group.Builder idCurator(int idCurator){
            this.idCurator = idCurator; return this;
        }

        public Group build(){
            return (new Group(this));
        }
    }



}
