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

    public static class Builder {

        private String firstName;
        private String lastName;
        private String patronymic;
        private String address;
        private String phone;
        private String bday;
        private String notes;
        private String subjects;

        public Builder() {
        }

        public Builder fname(String fname) {
            this.firstName = fname;
            return this;
        }

        public Builder lname(String lname) {
            this.lastName = lname;
            return this;
        }

        public Builder patronymic(String patronymic) {
            this.patronymic = patronymic;
            return this;
        }

        public Builder bday(String bday) {
            this.bday = bday;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder subjects(String subjects) {
            this.subjects = subjects;
            return this;
        }

        public Teacher build() {
            return new Teacher(this);
        }

    }

    public Teacher(Builder builder) {

        super(builder.firstName, builder.lastName, builder.patronymic, builder.address, builder.phone, builder.bday, builder.notes);
        this.subjects = builder.subjects;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

}
