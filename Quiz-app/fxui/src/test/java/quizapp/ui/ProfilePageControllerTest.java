package quizapp.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

import static org.junit.jupiter.api.Assertions.*;

public class ProfilePageControllerTest extends FxuiTest {
/*
  private Stage stage;
  private String usernamePath = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json";
  private String jsonPath = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json";

  @Override
  public void start(final Stage stage) throws Exception {
    // Sets Up Stage
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
    this.stage = stage;
  }

  @Test
  public void logOutTest() {
    // Checks that we on profile page
    assertNotNull(stage.getScene().lookup("#userMenuProfilePage"));
    assertNull(stage.getScene().lookup("#mainPageButton"));
    // Changes Scene to logOut
    clickOnMenuItem("#userMenuProfilePage", "#menuSignOut");
    // Checks that scene is changed to logOut
    assertNull(stage.getScene().lookup("#userMenuProfilePage"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
  }

  @Test
  public void goToMainMenuTest() {
    // Checks that we on profile page
    assertNotNull(stage.getScene().lookup("#userMenuProfilePage"));
    assertNull(stage.getScene().lookup("#menuButton"));
    // Changes Scene to Main Menu
    clickOnButton("#mainMenu");
    // Checks that we are on the Main page scene
    assertNull(stage.getScene().lookup("#userMenuProfilePage"));
    assertNotNull(stage.getScene().lookup("#menuButton"));
  }

  @Test
  public void checkUserText() {
    // Checks active user and makes sure it matches username displayed in the top
    UsernameHandler userHandler = new UsernameHandler(usernamePath);
    Label label = (Label) stage.getScene().lookup("#nameId");
    assertEquals(userHandler.loadActiveUser(), label.getText());
  }

  @Test
  public void checkUserScore() {
    // Checks active user and makes sure it matches score
    UsernameHandler userHandler = new UsernameHandler(usernamePath);
    Label label = (Label) stage.getScene().lookup("#scoreId");
    JsonHandler jsonHandler = new JsonHandler(jsonPath);
    User user = jsonHandler.loadFromFile().stream()
        .filter(u -> u.getUsername().equals(userHandler.loadActiveUser())).findFirst().get();
    assertEquals(String.valueOf(Math.round((user.meanScore()*100))) + "  %", label.getText());
  }
  */
}
