/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.helpers;

/**
 *
 * @author abrasha
 */
public class AccountInfo {

    
    private AccType accType;
    private String login;
    private int id;

    public AccountInfo(int accTypeCode, String login, int id) {
        
        switch (accTypeCode){
            
            case 0:
                this.accType = AccType.STUDENT;
                break;
            case 1:
                this.accType = AccType.TEACHER;
                break;
            case 2:
                this.accType = AccType.PARENT;
                break;
            case 3:
                this.accType = AccType.ADMIN;
                break;
            default:
                this.accType = AccType.UNKNOWN;
                break;
            
        }
        
        this.id = id;
        this.login = login;
        
    }

    public AccType getAccType() {
        return accType;
    }

    public void setAccType(AccType accType) {
        this.accType = accType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public enum AccType {

        STUDENT, TEACHER, ADMIN, PARENT, UNKNOWN
    }
    
}
