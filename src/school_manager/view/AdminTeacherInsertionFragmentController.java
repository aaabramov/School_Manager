/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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
    private Map<String, Integer> groupOverview;
    
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
    private ComboBox cbSubjects;
    @FXML
    private HBox hbSubjects;
    @FXML
    private CheckBox cBox;
    @FXML
    private ComboBox cbGroup;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initSubjectsComboBox();
        initGroups();
        
        cBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
        public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
                if(new_val != null && new_val == true)
                    cbGroup.setVisible(true);
                else if(new_val != null && new_val == false)
                    cbGroup.setVisible(false);
            }
        });
        
        
    }

    @Override
    public void setMainApp(MainApp ma) {
        this.mainApp = ma;
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
        cbSubjects.setValue(null);
        hbSubjects.getChildren().clear();
        cbGroup.setValue(null);
        cBox.selectedProperty().set(false);
    }

    @FXML
    private void initSubjectsComboBox() {
        ObservableList subjects = FXCollections.observableArrayList(
                "Українська мова", "Українська література", new Separator(),
                "Зарубіжна література", "Англійська мова", new Separator(),
                "Математика", "Алгебра", "Геометрія", "Інформатика", new Separator(),
                "Історія України", "Всесвітня історія", "Людина і суспільство", "Правознавство", new Separator(),
                "Географія", "Біологія", "Фізика", "Хімія", new Separator(),
                "Музика", "Фізична культура"
        );

        cbSubjects.setItems(subjects);
    }

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
    }
    
    @FXML
    private void initGroups(){
        groupOverview = DatabaseManager.getGroupsList();
        ObservableList<String> groupList = FXCollections.observableArrayList(groupOverview.keySet());
        cbGroup.setItems(groupList);
    }
}
