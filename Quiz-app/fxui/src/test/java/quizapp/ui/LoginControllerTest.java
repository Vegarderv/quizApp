package quizapp.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginControllerTest extends FxuiTest {
/*
  private Stage stage;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
    this.stage = stage;
  }

  @Test
  public void checkWrongPassword() {
    assertNull(stage.getScene().lookup("#historyQuizButton"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
    // assigns the textfields example values to test the logic
    TextField usernameField = (TextField) stage.getScene().lookup("#username");
    usernameField.setText("Hallvard");
    TextField passwordField = (TextField) stage.getScene().lookup("#password");
    passwordField.setText("Tretteberg");
    clickOnButton("#mainPageButton");
    // expects the scene to stay at sign in page
    assertNull(stage.getScene().lookup("#historyQuizButton"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
    // fetches the string of the label and checks if this is the same as the
    // expected string
    Label error = (Label) stage.getScene().lookup("#errorMessage");
    assertEquals("Wrong username or password", error.getText());
  }

  @Test
  public void checkWrongUsername() {
    // checks that login page is at right stage
    assertNull(stage.getScene().lookup("#historyQuizButton"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
    // assiging invalid values as password and username
    TextField usernameField = (TextField) stage.getScene().lookup("#username");
    usernameField.setText("Halva");
    TextField passwordField = (TextField) stage.getScene().lookup("#password");
    passwordField.setText("Trætteberg");
    // clicks on log in button
    clickOnButton("#mainPageButton");
    // checks that im still at login page
    assertNull(stage.getScene().lookup("#historyQuizButton"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
    // checks that the error label shows the right error message
    Label error = (Label) stage.getScene().lookup("#errorMessage");
    assertEquals("Wrong username or password", error.getText());
  }

  @Test
  public void checkValidFields() {
    assertNull(stage.getScene().lookup("#historyQuizButton"));
    assertNotNull(stage.getScene().lookup("#mainPageButton"));
    // assiging valid values for the username and password field
    TextField usernameField = (TextField) stage.getScene().lookup("#username");
    usernameField.setText("Hallvard");
    TextField passwordField = (TextField) stage.getScene().lookup("#password");
    passwordField.setText("Trætteberg");
    clickOnButton("#mainPageButton");
    // checks that scene is changed to the page menu after login button is pressed
    assertNull(stage.getScene().lookup("#mainPageButton"));
    assertNotNull(stage.getScene().lookup("#historyQuizButton"));
  }

  // need to check sign up button
  @Test
  public void signUpTest() {
    assertNotNull(stage.getScene().lookup("#signUpButtonLoginPage"));
    assertNull(stage.getScene().lookup("#signupButton"));
    clickOnButton("#signUpButtonLoginPage");
    assertNull(stage.getScene().lookup("#signUpButtonLoginPage"));
    assertNotNull(stage.getScene().lookup("#signupButton"));
  } */
}