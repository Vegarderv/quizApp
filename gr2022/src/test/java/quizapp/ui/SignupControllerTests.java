package quizapp.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testfx.framework.junit5.ApplicationTest;

import org.junit.jupiter.api.Test;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import quizapp.core.User;
import quizapp.core.UsernameCheck;
import quizapp.json.JSONHandler;

public class SignupControllerTests extends ApplicationTest {

    private static JSONHandler handler = new JSONHandler("src/main/resources/quizapp/json/JSONHandlerTest.json");
    private static List<User> users = handler.loadFromFile();
    private Stage stage;

    private void setUp() throws Exception {
        //sets up the class such that you can check if the saved data corrensponds with the code
        handler = new JSONHandler("src/main/resources/quizapp/json/JSONHandlerTest.json");
        User user = new User();
        user.setUsername("Gløs");
        user.setPassword("T-town");
        users.add(user);
        handler.writeToFile(users);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        setUp();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        final Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        this.stage = stage;
    }

    @Test
    public void checkUsernameAlreadyTaken() {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        //assigns the textfields example values to test the logic
        TextField usernameField = (TextField) stage.getScene().lookup("#username");
        usernameField.setText("Gløs");
        TextField passwordField = (TextField) stage.getScene().lookup("#password");
        passwordField.setText("Heisann");
        clickOn("#signupButton");
        //expects the scene to stay at sign in page
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        //fetches the string of the label and checks if this is the same as the expected string
        Label error = (Label) stage.getScene().lookup("#errorMessage");
        assertEquals("Username already taken", error.getText());
    }

    @Test
    public void checkEmptyUsername() {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        TextField usernameField = (TextField) stage.getScene().lookup("#username");
        usernameField.setText("");
        TextField passwordField = (TextField) stage.getScene().lookup("#password");
        passwordField.setText("Heisann");
        clickOn("#signupButton");
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        Label error = (Label) stage.getScene().lookup("#errorMessage");
        assertEquals("Username and password must at least contain 1 sign", error.getText());
    }

    @Test
    public void checkEmptyPassword() {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        TextField usernameField = (TextField) stage.getScene().lookup("#username");
        usernameField.setText("Heisann");
        TextField passwordField = (TextField) stage.getScene().lookup("#password");
        passwordField.setText("");
        clickOn("#signupButton");
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        Label error = (Label) stage.getScene().lookup("#errorMessage");
        assertEquals("Username and password must at least contain 1 sign", error.getText());
    }

    @Test
    public void checkValidFields() throws IOException {

        //deletes user from previous testrun
        JSONHandler jsonHandler = new JSONHandler("src/main/resources/quizapp/json/JSONHandler.json");
        List<User> users = jsonHandler.loadFromFile();
        users.remove(users.stream()
        .filter(user -> user.getUsername().equals("Dragvoll"))
        .findAny().get());
        jsonHandler.writeToFile(users);
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        TextField usernameField = (TextField) stage.getScene().lookup("#username");
        usernameField.setText("Dragvoll");
        TextField passwordField = (TextField) stage.getScene().lookup("#password");
        passwordField.setText("Hadebra");
        clickOn("#signupButton");
        //uses username check to see if the new user is saved for later log ins
        final UsernameCheck chk = new UsernameCheck();
        assertTrue(chk.checkUsername("Dragvoll", "Hadebra"));
        //expects now the scene to change to main page
        assertNull(stage.getScene().lookup("#signupButton"));
        assertNotNull(stage.getScene().lookup("#historyQuizButton"));
    }

}