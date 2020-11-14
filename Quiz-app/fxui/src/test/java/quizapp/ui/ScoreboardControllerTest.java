package quizapp.ui;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import javafx.scene.text.Text;
import java.util.Map;
import quizapp.json.QuizHandler;
import quizapp.json.UsernameHandler;
import java.util.List;
import quizapp.core.Quiz;
import java.util.HashMap;
import javafx.scene.text.TextFlow;
import javafx.scene.Node;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.beans.Transient;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.QuizHandler;

public class ScoreboardControllerTest extends FxuiTest {


  private Stage stage;

  @Override
  public void start(final Stage stage) throws Exception {
    setUp();
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
    this.stage = stage;
  }

  private void setUp() {

  }

  @Test
  public void logOutTest() {
    // Checks that we are in the main page scene
    assertNotNull(stage.getScene().lookup("#userMenu"));
    assertNull(stage.getScene().lookup("#mainPageButton"));
    // Changes Scene to login
    clickOnMenuItem("#userMenu", "#menuSignOut");
    // Checks that the scene has been changed
    assertNull(stage.getScene().lookup("#userMenu"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
  }

  @Test
  public void goToProfileTest() {
    assertNotNull(stage.getScene().lookup("#userMenu"));
    assertNull(stage.getScene().lookup("#userMenuProfilePage"));
    clickOnMenuItem("#userMenu", "#profilePage");
    assertNull(stage.getScene().lookup("#userMenu"));
    assertNotNull(stage.getScene().lookup("#userMenuProfilePage"));
  }

  @Test
  public void checkTextFlow() {
    List<Quiz> quizzes = new DirectQuizAccess().getQuizzes();
    StringBuilder sb = new StringBuilder();
    TextFlow textFlow = (TextFlow) stage.getScene().lookup("#textFlow");
    for (Node node : textFlow.getChildren()) {
      if (node instanceof Text) {
        sb.append(((Text) node).getText());
      }
    }
    String fullText = sb.toString();
    for (Quiz quiz : quizzes) {
      if (!fullText.contains(quiz.getName())) {
        fail("Quiz does not show");
      }
    }
  }
}