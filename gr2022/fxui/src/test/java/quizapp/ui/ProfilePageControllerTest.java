package quizapp.ui;

import org.testfx.api.FxAssert;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;
import quizapp.core.User;

public class ProfilePageControllerTest extends ApplicationTest {
    private Stage stage;
  

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
        final Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        this.stage = stage;
    }

    @Test
    public void goToMainPageTest() {
        assertNotNull(stage.getScene().lookup("#mainPageId")); 
        assertNull(stage.getScene().lookup("#historyQuizButton"));
         clickOn("#menubutton").clickOn("#mainPageId");
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
        }
        assertNotNull(stage.getScene().lookup("#historyQuizButton"));
        assertNull(stage.getScene().lookup("#mainPageId"));

    }
    @Test
    public void goToLogOut() {
        assertNotNull(stage.getScene().lookup("#loginId")); 
        assertNull(stage.getScene().lookup("#mainPageButton"));
        clickOn("#menubutton").clickOn("#loginId");
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
        }
        assertNotNull(stage.getScene().lookup("#mainPageButton"));
        assertNull(stage.getScene().lookup("#loginId"));

    }
    @Test
public void correctUserNameTest(){
        UsernameHandler userHandler = new UsernameHandler("src/main/resources/quizapp/json/activeUserTest.json");
        FxAssert.verifyThat("#nameId", org.testfx.matcher.control.LabeledMatchers.hasText(userHandler.loadActiveUser()));
        }
        
        @Test
public void correctScoreTest(){
  JsonHandler jsonHandler = new JsonHandler(
        "src/main/resources/quizapp/json/JSONHandler.json");
    UsernameHandler usernameHandler = new UsernameHandler(
        "src/main/resources/quizapp/json/activeUser.json");
    User user=jsonHandler.loadFromFile().stream()
        .filter(u-> u.getUsername()
        .equals(usernameHandler.loadActiveUser()))
        .findFirst().get();

  FxAssert.verifyThat("#scoreId", org.testfx.matcher.control.LabeledMatchers.hasText(user.meanScore().toString()));

}
      }