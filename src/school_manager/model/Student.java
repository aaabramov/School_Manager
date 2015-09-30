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

    int id_group;

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    /*public static class Builder {

        private String firstName;
        private String lastName;
        private String patronymic;
        private String address;
        private String phone;
        private String bday;
        private String notes;
        private int id_group;
        public Builder(){}
        public Builder fname(String fname){
            this.firstName = fname; return this;
        }
        public Builder lname(String lname){
            this.lastName = lname; return this;
        }
        public Builder patronymic(String patronymic){
            this.patronymic = patronymic; return this;
        }
        public Builder id_group(int id_group){
            this.id_group = id_group; return this;
        }
        public Builder bday(String bday){
            this.bday = bday; return this;
        }
        public Builder address(String address){
            this.address = address; return this;
        }
        public Builder phone(String phone){
            this.phone = phone; return this;
        }
        public Builder notes(String notes){
            this.notes = notes; return this;
        }
        public Student build(){
            return new Student(this);
        }

    }*/
    
    public 

    public Student(String firstName, String lastName, String patronymic, String address, String phone, String birthday, String specialNotes, int id_group) {
        super(firstName, lastName, patronymic, address, phone, birthday, specialNotes);
        this.id_group = id_group;
    }

    public Student(Student.Builder builder){
        super(builder.firstName, builder.lastName, builder.patronymic, builder.address, builder.phone, builder.bday, builder.notes);
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
