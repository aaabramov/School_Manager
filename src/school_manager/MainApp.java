package school_manager;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import school_manager.helpers.DatabaseManager;
import school_manager.view.LoginFragmentController;
import school_manager.view.RootLayoutController;

public class MainApp extends Application {

    public static void main(String[] args) {
        //launch(args);
        DatabaseManager.load();
        
        System.out.println(DatabaseManager.getStudentById(1));
        
        DatabaseManager.close();
        System.exit(0);
    }

    RootLayoutController rootController;
    VBox contentPane;
    VBox menuPane;
    Label statusLabel;

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = initRootLayout();
        initLogin();

        stage.setScene(scene);
        stage.show();
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
            contentPane.getChildren().add(pane);

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

}
