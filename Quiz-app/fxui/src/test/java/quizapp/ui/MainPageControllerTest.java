package quizapp.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainPageControllerTest extends FxuiTest {

  private Stage stage;
  private UserAccess directUserAccess;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
    this.stage = stage;
    directUserAccess = new DirectUserAccess();
  }


  @Test
  public void checkCorrectUserDisplayed() {
    // Checks active user and makes sure it matches username displayed on menu button
    String activeUser = ((MenuButton)stage.getScene().lookup("#menuButton")).getText();
    assertEquals(directUserAccess.getActiveUser().getUsername(), activeUser);
  }
  
  
  @Test
  public void logOutTest() {
    // Checks that we are in the main page scene
    assertNotNull(stage.getScene().lookup("#menuButton"));
    assertNull(stage.getScene().lookup("#mainPageButton"));
    // Changes Scene to login
    clickOnMenuItem("#menuButton", "#menuSignOut");
    // Checks that the scene has been changed
    assertNull(stage.getScene().lookup("#menuButton"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
  }

  // Will be implemented when profile page is finished
  @Test
  public void goToProfileTest() {
    // Checks that we are in the main page scene
    assertNotNull(stage.getScene().lookup("#menuButton"));
    assertNull(stage.getScene().lookup("#userMenuProfilePage"));
    // Changes Scene to profile page
    clickOnMenuItem("#menuButton", "#profileButton");
    // Checks that the scene has been changed
    assertNull(stage.getScene().lookup("#menuButton"));
    assertNotNull(stage.getScene().lookup("#userMenuProfilePage"));
  }

  @Test
  public void goToQuizTest() {
    // Checks that we are in the main page scene
    assertNotNull(stage.getScene().lookup("#menuButton"));
    assertNull(stage.getScene().lookup("#userMenu"));
    // Changes scene to quiz page
    clickOnButton("#Chemistry-quiz");
    // Checks that the scene has been changed
    assertNotNull(stage.getScene().lookup("#userMenu"));
    assertNull(stage.getScene().lookup("#menuButton"));
    //Checks that the correct quiz has been loaded
    assertEquals(((Label) stage.getScene().lookup("#quizName")).getText(), "Chemistry quiz");


  }

  @Test
  public void goToAddQuiz() {
    // Checks that we are in the main page scene
    assertNotNull(stage.getScene().lookup("#menuButton"));
    assertNull(stage.getScene().lookup("#userMenu"));
    clickOnButton("#newQuiz");
    // Checks that the scene has been changed
    assertNotNull(stage.getScene().lookup("#userMenu"));
    assertNull(stage.getScene().lookup("#menuButton"));
  }

}