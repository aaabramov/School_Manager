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

public class Student extends Person {

    private int id_group;

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public static class Builder extends Person.Builder<Student.Builder>{
        
        private int id_group;
        
        public Builder(){}
        
        public Builder id_group(int id_group){
            this.id_group = id_group; return this;
        }
        
        public Student build(){
            return new Student(this);
        }
        
    }
    
    public Student(Student.Builder builder){
        super(builder);
        this.id_group = builder.id_group;
    }

    @Override
    public String toString() {

        String result = "Student profile:"
                + "\nFirst name: " + getFirstName()
                + "\nLast name: " + getLastName()
                + "\nPatronymic: " + getPatronymic()
                + "\nClass: " + id_group
                + "\nAddress: " + getAddress()
                + "\nPhone:" + getPhone()
                + "\nBirthday: " + getBirthday();

        return result;

    }

}
