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
    private UsernameHandler userHandler = new UsernameHandler("src/main/resources/quizapp/json/activeUserTest.json");
    private User user = new User();
    private JSONHandler jsonHandler = new JSONHandler("src/main/resources/quizapp/json/JSONHandlerTest.json");
    private Score score = new Score();
    private List<User> users = Arrays.asList(user);

    

    
    public void setUp(){
        user.setUsername("TestName");
        user.setPassword("TestPassword");
        jsonHandler.writeToFile(users);
        userHandler.saveActiveUser("TestName", "src/main/resources/quizapp/json/JSONHandlerTest.json" );
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