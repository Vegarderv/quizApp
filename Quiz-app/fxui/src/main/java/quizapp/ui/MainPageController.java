package quizapp.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.QuizHandler;
import quizapp.json.UsernameHandler;

public class MainPageController extends QuizAppController {

  @FXML
  MenuBar menuBar;
  @FXML
  MenuButton menuButton;
  @FXML
  MenuItem menuSignOut;
  @FXML
  MenuItem profileButton;
  @FXML
  Button historyQuizButton;
  @FXML
  Button chemistryQuizButton;
  @FXML
  Button geographyQuizButton;

  private String usernamePath 
      = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json";
  private UsernameHandler userHandler = new UsernameHandler(usernamePath);
  private JsonHandler jsonHandler = new JsonHandler(
      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
  private QuizHandler quizHandler = new QuizHandler(
      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/quizzes.json");
  private String username;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    username = userHandler.loadActiveUser();
    menuButton.setText(username);
  }

  @FXML
  public void goToQuiz(ActionEvent event) {
    User currentUser = jsonHandler.loadActiveUser();
    currentUser.setCurrentQuiz(quizHandler.getQuizByName(((Button)event.getSource()).getId()));
    jsonHandler.updateUser(currentUser);
    this.switchSceneWithNode("Quiz.fxml", historyQuizButton);
  }

  @FXML
  public void goToProfile(ActionEvent event) {
    this.switchSceneWithNode("ProfilePage.fxml", menuButton);
  }

  @FXML
  public void logOut(ActionEvent event) {
    this.switchSceneWithNode("Login.fxml", menuButton);
  }

}
