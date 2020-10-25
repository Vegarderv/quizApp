package quizapp.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

public class ProfilePageController extends QuizAppController {

  @FXML
  MenuBar menuBar;

  @FXML
  MenuButton userMenuProfilePage;
  
  @FXML
  MenuItem menuMainMenu;

  @FXML
  MenuItem menuSignOut;

  @FXML
  Label nameId;

  @FXML
  Label scoreId;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    UsernameHandler userHandler = new UsernameHandler(
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");
    String userName = userHandler.loadActiveUser();
    double percentage = getActiveUser().meanScore() * 100;
    String score = String.valueOf(Math.round((percentage))) + "  %";
    nameId.setText(userName);
    scoreId.setText(score);
    userMenuProfilePage.setText(userName);

  }

  @FXML
  public void goToMainMenu(ActionEvent event) {
    this.switchSceneWithNode("MainPage.fxml", menuBar);
  }

  @FXML
  public void goLogOut(ActionEvent event) {
    this.switchSceneWithNode("Login.fxml", menuBar);
  }

  private User getActiveUser() {
    JsonHandler jsonHandler = new JsonHandler(
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
    UsernameHandler usernameHandler = new UsernameHandler(
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");
    return jsonHandler.loadFromFile().stream()
        .filter(user -> user.getUsername().equals(usernameHandler.loadActiveUser()))
        .findFirst().get();
  }

}
