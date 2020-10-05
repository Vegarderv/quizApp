package quizapp.ui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quizapp.core.User;
import quizapp.json.JSONHandler;

public class LoginControllerTest extends ApplicationTest {
    
    private static LoginController login;
    private static JSONHandler handler;
    private static List<User> users = new ArrayList<>();
    private Stage stage;


    @BeforeAll
    public void setUp(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        final Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        this.stage = stage;
        login = new LoginController();
        handler = new JSONHandler("src/main/resources/quizapp/json/JSONHandler.json");
        User user = new User();
        user.setUsername("Gløs");
        user.setPassword("T-town");
        users.add(user);
        handler.writeToFile(users);
    }

     @Test
    public void checkWrongPassword() {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#mainPageButton"));
        login.username.setText("Gløs");
        login.password.setText("Heisann");
        clickOn("#mainPageButton");
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#mainPageButton"));
        assertTrue(login.errorMessage.getText().equals("Wrong username or password"));
    }

     @Test
    public void checkWrongUsername() {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#mainPageButton"));
        login.username.setText("Gløsen");
        login.password.setText("T-town");
        clickOn("#mainPageButton");
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#mainPageButton"));
        assertTrue(login.errorMessage.getText().equals("Wrong username or password"));
    }

     @Test
    public void checkValidFields() {
        assertNull(stage.getScene().lookup("#historyQuizButton"));
        assertNotNull(stage.getScene().lookup("#mainPageButton"));
        login.username.setText("Gløs");
        login.password.setText("T-town");
        clickOn("#mainPageButton");
        assertNull(stage.getScene().lookup("#mainPageButton"));
        assertNotNull(stage.getScene().lookup("#historyQuizButton"));
    }
}