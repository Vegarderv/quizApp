package quizapp.ui;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testfx.framework.junit5.ApplicationTest;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quizapp.core.User;
import quizapp.json.JSONHandler;

public class SignupControllerTest extends ApplicationTest {

    private static SignupController signup;
    private static JSONHandler handler;
    private static List<User> users = new ArrayList<>();
    private Stage stage;
    private List<User> loadedUsers;

    @BeforeAll
    public void setUp(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        final Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        this.stage = stage;
        signup = new SignupController();
        handler = new JSONHandler("src/main/resources/quizapp/json/JSONHandler.json");
        User user = new User();
        user.setUsername("Gløs");
        user.setPassword("T-town");
        users.add(user);
        handler.writeToFile(users);
    }

    @Test
    public void checkUsernameAlreadyTaken() {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        this.loadedUsers = handler.loadFromFile();
        signup.username.setText("Gløs");
        signup.password.setText("Heisann");
        clickOn("#signupButton");
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        assertTrue(signup.errorMessage.getText().equals("Username already taken"));
    }

    @Test
    public void checkEmptyUsername() {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        signup.username.setText("");
        signup.password.setText("Heisann");
        clickOn("#signupButton");
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        assertTrue(signup.errorMessage.getText().equals("Username and password must at least contain 1 sign"));
    }

    @Test
    public void checkEmptyPassword() {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        signup.username.setText("Heisann");
        signup.password.setText("");
        clickOn("#signupButton");
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        assertTrue(signup.errorMessage.getText().equals("Username and password must at least contain 1 sign"));
    }

    @Test
    public void checkValidFields() throws IOException {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#signupButton"));
        signup.username.setText("Dragvoll");
        signup.password.setText("Hadebra");
        clickOn("#signupButton");
        final User newUser = new User();
        newUser.setUsername("Dragvoll");
        newUser.setPassword("Hadebra");
        this.loadedUsers = handler.loadFromFile();
        assertTrue(loadedUsers.contains(newUser));
        assertNull(stage.getScene().lookup("#signupButton"));
        assertNotNull(stage.getScene().lookup("#historyQuizButton"));
    }

}