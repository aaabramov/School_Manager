/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import school_manager.helpers.PasswordGenerator;

/**
 *
 * @author abrasha
 */
public class User {

    private AccType accType;
    private int login;
    private int id;
    private String password;

    public User(int id, int login, AccType accType){

        this.id = id;
        this.login = login;
        this.accType = accType;
        this.password = PasswordGenerator.generate();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public enum AccType {

        STUDENT, TEACHER, ADMIN, PARENT, UNKNOWN
    }

}
