package quizapp.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import quizapp.json.JSONHandler;
import quizapp.json.UsernameHandler;


public class ScoreTest {
    private String usernamePath = "src/main/resources/quizapp/json/activeUserTest.json";
    private String jsonPath = "src/main/resources/quizapp/json/JSONHandlerTest.json";
    private UsernameHandler userHandler = new UsernameHandler(usernamePath);
    private User user = new User();
    private JSONHandler jsonHandler = new JSONHandler(jsonPath);
    private Score score = new Score(jsonPath, usernamePath);
    private List<User> users = Arrays.asList(user);

    

    
    public void setUp(){
        user.setUsername("TestName");
        user.setPassword("TestPassword");
        jsonHandler.writeToFile(users);
        userHandler.saveActiveUser("TestName", jsonPath);
        score.scoreQuiz(4, 5, "Superquiz");
    }

    @Test
    public void rightSocre(){
        setUp();
        List<User> loadedUsers = jsonHandler.loadFromFile();
        user = loadedUsers.get(0);
        assertEquals(0.8, user.meanScore());
    }

    @Test
    public void rightQuizTaken(){
        setUp();
        List<User> loadedUsers = jsonHandler.loadFromFile();
        user = loadedUsers.get(0);
        assertTrue(user.quizTaken("Superquiz"));
    }
}