package quizapp.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControllerTest extends ApplicationTest {
    
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
        //assigns the textfields example values to test the logic
        TextField usernameField = (TextField) stage.getScene().lookup("#username");
        usernameField.setText("Hallvard");
        TextField passwordField = (TextField) stage.getScene().lookup("#password");
        passwordField.setText("Tretteberg");
        clickOn("#mainPageButton");
        //expects the scene to stay at sign in page
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#mainPageButton"));
        //fetches the string of the label and checks if this is the same as the expected string
        Label error = (Label) stage.getScene().lookup("#errorMessage");
        assertEquals("Wrong username or password", error.getText());
    }

     @Test
    public void checkWrongUsername() {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#mainPageButton"));
        TextField usernameField = (TextField) stage.getScene().lookup("#username");
        usernameField.setText("Halvar");
        TextField passwordField = (TextField) stage.getScene().lookup("#password");
        passwordField.setText("Trætteberg");
        clickOn("#mainPageButton");
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#mainPageButton"));
        Label error = (Label) stage.getScene().lookup("#errorMessage");
        assertEquals("Wrong username or password", error.getText());
    }

     @Test
    public void checkValidFields() {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#mainPageButton"));
        TextField usernameField = (TextField) stage.getScene().lookup("#username");
        usernameField.setText("Hallvard");
        TextField passwordField = (TextField) stage.getScene().lookup("#password");
        passwordField.setText("Trætteberg");
        clickOn("#mainPageButton");
        assertNull(stage.getScene().lookup("#mainPageButton"));
        assertNotNull(stage.getScene().lookup("#historyQuizButton"));
    }
}