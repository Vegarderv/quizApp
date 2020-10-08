package quizapp.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import quizapp.json.JsonHandler;

public class UsernameCheckTest {

  // Declaring variables
  static JsonHandler handler;
  static List<User> usernames = new ArrayList<>();
  List<User> loadedUsernames;
  static UsernameCheck nameCheck;

  // Setting up the tests
  @BeforeAll
  public static void setUp() {
    handler = new JsonHandler("/workspace/gr2022/gr2022/core/src/main/resources/quizapp/json/JSONHandlerTest.json");
    User user1 = new User();
    user1.setPassword("gitlab");
    user1.setUsername("gr2022");
    usernames.add(user1);
    nameCheck = new UsernameCheck();
    handler.writeToFile(usernames);
  }

  // Testing with wrong username and right password
  @Test
  public void wrongUsername() {
    assertFalse(nameCheck.checkUsername("bob", "gitlab"));
  }

  // Testing with right usernamen and wrong password
  @Test
  public void wrongPassword() {
    assertFalse(nameCheck.checkUsername("gr2022", "admin123"));
  }

  // Testing with correct username and password
  @Test
  public void correct() {
    assertTrue(nameCheck.checkUsername("gr2022", "gitlab"));
  }
}
