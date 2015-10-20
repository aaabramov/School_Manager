package school_manager.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import school_manager.helpers.DatabaseIndexes.Groups;
import school_manager.helpers.DatabaseIndexes.Students;
import school_manager.helpers.DatabaseIndexes.Subjects;
import school_manager.helpers.DatabaseIndexes.Teachers;
import school_manager.helpers.DatabaseIndexes.Users;
import school_manager.model.Admin;
import school_manager.model.Parent;
import school_manager.model.Student;
import school_manager.model.Subject;
import school_manager.model.Teacher;
import school_manager.model.User;

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
     * @author abrasha loading database connection
     */
    static {

        logger = Logger.getLogger(DatabaseManager.class.getCanonicalName());

        try {

            String dbUrl = "jdbc:mysql://" + DBAddress + ":" + DBPort + "/" + DBName;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, DBLogin, DBPassword);

            if (connection == null) {
                logger.log(Level.SEVERE, "Cannot establish connection with " + DBPort + ":" + DBName);
            } else {
                statement = connection.createStatement();
                logger.log(Level.CONFIG, "COnnection established with " + DBPort + ":" + DBName);
            }

        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, "Cannot establish connection with " + DBPort + ":" + DBName, e);
        }

    }

    /**
     * @author abrasha closes databases connection
     */
    public static void close() {

        try {
            if (connection != null) {
                logger.log(Level.CONFIG, "Connection closed.");
                connection.close();
            }
            if (statement != null) {
                logger.log(Level.CONFIG, "statement closed.");
                statement.close();
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception in closing db", e);
        }
    }

    /**
     * @author abrasha inserts new student to database
     */
    public static void insertStudent(Student added) {
        try {

            int insertedId = getLastIdFromUsers() + 1;
            int login = insertedId + LOGIN_START;

            insertUser(new User(insertedId, login, User.AccType.STUDENT));

            String sqlStatement = "INSERT INTO " + Students.TABLE
                    + "(%1, %2, %3, %4, %5, %6, %7, %8, %9) "
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            sqlStatement = sqlStatement.replace("%1", Students.ID_STUDENT);
            sqlStatement = sqlStatement.replace("%2", Students.FIRST_NAME);
            sqlStatement = sqlStatement.replace("%3", Students.LAST_NAME);
            sqlStatement = sqlStatement.replace("%4", Students.PATRONYMIC);
            sqlStatement = sqlStatement.replace("%5", Students.ID_GROUP);
            sqlStatement = sqlStatement.replace("%6", Students.BIRTHDAY);
            sqlStatement = sqlStatement.replace("%7", Students.ADDRESS);
            sqlStatement = sqlStatement.replace("%8", Students.PHONE);
            sqlStatement = sqlStatement.replace("%9", Students.NOTES);

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
            logger.log(Level.INFO, "Student inserted.");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting student", e);

        }

    }

    /**
     * @author abrasha inserts new user to database
     */
    private static void insertUser(User user) {
        try {
            String sqlStatement = ("INSERT INTO " + Users.TABLE
                    + " (%1, %2, %3)"
                    + " VALUES (?, ?, ?);");

            sqlStatement = sqlStatement.replace("%1", Users.LOGIN);
            sqlStatement = sqlStatement.replace("%2", Users.PASSWORD);
            sqlStatement = sqlStatement.replace("%3", Users.ACC_TYPE);

            preStatement = connection.prepareStatement(sqlStatement);
            preStatement.setInt(1, user.getLogin());
            preStatement.setString(2, PasswordGenerator.generate());
            preStatement.setInt(3, user.getAccTypeCode());
            preStatement.executeUpdate();
            logger.log(Level.INFO, "User inserted.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting user", e);
        }

    }

    /**
     * @author abrasha returns the last inserted id from users table
     */
    private static int getLastIdFromUsers() {

        int result = -1;

        try {
            String sql = "SELECT MAX("
                    + Users.ID_USER + ") max_id"
                    + " FROM " + Users.TABLE + ";";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                result = rs.getInt("max_id");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting last id from users", e);
        }

        if (result == -1) {
            logger.log(Level.WARNING, "Error getting last id from users");
        }

        return result;
    }

    /**
     * @author abrasha process of authorization into program
     */
    public static User authorize(int login, String password) {

        User result = null;

        try {
            String sql = "SELECT %1, %2, %3"
                    + " FROM " + Users.TABLE
                    + " WHERE " + Users.LOGIN + " = ?;";

            sql = sql.replace("%1", Users.ID_USER);
            System.out.println(sql);
            sql = sql.replace("%2", Users.PASSWORD);
            System.out.println(sql);
            sql = sql.replace("%3", Users.ACC_TYPE);
            System.out.println(sql);

            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, login);
            ResultSet rs = preStatement.executeQuery();

            if (rs.next()) {
                String dbpass = rs.getString(Users.PASSWORD);
                if (password.equals(dbpass)) {
                    result = new User(rs.getInt(Users.ID_USER), login, rs.getInt(Users.ACC_TYPE));
                }
            }
            logger.log(Level.INFO, "Authorization successful.");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error authorizing", e);
        }

        return result;

    }

    /**
     * @author abrasha
     * @return full list of subjects
     */
    // TODO make it like Map<String, Integer>
    public static ArrayList<Subject> getSubjects() {
        ArrayList<Subject> result = new ArrayList<>();

        try {

            ResultSet rs = statement.executeQuery("SELECT * FROM "
                    + Subjects.TABLE + ";");

            while (rs.next()) {

                result.add(new Subject(rs.getInt(Subjects.ID_SUBJECT),
                        rs.getString(Subjects.NAME),
                        rs.getString(Subjects.DESCRIPTION)));

            }

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error getting subjects", e);
        }

        return result;

    }

    /**
     * @author abrasha
     * @return full list of subjects
     */
    public static Map<String, Integer> getGroupsList() {
        Map<String, Integer> list = new HashMap<>();

        try {
            String sql = "SELECT %1, %2"
                    + " FROM " + Groups.TABLE;

            sql = sql.replace("%1", Groups.CODE);
            sql = sql.replace("%2", Groups.ID_GROUP);
            preStatement = connection.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                list.put(rs.getString(Groups.CODE), rs.getInt(Groups.ID_GROUP));
            }

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error getting group list", e);
        }

        return list;
    }

    /**
     * @author abrasha
     * @return Subject with concrete id
     */
    public static Subject getSubjectById(int subjectId) {

        Subject result = null;

        try {

            String sql = "SELECT * FROM " + Subjects.TABLE
                    + " WHERE " + Subjects.ID_SUBJECT + " = " + subjectId + ";";
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {

                result = new Subject(rs.getInt(Subjects.ID_SUBJECT),
                        rs.getString(Subjects.NAME),
                        rs.getString(Subjects.DESCRIPTION));

            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting subject by id", e);
        }

        return result;

    }

    /**
     * @author bepa inserts new teacher to database
     */
    public static void insertTeacher(Teacher added) {
        try {

            int insertedId = getLastIdFromUsers() + 1;
            int login = insertedId + LOGIN_START;

            insertUser(new User(insertedId, login, User.AccType.TEACHER));

            String sqlStatement = "INSERT INTO " + Teachers.TABLE
                    + "(%1, %2, %3, %4, %5, %6, %7, %8, %9) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            sqlStatement = sqlStatement.replace("%1", Teachers.ID_TEACHER);
            sqlStatement = sqlStatement.replace("%2", Teachers.FIRST_NAME);
            sqlStatement = sqlStatement.replace("%3", Teachers.LAST_NAME);
            sqlStatement = sqlStatement.replace("%4", Teachers.PATRONYMIC);
            sqlStatement = sqlStatement.replace("%5", Teachers.SUBJECTS);
            sqlStatement = sqlStatement.replace("%6", Teachers.BDAY);
            sqlStatement = sqlStatement.replace("%7", Teachers.PHONE);
            sqlStatement = sqlStatement.replace("%8", Teachers.ADDRESS);
            sqlStatement = sqlStatement.replace("%9", Teachers.NOTES);
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

            logger.log(Level.INFO, "Teacher inserted");
        } catch (SQLException e) {
            System.out.println("Error adding teacher: " + e.getMessage());

        }
    }

    /**
     * @author bepa gets teacher from database
     */
    public static Teacher getTeacherById(int id) {

        Teacher result = null;

        return result;
    }

    /**
     * @author bepa gets teacher from database
     */
    public static Parent getParentById(int id) {

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
     * @author bepa gets teacher from database
     */
    public static Admin getAdminById(int id) {

        //ЗАПРОСЫ ДЛЯ БД И В ИТОГЕ ВСЕ ДАННЫЕ
        Admin current = new Admin();

        return current;
    }

    /**
     * @author Shlimazl
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
    public static String getGroupByCurator(int curatorId) {
        String result = null;
        try {
            String sql = "SELECT " + Groups.CODE
                    + " FROM " + Groups.TABLE
                    + " WHERE " + Groups.ID_CURATOR + " = ?;";
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, curatorId);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = rs.getString(Groups.CODE);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error selecting group code by curator id", e);
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
            String sql = "SELECT *"
                    + " FROM " + Teachers.TABLE + " WHERE " + Teachers.ID_TEACHER + " = "
                    + "(SELECT " + Groups.ID_CURATOR
                    + " FROM " + Groups.TABLE + " WHERE " + Groups.ID_GROUP + " = "
                    + "(SELECT id_group "
                    + "FROM students "
                    + "WHERE id_student=?));";
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error selecting curator by student id", e);
        }
        return result;
    }

    /**
     *
     * @author Shlimazl
     *
     * TODO refactoring. abrasha. remade by
     * @author abrasha
     */
    public static Student getStudentById(int id) {

        Student result = null;
        try {
            String sql = "SELECT * FROM " + Students.TABLE
                    + " WHERE " + Students.ID_STUDENT + " = ?;";
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = new Student.Builder()
                        .fName(rs.getString(Students.FIRST_NAME))
                        .lName(rs.getString(Students.LAST_NAME))
                        .patronymic(rs.getString(Students.PATRONYMIC))
                        .idStudent(rs.getInt(Students.ID_STUDENT))
                        .idGroup(rs.getInt(Students.ID_GROUP))
                        .bday(rs.getString(Students.BIRTHDAY))
                        .address(rs.getString(Students.ADDRESS))
                        .phone(rs.getString(Students.PHONE))
                        .notes(rs.getString(Students.NOTES))
                        .build();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting student by id", e);
        }

        return result;
    }

    /**
     *
     * @author Shlimazl
     *
     * returns name of subject by id
     */
    /**
     * TODO . abrasha.
     *
     * @return Subject object
     */
    private static String GetSubjectById(int id) {
        String result = "";
        try {
            String sql = "SELECT name from subjects WHERE id_subject = ?;";
            preStatement = connection.prepareStatement(sql);
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
     * @return Group Object returns code of group by id
     */
    public static String getGroupById(int id) {
        String result = "";
        try {
            preStatement = connection.prepareStatement("SELECT code from groups WHERE id_group=?;");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = rs.getString("code");
            }
        } catch (SQLException e) {
            System.out.println("Error select group " + e.getMessage());
        }
        return result;
    }

}
