package quizapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import quizapp.core.Question;
import quizapp.core.Quiz;
import quizapp.json.QuizHandler;
import quizapp.json.UsernameHandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddQuizController extends QuizAppController {
/*
  @FXML
  RadioButton q1a1, q1a2, q1a3, q1a4, q2a1, q2a2, q2a3, q2a4, q3a1, q3a2, q3a3, q3a4;
  @FXML
  TextField q1an1, q1an2, q1an3, q1an4, q2an1, q2an2, q2an3, q2an4, q3an1, q3an2, q3an3, q3an4, title, q1, q2, q3;
  @FXML
  Label score;
  @FXML
  MenuButton userMenu;

  private List<RadioButton> radioButtonGroup1 = new ArrayList<>();
  private List<RadioButton> radioButtonGroup2 = new ArrayList<>();
  private List<RadioButton> radioButtonGroup3 = new ArrayList<>();
  private List<List<RadioButton>> radioButtons = new ArrayList<>();
  private List<TextField> textFieldGroup1 = new ArrayList<>();
  private List<TextField> textFieldGroup2 = new ArrayList<>();
  private List<TextField> textFieldGroup3 = new ArrayList<>();
  private List<TextField> textFieldOther = new ArrayList<>();
  private List<List<TextField>> textFields = new ArrayList<>();

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    addElemsToLists();
    userMenu
        .setText(new UsernameHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json")
            .loadActiveUser());

  }

  @FXML
  void goToProfile(ActionEvent event) {
    this.switchSceneWithNode("ProfilePage.fxml", title);
  }

  @FXML
  void goToScoreboard(ActionEvent event) {
    this.switchSceneWithNode("Scoreboard.fxml", title);
  }

  @FXML
  void goToLogIn(ActionEvent event) {
    this.switchSceneWithNode("Login.fxml", title);
  }

  @FXML
  void goToMainMenu(ActionEvent event) {
    this.switchSceneWithNode("MainPage.fxml", title);
  }

  public void submitQuiz() {
    if (!checkIfQuizIsFilled()) {
      score.setText("Invalid Quiz. Check that all fields are filled and correct answers are chosen");
      return;
    }
    QuizHandler quizHandler = new QuizHandler(
        "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/quizzes.json");
    Question question1 = new Question(q1.getText(), q1an1.getText(), q1an2.getText(), q1an3.getText(), q1an4.getText(),
        radioButtonGroup1.indexOf(radioButtonGroup1.stream().filter(p -> p.isSelected()).findAny().get()) + 1);
    Question question2 = new Question(q2.getText(), q2an1.getText(), q2an2.getText(), q2an3.getText(), q2an4.getText(),
        radioButtonGroup2.indexOf(radioButtonGroup2.stream().filter(p -> p.isSelected()).findAny().get()) + 1);
    Question question3 = new Question(q3.getText(), q3an1.getText(), q3an2.getText(), q3an3.getText(), q3an4.getText(),
        radioButtonGroup3.indexOf(radioButtonGroup3.stream().filter(p -> p.isSelected()).findAny().get()) + 1);
    List<Quiz> quizzes = quizHandler.loadFromFile();
    quizzes.add(new Quiz(title.getText(), question1, question2, question3));
    quizHandler.writeToFile(quizzes);
    switchSceneWithNode("MainPage.fxml", title);
  }

  private void addElemsToLists() {
    radioButtonGroup1.add(q1a1);
    radioButtonGroup1.add(q1a2);
    radioButtonGroup1.add(q1a3);
    radioButtonGroup1.add(q1a4);
    radioButtonGroup2.add(q2a1);
    radioButtonGroup2.add(q2a2);
    radioButtonGroup2.add(q2a3);
    radioButtonGroup2.add(q2a4);
    radioButtonGroup3.add(q3a1);
    radioButtonGroup3.add(q3a2);
    radioButtonGroup3.add(q3a3);
    radioButtonGroup3.add(q3a4);
    radioButtons.add(radioButtonGroup1);
    radioButtons.add(radioButtonGroup2);
    radioButtons.add(radioButtonGroup3);
    textFieldGroup1.add(q1an1);
    textFieldGroup1.add(q1an2);
    textFieldGroup1.add(q1an3);
    textFieldGroup1.add(q1an4);
    textFieldGroup2.add(q2an1);
    textFieldGroup2.add(q2an2);
    textFieldGroup2.add(q2an3);
    textFieldGroup2.add(q2an4);
    textFieldGroup3.add(q3an1);
    textFieldGroup3.add(q3an2);
    textFieldGroup3.add(q3an3);
    textFieldGroup3.add(q3an4);
    textFieldOther.add(q1);
    textFieldOther.add(q2);
    textFieldOther.add(q3);
    textFieldOther.add(title);
    textFields.add(textFieldGroup1);
    textFields.add(textFieldGroup2);
    textFields.add(textFieldGroup3);
    textFields.add(textFieldOther);
  }

  private boolean checkIfQuizIsFilled() {
    boolean correctAnswer = radioButtons.stream().allMatch(p -> p.stream().anyMatch(q -> q.isSelected()));
    boolean allFieldsAreFilled = textFields.stream()
        .allMatch(text -> text.stream().allMatch(field -> !field.getText().equals("")));
    return correctAnswer && allFieldsAreFilled;

  }
*/
}