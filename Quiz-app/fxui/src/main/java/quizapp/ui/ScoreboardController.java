package quizapp.ui;

import java.net.URL;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.input.MouseEvent;
import quizapp.core.Quiz;
import quizapp.core.User;
import javafx.scene.text.Font;

public class ScoreboardController extends QuizAppController {
  
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
  private UserAccess remoteUserAccess;
  private QuizAccess remoteQuizAccess;
  private List<User> users;
  private String username;
  private List<Quiz> quizzes;
 


  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    try {
      remoteUserAccess = new RemoteUserAccess(new URI("http://localhost:8080/api/user/"));
    } catch (Exception e) {
      remoteUserAccess = new DirectUserAccess();
    }
    users = remoteUserAccess.getUsers();
    username = remoteUserAccess.getActiveUser().getUsername();
    try {
      remoteQuizAccess = new RemoteQuizAccess(new URI("http://localhost:8080/api/quiz/"));
    } catch (Exception e) {
      remoteQuizAccess = new DirectQuizAccess();
    }
    quizzes = remoteQuizAccess.getQuizzes();
    userMenu.setText(username);
    Map<String, ArrayList<User>> scoreMap = getBoardInfo();
    System.out.println(scoreMap);
    for (Map.Entry< String, ArrayList<User> > quizname : scoreMap.entrySet()) {
      Text text = new Text(quizname.getKey());
      text.setFont(new Font(34.0));
      //text.setFill(Color.WHEAT);
      //text.setStyle("-fx-background-color: red");
      textFlow.getChildren().add(text);
      textFlow.getChildren().add(new Text(System.lineSeparator()));
      for (User user : scoreMap.get(quizname.getKey())) {
        Text text1 = new Text(scoreMap.get(quizname.getKey()).indexOf(user)+1 + ". " + 
        user.getUsername() + ": " + Math.round(user.getScore(quizname.getKey())*100) + "%");
        text1.setFont(new Font(24.0));
        textFlow.getChildren().add(text1);
        textFlow.getChildren().add(new Text(System.lineSeparator()));
      }
      textFlow.getChildren().add(new Text(System.lineSeparator()));
      textFlow.getChildren().add(new Text(System.lineSeparator()));
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
      if (topScorers.size() < index + 1) {
        topScorers.add(index, user);
        break;
      }
      else if (user.getScore(quiz) > topScorers.get(index).getScore(quiz)) {
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
     try {
        //makes the score map that is used in the scoreboard
    Map<String, ArrayList<User>> scoreMap = new HashMap<>();
    for (Quiz quiz : this.quizzes) {
      ArrayList<User> topScorers = new ArrayList<>();
      String name = quiz.getName();
      System.out.println(name);
      for (User userHey : this.users) {
        if (userHey.quizTaken(name)) {
          topScorers = mergeUser(userHey, topScorers, name);
        }
      }
      scoreMap.put(name, topScorers);
    }
    return scoreMap;
      } catch (Exception e) {
        System.out.println(this.quizzes);
        e.printStackTrace();
        return null;
      }
    
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