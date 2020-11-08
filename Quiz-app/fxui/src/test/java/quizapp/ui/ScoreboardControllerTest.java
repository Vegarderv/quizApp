package quizapp.ui;

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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.QuizHandler;

public class ScoreboardControllerTest extends FxuiTest {

  /*

  private Stage stage;
  private TextFlow textFlow;
  private TextFlow textFlow2;
  private Text text;
  private ScoreboardController sbc;
  private ArrayList<User> users;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
    this.stage = stage;
    setUp();
  }

  private void setUp() {
    text = new Text("Næmmen heisann");
    text.setFont(new Font(34.0));
    textFlow = new TextFlow();
    textFlow2 = new TextFlow();
    textFlow.getChildren().add(text);
    textFlow2.getChildren().add(new Text(System.lineSeparator()));
    this.sbc = new ScoreboardController();
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

  private static String getText(TextFlow textFlow) {
        StringBuilder textBuilder = new StringBuilder();

        for (Node child : textFlow.getChildren()) {
            if (Text.class.isAssignableFrom(child.getClass())) {
                textBuilder.append(((Text) child).getText());
            }
        }
        return textBuilder.toString();
    }


  @Test
  public void checkTextFlow() {
    assertTrue(Objects.equals("Næmmen heisann", getText(textFlow)));
    assertEquals(text.getFont(), new Font(34.0));
    assertTrue(Objects.equals(System.lineSeparator(), getText(textFlow2)));
  }

  @Test
  public void getBoardInfoTest() {
    Map<String, ArrayList<User>> scoreMap = sbc.getBoardInfo();
    QuizHandler quizHandler = new QuizHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/quizzes.json");
    List<Quiz> quizzes = quizHandler.loadFromFile();
    JsonHandler handler = new JsonHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
    List<User> users = handler.loadFromFile();
    for (Quiz quiz : quizzes) {
      assertTrue(scoreMap.containsKey(quiz.getName()));
      for (User u : users) {
        if (scoreMap.get(quiz.getName()).contains(u)) {
          assertTrue(u.quizTaken(quiz.getName()));
        }
        else {
          if (u.quizTaken(quiz.getName())) {
            assertTrue(scoreMap.get(quiz.getName()).stream()
            .anyMatch(us -> us.getScore(quiz.getName()) >= u.getScore(quiz.getName())));
          }
        }
      }
    }
  }

  @Test
  public void mergeUserTest() {
    User user1 = new User();
    User user2 = new User();
    User user3 = new User();
    User user4 = new User();
    user1.setUsername("u1");
    user2.setUsername("u2");
    user3.setUsername("u3");
    user4.setUsername("u4");
    user1.addQuiz("Cool quiz", 0.2);
    user2.addQuiz("Cool quiz", 0.4);
    user3.addQuiz("Cool quiz", 0.6);
    user4.addQuiz("Cool quiz", 1.0);
    ArrayList<User> users = new ArrayList<User>();
    users = sbc.mergeUser(user1, users, "Cool quiz");
    assertTrue(users.contains(user1));
    users = sbc.mergeUser(user2, users, "Cool quiz");
    assertTrue(users.contains(user1));
    assertTrue(users.contains(user2));
    users = sbc.mergeUser(user3, users, "Cool quiz");
    assertTrue(users.contains(user1));
    assertTrue(users.contains(user2));
    assertTrue(users.contains(user3));
    users = sbc.mergeUser(user4, users, "Cool quiz");
    assertFalse(users.contains(user1));
    assertTrue(users.contains(user2));
    assertTrue(users.contains(user3));
    assertTrue(users.contains(user4));
  }

  */
}