package quizapp.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

public class QuizControllerTest extends FxuiTest {

  private Stage stage;
  private UserAccess directUserAccess;


  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
    this.stage = stage;
    directUserAccess = new DirectUserAccess();
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

}