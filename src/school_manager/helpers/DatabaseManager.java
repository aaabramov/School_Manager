package school_manager.helpers;

import school_manager.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import school_manager.model.Student;
import school_manager.model.Subject;
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

    /**
     *
     * @author abrasha
     *
     * loading database connection
     */
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

    /**
     *
     * @author abrasha
     *
     * closes databases connection
     */
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

    /**
     *
     * @author abrasha
     *
     * inserts new student to database
     */
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
            preStatement.setString(2, added.getFName());
            preStatement.setString(3, added.getLName());
            preStatement.setString(4, added.getPatronymic());
            preStatement.setInt(5, added.getGroupId());
            preStatement.setString(6, added.getBirthday());
            preStatement.setString(7, added.getAddress());
            preStatement.setString(8, added.getPhone());
            preStatement.setString(9, added.getNotes());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }

    }

    /**
     *
     * @author abrasha
     *
     * inserts new user to database
     */
    public static void insertUser(User user) {
        try {
            String sqlStatement = ("INSERT INTO users (login, password, acc_type) VALUES (?, ?, ?);");
            preStatement = connection.prepareStatement(sqlStatement);
            preStatement.setInt(1, user.getLogin());
            preStatement.setString(2, PasswordGenerator.generate());
            preStatement.setInt(3, user.getAccTypeCode());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("insertUser failed: " + e.getMessage());
        }

    }

    /**
     *
     * @author abrasha
     *
     * returns the last inserted id from users table
     */
    private static int getLastIdFromUsers() {

        int result = -1;

        try {

            ResultSet rs = statement.executeQuery("SELECT MAX(id_user) max_id FROM users;");
            if (rs.next()) {
                System.out.println("Max id found");
            }
            result = rs.getInt("max_id");
        } catch (SQLException e) {
            System.out.println("getLastIdFromUsers failed: " + e.getMessage());
        }

        return result;
    }

    /**
     *
     * @author abrasha
     *
     * process of authorization into program
     */
    public static User authorize(int login, String password) {

        User result = null;

        try {
            preStatement = connection.prepareStatement("SELECT * FROM users WHERE login = ?;");
            preStatement.setInt(1, login);
            ResultSet rs = preStatement.executeQuery();

            if (rs.next()) {
                String dbpass = rs.getString("password");
                if (password.equals(dbpass)) {
                    result = new User(rs.getInt("id_user"), login, rs.getInt("acc_type"));
                }
            }

        } catch (SQLException e) {
            System.out.println("authorize fail: " + e.getMessage());
        }

        return result;

    }

    /**
     *
     * @author abrasha
     *
     * @return full list of subjects
     */
    public static ArrayList<Subject> getSubjects(){
        ArrayList<Subject> result = new ArrayList<>();
        
        try {
            
            ResultSet rs = statement.executeQuery("SELECT * FROM subjects;");
            
            while (rs.next()){
                
                result.add(new Subject(rs.getInt("id_subject"), rs.getString("name"), rs.getString("description")));
                
            }
            
        } catch (SQLException e){
            System.out.println("getSubjects() failed: " + e.getMessage());
        }
        
        return result;
        
    }
    
    /**
     *
     * @author abrasha
     *
     * @return Subject with concrete id
     */
    public static  Subject getSubjectById(int id_subject){
        
        Subject result = null;
        
        try {
            
            ResultSet rs = statement.executeQuery("SELECT * FROM subjects WHERE id_subject = " + id_subject + ";");
            
            if (rs.next()){
                
                result = new Subject(rs.getInt("id_subject"), rs.getString("name"), rs.getString("description"));
                
            }
            
        } catch (SQLException e){
            System.out.println("getSubjects(int) failed: " + e.getMessage());            
        }
        
        return result;
        
    }
    
    /**
 *
 * @author bepa
 * 
 * inserts new teacher to database
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
            preStatement.setString(2, added.getFName());
            preStatement.setString(3, added.getLName());
            preStatement.setString(4, added.getPatronymic());
            preStatement.setString(5, added.getSubjectsAsId());
            preStatement.setString(6, added.getNotes());
            preStatement.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error adding teacher: " + e.getMessage());
        }
        
    }
    
 /**
 *
 * @author bepa
 * 
 * gets teacher from database
 */
    
    public static Teacher getTeacherById(int id) {
        
        //ЗАПРОСЫ ДЛЯ БД И В ИТОГЕ ВСЕ ДАННЫЕ
        
        //String sqlStatement = "";
        
        Teacher current = new Teacher.Builder()
                .fName("Andriy")
                .lName("Abramov")
                .patronymic("Volodymyrovych")
                .bday("08.12.96")
                .address("")
                .phone("+380---------")
                .notes("lalalal")
//                .subjects(subjects)
                .build();
        
        return current;
    }
    
}
