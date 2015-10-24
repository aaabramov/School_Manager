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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.Group;
import school_manager.model.overviews.StudentOverview;
import school_manager.model.overviews.TeacherOverview;

/**
 * FXML Controller class
 *
 * @author bepa
 */
public class AdminGroupInsertionFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    private List<TeacherOverview> potentialCurators;

    @FXML
    private TextField tfCode;
    @FXML
    private ComboBox<TeacherOverview> cbPotentialCurators;
    @FXML
    private TextField tfNotes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initPotentialCurators();
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void btnClearClicked() {
        tfCode.clear();
        cbPotentialCurators.setValue(null);
        tfNotes.clear();
    }

    @FXML
    private void btnConfirmClicked() {

        if (cbPotentialCurators.getSelectionModel().getSelectedItem() != null) {

            int curatorId = cbPotentialCurators.getSelectionModel().getSelectedItem().getId();

            Group added = new Group.Builder()
                    .code(tfCode.getText())
                    .idCurator(curatorId)
                    .notes(tfNotes.getText())
                    .build();

            DatabaseManager.insertGroup(added);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error adding group");
            alert.setContentText("Please, select the curator for group");
            alert.showAndWait();
        }
    }

    private void initPotentialCurators() {
        potentialCurators = DatabaseManager.getTeachersNotCurators();

        if (!potentialCurators.isEmpty()) {
            potentialCurators.sort((t1, t2) -> {
                return t1.getInitials().compareTo(t2.getInitials());
            });
            ObservableList<TeacherOverview> items = FXCollections.observableList(potentialCurators);
            cbPotentialCurators.setItems(items);
            cbPotentialCurators.setValue(items.get(0));
        } else {
            cbPotentialCurators.setPromptText("No avaible teachers");
        }

    }
}
