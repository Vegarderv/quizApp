package quizapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePageController extends QuizAppController {


  @FXML
  MenuButton userMenuProfilePage;
  
  @FXML
  MenuItem menuMainMenu;

  @FXML
  MenuItem menuSignOut;

  @FXML
  MenuItem scoreboardButton;

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
  public void goToMainMenu(MouseEvent event) {
    this.switchSceneWithNode("MainPage.fxml", userMenuProfilePage);
  }

  @FXML
  public void goToScoreboard(ActionEvent event) {
    this.switchSceneWithNode("Scoreboard.fxml", userMenuProfilePage);
  }

  @FXML
  public void goLogOut(ActionEvent event) {
    this.switchSceneWithNode("Login.fxml", userMenuProfilePage);
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
