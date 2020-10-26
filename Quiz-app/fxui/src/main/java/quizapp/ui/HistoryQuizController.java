package quizapp.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import quizapp.core.Score;
import quizapp.json.UsernameHandler;

public class HistoryQuizController extends QuizAppController {

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
  ImageView mainMenu;
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
  private List<RadioButton> buttons = new ArrayList<>();
  private String userName;
  private String usernamePath 
      = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json";
  private String jsonPath 
      = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json";
  Score scoreCard = new Score(jsonPath, usernamePath);
  UsernameHandler userHandler = new UsernameHandler(usernamePath);

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    buttons.add(q1a1);
    buttons.add(q1a2);
    buttons.add(q1a3);
    buttons.add(q1a4);
    buttons.add(q2a1);
    buttons.add(q2a2);
    buttons.add(q2a3);
    buttons.add(q2a4);
    buttons.add(q3a1);
    buttons.add(q3a2);
    buttons.add(q3a3);
    buttons.add(q3a4);
    userName = userHandler.loadActiveUser();
    userMenu.setText(userName);
  }

  /**
   * This is the method for submitting your quiz. It will send the score and
   * disable the quiz
   */
  @FXML
  public void submitAnswers() {

    int sum = 0;
    if (q1a3.isSelected()) {
      sum++;
    }
    if (q2a4.isSelected()) {
      sum++;
    }
    if (q3a3.isSelected()) {
      sum++;
    }
    buttons.stream().forEach(a -> a.setDisable(true));
    submit.setDisable(true);
    scroll.setVvalue(0.01);
    score.setText("You got this Score: "  
        + Integer.toString(Math.round(((float) sum / (float) 3) * 100)) + "%");
    scoreCard.scoreQuiz(sum, 3, "HistoryQuiz");
  }


  @FXML
  void goToProfile(ActionEvent event) {
    this.switchSceneWithNode("ProfilePage.fxml", userMenu);
  }

  @FXML
  void goToLogIn(ActionEvent event) {
    this.switchSceneWithNode("Login.fxml", userMenu);
  }

  @FXML
  void goToMainMenu(MouseEvent event) {
    this.switchSceneWithNode("MainPage.fxml", userMenu);
  }

}
