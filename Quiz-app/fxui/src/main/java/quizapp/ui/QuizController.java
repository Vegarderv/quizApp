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
import javafx.stage.Stage;
import quizapp.core.Question;
import quizapp.core.Quiz;
import quizapp.core.Score;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

public class QuizController implements Initializable {

  @FXML
  RadioButton q1a1;
  @FXML
  RadioButton q1a2;
  @FXML
  RadioButton q1a3;
  @FXML
  RadioButton q1a4;
  @FXML
  RadioButton q2a1;
  @FXML
  RadioButton q2a2;
  @FXML
  RadioButton q2a3;
  @FXML
  RadioButton q2a4;
  @FXML
  RadioButton q3a1;
  @FXML
  RadioButton q3a2;
  @FXML
  RadioButton q3a3;
  @FXML
  RadioButton q3a4;
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
  Label question1;
  @FXML
  Label question2;
  @FXML
  Label question3;

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
    currentQuiz = jsonHandler.loadActiveUser().getCurrentQuiz();
    List<RadioButton> q1buttons = new ArrayList<>();
    q1buttons.add(q1a1);
    q1buttons.add(q1a2);
    q1buttons.add(q1a3);
    q1buttons.add(q1a4);
    List<RadioButton> q2buttons = new ArrayList<>();
    q2buttons.add(q2a1);
    q2buttons.add(q2a2);
    q2buttons.add(q2a3);
    q2buttons.add(q2a4);
    List<RadioButton> q3buttons = new ArrayList<>();
    q3buttons.add(q3a1);
    q3buttons.add(q3a2);
    q3buttons.add(q3a3);
    q3buttons.add(q3a4);
    buttons.add(q1buttons);
    buttons.add(q2buttons);
    buttons.add(q3buttons);
    userName = userHandler.loadActiveUser();
    userMenu.setText(userName);
    quiz_name.setText(currentQuiz.getName());
    question1.setText(currentQuiz.getQuestion(1).getQuestion());
    question2.setText(currentQuiz.getQuestion(2).getQuestion());
    question3.setText(currentQuiz.getQuestion(3).getQuestion());
    for (List<RadioButton> list : buttons) {
      for (RadioButton radioButton : list) {
        radioButton.setText(currentQuiz.getQuestion(buttons.indexOf(list)+1).getAlternative(list.indexOf(radioButton)+1));
      }
    }
  }

  /**
   * This is the method for submitting your quiz. It will send the score and
   * disable the quiz
   */
  @FXML
  public void submitAnswers() {
    int sum = 0;
    if (buttons.get(0).get(currentQuiz.getQuestion(1).getCorrect_alternative() - 1).isSelected()) {
      sum++;
    }
    if (buttons.get(1).get(currentQuiz.getQuestion(2).getCorrect_alternative() - 1).isSelected()) {
      sum++;
    }
    if (buttons.get(2).get(currentQuiz.getQuestion(3).getCorrect_alternative() - 1).isSelected()) {
      sum++;
    }
    buttons.stream().forEach(l -> l.stream().forEach(a -> a.setDisable(true)));
    submit.setDisable(true);
    scroll.setVvalue(0.01);
    score.setText("You got this Score: " + Integer.toString(Math.round(((float) sum / (float) 3) * 100)) + "%");
    scoreCard.scoreQuiz(sum, 3, currentQuiz.getName());
  }

  private void switchSceneWithMenuItem(String fxmlFile) {
    try {
      Stage stage = (Stage) userMenu.getScene().getWindow();
      Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
      Scene scene = new Scene(parent);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void goToProfile(ActionEvent event) {
    this.switchSceneWithMenuItem("ProfilePage.fxml");
  }

  @FXML
  void goToLogIn(ActionEvent event) {
    this.switchSceneWithMenuItem("Login.fxml");
  }

  @FXML
  void goToMainMenu(ActionEvent event) {
    this.switchSceneWithMenuItem("MainPage.fxml");
  }

  public String getName() {
    return quiz_name.getText();
  }

}