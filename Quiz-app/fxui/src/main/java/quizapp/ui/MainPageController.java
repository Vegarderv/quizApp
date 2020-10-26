package quizapp.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import quizapp.core.Quiz;
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
  @FXML
  HBox hBox;

  private String usernamePath = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json";
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
    addButtons();
  }

  @FXML
  public void goToQuiz(ActionEvent event) {
    User currentUser = jsonHandler.loadActiveUser();
    currentUser.setCurrentQuiz(quizHandler.getQuizByName(((Button) event.getSource()).getId()));
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


  /**
   * Method for adding buttons for extra quizzes created
   */
  private void addButtons() {
    List<Quiz> quizzes = new QuizHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/quizzes.json")
        .loadFromFile();
    if (quizzes.size() > 3) {
      ObservableList<Node> children = hBox.getChildren();
      for (int i = 3; i < quizzes.size(); i++) {
        Button button = new Button(quizzes.get(i).getName());
        button.setPrefSize(436.0, 180.0);
        button.setMinWidth(436.0);
        button.setId(quizzes.get(i).getName());
        button.setOnAction(new EventHandler<ActionEvent>(){
        
          @Override
          public void handle(ActionEvent event) {
            goToQuiz(event);
          }
        });
        children.add(button);
      }
    }
  }

}
