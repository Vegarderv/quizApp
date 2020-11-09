package quizapp.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import quizapp.core.User;
import quizapp.core.Quiz;
import org.testfx.api.FxAssert;
import java.awt.*;
import java.beans.Transient;

import org.junit.jupiter.api.Test;

public class QuizControllerTest extends FxuiTest {

  private Stage stage;
  private DirectUserAccess directUserAccess;
  private QuizAccess directQuizAccess;


  @Override
  public void start(final Stage stage) throws Exception {
    directUserAccess = new DirectUserAccess();
    directQuizAccess = new DirectQuizAccess();
    //deletes user if it exists from previous tests
    directUserAccess.deleteUser("Test1");
    Quiz quiz = directQuizAccess.getQuiz("Chemistry-quiz");
    User user = new User();
    user.setUsername("Test1");
    user.setPassword("password");
    user.setCurrentQuiz(quiz);
    directUserAccess.postUser(user);
    directUserAccess.putActiveUser("Test1");
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
    this.stage = stage;
  }

  @Test
  public void goToMainMenuTest() {
    // Checks that we on quiz page
    assertNotNull(stage.getScene().lookup("#userMenu"));
    assertNull(stage.getScene().lookup("#menuButton"));
    // Changes Scene to Main Menu
    clickOnButton("#mainMenu");
    // Checks that we are on the Main page scene
    assertNull(stage.getScene().lookup("#userMenu"));
    assertNotNull(stage.getScene().lookup("#menuButton"));
  }

  @Test
  public void checkCorrectUserDisplayed() {
    // Checks active user and makes sure it matches username displayed on menu button
    String activeUser = ((MenuButton)stage.getScene().lookup("#userMenu")).getText();
    assertEquals(directUserAccess.getActiveUser().getUsername(), activeUser);
  }

  @Test
  public void logOutTest() {
    // Checks that we are on the quiz page
    assertNotNull(stage.getScene().lookup("#userMenu"));
    assertNull(stage.getScene().lookup("#mainPageButton"));
    // Changes Scene to login
    clickOnMenuItem("#userMenu", "#menuSignOut");
    // Checks that the scene has been changed
    assertNull(stage.getScene().lookup("#userMenu"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
  }


  

  @Test
  public void runQuizwithEverythingCorrect() throws AWTException {
    // Runs through the quiz
    Robot r = new Robot();
    clickOn("#q0a2");
    clickOn("#q1a3");
    // Scrolls to bottom of screen
    r.mouseWheel(15);
    // Slows down the code to give the robot time to scroll
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    clickOn("#q2a1");
    clickOnButton("#submit");
    FxAssert.verifyThat("#score", org.testfx.matcher.control.LabeledMatchers.hasText("You got this Score: 100%"));
    assertEquals((double) directUserAccess.getUser("Test1").getScore("Chemistry quiz"), 1.0, 0);
  }

  @Test
  public void testRetakeAQuiz() throws AWTException {
    // Sets up user so it has taken the quiz before
    User user = directUserAccess.getUser("Test1");
    user.addQuiz("Chemistry quiz", (2 * 1.0) / (3 * 1.0));
    directUserAccess.putUser(user);
    //Takes quiz again
    Robot r = new Robot();
    clickOn("#q0a0"); //Clicks wrong alternative
    clickOn("#q1a0"); //Clicks wrong alternative
    // Scrolls to bottom of screen
    r.mouseWheel(15);
    // Slows down the code to give the robot time to scroll
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    clickOn("#q2a0"); //Clicks wrong alternative
    clickOnButton("#submit");
    FxAssert.verifyThat("#score", org.testfx.matcher.control.LabeledMatchers.hasText("You got this Score: 0%"));
    assertEquals((double) directUserAccess.getUser("Test1").getScore("Chemistry quiz"), 0, 0);
  }



  
}