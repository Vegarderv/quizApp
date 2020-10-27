package quizapp.ui;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

public class ProfilePageController extends QuizAppController {

  @FXML
  MenuButton userMenuProfilePage;

  @FXML
  MenuItem menuSignOut;

  @FXML
  Button DMButton;

  @FXML
  Label nameId, scoreId, DarkmodeLabel;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    UsernameHandler userHandler = new UsernameHandler(
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");
   
    String userName = userHandler.loadActiveUser();
    double percentage = getActiveUser().meanScore() * 100;
    String score = String.valueOf(Math.round((percentage))) + "  %";
    Boolean DM = this.getActiveUser().getDarkMode();
    nameId.setText(userName);
    userMenuProfilePage.setText(userName);
    DarkmodeLabel.setText(typeDarkMode());

  }

  public String typeDarkMode() {
    Boolean check = this.getActiveUser().getDarkMode();
    if (check == true) {
      return ("ON");
    }
    return ("OFF");
  }

  public void changeDarkMode() {
     JsonHandler jsonHandler = new JsonHandler(
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
      getActiveUser().setDarkMode(!getActiveUser().getDarkMode());
      jsonHandler.updateUser(getActiveUser());
  }

  @FXML
  public void goToMainMenu(MouseEvent event) {

    this.switchSceneWithNode("MainPage.fxml", userMenuProfilePage);
  }

  @FXML
  public void goLogOut(ActionEvent event) {
    this.switchSceneWithNode("Login.fxml", userMenuProfilePage);
  }

  @FXML
  public void changeTextDarkMode(ActionEvent event) {
    changeDarkMode();
    DarkmodeLabel.setText(typeDarkMode());
  }

  private User getActiveUser() {
    JsonHandler jsonHandler = new JsonHandler(
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
    UsernameHandler usernameHandler = new UsernameHandler(
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");
    return jsonHandler.loadFromFile().stream()
        .filter(user -> user.getUsername().equals(usernameHandler.loadActiveUser())).findFirst().get();
  }

}
