package quizapp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import jdk.internal.org.jline.terminal.MouseEvent;
import quizapp.core.Quiz;
import quizapp.core.User;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;
import quizapp.json.JsonHandler;
import quizapp.json.QuizHandler;
import quizapp.json.UsernameHandler;

public class ScoreboardController extends QuizAppController {

  //mvn javafx:run -f fxui/pom.xml

  @FXML
  MenuBar menuBar;
  @FXML
  TextFlow textFlow;
  @FXML
  MenuButton menuButton;
  @FXML
  MenuItem menuSignOut;
  @FXML
  MenuItem profileButton;

  private JsonHandler handler = new JsonHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
  final List<User> users = handler.loadFromFile();
  private Map<String, ArrayList<User>> scoreMap;
  private String usernamePath = 
      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json";
  private UsernameHandler userHandler = new UsernameHandler(usernamePath);
  private String username;
  private String quizPath = 
      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/quizzes.json";
  private QuizHandler quizHandler = new QuizHandler(quizPath);


  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    this.scoreMap = new HashMap<>();
    username = userHandler.loadActiveUser();
    menuButton.setText(username);
    updateBoardInfo();
    for (String quizname : this.scoreMap.keySet()) {
      Text text = new Text(quizname);
      textFlow.getChildren().add(text);
      for (User user : scoreMap.get(quizname)) {
        textFlow.getChildren().add(new Text(scoreMap.get(quizname).indexOf(user)+1 + ". " + 
        user.getUsername() + ": " + user.getScore(quizname)));
      }
    }
  }

  public List<Quiz> getQuizzes() {
    List<Quiz> quizzes = quizHandler.loadFromFile();
    return quizzes;
  }

  public int compareQuizScores(User a, User b, String quiz) {
    //compares quizscores of two users
    if (a.getScore(quiz) > b.getScore(quiz)) {
      return 1;
    }
    if (a.getScore(quiz) == b.getScore(quiz)) {
      return 0;
    }
    if (a.getScore(quiz) < b.getScore(quiz)) {
      return -1;
    }
    throw new IllegalArgumentException("The users must have taken the given quiz.");
  }


  public ArrayList<User> mergeUser(User user, ArrayList<User> topScorers, String quiz) {
    //this function checks if a new user schould be in the top three users in the relevant quiz
    if (topScorers.size() < 3) { 
        topScorers.add(user);
        Collections.sort(topScorers, (a, b) -> compareQuizScores(a,b,quiz));
        return topScorers;
    }
    topScorers.add(user);
    Collections.sort(topScorers, (a, b) -> compareQuizScores(a,b,quiz));
    topScorers.remove(3);
    return topScorers;
  }

  public Map<String, ArrayList<User>> updateBoardInfo() {
    //makes the score map that is used in the scoreboard
    for (Quiz quiz : this.getQuizzes()) {
      ArrayList<User> topScorers = new ArrayList<>();
      String name = quiz.getName();
      for (User user : this.users) {
        if (!user.quizTaken(name)) { break; }
        topScorers.clear();
        topScorers = mergeUser(user, topScorers, name);
      }
      this.scoreMap.put(name, topScorers);
    }
    return this.scoreMap;
  }

   @FXML
  public void goToProfilePage(ActionEvent event) {
    this.switchSceneWithNode("ProfilePage.fxml", userMenu);
  }

  @FXML
  public void goLogOut(ActionEvent event) {
    this.switchSceneWithNode("Login.fxml", userMenu);
  }
  
  @FXML
  void goToMainMenu(MouseEvent event) {
    this.switchSceneWithNode("MainPage.fxml", userMenu);
  }

}