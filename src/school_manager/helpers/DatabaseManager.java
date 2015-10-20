package school_manager.helpers;

import school_manager.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import school_manager.model.Admin;
import school_manager.model.Parent;
import school_manager.model.Student;
import school_manager.model.Subject;
import school_manager.model.Teacher;

/**
 *
 * @author abrasha
 */
public final class DatabaseManager {

    private static final Logger logger;
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preStatement = null;
    private static final String DBAddress = "stevie.heliohost.org";
    private static final String DBPort = "3306";
    private static final String DBName = "aabrasha_smdb";
    private static final String DBLogin = "aabrasha_andrew";
    private static final String DBPassword = "123234q";
    private static final int LOGIN_START = 10001;
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

        logger = Logger.getLogger(DatabaseManager.class.getCanonicalName());

        try {

            String dbUrl = "jdbc:mysql://" + DBAddress + ":" + DBPort + "/" + DBName;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, DBLogin, DBPassword);

            if (connection == null) {
                logger.log(Level.WARNING, "Cannot establish connection with " + DBPort + ":" + DBName);
            } else {
                statement = connection.createStatement();
            }

        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.WARNING, "Cannot establish connection with " + DBPort + ":" + DBName, e);
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
                logger.log(Level.SEVERE, "Connection closed.");
                connection.close();
            }
            if (statement != null) {
                logger.log(Level.SEVERE, "statement closed.");
                statement.close();
            }

        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception in closing db", e);
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
            logger.log(Level.SEVERE, "Student inserted.");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error inserting student", e);

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
            logger.log(Level.SEVERE, "User inserted.");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error inserting user", e);
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
            logger.log(Level.WARNING, "Error getting last id from users", e);
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
            logger.log(Level.SEVERE, "Authjrization successful.");

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error authorizing", e);
        }

        return result;

    }

    /**
     *
     * @author abrasha
     *
     * @return full list of subjects
     */
    public static ArrayList<Subject> getSubjects() {
        ArrayList<Subject> result = new ArrayList<>();

        try {

            ResultSet rs = statement.executeQuery("SELECT * FROM subjects;");

            while (rs.next()) {

                result.add(new Subject(rs.getInt("id_subject"), rs.getString("name"), rs.getString("description")));

            }

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error getting subjects", e);
        }

        return result;

    }

    /**
     *
     * @author abrasha
     *
     * @return Subject with concrete id
     */
    public static Subject getSubjectById(int id_subject) {

        Subject result = null;

        try {

            ResultSet rs = statement.executeQuery("SELECT * FROM subjects WHERE id_subject = " + id_subject + ";");

            if (rs.next()) {

                result = new Subject(rs.getInt("id_subject"), rs.getString("name"), rs.getString("description"));

            }

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error getting subject by id", e);
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
        try {

            int insertedId = getLastIdFromUsers() + 1;
            int login = insertedId + LOGIN_START;

            insertUser(new User(insertedId, login, User.AccType.TEACHER));

            String sqlStatement = "INSERT INTO teachers"
                    + "(id_teachers, fname, lname, patronymic, subjects, bday, phone, address, notes)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preStatement = connection.prepareStatement(sqlStatement);
            preStatement.setInt(1, insertedId);
            preStatement.setString(2, added.getFName());
            preStatement.setString(3, added.getLName());
            preStatement.setString(4, added.getPatronymic());
            preStatement.setString(5, added.getSubjects());
            preStatement.setString(6, added.getBday());
            preStatement.setString(7, added.getPhone());
            preStatement.setString(8, added.getAddress());
            preStatement.setString(9, added.getNotes());
            preStatement.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error adding teacher: " + e.getMessage());

            logger.log(Level.SEVERE, "Teacher added");
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
    
    /**
    *
    * @author bepa
    * 
    * gets teacher from database
    */
    public static  Parent getParentById(int id){
        
        //ЗАПРОСЫ ДЛЯ БД И В ИТОГЕ ВСЕ ДАННЫЕ
        
        Parent current = new Parent.Builder()
                .fName("Grabar")
                .lName("Mykola")
                .patronymic("...")
                .idChild(4)
                .phone("")
                .address("")
                .bday("")
                .notes("")
                .build();
        
        return current;
    }
    
    /**
    *
    * @author bepa
    * 
    * gets teacher from database
    */
    public static Admin getAdminById(int id){
        
        //ЗАПРОСЫ ДЛЯ БД И В ИТОГЕ ВСЕ ДАННЫЕ
        
        Admin current = new Admin();
        
        return current;
    }
    
    /**
     *
     * @author Shlimazl
     *
     * returns code of student's group
     */
    public static String getGroupCodeByStudent(int id) {
        String result = "";
        try {
            preStatement = connection.prepareStatement("SELECT code FROM groups WHERE id_group=(SELECT id_group FROM students WHERE id_student =?);");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = rs.getString("code");
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error selecting group code by student id", e);
        }
        return result;
    }

    /**
     *
     * @author Shlimazl
     *
     * returns code of curators group
     */
    public static String getGroupCodeByCurator(int id) {
        String result = "";
        try {
            preStatement = connection.prepareStatement("SELECT code FROM groups WHERE id_curator =?;");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = rs.getString("code");
            }
        } catch (SQLException e) {
            System.out.println("Error select group code " + e.getMessage());
        }
        return result;
    }

    /**
     *
     * @author Shlimazl
     *
     * returns curator's firstname,lastname and patroymic
     */
    /**
     * TODO refactoring. abrasha.
     */
    public static String getCuratorByStudent(int id) {
        String result = "";
        String lastname = "";
        String fname = "";
        String patronymic = "";
        try {
            preStatement = connection.prepareStatement("SELECT lastname,fname,patronymic FROM teachers WHERE id_teachers = (SELECT id_curator FROM groups WHERE id_group = (SELECT id_group FROM students WHERE id_student=?));");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                lastname = rs.getString("lastname");
                fname = rs.getString("fname");
                patronymic = rs.getString("patronymic");
                result = lastname + " " + fname + " " + patronymic;
            }
        } catch (SQLException e) {
            System.out.println("Error select curator " + e.getMessage());
        }
        return result;
    }

    /**
     *
     * @author Shlimazl
     *
     * returns student's own firstname,lastname and patroymic
     */
    /**
     * TODO refactoring. abrasha.
     */
    public static Student getStudentById(int id) {
        String result = "";
        String lastname = "";
        String fname = "";
        String patronymic = "";
        try {
            preStatement = connection.prepareStatement("SELECT lastname,fname,patronymic FROM students WHERE id_student = ?;");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                lastname = rs.getString("lastname");
                fname = rs.getString("fname");
                patronymic = rs.getString("patronymic");
                result = lastname + " " + fname + " " + patronymic;
            }
        } catch (SQLException e) {
            System.out.println("Error select curator " + e.getMessage());
        }
        //bepa
        Student current = new Student.Builder()
                .lName(lastname)
                .fName(fname)
                .patronymic(patronymic)
                .build();
        
        
        
        return current;
    }

    /**
     *
     * @author Shlimazl
     *
     * returns name of subject by id
     */
    /**
     * TODO refactoring. abrasha.
     */
    private static String GetSubjectById(int id) {
        String result = "";
        try {
            preStatement = connection.prepareStatement("SELECT name from subjects WHERE id_subject=?;");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = rs.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("Error select subject " + e.getMessage());
        }
        return result;
    }

    /**
     *
     * @author Shlimazl
     *
>>>>>>> 10007447d55680b8194105f3e0aee192a3487d04
     * returns student's group's schedule
     */
    /**
     * TODO refactoring. abrasha.
     */
    public static String getScheduleByStudent(int id) {
        String result = "";
        String subject = "";
        int order = 0;
        int subject_id = 0;
        int day_id = 0;
        int current_day = 1;
        try {
            preStatement = connection.prepareStatement("SELECT id_day,number,id_subject FROM schedule WHERE id_group = (select id_group FROM students WHERE id_student =?);");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                day_id = rs.getInt("id_day");
                order = rs.getInt("number");
                subject_id = rs.getInt("id_subject");
                subject = GetSubjectById(subject_id);

                if (day_id > current_day) {
                    current_day++;
                    result += "\n";
                }
                result += day_id + " " + order + "\t" + subject + "\n";

            }
            day_id = 0;
            order = 0;
            subject_id = 0;
            subject = "";
        } catch (SQLException e) {
            System.out.println("Error select schedule " + e.getMessage());
        }
        return result;
    }

    /**
     *
     * @author Shlimazl
     *
     * returns code of group by id
     */
    public static String getGroupById(int id) {
        String result = "";
        try {
            preStatement = connection.prepareStatement("SELECT code from groups WHERE id_group=?;");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = rs.getString("code");
                result += "\t";
            }
        } catch (SQLException e) {
            System.out.println("Error select group " + e.getMessage());
        }
        return result;
    }

    /**
     *
     * @author Shlimazl
     *
     * returns teacher's schedule
     */
    public static String getScheduleByTeacher(int id) {
        String result = "";
        String subject = "";
        String group = "";
        int order = 0;
        int subject_id = 0;
        int day = 0;
        int group_id;
        int current_day = 1;
        try {
            preStatement = connection.prepareStatement("SELECT id_day,number,id_subject,id_group FROM schedule WHERE id_teacher = ?;");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                day = rs.getInt("id_day");
                order = rs.getInt("number");
                subject_id = rs.getInt("id_subject");
                group_id = rs.getInt("id_group");

                subject = GetSubjectById(subject_id);
                group = getGroupById(group_id);

                if (day > current_day) {
                    current_day++;
                    result += "\n";
                }
                result += day + " " + order + "\t" + group + "\t" + subject + "\n";

            }
        } catch (SQLException e) {
            System.out.println("Error select schedule " + e.getMessage());
        }
        return result;
    }
    
    /**
     *
     * TODO !!!
     * 
     */
    public static ObservableList<String> getAvaliableGroups(){
        
        //Запросы
        
        ObservableList<String> groups = FXCollections.observableArrayList(
            "11-A", "11-Б", "11-В",
            "10-A", "10-Б", "10-В",
            "9-A", "9-Б", "9-В",
            "8-A", "8-Б", "8-В", "...");
        return groups;
    }
}
