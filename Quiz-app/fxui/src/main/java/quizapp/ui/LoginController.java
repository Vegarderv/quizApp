package quizapp.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import quizapp.core.UsernameCheck;
import quizapp.json.UsernameHandler;

public class LoginController extends QuizAppController {

  @FXML
  TextField username;
  @FXML
  PasswordField password;
  @FXML
  Label errorMessage;

  @FXML
  Button mainPageButton;

  private UsernameHandler usernameHandler = new UsernameHandler(
      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    //something
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

    usernameHandler.saveActiveUser(
        username.getText(), 
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
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