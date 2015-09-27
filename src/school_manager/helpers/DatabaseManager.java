/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.helpers;

import school_manager.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import school_manager.model.Student;
import school_manager.model.Teacher;

/**
 *
 * @author abrasha
 */
public final class DatabaseManager {

    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preStatement = null;
    private static final String DBAddress = "stevie.heliohost.org";
    private static final String DBPort = "3306";
    private static final String DBName = "aabrasha_smdb";
    private static final String DBLogin = "aabrasha_andrew";
    private static final String DBPassword = "123234q";
    public static final int LOGIN_START = 10001;
    public static final int STUDENT_TYPE = 0;
    public static final int TEACHER_TYPE = 1;
    public static final int PARENT_TYPE = 2;
    public static final int ADMIN_TYPE = 3;

    // loading database connection  
    static {

        try {

            String dbUrl = "jdbc:mysql://" + DBAddress + ":" + DBPort + "/" + DBName;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, DBLogin, DBPassword);

            if (connection == null) {
                System.err.println("Error in creating connection(null)");
            } else {

                statement = connection.createStatement();

            }

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Error connectiong to db: " + e.getMessage());

        }

    }

    public static void close() {

        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }

        } catch (Exception e) {

            System.out.println("Error in closing db: " + e.getMessage());

        }
    }

    public static void insertStudent(Student added) {
        try {

            int insertedId = getLastIdFromUsers() + 1;
            int login = insertedId + LOGIN_START;
            
            insertUser(new User(insertedId, login, User.AccType.STUDENT));
            
            String sqlStatement = "INSERT INTO students"
                    + "(id_student, fname, lname, patronymic, id_group, bday, address, phone, notes) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preStatement = connection.prepareStatement(sqlStatement);
            preStatement.setInt(1, insertedId);
            preStatement.setString(2, added.getFirstName());
            preStatement.setString(3, added.getLastName());
            preStatement.setString(4, added.getPatronymic());
            preStatement.setInt(5, added.getId_group());
            preStatement.setString(6, added.getBirthday());
            preStatement.setString(7, added.getAddress());
            preStatement.setString(8, added.getPhone());
            preStatement.setString(9, added.getNotes());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }

    }

    public static void insertUser(User user) {
        try {
            String sqlStatement = ("INSERT INTO users (login, password, acc_type) VALUES (?, ?, ?);");
            preStatement = connection.prepareStatement(sqlStatement);
            preStatement.setInt(1, user.getLogin());
            preStatement.setString(2, PasswordGenerator.generate());
            preStatement.setInt(3, user.getAccTypeCode());
            preStatement.executeUpdate();
        } catch (SQLException e) {

        }

    }

    private static int getLastIdFromUsers() {

        int result = -1;
        
        try {

            ResultSet rs = statement.executeQuery("SELECT MAX(id_user) max_id FROM users;");
            if (rs.next()) {
                System.out.println("Max id found");
            }
            result = rs.getInt("max_id");
        } catch (SQLException e) {

        }
        
        return result;
    }

    public static User authorize(int login, String password){
        
        User result = null;
        
        System.out.println("Login: " + login);
        System.out.println("Pass: " + password);
        
        try {
            preStatement = connection.prepareStatement("SELECT * FROM users WHERE login = ?;");
            preStatement.setInt(1, login);
            ResultSet rs = preStatement.executeQuery();
            
            
            if (rs.next()){
                
                System.out.println("DBLogin: " + rs.getInt("login"));
                System.out.println("DBPass: " + rs.getString("password"));
                
                String dbpass = rs.getString("password");
                
                if (password.equals(dbpass)){
                    result = new User(rs.getInt("id_user"), login, rs.getInt("acc_type"));
                }
                
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return result;
        
    }
    
    /**
 *
 * @author bepa
 */
    
    public static void insertTeacher(Teacher added) {
        try{
            
            int insertedId = getLastIdFromUsers() + 1;
            int login = insertedId + LOGIN_START;
            
            insertUser(new User(insertedId, login, User.AccType.TEACHER));
            
            String sqlStatement = "INSERT INTO teachers" 
                    + "(id_teachers, fname, lname, patronymic, subjects, notes)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            preStatement = connection.prepareStatement(sqlStatement);
            preStatement.setInt(1, insertedId);
            preStatement.setString(2, added.getFirstName());
            
            
            
            
            
        }catch(SQLException e){
            System.out.println("Error adding teacher: " + e.getMessage());
        }
        
    }
    
}
