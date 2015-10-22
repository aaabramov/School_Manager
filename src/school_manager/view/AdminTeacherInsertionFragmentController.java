/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.Teacher;

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
    private HBox hbSubjects;
    @FXML
    private ListView<String> lvSubjects;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initSubjectsListView();

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
        //String subjects = tfSubjects.getText();
        String notes = tfNotes.getText();

        Teacher added = new Teacher.Builder()
                .fName(fname)
                .lName(lname)
                .patronymic(patronymic)
                .bday(bday)
                .address(address)
                .phone(phone)
                .notes(notes)
                //.subjects(subjects)
                .build();

        DatabaseManager.insertTeacher(added);
        btnClearClicked();
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
        hbSubjects.getChildren().clear();
    }

    @FXML
    private void initSubjectsListView() {
        subjectsOverview = DatabaseManager.getSubjectsList();
        lvSubjects.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList subjects = FXCollections.observableArrayList(subjectsOverview.keySet());
        /*ObservableList subjects = FXCollections.observableArrayList(
                "Українська мова", "Українська література", new Separator(),
                "Зарубіжна література", "Англійська мова", new Separator(),
                "Математика", "Алгебра", "Геометрія", "Інформатика", new Separator(),
                "Історія України", "Всесвітня історія", "Людина і суспільство", "Правознавство", new Separator(),
                "Географія", "Біологія", "Фізика", "Хімія", new Separator(),
                "Музика", "Фізична культура"
        );*/
        lvSubjects.setEditable(false);
        lvSubjects.setItems(subjects);
    }
    /*
    @FXML
    public void btnAddSubjectClicked() {

        String[] listSubjects = new String[]{"Українська мова", "Українська література", "",
            "Зарубіжна література", "Англійська мова", "",
            "Математика", "Алгебра", "Геометрія", "Інформатика", "",
            "Історія України", "Всесвітня історія", "Людина і суспільство", "Правознавство", "",
            "Географія", "Біологія", "Фізика", "Хімія", "",
            "Музика", "Фізична культура"};

        hbSubjects.getChildren().add(new Label(
                listSubjects[cbSubjects.getSelectionModel().getSelectedIndex()]));
    }*/
    
}
