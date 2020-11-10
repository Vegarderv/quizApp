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
import javafx.scene.control.TextField;


import org.junit.jupiter.api.Test;

public class AddQuizTest extends FxuiTest {

  private Stage stage;
  private DirectUserAccess directUserAccess;
  private DirectQuizAccess directQuizAccess;


  @Override
  public void start(final Stage stage) throws Exception {
    directQuizAccess = new DirectQuizAccess();
    //Deletes quiz if it already exists
    Quiz quiz = directQuizAccess.getQuiz("Color-quiz");
    directQuizAccess.deleteQuiz(quiz);
    assertNull(directQuizAccess.getQuiz("Color-quiz"));
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("AddQuiz.fxml"));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
    this.stage = stage;
  }


  @Test
  public void runQuizwithEverythingCorrect() throws AWTException {
    // Runs through the quiz
    Robot r = new Robot();
    clickOn("#q0a2");
    findTextField("#title").setText("Color quiz");
    r.mouseWheel(15);
    clickOn("#q1a3");
    // Scrolls to bottom of screen
    // Slows down the code to give the robot time to scroll
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    clickOn("#q2a1");
    //Robot r = new Robot();
  }
    
  private TextField findTextField(String node) {
    return (TextField) stage.getScene().lookup(node);
  }


  
}