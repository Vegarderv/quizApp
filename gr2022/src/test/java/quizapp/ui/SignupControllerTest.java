package quizapp.ui;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
    final Parent root;

    
    @BeforeAll
    public static void setUp(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        this.root = loader.load();
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
        this.loadedUsers = handler.loadFromFile();
        signup.username.setText("Gløs");
        signup.password.setText("Heisann");
        signup.toMainMenu(clickOn("#toMainMenu"));
//      if (loadedUsers.stream().anyMatch(a -> a.getUsername().equals(signup.username.getText()))) {
        assertTrue(stage.getScene().equals(root));
        assertTrue(signup.errorMessage.getText().equals("Username already taken"));
    }

    @Test
    public void checkEmptyUsername() {
        signup.username.setText("");
        signup.password.setText("Heisann");
        signup.toMainMenu(clickOn("#toMainMenu"));
        assertTrue(stage.getScene().equals(root));
        assertTrue(signup.errorMessage.getText().equals("Username and password must at least contain 1 sign"));
    }

    @Test
    public void checkEmptyPassword() {
        signup.username.setText("Heisann");
        signup.password.setText("");
        signup.toMainMenu(clickOn("#toMainMenu"));
        assertTrue(stage.getScene().equals(root));
        assertTrue(signup.errorMessage.getText().equals("Username and password must at least contain 1 sign"));
    }

    @Test
    public void checkValidFields() {
        signup.username.setText("Dragvoll");
        signup.password.setText("Hadebra");
        signup.toMainMenu(clickOn("#toMainMenu"));
        final User newUser = new User();
        newUser.setUsername("Dragvoll");
        newUser.setPassword("Hadebra");
        this.loadedUsers = handler.loadFromFile();
        assertTrue(loadedUsers.contains(newUser));
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        final Parent main = loader.load();
        assertTrue(this.stage.getScene().equals(main));
    }

}