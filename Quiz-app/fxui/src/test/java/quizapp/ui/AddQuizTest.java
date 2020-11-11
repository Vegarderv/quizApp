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
    System.out.println(quiz);
    System.out.println(directQuizAccess.getQuizzes());
    directQuizAccess.deleteQuiz("Color quiz");
    System.out.println(directQuizAccess.getQuizzes());
    //assertNull(directQuizAccess.getQuiz("Color-quiz"));
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
    findTextField("#title").setText("Color quiz");
    findTextField("#q0").setText("What color do you get if you mix blue and yellow?");
    findTextField("#q0an0").setText("Blue");
    findTextField("#q0an1").setText("Green");
    findTextField("#q0an2").setText("Purple");
    findTextField("#q0an3").setText("Red");
    clickOnButton("#q0a2");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    r.mouseWheel(15);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    findTextField("#q1").setText("What color do you get if you mix red and yellow?");
    findTextField("#q1an0").setText("Green");
    findTextField("#q1an1").setText("Yellow");
    findTextField("#q1an2").setText("Blue");
    findTextField("#q1an3").setText("Orange");
    clickOnButton("#q1a3");
    // Scrolls to bottom of screen
    // Slows down the code to give the robot time to scroll
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    r.mouseWheel(15);
    findTextField("#q2").setText("What color do you get if you mix blue and red?");
    findTextField("#q2an0").setText("Black");
    findTextField("#q2an1").setText("White");
    findTextField("#q2an2").setText("Purple");
    findTextField("#q2an3").setText("Green");
    clickOnButton("#q2a2");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    clickOnButton("#submit");
    
  }
    
  private TextField findTextField(String node) {
    return (TextField) stage.getScene().lookup(node);
  }


  
}