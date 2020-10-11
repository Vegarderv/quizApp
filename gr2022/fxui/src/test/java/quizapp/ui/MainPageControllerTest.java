package quizapp.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPageControllerTest extends FxuiTest {

  private Stage stage;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
    this.stage = stage;
  }

  @Test
  public void logOutTest() {
    // Checks that we are in the main page scene
    assertNotNull(stage.getScene().lookup("#historyQuizButton"));
    assertNull(stage.getScene().lookup("#mainPageButton"));
    // Changes Scene to login
    clickOnMenuItem("#menuButton", "#menuSignOut");
    // Checks that the scene has been changed
    assertNull(stage.getScene().lookup("#historyQuizButton"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
  }

  // Will be implemented when profile page is finished
  @Test
  public void goToProfileTest() {

  }

  @Test
  public void goToHistoryQuizTest() {
    // Checks that we are in the main page scene
    assertNotNull(stage.getScene().lookup("#historyQuizButton"));
    assertNull(stage.getScene().lookup("#scroll"));
    // Changes scene to history quiz page
    clickOnButton("#historyQuizButton");
    // Checks that the scene has been changed
    assertNotNull(stage.getScene().lookup("#scroll"));
    assertNull(stage.getScene().lookup("#historyQuizButton"));

  }

}