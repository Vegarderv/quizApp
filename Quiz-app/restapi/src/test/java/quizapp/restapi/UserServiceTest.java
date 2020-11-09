package quizapp.restapi;

import org.junit.jupiter.api.Test;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

/**
 * All methods returns directly from JsonHandler, not a lot of testing is
 * needed, just some tests that verify that the paths are correct.
 */
public class UserServiceTest {

  private String usernamePath = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json";
  private String jsonPath = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json";
  JsonHandler jsonHandler = new JsonHandler(jsonPath);
  UserService service = new UserService();

  @Test
  public void verifyThatJsonPathIsCorrect() {
    System.out.println(jsonHandler.loadActiveUser().getQuizzesTaken()+ ":" + service.getActiveUser().getQuizzesTaken());
    assertTrue(jsonHandler.loadActiveUser().equals(service.getActiveUser()));
  }

}