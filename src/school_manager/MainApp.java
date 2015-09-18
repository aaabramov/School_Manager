/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import school_manager.view.RootLayoutController;

/**
 *
 * @author abrasha
 */
public class MainApp extends Application {
    
    RootLayoutController rootController;
    VBox contentPane;
    VBox menuPane;
    Label statusLabel;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Scene scene = initRootLayout();
        setTestMenu();
        
        stage.setScene(scene);
        stage.show();
    }
    
    private void setTestMenu(){
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/TestMenu.fxml"));
        setMenu((VBox)loader.load());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        setStatus("Menu loaded");
    }
    
    private Scene initRootLayout() throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            BorderPane root = (BorderPane)loader.load();

            rootController = loader.getController();
            contentPane = rootController.getContentPane();
            menuPane = rootController.getMenuPane();
            statusLabel = rootController.getLabelStatus();
            rootController.setMainApp(this);
        setStatus("RootLoaded");
     
        return (new Scene(root));
        
    }
    
    public MainApp getMainApp(){
        return this;
    }
    
    public void setStatus(String status){
        if (status != null){
            statusLabel.setText(status);
        } else {
            statusLabel.setText("Error setting status: null string");
        }
    }
    
    public void setContent(Node content){
        if (content != null){
            contentPane.getChildren().clear();
            contentPane.getChildren().add(content);
        } else {
            setStatus("Error setting content: null node");
        }
    }
    
    public void setMenu(Node menu){
        if (menu != null){
            menuPane.getChildren().clear();
            menuPane.getChildren().add(menu);
        } else {
            setStatus("Set menu error");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
