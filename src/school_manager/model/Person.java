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
public abstract class Person implements Notable {

    private String firstName;
    private String lastName;
    private String patronymic;
    private String address;
    private String phone;
    private String bday;
    private String notes;

    public Person(String firstName, String lastName, String patronymic, String address, String phone, String birthday, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.address = address;
        this.bday = birthday;
        this.notes = notes;
        this.phone = phone;
    }
    
    /**
    *
    * @author bepa
    */
        public static class Builder <T extends Person.Builder>{
            private String firstName;
            private String lastName;
            private String patronymic;
            private String address;
            private String bday;
            private String phone;
            private String notes;
            
            public Builder(){}
            
            public T fName(String fname){
                this.firstName = fname;
                return (T)this;
            }
            
            public T lName(String lname){
                this.lastName = lname;
                return  (T)this;
            }
            
            public T patronymic(String patr){
                this.patronymic = patr;
                return (T)this;
            }
            
            public T address(String addr){
                this.address = addr;
                return (T)this;
            }
            
            public T bday(String BDay){
                this.bday = BDay;
                return (T)this;
            }
            
            public T notes(String note){
                this.notes = note;
                return (T)this;
            }
            
            public T phone(String phone){
                this.phone = phone;
                return (T)this;
            }
            
        }
        
        

    public Person(Builder builder) {
        
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.patronymic = builder.patronymic;
        this.address = builder.address;
        this.phone = builder.phone;
        this.bday = builder.bday;
        this.notes = builder.notes;
        
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialNotes() {
        return notes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.notes = specialNotes;
    }

    @Override
    public void addNote(String note) {
        notes += "; " + note;
    }

    @Override
    public String getNote() {
        return notes;
    }

    @Override
    public void removeNotes() {
        notes = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return bday;
    }

    public void setBirthday(String birthday) {
        this.bday = birthday;
    }

}
