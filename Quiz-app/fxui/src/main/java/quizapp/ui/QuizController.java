package quizapp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import quizapp.core.Quiz;
import quizapp.core.Score;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

public class QuizController extends QuizAppController {

  @FXML
  RadioButton q0a0;
  @FXML
  RadioButton q0a1;
  @FXML
  RadioButton q0a2;
  @FXML
  RadioButton q0a3;
  @FXML
  RadioButton q1a0;
  @FXML
  RadioButton q1a1;
  @FXML
  RadioButton q1a2;
  @FXML
  RadioButton q1a3;
  @FXML
  RadioButton q2a0;
  @FXML
  RadioButton q2a1;
  @FXML
  RadioButton q2a2;
  @FXML
  RadioButton q2a3;
  @FXML
  Button submit;
  @FXML
  Label score;
  @FXML
  ScrollPane scroll;
  @FXML
  MenuButton userMenu;
  @FXML
  MenuItem menuProfilePage;
  @FXML
  MenuItem menuSignOut;
  @FXML
  Label quiz_name;
  @FXML
  Label question0;
  @FXML
  Label question1;
  @FXML
  Label question2;
  @FXML
  MenuItem scoreboardButton;

  private List<List<RadioButton>> buttons = new ArrayList<>();
  private String userName;
  private String usernamePath = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json";
  private String jsonPath = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json";
  private JsonHandler jsonHandler = new JsonHandler(jsonPath);
  Score scoreCard = new Score(jsonPath, usernamePath);
  UsernameHandler userHandler = new UsernameHandler(usernamePath);
  private Quiz currentQuiz;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    System.out.println("nå initialiseres quiz");
    currentQuiz = jsonHandler.loadActiveUser().getCurrentQuiz();
    List<RadioButton> q0buttons = new ArrayList<>();
    q0buttons.add(q0a0);
    q0buttons.add(q0a1);
    q0buttons.add(q0a2);
    q0buttons.add(q0a3);
    List<RadioButton> q1buttons = new ArrayList<>();
    q1buttons.add(q1a0);
    q1buttons.add(q1a1);
    q1buttons.add(q1a2);
    q1buttons.add(q1a3);
    List<RadioButton> q2buttons = new ArrayList<>();
    q2buttons.add(q2a0);
    q2buttons.add(q2a1);
    q2buttons.add(q2a2);
    q2buttons.add(q2a3);
    buttons.add(q0buttons);
    buttons.add(q1buttons);
    buttons.add(q2buttons);
    userName = userHandler.loadActiveUser();
    userMenu.setText(userName);
    quiz_name.setText(currentQuiz.getName());
    question0.setText(currentQuiz.getQuestion(0).getQuestion());
    question1.setText(currentQuiz.getQuestion(1).getQuestion());
    question2.setText(currentQuiz.getQuestion(2).getQuestion());
    for (List<RadioButton> list : buttons) {
      for (RadioButton radioButton : list) {
        radioButton.setText(currentQuiz.getQuestion(buttons.indexOf(list)).getAlternative(list.indexOf(radioButton)));
      }
    }
  }

  /**
   * This is the method for submitting your quiz. It will send the score and
   * disable the quiz
   */
  @FXML
  public void submitAnswers() {
    System.out.println("nå submitter vi");
    int sum = 0;
    if (buttons.get(0).get(currentQuiz.getQuestion(0).getCorrect_alternative()).isSelected()) {
      sum++;
    }
    if (buttons.get(1).get(currentQuiz.getQuestion(1).getCorrect_alternative()).isSelected()) {
      sum++;
    }
    if (buttons.get(2).get(currentQuiz.getQuestion(2).getCorrect_alternative()).isSelected()) {
      sum++;
    }
    buttons.stream().forEach(l -> l.stream().forEach(a -> a.setDisable(true)));
    submit.setDisable(true);
    scroll.setVvalue(0.01);
    score.setText("You got this Score: " + Integer.toString(Math.round(((float) sum / (float) 3) * 100)) + "%");
    System.out.println("neste scorequiz");
    scoreCard.scoreQuiz(sum, 3, currentQuiz.getName());
  }



  @FXML
  void goToProfile(ActionEvent event) {
    switchSceneWithNode("ProfilePage.fxml", userMenu);
  }

  @FXML
  void goToLogIn(ActionEvent event) {
    switchSceneWithNode("Login.fxml", userMenu);
  }

  @FXML
  void goToMainMenu(MouseEvent event) {
    switchSceneWithNode("MainPage.fxml", userMenu);
  }

  @FXML
  void goToScoreboard(ActionEvent event) {
    this.switchSceneWithNode("Scoreboard.fxml", userMenu);
  }

  public String getName() {
    return quiz_name.getText();
  }

}