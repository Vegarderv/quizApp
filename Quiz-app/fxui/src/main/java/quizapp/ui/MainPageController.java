package quizapp.ui;

import java.net.URI;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import quizapp.core.Quiz;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.QuizHandler;
import quizapp.json.UsernameHandler;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController extends QuizAppController {

  @FXML
  MenuButton menuButton;
  @FXML
  MenuItem menuSignOut;
  @FXML
  MenuItem profileButton;
  @FXML
  MenuItem scoreboardButton;
  @FXML
  Button historyQuizButton;
  @FXML
  Button chemistryQuizButton;
  @FXML
  Button geographyQuizButton;
  @FXML
  HBox hBox;

  private User currentUser;
  private UserAccess remoteUserAccess;
  private QuizAccess remoteQuizAccess;



  @FXML
  public void goToNewQuiz() {
    this.switchSceneWithNode("AddQuiz.fxml", menuButton);
  }
  
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    try {
        remoteUserAccess = new RemoteUserAccess(new URI("http://localhost:8080/api/user/"));
    } catch (Exception e) {
        remoteUserAccess = new DirectUserAccess();
    }
    try {
        remoteQuizAccess = new RemoteQuizAccess(new URI("http://localhost:8080/api/quiz/"));
    } catch (Exception e) {
      remoteQuizAccess = new DirectQuizAccess();
    }
    currentUser = remoteUserAccess.getActiveUser();
    menuButton.setText(currentUser.getUsername());
    addButtons();
  }

  @FXML
  public void goToQuiz(ActionEvent event) {
    System.out.println((((Button) event.getSource()).getId()));
    
    Quiz quiz = remoteQuizAccess.getQuiz((((Button) event.getSource()).getId()));
    currentUser.setCurrentQuiz(quiz);
    try {
      remoteUserAccess = new RemoteUserAccess(new URI("http://localhost:8080/api/user/update"));
    } catch (Exception e) {
    }
    remoteUserAccess.putUser(currentUser);
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

  @FXML
  public void goToScoreboard(ActionEvent event) {
    this.switchSceneWithNode("Scoreboard.fxml", menuButton);
  }

  /**
   * Method for adding buttons for extra quizzes created
   */
  private void addButtons() {
    List<Quiz> quizzes = remoteQuizAccess.getQuizzes();
    if (quizzes.size() > 5) {
      ObservableList<Node> children = hBox.getChildren();
      for (int i = 5; i < quizzes.size(); i++) {
        Button button = new Button(quizzes.get(i).getName());
        button.setPrefSize(436.0, 180.0);
        button.setMinWidth(436.0);
        button.setId(quizzes.get(i).getId());
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
