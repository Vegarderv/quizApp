package quizapp.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import quizapp.core.User;
import quizapp.json.JsonHandler;

public class ScoreboardController implements Initializable {

  private JsonHandler handler = new JsonHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
  final List<User> users = handler.loadFromFile();
  private Map<String, ArrayList<User>> scoreMap = new HashMap<>();


  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    updateBoardInfo();
    TableView<String> tableView = new TableView<String>();
    for (String quizname : scoreMap) {
      tableView.getItems().add(quizname);
      for (User user : scoreMap.get(quizname)) {
        tableView.getItems().add(scoreMap.get(quizname).indexOf(user)+1 + ". " + 
        user.getUsername() + ": " + user.getScore(quizname));
      }
      tableView.getItems().add("  ");
      tableView.getItems().add("**************************************");
      tableView.getItems().add("  ");
    }
    VBox vbox = new VBox(tableView);
    Scene scene = new Scene(vbox);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void getQuizzes() {
    //need to implement this method 
  }


  public ArrayList<User> mergeUser(User user, ArrayList<User> topScorers, QuizController quiz) {
    if (topScorers.size() < 3) { 
        topScorers.add(user);
        Collections.sort(topScorers, (a, b) -> a.compare(a,b,quiz));
        return topScorers;
    }
    topScorers.add(user);
    Collections.sort(topScorers, (a, b) -> a.compare(a,b,quiz));
    topScorers.remove(3);
    return topScorers;
  }

  public Map<String, ArrayList<User>> updateBoardInfo() {
    for (QuizController quiz : this.getQuizzes()) {
      ArrayList<User> topScorers = new ArrayList<>();
      String name = quiz.getname();
      for (User user : this.users) {
        if (!user.quizTaken(name)) { break; }
        topScorers.clear();
        topScorers = mergeUser(user, topScorers, quiz);
      }
      this.scoreMap.put(name, topScorers);
    }
    return this.scoreMap;
  }

}