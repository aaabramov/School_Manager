package school_manager.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import school_manager.helpers.DatabaseIndexes.Admins;
import school_manager.helpers.DatabaseIndexes.Families;
import school_manager.helpers.DatabaseIndexes.Groups;
import school_manager.helpers.DatabaseIndexes.Journal;
import school_manager.helpers.DatabaseIndexes.Parents;
import school_manager.helpers.DatabaseIndexes.Schedules;
import school_manager.helpers.DatabaseIndexes.Schedules;
import school_manager.helpers.DatabaseIndexes.Students;
import school_manager.helpers.DatabaseIndexes.Subjects;
import school_manager.helpers.DatabaseIndexes.Teachers;
import school_manager.helpers.DatabaseIndexes.Users;
import school_manager.model.Admin;
import school_manager.model.Group;
import school_manager.model.Parent;
import school_manager.model.Student;
import school_manager.model.Subject;
import school_manager.model.Teacher;
import school_manager.model.User;
import school_manager.model.overviews.GroupOverview;
import school_manager.model.overviews.MarkOverview;
import school_manager.model.overviews.ParentOverview;
import school_manager.model.overviews.StudentOverview;
import school_manager.model.overviews.SubjectOverview;
import school_manager.model.overviews.TeacherOverview;
import school_manager.model.schedule.Lesson;
import school_manager.model.schedule.StudentLesson;
import school_manager.model.schedule.StudentSchedule;
import school_manager.model.schedule.TeacherLesson;
import school_manager.model.schedule.TeacherSchedule;

public final class DatabaseManager {

    private static Logger logger;
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preStatement = null;
    private static final String DBAddress = "sql4.freemysqlhosting.net";
    private static final String DBPort = "3306";
    private static final String DBName = "sql499817";
    private static final String DBLogin = "sql499817";
    private static final String DBPassword = "WjVGy87DA3";
    private static final int LOGIN_START = 10001;

    public static final int STUDENT_TYPE = 0;
    public static final int TEACHER_TYPE = 1;
    public static final int PARENT_TYPE = 2;
    public static final int ADMIN_TYPE = 3;

