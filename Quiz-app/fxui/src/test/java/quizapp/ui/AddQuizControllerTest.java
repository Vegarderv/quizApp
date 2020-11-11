package quizapp.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import quizapp.core.Quiz;
import org.junit.jupiter.api.Test;
import java.awt.*;




public class AddQuizControllerTest extends FxuiTest {


  private Stage stage;
  private DirectQuizAccess directQuizAccess;

  private Robot r;


  @Override
  public void start(final Stage stage) throws Exception {
    directQuizAccess = new DirectQuizAccess();
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("AddQuiz.fxml"));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
    this.stage = stage;
    r = new Robot();
  }

  /*@Test
  public void check() throws AWTException {
    Robot r1 = new Robot();
    r1.mouseWheel(15);
  }*/

  /*@Test
  public void TestMakeAQuiz() throws AWTException {
    //Robot r = new Robot();
    //Deletes quiz if it already exists
    Quiz quiz = directQuizAccess.getQuiz("Color-quiz");
    directQuizAccess.deleteQuiz(quiz);
    assertNull(directQuizAccess.getQuiz("Color-quiz"));
    //Sets question 1
    findTextField("#title").setText("Color quiz");
    findTextField("#q0").setText("What color do you get if you mix blue and yellow?");
    findTextField("#q0an0").setText("Blue");
    findTextField("#q0an1").setText("Green");
    findTextField("#q0an2").setText("Purple");
    findTextField("#q0an3").setText("Red");
    findRadioButton("#q0a1").setSelected(true);
    //Sets question 2
    findTextField("#q1").setText("What color do you get if you mix red and yellow?");
    findTextField("#q1an0").setText("Green");
    findTextField("#q1an1").setText("Yellow");
    findTextField("#q1an2").setText("Blue");
    findTextField("#q1an3").setText("Orange");
    findRadioButton("#q1a3").setSelected(true);
    //Sets question 3
    findTextField("#q2").setText("What color do you get if you mix blue and red?");
    findTextField("#q2an0").setText("Black");
    findTextField("#q2an1").setText("White");
    findTextField("#q2an2").setText("Purple");
    findTextField("#q2an3").setText("Green");
    findRadioButton("#q2a2").setSelected(true);
    //stage.getScene().lookup("#submit").setVisible(true);
    //stage.getScene().lookup("#submit").
    r.mouseWheel(15);
    clickOnButton("#submit");
    quiz = directQuizAccess.getQuiz("Color-quiz");
    assertNotNull(quiz);
    assertEquals("Color quiz", quiz.getName());
    assertEquals(1, quiz.getQuestion(0).getCorrectAlternative());
    assertEquals("Green", quiz.getQuestion(0).getAlternative(1));
    assertEquals("What color do you get if you mix red and yellow?", quiz.getQuestion(1));
  }
  */


  

  private TextField findTextField(String node) {
    return (TextField) stage.getScene().lookup(node);
  }

  private RadioButton findRadioButton(String button) {
    return (RadioButton) stage.getScene().lookup(button);
  }
  


}
