package quizapp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

public class QuizAppController implements Initializable {
  private String usernamePath 
      = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json";
  private String jsonPath 
      = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json";

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  protected void switchSceneWithNode(String fxmlFile, Node node) {
    try {
      Stage stage = (Stage) node.getScene().getWindow();
      Parent parent = FXMLLoader.load(QuizAppController.class.getResource(fxmlFile));
      Scene scene = new Scene(parent);
      User user = new JsonHandler(jsonPath).loadFromFile().stream()
          .filter(user1 -> new UsernameHandler(usernamePath).loadActiveUser()
          .equals(user1.getUsername()))
          .findAny()
          .orElse(new User());
      if (user.getDarkMode()) {
        scene.getStylesheets().add(QuizAppController.class.getResource("darkmode.css")
            .toExternalForm());
      } else {
        scene.getStylesheets().add(QuizAppController.class.getResource("lightmode.css")
            .toExternalForm());
      }
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}