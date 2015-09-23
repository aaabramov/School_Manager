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
public abstract class Person implements Notable {


    private String firstName;
    private String lastName;
    private String patronymic;
    private String address;
    private String phone;
    private LocalDate birthday;
    private Sex sex;
    private String specialNotes;
    
    public Person(String firstName, String lastName, String patronymic, String address, String phone, String birthday, Sex sex, String specialNotes){
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.address = address;
        this.birthday = DateTimeConverter.parse(birthday);
        this.sex = sex;
        this.specialNotes = specialNotes;
        this.phone = phone;
    }
    
    public Person(){
        firstName = "";
        lastName = "";
        birthday = LocalDate.now();
        sex = Sex.UNKNOWN;
    }
    
    
    
    public String getPatronymic(){
        return patronymic;
    }

    public void setPatronymic(String patronymic){
        this.patronymic = patronymic;
    }

    public String getAddress(){
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
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    @Override
    public void addNote(String note) {
        specialNotes += "; " + note;
    }

    @Override
    public String getNote() {
        return specialNotes;
    }

    @Override
    public void removeNotes() {
        specialNotes = "";
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

    public enum Sex {

        MALE, FEMALE, UNKNOWN
    }
    
    
    
}
