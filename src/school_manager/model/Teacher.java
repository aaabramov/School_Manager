/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

/**
 *
 * @author abrasha
 */
public class Teacher extends Person {

    private String subjects;
    
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

        public Teacher build() {
            return new Teacher(this);
        }

    }

    public Teacher(Teacher.Builder builder) {

        super(builder);
        this.subjects = builder.subjects;
    }

    @Override
    public String toString(){
        
        String result = "Teacher profile:"
                + "\nFirst name: " + getFirstName()
                + "\nLast name: " + getLastName()
                + "\nPatronymic: " + getPatronymic()
                + "\nAddress: " + getAddress()
                + "\nPhone:" + getPhone()
                + "\nBirthday: " + getBirthday()
                + "\nSubjects: " + subjects;
        
        return result;
    }

}
