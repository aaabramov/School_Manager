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
public class Person {
    
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    
    public Person(String firstName, String lastName, String birthday){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = DateTimeConverter.parse(birthday);
    }
    
    public Person(){
        firstName = "";
        lastName = "";
        birthday = LocalDate.now();
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
    
    
    
}