    /**
     @author abrasha loading database connection
     */
    public static void load(){

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
     @author abrasha closes databases connection
     */
    public static void close(){

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
     @author abrasha inserts new user to database
     */
    private static void insertUser(User user){
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
     @author abrasha inserts new student to database
     */
    public static boolean insertStudent(Student added){

        boolean success = false;

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
            success = true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting student", e);

        }

        return success;

    }

    /**
     @author Shlimazl inserts new parent to database
     */
    public static boolean insertParent(Parent added){

        boolean success = false;

        int insertedId = getLastIdFromUsers() + 1;
        int login = insertedId + LOGIN_START;

        String sqlStatement = "INSERT INTO " + Parents.TABLE
                + "(%1, %2, %3, %4, %5, %6, %7, %8) "
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        sqlStatement = sqlStatement.replace("%1", Parents.ID_PARENT);
        sqlStatement = sqlStatement.replace("%2", Parents.FIRST_NAME);
        sqlStatement = sqlStatement.replace("%3", Parents.LAST_NAME);
        sqlStatement = sqlStatement.replace("%4", Parents.PATRONYMIC);
        sqlStatement = sqlStatement.replace("%5", Parents.ADDRESS);
        sqlStatement = sqlStatement.replace("%6", Parents.PHONE);
        sqlStatement = sqlStatement.replace("%7", Parents.JOB);
        sqlStatement = sqlStatement.replace("%8", Parents.NOTES);

        try {
            preStatement = connection.prepareStatement(sqlStatement);
            preStatement.setInt(1, insertedId);
            preStatement.setString(2, added.getFName());
            preStatement.setString(3, added.getLName());
            preStatement.setString(4, added.getPatronymic());
            preStatement.setString(5, added.getAddress());
            preStatement.setString(6, added.getPhone());
            preStatement.setString(7, added.getJob());
            preStatement.setString(8, added.getNotes());
            preStatement.executeUpdate();
            logger.log(Level.INFO, "Parent inserted.");
            insertUser(new User(insertedId, login, User.AccType.PARENT));
            success = true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting parent", e);
        }
        return success;
    }

    /**
     @author abrasha inserts new teacher to database
     */
    public static boolean insertTeacher(Teacher added){

        boolean success = false;

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
            success = true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding teacher", e);
        }

        return success;

    }

    /**
     @author Shlimazl inserts new admin to database
     */
    public static boolean insertAdmin(){

        boolean success = false;
        int insertedId = getLastIdFromUsers() + 1;
        int login = insertedId + LOGIN_START;
        String sqlStatement = "INSERT INTO " + Admins.TABLE + " VALUES(?, NULL);";
        try {
            preStatement = connection.prepareStatement(sqlStatement);
            preStatement.setInt(1, insertedId);
            preStatement.executeUpdate();
            insertUser(new User(insertedId, login, User.AccType.ADMIN));
            success = true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding admin", e);
        }
        return success;
    }

    /**
     @author Shlimazl inserts pair parentId-studentId to database
     */
    public static boolean insertFamily(int idParent, int idChild){
        boolean success = false;
        try {
            String sqlStatement = "INSERT INTO " + Families.TABLE + " VALUES(?,?);";
            preStatement = connection.prepareStatement(sqlStatement);
            preStatement.setInt(1, idParent);
            preStatement.setInt(2, idChild);
            preStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding family", e);

        }

        return success;
    }

    /**
     @author abrasha inserts new group to database
     */
    public static boolean insertGroup(Group added){

        boolean success = false;

        if (!groupCodeIsUsed(added.getCode())) {
            logger.log(Level.WARNING, "Error inserting group: Group code is already in use", added.getCode());
        } else {
            String sqlStatement = "INSERT INTO " + Groups.TABLE
                    + "(%1, %2, %3) "
                    + "VALUES (?, ?, ?);";
            sqlStatement = sqlStatement.replace("%1", Groups.CODE);
            sqlStatement = sqlStatement.replace("%2", Groups.ID_CURATOR);
            sqlStatement = sqlStatement.replace("%3", Groups.NOTE);
            try {

                preStatement = connection.prepareStatement(sqlStatement);
                preStatement.setString(1, added.getCode());
                preStatement.setInt(2, added.getCuratorId());
                preStatement.setString(3, added.getNote());
                preStatement.executeUpdate();

                logger.log(Level.INFO, "Group inserted");
                success = true;
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error adding group", e);
            }
        }
        return success;

    }

    /**
     @author abrasha returns the last inserted id from users table
     */
    private static int getLastIdFromUsers(){

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
     @author abrasha process of authorization into program
     */
    public static User authorize(int login, String password){

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
     @author abrasha
     @return overview full list of subjects
     */
    public static List<SubjectOverview> getSubjectsList(){

        List<SubjectOverview> list = new ArrayList<>();

        try {
            String sql = "SELECT %1, %2"
                    + " FROM " + Subjects.TABLE + ";";

            sql = sql.replace("%1", Subjects.ID_SUBJECT);
            sql = sql.replace("%2", Subjects.NAME);
            preStatement = connection.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                list.add(new SubjectOverview(rs.getString(Subjects.NAME), rs.getInt(Subjects.ID_SUBJECT)));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting subject list", e);
        }

        return list;
    }

    /**
     @author abrasha
     @return overview full list of groups
     */
    public static List<GroupOverview> getGroupsList(){
        List<GroupOverview> list = new ArrayList<>();

        try {
            String sql = "SELECT %1, %2"
                    + " FROM " + Groups.TABLE + ";";

            sql = sql.replace("%1", Groups.CODE);
            sql = sql.replace("%2", Groups.ID_GROUP);
            preStatement = connection.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                list.add(new GroupOverview(rs.getInt(Groups.ID_GROUP), rs.getString(Groups.CODE)));
            }

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error getting group list", e);
        }

        return list;
    }

    /**
     @author abrasha
     @return overview list of students in concrete group
     */
    public static List<StudentOverview> getGroupMembersById(int groupId){
        List<StudentOverview> list = new ArrayList<>();

        try {
            String sql = "SELECT %1, %2, %3, %4"
                    + " FROM " + Students.TABLE
                    + " WHERE " + Students.ID_GROUP + " = " + groupId + ";";

            sql = sql.replace("%1", Students.ID_STUDENT);
            sql = sql.replace("%2", Students.FIRST_NAME);
            sql = sql.replace("%3", Students.LAST_NAME);
            sql = sql.replace("%4", Students.PATRONYMIC);

            preStatement = connection.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                String studentInitials = rs.getString(Students.LAST_NAME) + " "
                        + rs.getString(Students.FIRST_NAME) + " " + rs.getString(Students.PATRONYMIC);
                list.add(new StudentOverview(studentInitials, rs.getInt(Students.ID_STUDENT)));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting group list", e);
        }
        return list;
    }

    /**
     @author abrasha
     @return Subject with concrete id
     */
    public static Subject getSubjectById(int subjectId){

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
     @author abrasha
     @return Subject with concrete id
     */
    public static SubjectOverview getSubjectOverviewById(int subjectId){

        SubjectOverview result = null;

        try {

            String sql = "SELECT " + Subjects.ID_SUBJECT + ',' + Subjects.NAME
                    + " FROM " + Subjects.TABLE
                    + " WHERE " + Subjects.ID_SUBJECT + " = " + subjectId + ";";
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {

                result = new SubjectOverview(rs.getString(Subjects.NAME), rs.getInt(Subjects.ID_SUBJECT));

            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting subject overview by id", e);
        }

        return result;

    }

    /**
     @author abrasha
     */
    public static Teacher getTeacherById(int id){

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
     @author abrasha
     */
    public static TeacherOverview getTeacherOverviewById(int id){

        TeacherOverview result = null;
        String sql = "SELECT %1, %2, %3, %4 FROM " + Teachers.TABLE
                + " WHERE " + Teachers.ID_TEACHER + '=' + id + ';';
        sql = sql.replace("%1", Teachers.ID_TEACHER);
        sql = sql.replace("%2", Teachers.FIRST_NAME);
        sql = sql.replace("%3", Teachers.LAST_NAME);
        sql = sql.replace("%4", Teachers.PATRONYMIC);
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                String initials = rs.getString(Teachers.LAST_NAME) + ' ' + rs.getString(Teachers.FIRST_NAME)
                        + ' ' + rs.getString(Teachers.PATRONYMIC);
                result = new TeacherOverview(initials, rs.getInt(Teachers.ID_TEACHER));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting teacher overview by id", e);
        }

        return result;
    }

    /**
     @author abrasha
     */
    public static Student getStudentById(int id){

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
     @author abrasha
     */
    public static StudentOverview getStudentOverviewById(int id){

        StudentOverview result = null;
        try {
            String sql = "SELECT %1,%2,%3,%3 FROM " + Students.TABLE
                    + " WHERE " + Students.ID_STUDENT + '=' + id + ';';

            sql = sql.replace("%1", Students.ID_STUDENT);
            sql = sql.replace("%2", Students.FIRST_NAME);
            sql = sql.replace("%3", Students.LAST_NAME);
            sql = sql.replace("%4", Students.PATRONYMIC);

            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                String initials = rs.getString(Students.LAST_NAME) + ' '
                        + rs.getString(Students.FIRST_NAME) + ' ' + rs.getString(Students.PATRONYMIC);
                result = new StudentOverview(initials, rs.getInt(Students.ID_STUDENT));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting student overview by id", e);
        }

        return result;
    }

    /**
     @author abrasha
     */
    public static Parent getParentById(int id){
        Parent result = null;
        try {
            String sql = "SELECT * FROM " + Parents.TABLE
                    + " WHERE " + Parents.ID_PARENT + " = ?;";
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = new Parent.Builder()
                        .id(rs.getInt(Parents.ID_PARENT))
                        .fName(rs.getString(Parents.FIRST_NAME))
                        .lName(rs.getString(Parents.LAST_NAME))
                        .patronymic(rs.getString(Parents.PATRONYMIC))
                        .job(rs.getString(Parents.JOB))
                        .address(rs.getString(Parents.ADDRESS))
                        .phone(rs.getString(Parents.PHONE))
                        .notes(rs.getString(Parents.NOTES))
                        .build();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting parent by id", e);
        }

        return result;
    }

    //TODO public static ParentOverview getParentById(int id){    
    /**
     @author bepa gets teacher from database
     */
    public static Admin getAdminById(int id){
        Admin result = null;
        try {
            String sql = "SELECT * FROM " + Admins.TABLE
                    + " WHERE " + Admins.ID_ADMIN + " = ?;";
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = new Admin(rs.getInt(Admins.ID_ADMIN), rs.getString(Admins.LAST_SEEN));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting admin by id", e);
        }
        return result;
    }

    /**
     @author Shlimazl returns code of student's group
     */
    public static GroupOverview getGroupOverviewByStudent(int id){
        GroupOverview result = null;
        String sql = "SELECT " + Groups.CODE + ',' + Groups.ID_GROUP
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
                result = new GroupOverview(rs.getInt(Groups.ID_GROUP), rs.getString(Groups.CODE));
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error getting group overview by student id", e);
        }
        return result;
    }

    /**
     @author Shlimazl returns code of group by group id
     */
    public static String getGroupCodeById(int id){
        String result = "";
        String sql = "SELECT " + Groups.CODE
                + " FROM " + Groups.TABLE
                + " WHERE " + Groups.ID_GROUP + " =? ;";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = rs.getString(Groups.CODE);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error selecting group code by group id", e);
        }
        return result;
    }

    /**
     @author Shlimazl returns code of curators group
     */
    public static Group getGroupByCuratorId(int curatorId){
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
     @author Shlimazl returns initials of curator of student's group
     */
    public static TeacherOverview getCuratorByStudentId(int id){
        TeacherOverview result = null;
        String initials = "";
        int curatorId;

        String sql = "SELECT %1,%2,%3,%4"
                + " FROM " + Teachers.TABLE
                + " INNER JOIN " + Groups.TABLE
                + " ON( " + Teachers.TABLE + '.' + Teachers.ID_TEACHER
                + '=' + Groups.TABLE
                + '.' + Groups.ID_CURATOR
                + " AND " + Groups.TABLE + '.' + Groups.ID_CURATOR
                + " =(SELECT " + Groups.ID_CURATOR
                + " FROM " + Groups.TABLE
                + " INNER JOIN " + Students.TABLE
                + " ON( " + Groups.TABLE
                + '.' + Groups.ID_GROUP
                + '=' + Students.TABLE + "." + Students.ID_GROUP
                + " AND " + Students.TABLE
                + "." + Students.ID_STUDENT + "=? )));";

        sql = sql.replace("%1", Teachers.ID_TEACHER);
        sql = sql.replace("%2", Teachers.FIRST_NAME);
        sql = sql.replace("%3", Teachers.LAST_NAME);
        sql = sql.replace("%4", Teachers.PATRONYMIC);
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                curatorId = rs.getInt(Teachers.ID_TEACHER);
                initials += rs.getString(Teachers.LAST_NAME) + ' '
                        + rs.getString(Teachers.FIRST_NAME) + ' ' + rs.getString(Teachers.PATRONYMIC);
                result = new TeacherOverview(initials, curatorId);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error selecting curator by student id", e);
        }
        return result;
    }

    /**

     @author Shlimazl
     @return Group Object returns code of group by id
     */
    public static Group getGroupById(int id){
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
            logger.log(Level.SEVERE, "Error getting group by id", e);
        }
        return result;
    }

    public static GroupOverview getGroupOverviewById(int id){
        GroupOverview result = null;
        try {
            String sql = "SELECT %1, %2 FROM " + Groups.TABLE
                    + " WHERE " + Groups.ID_GROUP + '=' + id + ';';
            sql = sql.replace("%1", Groups.ID_GROUP);
            sql = sql.replace("%2", Groups.CODE);
            preStatement = connection.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = new GroupOverview(rs.getInt(Groups.ID_GROUP), rs.getString(Groups.CODE));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting group overview by id", e);
        }
        return result;
    }

    /**

     @author Shlimazl
     @return list of student's initials by surname
     */
    public static List<StudentOverview> getStudentsBySurname(String surname){
        List<StudentOverview> result = new ArrayList<>();
        String initials;
        int studentId;
        try {
            String sql = "SELECT * FROM " + Students.TABLE
                    + " WHERE " + Students.LAST_NAME + " LIKE '%" + surname + "%';";
            preStatement = connection.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                studentId = rs.getInt(Students.ID_STUDENT);
                initials = rs.getString(Students.LAST_NAME) + " "
                        + rs.getString(Students.FIRST_NAME) + " "
                        + rs.getString(Students.PATRONYMIC);
                StudentOverview student = new StudentOverview(initials, studentId);

                result.add(student);

            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting student by surname", e);
        }

        return result;
    }

    /**

     @author Shlimazl
     @return list of initials of teachers who are not curators
     */
    public static List<TeacherOverview> getTeachersNotCurators(){
        List<TeacherOverview> result = new ArrayList<>();
        String initials;
        int id;
        try {
            String sql = "SELECT * FROM " + Teachers.TABLE
                    + " WHERE " + Teachers.ID_TEACHER
                    + " NOT IN " + "(SELECT " + Groups.ID_CURATOR
                    + " FROM " + Groups.TABLE + ");";
            preStatement = connection.prepareStatement(sql);

            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt(Teachers.ID_TEACHER);
                initials = rs.getString(Teachers.LAST_NAME) + " "
                        + rs.getString(Teachers.FIRST_NAME) + " "
                        + rs.getString(Teachers.PATRONYMIC);
                TeacherOverview teacher = new TeacherOverview(initials, id);
                result.add(teacher);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting teachers", e);
        }
        return result;
    }

    /**

     @author abrasha
     @return true if group code is used
     */
    private static boolean groupCodeIsUsed(String code){

        boolean result = true;

        String sql = "SELECT " + Groups.ID_GROUP
                + " FROM " + Groups.TABLE
                + " WHERE " + Groups.CODE + "='" + code + "';";

        try {
            ResultSet rs = statement.executeQuery(sql);
            result = rs.next(); // any rows returned
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting teachers", e);
        }

        return result;
    }

    /**

     @author Shlimazl
     @return list of initials of student's parents
     */
    public static List<ParentOverview> getParentsByStudentId(int id){
        List<ParentOverview> result = new ArrayList<>();
        String initials;
        int parentId;
        try {
            String sql = "SELECT p. * FROM "
                    + Students.TABLE + " s, "
                    + Families.TABLE + " f, "
                    + Parents.TABLE + " p\n"
                    + "WHERE s." + Students.ID_STUDENT
                    + " = f." + Students.ID_STUDENT
                    + " AND f." + Parents.ID_PARENT
                    + "= p." + Parents.ID_PARENT
                    + " AND s." + Students.ID_STUDENT + "=?;";
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                parentId = rs.getInt(Parents.ID_PARENT);
                initials = rs.getString(Parents.LAST_NAME) + " "
                        + rs.getString(Parents.FIRST_NAME) + " " + rs.getString(Parents.PATRONYMIC);
                ParentOverview parent = new ParentOverview(initials, parentId);
                result.add(parent);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting parents", e);
        }
        return result;

    }

    /**

     @author Shlimazl
     @return list of initials of parent's childs
     */
    public static List<StudentOverview> getStudentsByParentId(int id){
        List<StudentOverview> result = new ArrayList<>();
        String initials;
        int studentId;
        try {
            String sql = "SELECT s. * FROM "
                    + Students.TABLE + " s, "
                    + Families.TABLE + " f, "
                    + Parents.TABLE + " p\n"
                    + "WHERE s." + Students.ID_STUDENT
                    + " = f." + Students.ID_STUDENT
                    + " AND f." + Parents.ID_PARENT
                    + "= p." + Parents.ID_PARENT
                    + " AND p." + Parents.ID_PARENT + "=?;";
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                studentId = rs.getInt(Students.ID_STUDENT);
                initials = rs.getString(Students.LAST_NAME) + " "
                        + rs.getString(Students.FIRST_NAME) + " " + rs.getString(Students.PATRONYMIC);
                StudentOverview student = new StudentOverview(initials, studentId);
                result.add(student);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting students", e);
        }
        return result;

    }

    public static List<ParentOverview> getParentsByGroupId(int id){
        List<ParentOverview> result = new ArrayList<>();
        String initials;
        int parentId;
        try {
            String sql = "SELECT p. * FROM "
                    + Students.TABLE + " s, "
                    + Families.TABLE + " f, "
                    + Parents.TABLE + " p\n"
                    + "WHERE s." + Students.ID_STUDENT
                    + " = f." + Students.ID_STUDENT
                    + " AND f." + Parents.ID_PARENT
                    + "= p." + Parents.ID_PARENT
                    + " AND s." + Students.ID_GROUP + "=?;";
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                parentId = rs.getInt(Parents.ID_PARENT);
                initials = rs.getString(Parents.LAST_NAME) + " "
                        + rs.getString(Parents.FIRST_NAME) + " " + rs.getString(Parents.PATRONYMIC);
                ParentOverview parent = new ParentOverview(initials, parentId);
                result.add(parent);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting parent list", e);
        }
        return result;
    }

    public static void refreshAdminData(int id){

        Runnable r = () -> {

            String sql = "UPDATE " + Admins.TABLE
                    + " SET " + Admins.LAST_SEEN + "='"
                    + DateTimeConverter.format(LocalDate.now())
                    + "' WHERE " + Admins.ID_ADMIN + '=' + id + ';';
            try {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error updating admin last seen", e);
            }
        };
        r.run();
    }

    /**
     @author Shlimazl
     @return if classroom is used
     */
    public static boolean ClassroomIsUsed(int day, int number, int classroom){
        boolean result = false;
        String sql = "SELECT " + Schedules.ID_LESSON
                + " FROM " + Schedules.TABLE
                + " WHERE " + Schedules.ID_DAY
                + " =? AND " + Schedules.ORDER
                + " =? AND " + Schedules.CLASSROOM
                + " =?;";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, day);
            preStatement.setInt(2, number);
            preStatement.setInt(3, classroom);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error checking classroom", e);
        }
        return result;
    }

