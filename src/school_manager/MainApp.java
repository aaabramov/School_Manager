package school_manager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import school_manager.helpers.DatabaseManager;
import school_manager.model.Teacher;
import school_manager.model.User;
import school_manager.view.AdminMenuFragmentController;
import school_manager.view.LoginFragmentController;
import school_manager.view.ParentMenuFragmentController;
import school_manager.view.RootLayoutController;
import school_manager.view.TeacherMenuFragmentController;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private RootLayoutController rootController;
    private VBox contentPane;
    private VBox menuPane;
    private Label statusLabel;
    private User accountInfo;

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = initRootLayout();
        initLogin();
        //TODO
        //stage.getIcons().add(new Image(new File("appicon.png").toURI().toString()));
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DatabaseManager.close();
    }

    private Scene initRootLayout() {

        FXMLLoader loader = new FXMLLoader();
        BorderPane root = null;

        try {

            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            root = (BorderPane) loader.load();

            rootController = loader.getController();
            rootController.setMainApp(this);

            contentPane = rootController.getContentPane();
            menuPane = rootController.getMenuPane();
            statusLabel = rootController.getLabelStatus();
            setStatus("RootLoaded");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return (new Scene(root));
    }

    private void initLogin() {
        FXMLLoader loader = new FXMLLoader();
        BorderPane pane;
        LoginFragmentController loginController;
        try {

            loader.setLocation(getClass().getResource("view/LoginFragment.fxml"));
            pane = (BorderPane) loader.load();

            loginController = loader.getController();
            loginController.setMainApp(this);

            setContent(pane);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public MainApp getMainApp() {
        return this;
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

    public void setMenu(Node menu) {
        if (menu != null) {
            menuPane.getChildren().clear();
            menuPane.getChildren().add(menu);
        } else {
            setStatus("Error setting menu: null node");
        }
    }

    public void logOut() {
        if (accountInfo != null) {
            setAccountInfo(null);
            menuPane.getChildren().clear();
            initLogin();
        }
    }

    public void setAccountInfo(User accountInfo) {

        if (accountInfo != null) {

            FXMLLoader loader = new FXMLLoader();

            this.accountInfo = accountInfo;
            switch (accountInfo.getAccType()) {

                case STUDENT:

                    break;
                case TEACHER:
                    VBox teacherMenuPane;

                    try {
                        loader.setLocation(getClass().getResource("view/TeacherMenuFragment.fxml"));
                        teacherMenuPane = (VBox) loader.load();

                        TeacherMenuFragmentController teacherMenuController = loader.getController();
                        teacherMenuController.setMainApp(this);

                        setMenu(teacherMenuPane);
                        setStatus("Teacher menu set.");
                        //TODO teacherMenuController.setTeacher(null);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        setStatus("Error setting teacher menu...");
                    }

                    break;
                case PARENT:
                    VBox parentMenuPane;

                    try {

                        loader.setLocation(getClass().getResource("view/ParentMenuFragment.fxml"));
                        parentMenuPane = (VBox) loader.load();

                        ParentMenuFragmentController parentMenuController = loader.getController();
                        parentMenuController.setMainApp(this);
                        //TODO parentMenuController.setParent(null);

                        setMenu(parentMenuPane);
                        setStatus("Parent menu set.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        setStatus("Error setting parent menu.");
                    }
                    break;
                case ADMIN:
                    Accordion adminMenuPane;

                    try {

                        loader.setLocation(getClass().getResource("view/AdminMenuFragment.fxml"));
                        adminMenuPane = (Accordion) loader.load();

                        AdminMenuFragmentController adminMenuController = loader.getController();
                        adminMenuController.setMainApp(this);

                        setMenu(adminMenuPane);
                        setStatus("Admin menu set.");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        setStatus("Error settong admin menu");
                    }

                    break;
                default:

                    break;

            }

        } else {

        }

    }

}
