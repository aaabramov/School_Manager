package school_manager.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import school_manager.helpers.DatabaseIndexes.*;
import school_manager.model.*;

public final class DatabaseManager {

    private static final Logger logger;
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preStatement = null;
    private static final String DBAddress = "sql2.freemysqlhosting.net"/*"stevie.heliohost.org"*/;
    private static final String DBPort = "3306";
    private static final String DBName = "sql294080"/*"aabrasha_smdb"*/;
    private static final String DBLogin = "sql294080"/*"aabrasha_andrew"*/;
    private static final String DBPassword = "kX1*fP2!"/*"123234q"*/;
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

            String dbUrl = "jdbc:mysql://" + DBAddress + ":" + DBPort + "/" + DBName + "?characterEncoding=UTF-8";
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
     * @author abrasha inserts new student to database
     */
    public static void insertStudent(Student added) {

        int insertedId = getLastIdFromUsers() + 1;
        int login = insertedId + LOGIN_START;

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

        try {
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
            insertUser(new User(insertedId, login, User.AccType.STUDENT));

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting student", e);

        }

    }

    /**
     * @author a inserts new teacher to database
     */
    public static void insertTeacher(Teacher added) {
        int insertedId = getLastIdFromUsers() + 1;
        int login = insertedId + LOGIN_START;

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
        try {

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
            insertUser(new User(insertedId, login, User.AccType.TEACHER));
        } catch (SQLException e) {
            System.out.println("Error adding teacher: " + e.getMessage());

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
            sql = sql.replace("%2", Users.PASSWORD);
            sql = sql.replace("%3", Users.ACC_TYPE);

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
     * @return overview full list of subjects
     */
    public static Map<String, Integer> getSubjectsList() {

        Map<String, Integer> list = new TreeMap<>();

        try {
            String sql = "SELECT %1, %2"
                    + " FROM " + Subjects.TABLE + ";";

            sql = sql.replace("%1", Subjects.ID_SUBJECT);
            sql = sql.replace("%2", Subjects.NAME);
            preStatement = connection.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                list.put(rs.getString(Subjects.NAME), rs.getInt(Subjects.ID_SUBJECT));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting subject list", e);
        }

        return list;
    }

    /**
     * @author abrasha
     * @return overview full list of groups
     */
    public static Map<String, Integer> getGroupsList() {
        Map<String, Integer> list = new TreeMap<>();

        try {
            String sql = "SELECT %1, %2"
                    + " FROM " + Groups.TABLE + ";";

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
     * @return overview list of students in concrete group
     */
    public static Map<String, Integer> getGroupMembersById(int groupId) {
        Map<String, Integer> list = new TreeMap<>();

        try {
            String sql = "SELECT %1, %2, %3"
                    + " FROM " + Students.TABLE
                    + " WHERE " + Students.ID_GROUP + " = " + groupId + ";";

            sql = sql.replace("%1", Students.ID_STUDENT);
            sql = sql.replace("%2", Students.FIRST_NAME);
            sql = sql.replace("%3", Students.LAST_NAME);

            preStatement = connection.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                String studentInitials = rs.getString(Students.LAST_NAME) + " "
                        + rs.getString(Students.FIRST_NAME);
                list.put(studentInitials, rs.getInt(Students.ID_STUDENT));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting group list", e);
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
     * @author abrasha
     */
    public static Teacher getTeacherById(int id) {

        Teacher result = null;
        String sql = "SELECT * FROM " + Teachers.TABLE
                + " WHERE " + Teachers.ID_TEACHER + " = ?;";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = new Teacher.Builder()
                        .id(id)
                        .fName(rs.getString(Teachers.FIRST_NAME))
                        .lName(rs.getString(Teachers.LAST_NAME))
                        .patronymic(rs.getString(Teachers.PATRONYMIC))
                        .subjects(rs.getString(Teachers.SUBJECTS))
                        .bday(rs.getString(Teachers.BDAY))
                        .address(rs.getString(Teachers.ADDRESS))
                        .phone(rs.getString(Teachers.PHONE))
                        .notes(rs.getString(Teachers.NOTES))
                        .build();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting teacher by id", e);
        }

        return result;
    }

    /**
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
                        .id(rs.getInt(Students.ID_STUDENT))
                        .fName(rs.getString(Students.FIRST_NAME))
                        .lName(rs.getString(Students.LAST_NAME))
                        .patronymic(rs.getString(Students.PATRONYMIC))
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
     * @author bepa gets teacher from database
     */
    public static Admin getAdminById(int id) {

        //ЗАПРОСЫ ДЛЯ БД И В ИТОГЕ ВСЕ ДАННЫЕ
        Admin current = new Admin();

        return current;
    }

    /**
     * @author Shlimazl returns code of student's group
     */
    public static String getGroupCodeByStudent(int id) {
        String result = "";
        String sql = "SELECT " + Groups.CODE
                + " FROM " + Groups.TABLE
                + " WHERE " + Groups.ID_GROUP + " = "
                + " (SELECT " + Students.ID_GROUP
                + " FROM " + Students.TABLE
                + " WHERE " + Students.ID_STUDENT + " = ? );";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = rs.getString(Groups.CODE);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error selecting group code by student id", e);
        }
        return result;
    }

    /**
     * @author Shlimazl returns code of curators group
     */
    public static Group getGroupByCuratorId(int curatorId) {
        Group result = null;
        try {
            String sql = "SELECT * "
                    + " FROM " + Groups.TABLE
                    + " WHERE " + Groups.ID_CURATOR + " = ?;";
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, curatorId);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = new Group.Builder()
                        .code(rs.getString(Groups.CODE))
                        .idCurator(rs.getInt(Groups.ID_GROUP))
                        .idGroup(rs.getInt(Groups.ID_GROUP))
                        .list(getGroupMembersById(curatorId))
                        .notes(rs.getString(Groups.NOTE))
                        .build();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error selecting group code by curator id", e);
        }
        return result;

    }

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
     * @return Group Object returns code of group by id
     */
    public static Group getGroupById(int id) {
        Group result = null;
        try {
            String sql = "SELECT * FROM " + Groups.TABLE
                    + " WHERE " + Groups.ID_GROUP + " = ? ;";
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = new Group.Builder()
                        .code(rs.getString(Groups.CODE))
                        .notes(rs.getString(Groups.NOTE))
                        .idCurator(rs.getInt(Groups.ID_CURATOR))
                        .idGroup(id)
                        .list(getGroupMembersById(id))
                        .build();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error gettinf group by id", e);
        }
        return result;
    }

    // запрос на учителей которые не являются кураторами
    // SELECT * FROM teachers WHERE teachers.id_teacher NOT IN (SELECT groups.id_curator FROM groups)
}
