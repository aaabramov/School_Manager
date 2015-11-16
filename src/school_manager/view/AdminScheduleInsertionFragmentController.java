/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.overviews.GroupOverview;
import school_manager.model.overviews.SubjectOverview;
import school_manager.model.overviews.TeacherOverview;

/**
 * FXML Controller class
 *
 * @author bepa
 */
public class AdminScheduleInsertionFragmentController implements Initializable, MainReferenced {
    
    private MainApp mainApp;
    private List<GroupOverview> groupOverview;
    private List<SubjectOverview> subjectsOverview;
    private List<TeacherOverview> teacherOverview;

    @FXML
    private ComboBox<GroupOverview> cbGroup;
    @FXML
    private ComboBox<SubjectOverview> cbSubjectsMon;
    @FXML
    private ComboBox<SubjectOverview> cbSubjectsTue;
    @FXML
    private ComboBox<SubjectOverview> cbSubjectsWed;
    @FXML
    private ComboBox<SubjectOverview> cbSubjectsThu;
    @FXML
    private ComboBox<SubjectOverview> cbSubjectsFri;
    @FXML
    private ComboBox<TeacherOverview> cbTeachersMon;
    @FXML
    private ComboBox<TeacherOverview> cbTeachersTue;
    @FXML
    private ComboBox<TeacherOverview> cbTeachersWed;
    @FXML
    private ComboBox<TeacherOverview> cbTeachersThu;
    @FXML
    private ComboBox<TeacherOverview> cbTeachersFri;
    @FXML
    private TextField tfMon;
    @FXML
    private TextField tfTue;
    @FXML
    private TextField tfWed;
    @FXML
    private TextField tfThu;
    @FXML
    private TextField tfFri;
    @FXML
    private TableView<TableItemSchedule> tvMonday;
    @FXML
    private TableView<TableItemSchedule> tvTuesday;
    @FXML
    private TableView<TableItemSchedule> tvWednesday;
    @FXML
    private TableView<TableItemSchedule> tvThuesday;
    @FXML
    private TableView<TableItemSchedule> tvFriday;
    @FXML 
    private Label lblMon;
    @FXML 
    private Label lblTue;
    @FXML 
    private Label lblWed;
    @FXML 
    private Label lblThu;
    @FXML 
    private Label lblFri;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initGroups();
        initSubjects();
        initGroups();
    }    
    
    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    public void initGroups(){
        groupOverview = DatabaseManager.getGroupsList();
        ObservableList<GroupOverview> groupList = FXCollections.observableArrayList(groupOverview);
        cbGroup.setItems(groupList);
        cbGroup.setValue(groupList.get(0));
    }
    
    public void initTeachers(){
        teacherOverview = DatabaseManager.getTeachersNotCurators();
        ObservableList<TeacherOverview> teacherList = FXCollections.observableArrayList(teacherOverview);
        cbTeachersMon.setItems(teacherList);
        cbTeachersMon.setValue(teacherList.get(0));
        cbTeachersTue.setItems(teacherList);
        cbTeachersTue.setValue(teacherList.get(0));
        cbTeachersWed.setItems(teacherList);
        cbTeachersWed.setValue(teacherList.get(0));
        cbTeachersThu.setItems(teacherList);
        cbTeachersThu.setValue(teacherList.get(0));
        cbTeachersFri.setItems(teacherList);
        cbTeachersFri.setValue(teacherList.get(0));
    }
    
    public void initSubjects(){
        subjectsOverview = DatabaseManager.getSubjectsList();
        ObservableList<SubjectOverview> subjectsList = FXCollections.observableArrayList(subjectsOverview);
        cbSubjectsMon.setItems(subjectsList);
        cbSubjectsMon.setValue(subjectsList.get(0));
        cbSubjectsTue.setItems(subjectsList);
        cbSubjectsTue.setValue(subjectsList.get(0));
        cbSubjectsWed.setItems(subjectsList);
        cbSubjectsWed.setValue(subjectsList.get(0));
        cbSubjectsThu.setItems(subjectsList);
        cbSubjectsThu.setValue(subjectsList.get(0));
        cbSubjectsFri.setItems(subjectsList);
        cbSubjectsFri.setValue(subjectsList.get(0));
    }
    
    class TableItemSchedule{
        private final SimpleStringProperty order;
        private final SimpleStringProperty subject;
        private final SimpleStringProperty teacher;
        private final SimpleStringProperty classroom;
        
        public TableItemSchedule(String Order, String Subject, String Teacher, String Classroom){
            order = new SimpleStringProperty(Order);
            subject = new SimpleStringProperty(Subject);
            teacher = new SimpleStringProperty(Teacher);
            classroom = new SimpleStringProperty(Classroom);
        }
    }
}
