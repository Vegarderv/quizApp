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
import javafx.scene.control.TableView;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import quizapp.core.Quiz;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.QuizHandler;
import quizapp.json.UsernameHandler;

public class ScoreboardController implements Initializable {

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
    //need to update from tableview to to textflow
    for (String quizname : this.scoreMap.keySet()) {
      scoreTable.getItems().add(quizname);
      for (User user : scoreMap.get(quizname)) {
        scoreTable.getItems().add(scoreMap.get(quizname).indexOf(user)+1 + ". " + 
        user.getUsername() + ": " + user.getScore(quizname));
      }
      scoreTable.getItems().add("  ");
      scoreTable.getItems().add("**************************************");
      scoreTable.getItems().add("  ");
    }
  }

  public List<Quiz> getQuizzes() {
    List<Quiz> quizzes = quizHandler.loadFromFile();
    return quizzes;
  }


  public ArrayList<User> mergeUser(User user, ArrayList<User> topScorers, String quiz) {
    if (topScorers.size() < 3) { 
        topScorers.add(user);
        Collections.sort(topScorers, (a, b) -> a.compareQuizScores(a,b,quiz));
        return topScorers;
    }
    topScorers.add(user);
    Collections.sort(topScorers, (a, b) -> a.compareQuizScores(a,b,quiz));
    topScorers.remove(3);
    return topScorers;
  }

  public Map<String, ArrayList<User>> updateBoardInfo() {
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

  public void switchSceneWithButton(ActionEvent event, String fxmlFile) {
    try {
      Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
      Scene scene = new Scene(parent);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method for changing scene with a MenuItem.
   */
  public void switchSceneWithMenuItem(String fxmlFile) {
    try {
      Stage stage = (Stage) menuBar.getScene().getWindow();
      Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
      Scene scene = new Scene(parent);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

   @FXML
  public void goToProfile(ActionEvent event) {
    this.switchSceneWithMenuItem("ProfilePage.fxml");
  }

  @FXML
  public void logOut(ActionEvent event) {
    this.switchSceneWithMenuItem("Login.fxml");
  }
  
  @FXML
  void goToMainMenu(ActionEvent event) {
    this.switchSceneWithMenuItem("MainPage.fxml");
  }

}