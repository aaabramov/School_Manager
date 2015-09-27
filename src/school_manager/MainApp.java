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
import school_manager.model.User;
import school_manager.view.LoginFragmentController;
import school_manager.view.ParentMenuFragmentController;
import school_manager.view.RootLayoutController;

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

       /* Scene scene = initRootLayout();
        initLogin();
*/
        System.out.println(DatabaseManager.getSubjectById(1));
  /*      
        stage.setScene(scene);
        stage.show();
    */    
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

    public void setAccountInfo(User accountInfo){
        
        if (accountInfo != null){
            
            this.accountInfo = accountInfo;
            switch (accountInfo.getAccType()){
                
                case STUDENT:
                    
                    break;
                case TEACHER:
                    VBox parentMenuPane;
                    FXMLLoader loader = new FXMLLoader();

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
                case PARENT:
                    
                    break;
                case ADMIN:
                    
                    break;
                default:

                    break;
                
            }
            
        }
        
    }
    
}
