/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.Teacher;

/**
 * FXML Controller class
 *
 * @author bepa
 */
public class AdminTeacherInsertionFragmentController implements Initializable, MainReferenced {

    MainApp mainApp;

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
    private TextField tfSubjects;

    @FXML
    private Button buttonConfirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void btnConfirmClicked() {

        String fname = tfFname.getText();
        String lname = tfLname.getText();
        String patronymic = tfPatronymic.getText();
        String bday = tfBDay.getText();
        String address = tfAdress.getText();
        String phone = tfPhone.getText();
        String subjects = tfSubjects.getText();
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

    }

    @Override
    public void setMainApp(MainApp ma) {
        this.mainApp = ma;
    }

}
