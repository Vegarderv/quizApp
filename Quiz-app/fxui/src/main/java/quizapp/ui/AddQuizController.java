package quizapp.ui;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import quizapp.core.Question;
import quizapp.core.Quiz;

public class AddQuizController extends QuizAppController {

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
  TextField q0an0;
  @FXML
  TextField q0an1;
  @FXML
  TextField q0an2;
  @FXML
  TextField q0an3;
  @FXML
  TextField q1an0;
  @FXML
  TextField q1an1;
  @FXML
  TextField q1an2;
  @FXML
  TextField q1an3;
  @FXML
  TextField q2an0;
  @FXML
  TextField q2an1;
  @FXML
  TextField q2an2;
  @FXML
  TextField q2an3;
  @FXML
  TextField title;
  @FXML
  TextField q0;
  @FXML
  TextField q1;
  @FXML
  TextField q2;
  @FXML
  Label score;
  @FXML
  MenuButton userMenu;
  @FXML
  ScrollPane scroll;

  private UserAccess remoteUserAccess;
  private QuizAccess remoteQuizAccess;

  private List<RadioButton> radioButtonGroup0 = new ArrayList<>();
  private List<RadioButton> radioButtonGroup1 = new ArrayList<>();
  private List<RadioButton> radioButtonGroup2 = new ArrayList<>();
  private List<List<RadioButton>> radioButtons = new ArrayList<>();
  private List<TextField> textFieldGroup0 = new ArrayList<>();
  private List<TextField> textFieldGroup1 = new ArrayList<>();
  private List<TextField> textFieldGroup2 = new ArrayList<>();
  private List<TextField> textFieldOther = new ArrayList<>();
  private List<List<TextField>> textFields = new ArrayList<>();

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    addElemsToLists();
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
    userMenu
        .setText(remoteUserAccess.getActiveUser().getUsername());
    
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
  void goToMainMenu(MouseEvent event) {
    this.switchSceneWithNode("MainPage.fxml", title);
  }

  /**
   * Submits the new quiz.
   */
  public void submitQuiz() {
    if (!checkIfQuizIsFilled()) {
      score.setText("Invalid Quiz. Check that all fields are" 
          + " filled and correct answers are chosen");
      scroll.setVvalue(0.01);
      return;
    }
    try {
      remoteQuizAccess = new RemoteQuizAccess(new URI("http://localhost:8080/api/quiz/"));
    } catch (Exception e) {
      remoteQuizAccess = new DirectQuizAccess();
    }
    List<Quiz> quizzes = remoteQuizAccess.getQuizzes();
    if (quizzes.stream().anyMatch(q -> q.getName().equals(title.getText()))) {
      score
          .setText("Invalid Quizname. The title must be unique, " 
          + "there is already a quiz named " + title.getText());
      scroll.setVvalue(0.01);
      return;
    }

    try {
      remoteQuizAccess = new RemoteQuizAccess(new URI("http://localhost:8080/api/quiz/"));
    } catch (Exception e) {
      remoteQuizAccess = new DirectQuizAccess();
    }

    Question question0 = new Question(q0.getText(), q0an0.getText(), 
        q0an1.getText(), q0an2.getText(), q0an3.getText(),
        radioButtonGroup0.indexOf(radioButtonGroup0.stream()
        .filter(p -> p.isSelected()).findAny().get()));
    Question question1 = new Question(q1.getText(), q1an0.getText(), 
        q1an1.getText(), q1an2.getText(), q1an3.getText(),
        radioButtonGroup1.indexOf(radioButtonGroup1.stream()
        .filter(p -> p.isSelected()).findAny().get()));
    Question question2 = new Question(q2.getText(), q2an0.getText(), 
        q2an1.getText(), q2an2.getText(), q2an3.getText(),
        radioButtonGroup2.indexOf(radioButtonGroup2.stream()
        .filter(p -> p.isSelected()).findAny().get()));
    Quiz quiz = new Quiz(title.getText(), question0, question1, question2);
    remoteQuizAccess.postQuiz(quiz);
    switchSceneWithNode("MainPage.fxml", title);
  }

  private void addElemsToLists() {
    radioButtonGroup0.add(q0a0);
    radioButtonGroup0.add(q0a1);
    radioButtonGroup0.add(q0a2);
    radioButtonGroup0.add(q0a3);
    radioButtonGroup1.add(q1a0);
    radioButtonGroup1.add(q1a1);
    radioButtonGroup1.add(q1a2);
    radioButtonGroup1.add(q1a3);
    radioButtonGroup2.add(q2a0);
    radioButtonGroup2.add(q2a1);
    radioButtonGroup2.add(q2a2);
    radioButtonGroup2.add(q2a3);
    radioButtons.add(radioButtonGroup0);
    radioButtons.add(radioButtonGroup1);
    radioButtons.add(radioButtonGroup1);
    textFieldGroup0.add(q0an0);
    textFieldGroup0.add(q0an1);
    textFieldGroup0.add(q0an2);
    textFieldGroup0.add(q0an3);
    textFieldGroup1.add(q1an0);
    textFieldGroup1.add(q1an1);
    textFieldGroup1.add(q1an2);
    textFieldGroup1.add(q1an3);
    textFieldGroup2.add(q2an0);
    textFieldGroup2.add(q2an1);
    textFieldGroup2.add(q2an2);
    textFieldGroup2.add(q2an3);
    textFieldOther.add(q0);
    textFieldOther.add(q1);
    textFieldOther.add(q2);
    textFieldOther.add(title);
    textFields.add(textFieldGroup0);
    textFields.add(textFieldGroup1);
    textFields.add(textFieldGroup2);
    textFields.add(textFieldOther);
  }

  private boolean checkIfQuizIsFilled() {
    boolean correctAnswer = radioButtons.stream()
        .allMatch(p -> p.stream().anyMatch(q -> q.isSelected()));
    boolean allFieldsAreFilled = textFields.stream()
        .allMatch(text -> text.stream()
        .allMatch(field -> !field.getText().equals("")));
    return correctAnswer && allFieldsAreFilled;

  }

}