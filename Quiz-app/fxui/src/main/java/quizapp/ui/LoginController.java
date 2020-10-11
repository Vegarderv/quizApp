package quizapp.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import quizapp.core.UsernameCheck;
import quizapp.json.UsernameHandler;

public class LoginController implements Initializable {

  @FXML
  TextField username;
  @FXML
  TextField password;
  @FXML
  Label errorMessage;

  @FXML
  Button mainPageButton;

  private UsernameHandler usernameHandler = new UsernameHandler(
      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
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

<<<<<<< HEAD:gr2022/fxui/src/main/java/quizapp/ui/LoginController.java
    usernameHandler.saveActiveUser(username.getText(),
        "/workspace/gr2022/gr2022/core/src/main/resources/quizapp/json/JSONHandler.json");
=======
    usernameHandler.saveActiveUser(
        username.getText(), 
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
>>>>>>> origin/issue-38-update-readme:Quiz-app/fxui/src/main/java/quizapp/ui/LoginController.java
    // Gets the stage information and sets the scene
    Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
    Scene tableViewScene = new Scene(tableViewParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(tableViewScene);
    window.show();
  }

  /**
   * Goes from Login to SignUp.
   */
  @FXML
  public void toSignup(ActionEvent event) throws Exception {
    // Gets the stage information and sets the scene
    Parent tableViewParent = FXMLLoader.load(getClass().getResource("Signup.fxml"));
    Scene tableViewScene = new Scene(tableViewParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(tableViewScene);
    window.show();
  }
}