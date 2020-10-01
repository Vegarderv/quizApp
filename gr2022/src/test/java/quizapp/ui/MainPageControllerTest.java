package quizapp.ui;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPageControllerTest extends ApplicationTest {

    private MainPageController controller;
    private Stage stage;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
        this.stage = stage;
    }


    @Test
    public void test1() {
        clickOn("#menuButton").clickOn("#logOutButton");
        assertTrue(stage.getScene().lookup("#mainPageButton").isVisible());
        assertNull(stage.getScene().lookup("#historyQuizButton"));    
    }

    @Test
    public void goToHistoryQuizTest() {
        clickOn("#historyQuizButton");
        assertTrue(stage.getScene().lookup("#scroll").isVisible());
        
    }

}