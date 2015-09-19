/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import java.time.LocalDate;
import school_manager.helpers.DateTimeConverter;

/**
 *
 * @author abrasha
 */
public class Person implements Notable {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Sex sex;
    private String specialNotes;
    
    public Person(String firstName, String lastName, String birthday, Sex sex){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = DateTimeConverter.parse(birthday);
        this.sex = sex;
    }
    
    public Person(String firstName, String lastName, LocalDate birthday, Sex sex){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sex = sex;
    }
    
    public Person(){
        firstName = "";
        lastName = "";
        birthday = LocalDate.now();
        sex = Sex.UNKNOWN;
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
    
    public enum Sex {
        MALE, FEMALE, UNKNOWN
    }
    
    

    public String getFirstName() {
        return firstName;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
    
    
    
}
