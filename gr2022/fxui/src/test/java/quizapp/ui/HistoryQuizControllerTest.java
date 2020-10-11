package quizapp.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

import java.awt.AWTException;
import java.awt.Robot;

public class HistoryQuizControllerTest extends FxuiTest {

  private Stage stage;
  private String usernamePath = "/workspace/gr2022/gr2022/core/src/main/resources/quizapp/json/activeUserTest.json";
  private String jsonPath = "/workspace/gr2022/gr2022/core/src/main/resources/quizapp/json/JSONHandlerTest.json";

  @Override
  public void start(final Stage stage) throws Exception {
    // Creates user for tests
    User user = new User();
    user.setUsername("Hallvard");
    user.setPassword("Trætteberg");
    List<User> userList = new ArrayList<>();
    userList.add(user);
    new JsonHandler(jsonPath).writeToFile(userList);
    new UsernameHandler(usernamePath).saveActiveUser(user.getUsername(), jsonPath);
    // Sets Up Stage
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("HistoryQuiz.fxml"));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
    this.stage = stage;
  }

  @Test
  public void logOutTest() {
    // Checks that we are in the history quiz scene
    assertNotNull(stage.getScene().lookup("#submit"));
    assertNull(stage.getScene().lookup("#mainPageButton"));
    // Changes Scene to logOut
    clickOnMenuItem("#userMenu", "#menuSignOut");
    // Checks that scene is changed to logOut
    assertNull(stage.getScene().lookup("#submit"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
  }

  // Will be implemented when profile page is finished
  @Test
  public void goToProfileTest() {
    assertNotNull(stage.getScene().lookup("#submit"));
    assertNull(stage.getScene().lookup("#userMenuProfilePage"));
    clickOnMenuItem("#userMenu", "#menuProfilePage");
    assertNull(stage.getScene().lookup("#submit"));
    assertNotNull(stage.getScene().lookup("#userMenuProfilePage"));
  }

  @Test
  public void checkUserMenuText() {
    // Checks active user and makes sure it matches username displayed in the
    // MenuButton top right
    UsernameHandler userHandler = new UsernameHandler(usernamePath);
    FxAssert.verifyThat("#userMenu", org.testfx.matcher.control.LabeledMatchers.hasText(userHandler.loadActiveUser()));
  }

  @Test
  public void goToMainMenuTest() {
    // Checks that we are in the history quiz stage
    assertNotNull(stage.getScene().lookup("#submit"));
    assertNull(stage.getScene().lookup("#mainPageButton"));
    // Changes Scene to Main Menu
    clickOnButton("#mainMenu");
    // Checks that we are on the Main page scene
    assertNull(stage.getScene().lookup("#submit"));
    assertNotNull(stage.getScene().lookup("#historyQuizButton"));
  }

  @Test
  public void runQuizwithEverythingCorrect() throws AWTException {
    // Runs through the quiz
    Robot r = new Robot();
    clickOn("#q1a3");
    clickOn("#q2a4");
    // Scrolls to bottom of screen
    r.mouseWheel(15);
    // Slows down the code to give the robot time to scroll
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    clickOn("#q3a3");
    clickOnButton("#submit");
    FxAssert.verifyThat("#score", org.testfx.matcher.control.LabeledMatchers.hasText("You got this Score: 100%"));

  }

}