package quizapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SignupController extends QuizAppController {

  @FXML
  TextField username;
  @FXML
  TextField password;
  @FXML
  Label errorMessage;
  @FXML
  Button loginButton;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
  }

  @FXML
  public void toLogin(ActionEvent event) {
    this.switchSceneWithNode("Login.fxml", loginButton);
  }

  /**
   * Saves user and goes to main menu.
   */
  @FXML
  public void toMainMenu(ActionEvent event) throws Exception {
    final JsonHandler handler = new JsonHandler(
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
    final UsernameHandler userhandler = new UsernameHandler(
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");
    final List<User> user = handler.loadFromFile();
    if (user.stream().anyMatch(a -> a.getUsername().equals(username.getText()))) {
      username.clear();
      password.clear();
      errorMessage.setText("Username already taken");
      return;
    } else if (username.getText().equals("") || password.getText().equals("")) {
      username.clear();
      password.clear();
      errorMessage.setText("Username and password must at least contain 1 sign");
      return;
    }
    // saves user
    final User newUser = new User();
    newUser.setUsername(this.username.getText());
    newUser.setPassword(this.password.getText());
    user.add(newUser);
    handler.writeToFile(user);
    userhandler.saveActiveUser(newUser.getUsername(),
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");

    this.switchSceneWithNode("MainPage.fxml", loginButton);
  }


}
