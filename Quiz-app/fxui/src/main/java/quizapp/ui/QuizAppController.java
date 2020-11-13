package quizapp.ui;

import java.net.URI;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quizapp.core.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizAppController implements Initializable {

  private UserAccess remoteUserAccess;
  private User currentUser;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  protected void switchSceneWithNode(String fxmlFile, Node node) {
    try {
      Stage stage = (Stage) node.getScene().getWindow();
      Parent parent = FXMLLoader.load(QuizAppController.class.getResource(fxmlFile));
      Scene scene = new Scene(parent);
      try {
        remoteUserAccess = new RemoteUserAccess(new URI("http://localhost:8080/api/user/"));
      } catch (Exception e) {
        remoteUserAccess = new DirectUserAccess();
      }
      currentUser = remoteUserAccess.getActiveUser();
      if (currentUser.getDarkMode()) {
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