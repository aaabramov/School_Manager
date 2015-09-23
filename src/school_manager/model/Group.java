/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import java.util.ArrayList;

/**
 *
 * @author abrasha
 */
public class Group implements Notable {
    
    private int id;
    private String code;
    private ArrayList<Student> list;
    private Teacher teacher;
    private String specialNotes;

    public Group(int id, String code, ArrayList<Student> list, Teacher teacher, String specialNotes) {
        this.id = id;
        this.code = code;
        this.list = list;
        this.teacher = teacher;
        this.specialNotes = specialNotes;
    }

    public Group(String code, ArrayList<Student> list, Teacher teacher) {
        this.code = code;
        this.list = list;
        this.teacher = teacher;
    }
    
    @Override
    public void addNote(String note){
        specialNotes += "; " + note;
    }

    @Override
    public String getNote(){
        return specialNotes;
    }

    @Override
    public void removeNotes(){
        specialNotes = "";
    }
    
    public Teacher getTeacher(){
        return teacher;
    }
    
    public ArrayList<Student> getList(){
        return list;
    }
    
    public String getCode(){
        return code;
    }

    
    
}
