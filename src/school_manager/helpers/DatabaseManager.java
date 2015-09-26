/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import school_manager.model.Parent;
import school_manager.model.Person.Sex;
import school_manager.model.Student;

/**
 *
 * @author abrasha
 */
public class DatabaseManager {
    
    private static Connection connection = null;
    private static Statement statement = null;
    private static final String DBAddress = "127.0.0.1";
    private static final String DBPort = "3306";
    private static final String DBName = "projectdb";
    private static final String DBLogin = "root";
    private static final String DBPassword = "root";

        
    static {
        
        
        try {
            
            String dbUrl = "jdbc:mysql://" + DBAddress + ":" + DBPort + "/" + DBName;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, DBLogin, DBPassword);
            
            if (connection == null){
                System.err.println("Error in creating connection(null)");
            } else {
                
                statement = connection.createStatement();
                
            }
                
            
        } catch (ClassNotFoundException | SQLException e){
            
            System.out.println("Error connectiong to db: " + e.getMessage());
            
        }
        
    }
    
    
    
    public static void close(){
        
        try {
            
            connection.close();
            statement.close();
            
        } catch (Exception e){
            
            System.out.println("Error in closing db: " + e.getMessage());
            
        }
    }
    
    
    
    public static Student getStudentById(int id){
        
        Student result = null;
        
        try {
            // TODO
            ResultSet rs = statement.executeQuery("SELECT * FROM students WHERE id = " + id);
            if (rs.next()){
                
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String patronymic = rs.getString("patronymic");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String bday = rs.getString("bday");
                String specialNotes = rs.getString("special_notes");
                String groupCode = getGroupCodeById(rs.getInt("group_id"));
                Sex sex = rs.getInt("sex") == 1 ? Sex.MALE : Sex.FEMALE;
                
                result = new Student(firstName, lastName, patronymic, address, phone, bday, sex, specialNotes, groupCode);
            }
            
        } catch (SQLException e){
            
            System.out.println("Error quering data: " + "SELECT * FROM students WHERE id = " + id);
            
        }
        
        return result;
        
    }
    
    
    
    public static Parent getParentBuId(int id){
        
        Parent result = null;
        //TODO
        
        return result;
    }
    
    
    
    public static boolean authorize(String login, String password){
        
        boolean success = false;
        
        try {
            
            ResultSet rs = statement.executeQuery("SELECT password FROM users WHERE login = \"" + login + "\"");
            
            if (rs.next()){
                String dbpassword = rs.getString("password");
                
                success = dbpassword.equals(password);
            }
            
        } catch (SQLException e){
            
            System.out.println("Error quering data: " + "SELECT password FROM users WHERE login = \"" + login + "\"");
            
        }
        
        return success;
        
    }
    
    
    
    public static AccountInfo getAccountInfoByLogin(String login){
        
        AccountInfo result = null;
        
        try {
            
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE login = \"" + login + "\"");
            
            if (rs.next()){
                
                result = new AccountInfo(rs.getInt("acc_type"), login, rs.getInt("acc_id"));
                
            }
            
        } catch (SQLException e){
            
            System.out.println("Error quering data: " + "SELECT * FROM users WHERE login = \"" + login + "\"");
            
        }
            
            
        return result;
        
    }
    
    
    
    public static String getGroupCodeById(int id){
        
        try {
            
            ResultSet rs = statement.executeQuery("SELECT code FROM groups WHERE id = " + id);
            
            if (rs.next()){
                return rs.getString("code");
            }
            
        } catch (Exception e){
            
        }
        
        return "";
        
    }
        
    
}
