/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import java.util.Arrays;

/**
 *
 * @author abrasha
 */
public class Teacher extends Person {

    private Subject[] subjects;
    
    public Subject[] getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }

    public static class Builder extends Person.Builder<Teacher.Builder>{
        
        private Subject[] subjects;

        public Builder() {}
        
        public Builder subjects(Subject[] subjects){
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

    public String getSubjectsAsId(){
        String result = "";
        for (Subject s : subjects){
            result += s.getId() + " ";
        }
        return  result;
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
                + "\nSubjects: " + Arrays.toString(subjects);
        
        return result;
    }

}
