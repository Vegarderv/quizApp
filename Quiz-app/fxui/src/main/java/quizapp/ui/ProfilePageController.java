package quizapp.ui;

import java.net.URI;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
  MenuItem menuSignOut;

  @FXML
  Button DMButton;

  @FXML
  MenuItem scoreboardButton;

  @FXML
  Label nameId, scoreId, DarkmodeLabel;

  private RemoteUserAccess remoteUserAccess;
  private User currentUser;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    //UsernameHandler userHandler = new UsernameHandler(
    //    "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");
    try {
        remoteUserAccess = new RemoteUserAccess(new URI("http://localhost:8080/api/user/"));
    } catch (Exception e) {

    }
    currentUser = remoteUserAccess.getActiveUser();
    double percentage = currentUser.meanScore() * 100;
    String score = String.valueOf(Math.round((percentage))) + "  %";
    //Boolean DM = this.getActiveUser().getDarkMode();
    nameId.setText(currentUser.getUsername());
    userMenuProfilePage.setText(currentUser.getUsername());
    DarkmodeLabel.setText(initDarkMode());
    scoreId.setText(score);
  }

  public String initDarkMode() {
    Boolean check = currentUser.getDarkMode();
    if (check) {
      return ("ON");
    }
    return ("OFF");
  }

  public String typeDarkMode() {
    try {
      Boolean check = currentUser.getDarkMode();
      Parent parent = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
      Scene scene = new Scene(parent);
      if (check) {
        try {
          scene.getStylesheets().remove("lightmode.css");
        } catch (Exception e) {
          //TODO: handle exception
        }
        try {
          scene.getStylesheets().remove("darkmode.css");
        } catch (Exception e) {
          //TODO: handle exception
        }
        scene.getStylesheets().add(getClass().getResource("darkmode.css").toExternalForm());
        
        return ("ON");
      }
      scene.getStylesheets().add(getClass().getResource("lightmode.css").toExternalForm());
      return ("OFF");
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

  }

  public void changeDarkMode() {
    //User user = getActiveUser();
    //JsonHandler jsonHandler = new JsonHandler(
    //    "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
    currentUser.setDarkMode(!currentUser.getDarkMode());
    //jsonHandler.updateUser(user);
    remoteUserAccess.putUser(currentUser);
    this.switchSceneWithNode("ProfilePage.fxml", userMenuProfilePage);
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

  @FXML
  public void changeTextDarkMode(ActionEvent event) {
    changeDarkMode();
    DarkmodeLabel.setText(typeDarkMode());
  }

  //private User getActiveUser() {
  //  JsonHandler jsonHandler = new JsonHandler(
  //      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
  //  UsernameHandler usernameHandler = new UsernameHandler(
  //      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");
  //  return jsonHandler.loadFromFile().stream()
  //      .filter(user -> user.getUsername().equals(usernameHandler.loadActiveUser())).findFirst().get();
  //}

}
