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
public class Student extends Person {
    
    String groupCode;

    public Student(int id, String firstName, String lastName, String patronymic, String address, String phone, String birthday, Sex sex, String specialNotes, String groupCode) {
        super(id, firstName, lastName, patronymic, address, phone, birthday, sex, specialNotes);
        this.groupCode = groupCode;
    }
    
    public Student(){}

    @Override
    public String toString() {
        
        String result = "Student profile:" +
                        "\nFirst name: " + getFirstName() +
                        "\nLast name: " + getLastName() + 
                        "\nPatronymic: " + getPatronymic() +
                        "\nClass: " + groupCode + 
                        "\nAddress: " + getAddress() +
                        "\nPhone:" + getPhone() +
                        "\nBirthday: " + DateTimeConverter.format(getBirthday());
        
        return result;
        
    }
    
    
    
    
    
}
