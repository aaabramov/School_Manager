package school_manager;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import school_manager.helpers.DatabaseManager;
import school_manager.model.User;
import school_manager.view.RootLayoutController;

public class MainApp extends Application {

    public static void main(String[] args) {
      
        launch(args);
    }

    private RootLayoutController rootController;
    private User accountInfo;
    private Logger logger;

    @Override
    public void start(Stage stage) throws Exception {

        //new Thread(DatabaseManager::load).start();
        DatabaseManager.load();
        
        logger = Logger.getLogger(getClass().getCanonicalName());

        Scene scene = initRootLayout();
        rootController.loadLoginFragment();

        stage.setTitle("School Manager");
        stage.getIcons().add(new Image("file:view/res/logl_small.png"));
        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setMinWidth(800);
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
            setStatus("RootLoaded");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error initializing Root Layout", e);
        }

        return (new Scene(root));
    }

    public MainApp getMainApp() {
        return this;
    }

    public void setStatus(String status) {
        rootController.setStatus(status);
    }

    public void setContent(Node content) {
        rootController.setContent(content);
    }

    public void setUserName(String name) {
        rootController.setUserName(name);
    }

    public void setMenu(Node menu) {
        rootController.setMenu(menu);
    }

    public void logOut() {
        if (accountInfo != null) {
            setAccountInfo(null);
            rootController.loadLoginFragment();
            rootController.nameLabel.setText("Виконайте вхід до системи");
            setStatus("User logged out");
        }
    }

    public void setAccountInfo(User accountInfo) {

        if (accountInfo != null) {

            this.accountInfo = accountInfo;
            rootController.loadMenu(accountInfo.getAccType());

        } else {
            setStatus("Error setting user: User is null");
        }

    }

    public User getAccountInfo() {
        return accountInfo;
    }

    public Logger getLogger() {
        return logger;
    }

}
