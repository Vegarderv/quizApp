package quizapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import quizapp.core.UsernameCheck;

import java.net.URL;
import java.util.ResourceBundle;

public class SigninController implements Initializable {

    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Label errorMessage;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }

    //also need method that sends loginpage to signinpage in logincontroller, and a button in the ui

    @FXML
    public void toMainMenu(ActionEvent event) throws Exception {
        UsernameCheck chk = new UsernameCheck();
        if(chk.checkUsername(username.getText(), password.getText())){
            username.clear();
            password.clear();
            errorMessage.setText("Username already taken");
            return;
        }
        //need method that saves the new username and password

        //Gets the stage information and sets the scene
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
