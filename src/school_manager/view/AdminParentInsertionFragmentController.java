/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.overviews.StudentOverview;

/**
 * FXML Controller class
 *
 * @author bepa
 */
public class AdminParentInsertionFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    private List<StudentOverview> foundStudents;

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
    private TextField tfChildSearch;
    @FXML
    private ComboBox<StudentOverview> cbFoundedStudents;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void btnClearClicked() {
        tfLname.clear();
        tfFname.clear();
        tfPatronymic.clear();
        tfBDay.clear();
        tfAdress.clear();
        tfPhone.clear();
        tfNotes.clear();
        tfChildSearch.clear();
    }

    @FXML
    public void btnAddClicked() {

    }

    @FXML
    public void btnFindChildrenClicked() {
        cbFoundedStudents.getItems().clear();
        String surname = tfChildSearch.getText();
        if (!surname.isEmpty()) {
            foundStudents = DatabaseManager.getStudentsBySurname(surname);
            foundStudents.sort((s1, s2) -> {
                return s1.getInitials().compareTo(s2.getInitials());
            });
            
            if (!foundStudents.isEmpty()) {
                ObservableList<StudentOverview> items = FXCollections.observableArrayList(foundStudents);
                cbFoundedStudents.setItems(items);
                cbFoundedStudents.setValue(items.get(0));
            } else {
                cbFoundedStudents.setPromptText("No students found");
            }
        }
    }

}
