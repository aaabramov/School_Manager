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
    public static String MyGroupCodeByStudent(int id)
    {
        String result="";
        try {
            preStatement = connection.prepareStatement ("SELECT code FROM groups WHERE id_group=(SELECT id_group FROM students WHERE id_student =?);");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if(rs.next())
            result=rs.getString("code");
            }
        catch (SQLException e){
            System.out.println("Error select group code " + e.getMessage());
            }
        return result;
    }
        
    /**
     *
     * @author Shlimazl
     *
     * returns code of curator's group
     */
    public static String MyGroupCodeByCurator(int id)
    {
        String result="";
        try {
            preStatement = connection.prepareStatement ("SELECT code FROM groups WHERE id_curator =?;");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if(rs.next())
            result=rs.getString("code");
            }
        catch (SQLException e){
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
    public static String MyCuratorByStudent(int id)
    {
        String result = "";
        String res1="";
        String res2="";
        String res3="";
        try  {
        preStatement = connection.prepareStatement ("SELECT lastname,fname,patronymic FROM teachers WHERE id_teachers = (SELECT id_curator FROM groups WHERE id_group = (SELECT id_group FROM students WHERE id_student=?));");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if(rs.next())
            {
                res1=rs.getString("lastname");
                res2=rs.getString("fname");
                res3=rs.getString("patronymic");
                result=res1 + " " + res2 + " " + res3;
            }
        }
        catch (SQLException e){
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
    public static String MyInfoByStudent(int id)
    {
        String result = "";
        String res1="";
        String res2="";
        String res3="";
        try  {
        preStatement = connection.prepareStatement ("SELECT lastname,fname,patronymic FROM students WHERE id_student = ?;");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if(rs.next())
            {
                res1=rs.getString("lastname");
                res2=rs.getString("fname");
                res3=rs.getString("patronymic");
                result=res1 + " " + res2 + " " + res3;
            }
        }
        catch (SQLException e){
            System.out.println("Error select curator " + e.getMessage());
            }
        return result;
    }
     /**
     *
     * @author Shlimazl
     *
     * returns name of subject by id
     */
    
     private static String GetSubject(int id)
   {
       String result="";
       try{
        preStatement = connection.prepareStatement ("SELECT name from subjects WHERE id_subject=?;");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if(rs.next())
            {
                result=rs.getString("name");
                result+="\n";
            }
            }
       catch (SQLException e){
            System.out.println("Error select subject " + e.getMessage());
            }
        return result;
   }
     
     /**
     *
     * @author Shlimazl
     *
     * returns day means by id
     */
     private static String Whatday(int day)
     {
       String str1="";
       if(day==1)
                {str1="понеділок\t";}
                else if(day==2)
                {str1="вівторок\t";}
                else if(day==3)
                {str1="середа\t\t";}
                else if(day==4)
                {str1="четвер\t\t";}
                else if(day==5)
                {str1="п'ятниця\t";}
       return str1;
     }
      /**
     *
     * @author Shlimazl
     *
     * returns student's group's schedule
     */
     public static String ScheduleByStudent(int id)
    {
        String result="";
        String str1="";
        String str2="";
        int numb=0;
        int tmp=0;
        int day=0;
        int current_day=1;
        try{
            preStatement = connection.prepareStatement ("SELECT id_day,number,id_subject FROM schedule WHERE id_group = (select id_group FROM students WHERE id_student =?);");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            while(rs.next())
            {
                day=rs.getInt("id_day");
                numb=rs.getInt("number");
                tmp=rs.getInt("id_subject");
                
                str2=GetSubject(tmp);
                
                str1=Whatday(day);
                
                if(day>current_day)
                {
                 current_day++;
                 result+="\n";
                }
                result+=str1+numb+"\t"+str2;
                
                }
                day=0;numb=0;tmp=0;
                str1="";str2="";
            }
            
        catch (SQLException e){
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
     private static String GetGroup(int id)
   {
       String result="";
       try{
        preStatement = connection.prepareStatement ("SELECT code from groups WHERE id_group=?;");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            if(rs.next())
            {
                result=rs.getString("code");
                result+="\t";
            }
            }
       catch (SQLException e){
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
    public static String ScheduleByTeacher(int id)
    {
        String result="";
        String str1="";
        String str2="";
        String str3="";
        int numb=0;
        int tmp=0;
        int day=0;
        int tmp1;
        int current_day=1;
        try{
            preStatement = connection.prepareStatement ("SELECT id_day,number,id_subject,id_group FROM schedule WHERE id_teacher = ?;");
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
            while(rs.next())
            {
                day=rs.getInt("id_day");
                numb=rs.getInt("number");
                tmp=rs.getInt("id_subject");
                tmp1=rs.getInt("id_group");
               
                str1=Whatday(day); 
                str2=GetSubject(tmp);
                str3=GetGroup(tmp1);
                
                if(day>current_day)
                {
                 current_day++;
                 result+="\n";
                }
                result+=str1+numb+"\t"+str3+"\t"+str2+"\n";
                
                }
                day=0;numb=0;tmp=0;tmp1=0;
                str1="";str2="";str3="";
            }
            
        catch (SQLException e){
            System.out.println("Error select schedule " + e.getMessage());
            }
        return result;
   }
}
