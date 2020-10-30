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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import quizapp.core.Quiz;
import quizapp.core.User;
import javafx.scene.text.Font;
import quizapp.json.JsonHandler;
import quizapp.json.QuizHandler;
import quizapp.json.UsernameHandler;

public class ScoreboardController extends QuizAppController implements Initializable {

  //mvn javafx:run -f fxui/pom.xml

  @FXML
  MenuBar menuBar;
  @FXML
  TextFlow textFlow;
  @FXML
  MenuButton userMenu;
  @FXML
  MenuItem menuSignOut;
  @FXML
  MenuItem profileButton;
  @FXML
  ScrollPane scroll;

  private JsonHandler handler = new JsonHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
  final List<User> users = handler.loadFromFile();
  private String usernamePath = 
      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json";
  private UsernameHandler userHandler = new UsernameHandler(usernamePath);
  private String username = userHandler.loadActiveUser();
  private String quizPath = 
      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/quizzes.json";
  private QuizHandler quizHandler = new QuizHandler(quizPath);
  private List<Quiz> quizzes = quizHandler.loadFromFile();


  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    userMenu.setText(username);
    Map<String, ArrayList<User>> scoreMap = getBoardInfo();
    for (String quizname : scoreMap.keySet()) {
      Text text = new Text(quizname);
      text.setFont(new Font(34.0));
      textFlow.getChildren().add(text);
      textFlow.getChildren().add(new Text(System.lineSeparator()));
      for (User user : scoreMap.get(quizname)) {
        Text text1 = new Text(scoreMap.get(quizname).indexOf(user)+1 + ". " + 
        user.getUsername() + ": " + user.getScore(quizname)*100 + "%");
        text1.setFont(new Font(24.0));
        textFlow.getChildren().add(text1);
        textFlow.getChildren().add(new Text(System.lineSeparator()));
      }
    }
  }

  public ArrayList<User> mergeUser(User user, ArrayList<User> topScorers, String quiz) {
    //this function checks if a new user should be in the top three users in the relevant quiz
    if (topScorers.isEmpty()) {
      topScorers.add(user);
      return topScorers;
    }
    int index = 0;
    while (index < 3) {
      if (user.getScore(quiz) > topScorers.get(index).getScore(quiz)) {
        topScorers.add(index, user);
        break;
      }
      index++;
    }
    if (topScorers.size() > 3) {
      topScorers.remove(3);
    }
    return topScorers;
  }

  public Map<String, ArrayList<User>> getBoardInfo() {
    //makes the score map that is used in the scoreboard
    Map<String, ArrayList<User>> scoreMap = new HashMap<>();
    for (Quiz quiz : this.quizzes) {
      ArrayList<User> topScorers = new ArrayList<>();
      String name = quiz.getName();
      for (User user : this.users) {
        if (user.quizTaken(name)) {
          topScorers = mergeUser(user, topScorers, name);
        }
      }
      scoreMap.put(name, topScorers);
    }
    return scoreMap;
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