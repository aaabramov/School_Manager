/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import java.util.ArrayList;



public class Group {

    private int idGroup;
    private String code;
    private ArrayList<Student> list;
    private int idCurator;
    private String specialNotes;

    public Group(int idGroup, String code, ArrayList<Student> list, int idCurator, String specialNotes) {
        this.idGroup = idGroup;
        this.code = code;
        this.list = list;
        this.idCurator = idCurator;
        this.specialNotes = specialNotes;
    }

    public Group(String code, ArrayList<Student> list, int idGroup) {
        this.code = code;
        this.list = list;
        this.idGroup = idGroup;
    }

    public void addNote(String note){
        specialNotes += "; " + note;
    }

    public String getNote(){
        return specialNotes;
    }

    public void removeNotes(){
        specialNotes = "";
    }

    public int getTeacher(){
        return idCurator;
    }

    public ArrayList<Student> getList(){
        return list;
    }

    public String getCode(){
        return code;
    }



}
