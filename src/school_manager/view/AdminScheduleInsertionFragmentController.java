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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.overviews.GroupOverview;
import school_manager.model.overviews.SubjectOverview;
import school_manager.model.overviews.TeacherOverview;
import school_manager.model.schedule.Schedule.Day;
import school_manager.model.schedule.Lesson;

/**
 * FXML Controller class
 *
 * @author bepar
 */
public class AdminScheduleInsertionFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    private List<GroupOverview> groupOverview;
    private List<SubjectOverview> subjectsOverview;
    private List<TeacherOverview> teacherOverview;

    @FXML
    private BorderPane bpMain;
    @FXML
    private ComboBox<GroupOverview> cmbGroup;
    @FXML
    private Label lblOrder;
    @FXML
    private ComboBox<SubjectOverview> cmbSubject;
    @FXML
    private ComboBox<TeacherOverview> cmbTeacher;
    @FXML 
    private TextField tfClassroom;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnConfirm;
    @FXML
    private Label lblDay;
    
    private final String[] Days = new String[]{"Monday", "Tuesday", "Wednesday", "Thuesday", "Friday"};
    private final Day[] eDays = new Day[]{Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY};
    private int indexDay = 1;
    private int indexLesson = DatabaseManager.getLastIdFromSchedule() + 1;
    
    public List<Lesson> scheduleToAdd = FXCollections.observableArrayList();
    
    private TableView<ScheduleItemTable> table = new TableView<ScheduleItemTable>();
    private final ObservableList<ScheduleItemTable> data =
        FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initGroups();
        initSubjects();
        initTeachers();
        initTable();
    }   
    
   @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    @FXML
    public void addItem(){
        data.add(new ScheduleItemTable(lblOrder.getText(), cmbSubject.getValue().toString(), 
                cmbTeacher.getValue().toString(), tfClassroom.getText()));
        
        scheduleToAdd.add(new Lesson.Builder()
                .classroom(tfClassroom.getText())
                .day(eDays[indexDay - 1])
                .group(cmbGroup.getValue())
                .idLesson(indexLesson++)
                .order(Integer.parseInt(lblOrder.getText()))
                .subject(cmbSubject.getValue())
                .teacher(cmbTeacher.getValue())
                .build());
        
        lblOrder.setText(Integer.toString(data.size() + 1));
        tfClassroom.setText("13");
    }
    
    @FXML
    public void btnCancelIsClicked(){
        indexDay = 0;
        lblDay.setText("Schedule for " + Days[indexDay]);
        btnNext.setText(Days[++indexDay]);
        btnNext.setDisable(false);
        btnConfirm.setDisable(true);
        tfClassroom.setText(null);
        cmbGroup.setDisable(false);
        data.clear();
    }
    
    @FXML
    public void NextDay(){
        data.clear();
        scheduleToAdd.clear();
        lblOrder.setText(Integer.toString(data.size() + 1));
        tfClassroom.setText(null);
        if(indexDay < 5){
            lblDay.setText("Schedule for " + Days[indexDay]);
            if(indexDay < 4)btnNext.setText(Days[++indexDay]);
            else {
                btnNext.setDisable(true);
                btnConfirm.setDisable(false);
            }                        
        }
        cmbGroup.setDisable(true);
    }
    
    @FXML
    public void btnConfirmIsClicked(){
        scheduleToAdd.forEach((e) ->{
            DatabaseManager.setLesson(e, DatabaseManager.getLastIdFromSchedule() + 1);
        });
        btnCancelIsClicked();
    }
    
    public void initGroups(){
        groupOverview = DatabaseManager.getGroupsList();
        ObservableList<GroupOverview> groupList = FXCollections.observableArrayList(groupOverview);
        cmbGroup.setItems(groupList);
        cmbGroup.setValue(groupList.get(0));
    }
    
     public void initSubjects(){
        subjectsOverview = DatabaseManager.getSubjectsList();
        ObservableList<SubjectOverview> subjectsList = FXCollections.observableArrayList(subjectsOverview);
        cmbSubject.setItems(subjectsList);
        cmbSubject.setValue(subjectsList.get(0));
     }
     
     public void initTeachers(){
        teacherOverview = DatabaseManager.getTeachersNotCurators();
        ObservableList<TeacherOverview> teacherList = FXCollections.observableArrayList(teacherOverview);
        cmbTeacher.setItems(teacherList);
        cmbTeacher.setValue(teacherList.get(0));
     }
    
    private void initTable(){
        
        TableColumn orderCol = new TableColumn("№");
        orderCol.setMinWidth(25);
        orderCol.setMaxWidth(25);
        orderCol.setCellValueFactory(
                new PropertyValueFactory<ScheduleItemTable,String>("order")
        );

        TableColumn subjectCol = new TableColumn("Subject");
        subjectCol.setMinWidth(175);
        subjectCol.setCellValueFactory(
            new PropertyValueFactory<ScheduleItemTable,String>("subject")
        );

        TableColumn teacherCol = new TableColumn("Teacher");
        teacherCol.setMinWidth(270);
        teacherCol.setCellValueFactory(
            new PropertyValueFactory<ScheduleItemTable,String>("teacher")
        );
        
        TableColumn classCol = new TableColumn("Classroom");
        classCol.setMinWidth(30);
        classCol.setCellValueFactory(
                new PropertyValueFactory<ScheduleItemTable, String>("classroom")
        );
                                     
        table.setItems(data);
        table.getColumns().addAll(orderCol, subjectCol, teacherCol, classCol);
        
        bpMain.setCenter(table);
    }
    
    public static class ScheduleItemTable {
        private final SimpleStringProperty order;
        private final SimpleStringProperty subject;
        private final SimpleStringProperty teacher;
        private final SimpleStringProperty classroom;

        private ScheduleItemTable(String sOrder, String sSubject, String sTeacher, String sClassroom) {
            this.order = new SimpleStringProperty(sOrder);
            this.subject = new SimpleStringProperty(sSubject);
            this.teacher = new SimpleStringProperty(sTeacher);
            this.classroom = new SimpleStringProperty(sClassroom);
        }

        public String getOrder() {
            return order.get();
        }
        public void setOrder(String sOrder) {
            order.set(sOrder);
        }
       
        public String getSubject() {
            return subject.get();
        }
        public void setSubject(String sSubject) {
            subject.set(sSubject);
        }
       
        public String getTeacher() {
            return teacher.get();
        }
        public void setTeacher(String sTeacher) {
            teacher.set(sTeacher);
        }
        
        public String getClassroom() {
            return classroom.get();
        }
        
        public void setClassroom(String sClassroom) {
            classroom.set(sClassroom);
        }
       
    }
}