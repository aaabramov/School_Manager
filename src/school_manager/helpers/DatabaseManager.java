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

/**
 *
 * @author abrasha
 */
public class DatabaseManager {

    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preStatement = null;
    private static final String DBAddress = "127.0.0.1";
    private static final String DBPort = "3306";
    private static final String DBName = "schooldb";
    private static final String DBLogin = "root";
    private static final String DBPassword = "root";
    private static final int LOGIN_START = 10001;
    private static final int STUDENT_TYPE = 0;
    private static final int TEACHER_TYPE = 1;
    private static final int PARENT_TYPE = 2;
    private static final int ADMIN_TYPE = 3;

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
            preStatement.setString(2, user.getPassword());
            preStatement.setInt(3, user.getAccTypeCode());
            preStatement.executeUpdate();
        } catch (SQLException e) {

        }

    }

    private static int getLastIdFromUsers() {

        try {

            ResultSet rs = statement.executeQuery("SELECT MAX(id_user) max_id FROM users;");
            if (rs.next()) {
                System.out.println("Max id found");
            }
            int last_id = rs.getInt("max_id");
            return last_id;
        } catch (SQLException e) {

        }
    }

}
