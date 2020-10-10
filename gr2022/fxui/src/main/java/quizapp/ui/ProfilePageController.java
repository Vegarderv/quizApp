package quizapp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

public class ProfilePageController implements Initializable {

  @FXML
  MenuBar menuBar;

  @FXML
  MenuButton menu;

  @FXML
  MenuItem mainPageId;

  @FXML
  MenuItem loginId;

  @FXML
  Label nameId;

  @FXML
  Label scoreId;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    UsernameHandler userHandler = new UsernameHandler(
        "/workspace/gr2022/gr2022/core/src/main/resources/quizapp/json/activeUser.json");
    String userName = userHandler.loadActiveUser();
    double percentage=getActiveUser().meanScore()*100;
    String score = String.valueOf(percentage);
    nameId.setText(userName);
    scoreId.setText(score);
    menu.setText(userName);

  }

  @FXML
  public void goToMainMenu(ActionEvent event) {
    this.switchScene("MainPage.fxml");
  }

  @FXML
  public void goLogOut(ActionEvent event) {
    this.switchScene("Login.fxml");
  }

  private User getActiveUser() {
    JsonHandler jsonHandler = new JsonHandler(
        "/workspace/gr2022/gr2022/core/src/main/resources/quizapp/json/JSONHandler.json");
    UsernameHandler usernameHandler = new UsernameHandler(
        "/workspace/gr2022/gr2022/core/src/main/resources/quizapp/json/activeUser.json");
    return jsonHandler.loadFromFile().stream()
        .filter(user -> user.getUsername().equals(usernameHandler.loadActiveUser())).findFirst().get();
  }

  /**
   * method for switching scene.
   */
  public void switchScene(String fxmlFile) {
    try {
      Stage stage = (Stage) menuBar.getScene().getWindow();
      Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
      Scene scene = new Scene(parent);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
