package quizapp.ui;

import java.net.URI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import quizapp.core.UsernameCheck;
import quizapp.core.User;
import quizapp.core.Quiz;
import quizapp.json.UsernameHandler;
import quizapp.json.JsonHandler;


import java.net.URL;
import java.rmi.Remote;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController extends QuizAppController {

  @FXML
  TextField username;
  @FXML
  PasswordField password;
  @FXML
  Label errorMessage;
  @FXML
  Button mainPageButton;
  
  private UserAccess remoteUserAccess;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
   
    
  }

  /**
   * Checks if username and password is valid. Error message will occur if not. If
   * valid the user will be redirected to another fxml file.
   */
  @FXML
  public void toMainMenu(ActionEvent event) throws Exception {
    UsernameCheck chk = new UsernameCheck();
    if (!chk.checkUsername(username.getText(), password.getText())) {
      username.clear();
      password.clear();
      errorMessage.setText("Wrong username or password");
      return;
    }

    try {
        remoteUserAccess = new RemoteUserAccess(new URI("http://localhost:8080/api/user/updateActive/"));
    } catch (Exception e) {
      remoteUserAccess = new DirectUserAccess();
    }
    remoteUserAccess.putActiveUser(username.getText());
    System.out.println(username.getText());
    // Gets the stage information and sets the scene
    switchSceneWithNode("MainPage.fxml", mainPageButton);
  }

  /**
   * Goes from Login to SignUp.
   */
  @FXML
  public void toSignup(ActionEvent event) throws Exception {
    // Gets the stage information and sets the scene
    switchSceneWithNode("Signup.fxml", mainPageButton);
  }
}