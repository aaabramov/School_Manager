package school_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.User;



/**
 * FXML Controller class
 *
 * @author abrasha
 */
public class LoginFragmentController implements Initializable, MainReferenced {
    
    MainApp mainApp;

    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Label infoField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoField.setTextFill(Color.RED);
        
        tfLogin.setText("10004");
        tfPassword.setText("admin");
    }

    @FXML
    public void okClicked() {

        int login = Integer.valueOf(tfLogin.getText());
        String password = tfPassword.getText();
        
        if (!password.isEmpty()){
            
            User result = DatabaseManager.authorize(login, password);
            
            if (result != null){
                
                mainApp.setAccountInfo(result);
                mainApp.setStatus("Authorization successful.");
                
            } else {
                
                infoField.setText("Wrong login or password");
                mainApp.setStatus("Authorization failed.");
                
            }      
        }
    }

    @FXML
    public void cancelClicked() {
            
        tfPassword.clear();
        tfLogin.clear();
        
    }
    
    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
}
