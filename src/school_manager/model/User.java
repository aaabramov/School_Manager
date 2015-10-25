/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.logging.Logger;
import school_manager.helpers.DatabaseManager;

/**
 *
 * @author abrasha
 */
public class User {
    
    @JsonProperty("accType")
    private AccType accType;
    @JsonProperty("login")
    private int login;
    @JsonProperty("id")
    private int id;

    public User(int id, int login, AccType accType){

        this.id = id;
        this.login = login;
        this.accType = accType;

    }
    
    public User(int id, int login, int accType){

        this.id = id;
        this.login = login;
        
        switch (accType){
            
            case DatabaseManager.STUDENT_TYPE:
                this.accType = AccType.STUDENT;
                break;
            case DatabaseManager.TEACHER_TYPE:
                this.accType = AccType.TEACHER;                
                break;
            case DatabaseManager.PARENT_TYPE:
                this.accType = AccType.PARENT;
                break;
            case DatabaseManager.ADMIN_TYPE:
                this.accType = AccType.ADMIN;
                break;
            default:
                this.accType = AccType.UNKNOWN;
                break;
            
        }

    }

    public int getAccTypeCode() {

        int result = -1;

        
        switch (accType) {

            case STUDENT:
                result = 0;
                break;
            case TEACHER:
                result = 1;
                break;
            case PARENT:
                result = 2;
                break;
            case ADMIN:
                result = 3;
                break;
            case UNKNOWN:
                result = -1;
                break;

        }

        return result;
    }

    public AccType getAccType() {
        return accType;
    }

    public void setAccType(AccType accType) {
        this.accType = accType;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" + "accType=" + accType + ", login=" + login + ", id=" + id + '}';
    }

    public enum AccType {

        STUDENT, TEACHER, ADMIN, PARENT, UNKNOWN
    }

}
