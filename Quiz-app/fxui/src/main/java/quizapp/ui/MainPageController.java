package quizapp.ui;

import java.io.IOException;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import quizapp.json.UsernameHandler;

public class MainPageController implements Initializable {



  @FXML
  MenuButton menuButton;
  @FXML
  MenuItem menuSignOut;
  @FXML
  MenuItem profileButton;

  @FXML
  Button historyQuizButton;

  private String usernamePath = 
      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json";
  private UsernameHandler userHandler = new UsernameHandler(usernamePath);
  private String username;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    username = userHandler.loadActiveUser();
    menuButton.setText(username);
  }

  @FXML
  public void goToHistoryQuiz(ActionEvent event) {
    this.switchSceneWithButton(event, "HistoryQuiz.fxml");
  }

  @FXML
  public void goToProfile(ActionEvent event) {
    this.switchSceneWithMenuItem("ProfilePage.fxml");
  }

  @FXML
  public void logOut(ActionEvent event) {
    this.switchSceneWithMenuItem("Login.fxml");
  }

  /**
   * Method for changing scene with a button.
   */
  public void switchSceneWithButton(ActionEvent event, String fxmlFile) {
    try {
      Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
      Scene scene = new Scene(parent);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method for changing scene with a MenuItem.
   */
  public void switchSceneWithMenuItem(String fxmlFile) {
    try {
      Stage stage = (Stage) menuButton.getScene().getWindow();
      Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
      Scene scene = new Scene(parent);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
