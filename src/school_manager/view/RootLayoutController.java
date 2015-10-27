/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import school_manager.MainApp;
import school_manager.helpers.MainReferenced;
import school_manager.model.User;

/**
 * FXML Controller class
 *
 * @author abrasha
 */
public class RootLayoutController implements Initializable, MainReferenced {

    private MainApp mainApp;

    @FXML
    private StackPane contentPane;
    @FXML
    private StackPane menuPane;
    @FXML
    private Label statusLabel;
    @FXML
    private SplitPane splitPane;
    @FXML
    public Label nameLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        splitPane.widthProperty().addListener(
                (observable, oldValue, newValue) -> {

                    menuPane.setMaxWidth(newValue.doubleValue() * 0.2);
                    contentPane.setMaxWidth(newValue.doubleValue() * 0.8);
                });
    }

    @FXML
    public void menuLoginClicked() {

    }

    @FXML
    public void btnLogoutClicked() {
        mainApp.logOut();
    }

    @FXML
    public void menuLoginAsAdminClicked() {
        mainApp.setAccountInfo(new User(3, 0, User.AccType.ADMIN));
        setStatus("Forward login as admin");
    }

    @FXML
    public void menuLoginAsStudentClicked() {
        mainApp.setAccountInfo(new User(1, 0, User.AccType.STUDENT));
        setStatus("Forward login as student");
    }

    @FXML
    public void menuLoginAsTeacherClicked() {
        mainApp.setAccountInfo(new User(7, 0, User.AccType.TEACHER));
        setStatus("Forward login as teacher");
    }

    @FXML
    public void menuLoginAsParentClicked() {
        mainApp.setAccountInfo(new User(15, 0, User.AccType.PARENT));
        setStatus("Forward login as parent");
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public StackPane getContentPane() {
        return contentPane;
    }

    public StackPane getMenuPane() {
        return menuPane;
    }

    public SplitPane getSplitPane() {
        return splitPane;
    }

    public Label getLabelStatus() {
        return statusLabel;
    }

    public Label getUserInfoLabel() {
        return nameLabel;
    }

    public void setStatus(String status) {
        if (status != null) {
            statusLabel.setText(status);
        } else {
            statusLabel.setText("Error setting status: null string");
        }
    }

    public void setContent(Node content) {
        if (content != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(content);
        } else {
            setStatus("Error setting content: null node");
        }
    }

    public void setUserName(String name) {
        nameLabel.setText(name);
    }

    public void setMenu(Node menu) {
        if (menu != null) {
            menuPane.getChildren().clear();
            menuPane.getChildren().add(menu);
        } else {
            setStatus("Error setting menu: null node");
        }
    }

    public void loadLoginFragment() {
        menuPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();
        BorderPane pane;
        LoginFragmentController loginController;
        try {

            loader.setLocation(getClass().getResource("LoginFragment.fxml"));
            pane = (BorderPane) loader.load();

            loginController = loader.getController();
            loginController.setMainApp(mainApp);

            setContent(pane);

        } catch (IOException e) {
            mainApp.getLogger().log(Level.SEVERE, "Error initializing Login Layout", e);
        }
    }

    public void loadMenu(User.AccType accType) {

        switch (accType) {

            case STUDENT:
                setStatus("Student form has not been created yet.");
                loadStudentMenu();
                break;
            case TEACHER:
                loadTeacherMenu();
                break;
            case PARENT:
                loadParentMenu();
                break;
            case ADMIN:
                loadAdminMenu();
                break;
            case UNKNOWN:
                setStatus("Unknown type off accaount");
                break;

        }
    }

    private void loadStudentMenu() {
        FXMLLoader loader = new FXMLLoader();
        VBox studentMenuPane;

        try {
            loader.setLocation(getClass().getResource("StudentMenuFragment.fxml"));
            studentMenuPane = (VBox) loader.load();

            StudentMenuFragmentController studentMenuController = loader.getController();
            studentMenuController.setMainApp(mainApp);

            setMenu(studentMenuPane);
            setStatus("Student menu set.");
        } catch (IOException e) {
            mainApp.getLogger().log(Level.SEVERE, "Error setting student menu", e);
            setStatus("Error setting student menu...");
        }
    }

    private void loadTeacherMenu() {
        FXMLLoader loader = new FXMLLoader();
        VBox teacherMenuPane;

        try {
            loader.setLocation(getClass().getResource("TeacherMenuFragment.fxml"));
            teacherMenuPane = (VBox) loader.load();

            TeacherMenuFragmentController teacherMenuController = loader.getController();
            teacherMenuController.setMainApp(mainApp);

            setMenu(teacherMenuPane);
            setStatus("Teacher menu set.");
        } catch (IOException e) {
            mainApp.getLogger().log(Level.SEVERE, "Error setting teacher menu", e);
            setStatus("Error setting teacher menu...");
        }
    }

    private void loadAdminMenu() {

        FXMLLoader loader = new FXMLLoader();
        Accordion adminMenuPane;

        try {

            loader.setLocation(getClass().getResource("AdminMenuFragment.fxml"));
            adminMenuPane = (Accordion) loader.load();

            AdminMenuFragmentController adminMenuController = loader.getController();
            adminMenuController.setMainApp(mainApp);

            setMenu(adminMenuPane);
            setStatus("Admin menu set.");
        } catch (IOException e) {
            mainApp.getLogger().log(Level.SEVERE, "Error setting admin menu", e);
            setStatus("Error settong admin menu");
        }
    }

    private void loadParentMenu() {
        FXMLLoader loader = new FXMLLoader();
        VBox parentMenuPane;

        try {

            loader.setLocation(getClass().getResource("ParentMenuFragment.fxml"));
            parentMenuPane = (VBox) loader.load();

            ParentMenuFragmentController parentMenuController = loader.getController();
            parentMenuController.setMainApp(mainApp);

            setMenu(parentMenuPane);
            setStatus("Parent menu set.");
        } catch (Exception e) {
            mainApp.getLogger().log(Level.SEVERE, "Error setting parent menu", e);
            setStatus("Error setting parent menu.");
        }
    }

}
