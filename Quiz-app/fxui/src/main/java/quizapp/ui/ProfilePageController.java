package quizapp.ui;

import java.io.IOException;
import java.net.URI;
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

public class ProfilePageController extends QuizAppController {

  @FXML
  MenuButton userMenuProfilePage;

  @FXML
  MenuItem menuSignOut;

  @FXML
  Button dmButton;

  @FXML
  MenuItem scoreboardButton;

  @FXML
  Label nameId;

  @FXML
  Label scoreId;

  @FXML
  Label darkmodeLabel;

  private UserAccess remoteUserAccess;
  private User currentUser;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      remoteUserAccess = new RemoteUserAccess(new URI("http://localhost:8080/api/user/"));
    } catch (Exception e) {
      remoteUserAccess = new DirectUserAccess();
    }
    currentUser = remoteUserAccess.getActiveUser();
    double percentage = currentUser.meanScore() * 100;
    scoreId.setText(String.valueOf(Math.round(percentage) + "  %"));
    nameId.setText(currentUser.getUsername());
    userMenuProfilePage.setText(currentUser.getUsername());
    darkmodeLabel.setText(initDarkMode());
    scoreId.setText(String.valueOf(Math.round((currentUser.meanScore() * 100)) + "  %"));
  }

  /**
   * Makes sure that the user sees the page as darmode or not.
   * 

   * @return
   */
  public String initDarkMode() {
    Boolean check = currentUser.getDarkMode();
    if (check) {
      return ("ON");
    }
    return ("OFF");
  }

  /**
   * This method takes care of thefunctionality of the darkmode button.
   * 

   * @return
   */
  public String typeDarkMode() {
    try {
      Boolean check = currentUser.getDarkMode();
      Parent parent = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
      Scene scene = new Scene(parent);
      if (check) {
        try {
          scene.getStylesheets().remove("lightmode.css");
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          scene.getStylesheets().remove("darkmode.css");
        } catch (Exception e) {
          e.printStackTrace();
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

  /**
   * Saves darkmode in user.
   * 
   */
  public void changeDarkMode() {
    currentUser.setDarkMode(!currentUser.getDarkMode());
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
    darkmodeLabel.setText(typeDarkMode());
  }
}
