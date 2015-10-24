/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.Teacher;
import school_manager.model.overviews.SubjectOverview;

/**
 * @author bepa
 */
public class AdminTeacherInsertionFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    private Map<String, Integer> subjectsOverview;

    @FXML
    private TextField tfFname;
    @FXML
    private TextField tfLname;
    @FXML
    private TextField tfPatronymic;
    @FXML
    private TextField tfBDay;
    @FXML
    private TextField tfAdress;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfNotes;
    @FXML
    private ListView<SubjectCell> lvSubjects;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initSubjectsListView();
    }
    
    private void initSubjectsListView() {
        subjectsOverview = DatabaseManager.getSubjectsList();
        lvSubjects.setCellFactory(CheckBoxListCell.forListView(SubjectCell::isChecked));
        subjectsOverview.forEach((name, id) -> {
            lvSubjects.getItems().add(new SubjectCell(name, id));
        });
        
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void btnConfirmClicked() {
        
        String fname = tfFname.getText();
        String lname = tfLname.getText();
        String patronymic = tfPatronymic.getText();
        String bday = tfBDay.getText();
        String address = tfAdress.getText();
        String phone = tfPhone.getText();
        String subjects = parseCheckedSubjects();
        String notes = tfNotes.getText();

        Teacher added = new Teacher.Builder()
                .fName(fname)
                .lName(lname)
                .patronymic(patronymic)
                .bday(bday)
                .address(address)
                .phone(phone)
                .notes(notes)
                .subjects(subjects)
                .build();
        
        DatabaseManager.insertTeacher(added);
        btnClearClicked();
    }
    
    private String parseCheckedSubjects(){
        final StringBuilder builder = new StringBuilder("");
        lvSubjects.getItems().forEach((e) -> {
            if (e.isChecked().getValue()){
                builder.append(e.getId());
                builder.append(",");
            }
        });
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    @FXML
    public void btnClearClicked() {
        tfFname.clear();
        tfLname.clear();
        tfPatronymic.clear();
        tfBDay.clear();
        tfAdress.clear();
        tfPhone.clear();
        tfNotes.clear();
    }
    
    class SubjectCell extends SubjectOverview {
        
        public SubjectCell(String name, int id){
            super(name, id);
        }
        
        SimpleBooleanProperty checked = new SimpleBooleanProperty(false);

        public SimpleBooleanProperty isChecked() {
            return checked;
        }
        public void setChecked(boolean checked){
            this.checked.set(checked);
        }
        
    }
        
    
}