    /**
     @author Shlimazl
     @return if teacher is busy
     */
    public static boolean TeacherIsBusy(int day, int number, int teacher){
        boolean result = false;
        String sql = "SELECT " + Schedules.ID_LESSON
                + " FROM " + Schedules.TABLE
                + " WHERE " + Schedules.ID_DAY
                + " =? AND " + Schedules.ORDER
                + " =? AND " + Schedules.ID_TEACHER
                + " =?;";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, day);
            preStatement.setInt(2, number);
            preStatement.setInt(3, teacher);
            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error checking teacher", e);
        }
        return result;
    }

    /**
     @author Shlimazl
     @return returns student's marks of subjects
     */
    public static List<MarkOverview> getMarksOfSubjectByStudent(int student, int subject){
        List<MarkOverview> result = new ArrayList<>();
        String sql = "SELECT * FROM " + Journal.TABLE
                + " WHERE " + Journal.ID_STUDENT
                + " =? AND " + Journal.ID_SUBJECT
                + " =?;";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, student);
            preStatement.setInt(2, subject);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                MarkOverview mark = new MarkOverview(rs.getInt(Journal.MARK),
                        rs.getString(Journal.MARK_TYPE),
                        rs.getInt(Journal.PRESENCE),
                        rs.getString(Journal.DATE));
                result.add(mark);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error selecting marks", e);
        }
        return result;

    }

    public static Lesson getLessonById(int id){

        Lesson result = null;

        String sql = "SELECT * FROM " + Schedules.TABLE
                + " WHERE " + Schedules.ID_LESSON + '=' + id + ';';
        try {
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                result = new Lesson.Builder()
                        .idLesson(rs.getInt(Schedules.ID_LESSON))
                        .day(DateTimeConverter.parseDayOfWeek(rs.getInt(Schedules.ID_DAY)))
                        .group(getGroupOverviewById(rs.getInt(Schedules.ID_GROUP)))
                        .order(rs.getInt(Schedules.ORDER))
                        .subject(getSubjectOverviewById(rs.getInt(Schedules.ID_SUBJECT)))
                        .teacher(getTeacherOverviewById(rs.getInt(Schedules.ID_TEACHER)))
                        .classroom(rs.getString(Schedules.CLASSROOM))
                        .build();
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting lesson by id", e);
        }

        return result;
    }

    public static StudentSchedule getStudentScheduleById(int id){

        StudentSchedule result = new StudentSchedule(getGroupOverviewByStudent(id));

        String sql = "SELECT %1, %2 ,%3, %4, %5, %6 "
                + " FROM " + Schedules.TABLE
                + " WHERE " + Schedules.ID_GROUP + '=' + result.getGroup().getId() + ';';
        sql = sql.replace("%1", Schedules.ID_LESSON);
        sql = sql.replace("%2", Schedules.ID_SUBJECT);
        sql = sql.replace("%3", Schedules.ID_DAY);
        sql = sql.replace("%4", Schedules.CLASSROOM);
        sql = sql.replace("%5", Schedules.ID_TEACHER);
        sql = sql.replace("%6", Schedules.ORDER);

        try {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                StudentLesson added = new StudentLesson.Builder()
                        .idLesson(rs.getInt(Schedules.ID_LESSON))
                        .subject(getSubjectOverviewById(rs.getInt(Schedules.ID_SUBJECT)))
                        .day(DateTimeConverter.parseDayOfWeek(rs.getInt(Schedules.ID_DAY)))
                        .classroom(rs.getString(Schedules.CLASSROOM))
                        .order(rs.getInt(Schedules.ORDER))
                        .teacher(getTeacherOverviewById(rs.getInt(Schedules.ID_TEACHER)))
                        .build();

                result.addLesson(added);

            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting student schedule", e);
        }

        return result;
    }

    public static TeacherSchedule getTeacherScheduleById(int id){

        TeacherSchedule result = new TeacherSchedule(getTeacherOverviewById(id));

        String sql = "SELECT %1, %2 ,%3, %4, %5, %6 "
                + " FROM " + Schedules.TABLE
                + " WHERE " + Schedules.ID_GROUP + '=' + result.getTeacher().getId() + ';';
        sql = sql.replace("%1", Schedules.ID_LESSON);
        sql = sql.replace("%2", Schedules.ID_SUBJECT);
        sql = sql.replace("%3", Schedules.ID_DAY);
        sql = sql.replace("%4", Schedules.CLASSROOM);
        sql = sql.replace("%5", Schedules.ID_GROUP);
        sql = sql.replace("%6", Schedules.ORDER);

        try {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                TeacherLesson added = new TeacherLesson.Builder()
                        .idLesson(rs.getInt(Schedules.ID_LESSON))
                        .subject(getSubjectOverviewById(rs.getInt(Schedules.ID_SUBJECT)))
                        .day(DateTimeConverter.parseDayOfWeek(rs.getInt(Schedules.ID_DAY)))
                        .classroom(rs.getString(Schedules.CLASSROOM))
                        .order(rs.getInt(Schedules.ORDER))
                        .group(getGroupOverviewById(rs.getInt(Schedules.ID_GROUP)))
                        .build();

                result.addLesson(added);

            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting teacher schedule", e);
        }

        return result;
    }
}
