/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author abrasha
 */
public abstract class Person {

    @JsonProperty("fname")
    protected String fName;
    @JsonProperty("lName")
    protected String lName;
    @JsonProperty("patronymic")
    protected String patronymic;
    @JsonProperty("address")
    protected String address;
    @JsonProperty("phone")
    protected String phone;
    @JsonProperty("bday")
    protected String bday;
    @JsonProperty("notes")
    protected String notes;
    @JsonProperty("id")
    protected int id;
    
    /**
    * @author bepa
    */
        public static abstract class Builder <T extends Person.Builder>{
            private String fName;
            private String lName;
            private String patronymic;
            private String address;
            private String bday;
            private String phone;
            private String notes;
            private int id;
            
            public Builder(){}
            
            public T id(int id){
                this.id = id; return (T)this;
            }
            
            public T fName(String fname){
                this.fName = fname; return (T)this;
            }
            
            public T lName(String lname){
                this.lName = lname; return  (T)this;
            }
            
            public T patronymic(String patr){
                this.patronymic = patr; return (T)this;
            }
            
            public T address(String addr){
                this.address = addr; return (T)this;
            }
            
            public T bday(String BDay){
                this.bday = BDay; return (T)this;
            }
            
            public T notes(String note){
                this.notes = note; return (T)this;
            }
            
            public T phone(String phone){
                this.phone = phone; return (T)this;
            }
            
            public abstract Person build();
            
        }
        
        

    public Person(Builder builder) {
        
        this.id = builder.id;
        this.fName = builder.fName;
        this.lName = builder.lName;
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

    public void addNote(String note) {
        notes += "; " + note;
    }

    public String getNote() {
        return notes;
    }

    public void removeNotes() {
        notes = "";
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

    public String getBirthday() {
        return bday;
    }

    public void setBirthday(String birthday) {
        this.bday = birthday;
    }
    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }
    
    public String getInitials(){
        return lName + ' ' + fName + ' ' + patronymic;
    }

}
