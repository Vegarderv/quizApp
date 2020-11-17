package quizapp.ui;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import quizapp.core.DirectQuizAccess;
import quizapp.core.Quiz;

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
    Collection<Quiz> quizzes = new DirectQuizAccess().getQuizzes();
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