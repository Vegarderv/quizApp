package quizapp.ui;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import quizapp.core.DirectQuizAccess;
import quizapp.core.DirectUserAccess;
import quizapp.core.Quiz;
import quizapp.core.QuizAccess;
import quizapp.core.RemoteQuizAccess;
import quizapp.core.RemoteUserAccess;
import quizapp.core.User;
import quizapp.core.UserAccess;

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
  HBox hbox;

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

  /**
   * Switches scene to a quiz.
   * 

   * @param event the catalyzing event
   */
  @FXML
  public void goToQuiz(ActionEvent event) {
    Quiz quiz = remoteQuizAccess.getQuiz((((Button) event.getSource()).getId()));
    currentUser.setCurrentQuiz(quiz);
    try {
      remoteUserAccess = new RemoteUserAccess(new URI("http://localhost:8080/api/user/update"));
    } catch (Exception e) {
      remoteUserAccess = new DirectUserAccess();
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

  // Method for adding buttons for extra quizzes created.
  private void addButtons() {
    Collection<Quiz> quizzes = new ArrayList<>(remoteQuizAccess.getQuizzes());
    if (quizzes.size() > 5) {
      ObservableList<Node> children = hbox.getChildren();
      List<String> colors = Arrays.asList("#EB4034", "#FFC0CB", "#FFAC20", "#7EB593", "#73c1df");
      int size = quizzes.size();
      for (int i = 5; i < size; i++) {
        Collection<String> ids = List.of(
            "History-quiz", "Chemistry-quiz", "Geography-quiz", 
            "Christmas-quiz", "Malin-quiz");
        Quiz quiz = quizzes.stream().filter(q -> !ids.contains(q.getId()))
            .findFirst().get();
        Button button = new Button(quiz.getName().toUpperCase());
        Font font = new Font(40); // Button font's size should increase to 40
        button.setFont(font);
        int chosenColor = (int) (Math.random() * colors.size());
        button.setStyle("-fx-background-color:" + colors.get(chosenColor));
        button.setPrefSize(436.0, 230.0);
        button.setMinWidth(436.0);
        button.setId(quiz.getId());
        quizzes.remove(quiz);
        button.setOnAction(new EventHandler<ActionEvent>() {

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
