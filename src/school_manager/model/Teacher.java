/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import school_manager.helpers.DatabaseManager;

/**
 *
 * @author abrasha
 */
public class Teacher extends Person {

    private String subjects; // Stores the enumeration of subject ids like "1,2,3,..."
    
    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public static class Builder extends Person.Builder<Teacher.Builder>{
        
        private String subjects;

        public Builder() {}
        
        public Builder subjects(String subjects){
            this.subjects = subjects;
            return this;
        }

        @Override
        public Teacher build() {
            return new Teacher(this);
        }

    }

    public Teacher(Teacher.Builder builder) {

        super(builder);
        this.subjects = builder.subjects;
    }

    public String getSubjectsAsList(){
        String result = "";
        String[] subjectIds = subjects.split(",");
        for (int i = 0; i < subjectIds.length; i++) {
            if (i != subjectIds.length - 1)
                result += DatabaseManager.getSubjectById(Integer.valueOf(subjectIds[i])).toString() + ", ";
            else 
                result += DatabaseManager.getSubjectById(Integer.valueOf(subjectIds[i])).toString();
        }
        
        return result;
    }
    
    @Override
    public String toString(){
        
        String result = "Teacher profile:"
                + "\nFirst name: " + getFName()
                + "\nLast name: " + getLName()
                + "\nPatronymic: " + getPatronymic()
                + "\nAddress: " + getAddress()
                + "\nPhone:" + getPhone()
                + "\nBirthday: " + getBirthday()
                + "\nSubjects: " + getSubjectsAsList();
        
        return result;
    }

}
