package quizapp.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

import java.awt.AWTException;
import java.awt.Robot;

public class ProfilePageControllerTest extends FxuiTest {

  private Stage stage;
  private String usernamePath = "/workspace/gr2022/gr2022/core/src/main/resources/quizapp/json/activeUser.json";
  private String jsonPath = "/workspace/gr2022/gr2022/core/src/main/resources/quizapp/json/JSONHandler.json";

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
    // Checks that we are in the history quiz scene
    assertNotNull(stage.getScene().lookup("#menuBar"));
    assertNull(stage.getScene().lookup("#mainPageButton"));
    // Changes Scene to logOut
    clickOnMenuItem("#userMenu", "#menuSignOut");
    // Checks that scene is changed to logOut
    assertNull(stage.getScene().lookup("#submit"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
  }

  @Test
  public void goToMainMenuTest() {
    // Checks that we are in the history quiz stage
    assertNotNull(stage.getScene().lookup("#menuBar"));
    assertNull(stage.getScene().lookup("#mainPageButton"));
    // Changes Scene to Main Menu
    clickOnMenuItem("#userMenu", "#menuMainMenu");
    // Checks that we are on the Main page scene
    assertNull(stage.getScene().lookup("#submit"));
    assertNotNull(stage.getScene().lookup("#historyQuizButton"));
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
    assertEquals(user.meanScore().toString(), label.getText());
  }
}