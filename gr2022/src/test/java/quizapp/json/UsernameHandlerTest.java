package quizapp.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import quizapp.core.User;

public class UsernameHandlerTest {
  private UsernameHandler handler = new UsernameHandler("src/main/resources/quizapp/json/activeUserTest.json");
  private String name = "gr2022";
  private String loadedName;

  @BeforeAll
  public static void setUp() {
    // Creates a user
    User user = new User();
    user.setUsername("gr2022");
    new JsonHandler("src/main/resources/quizapp/json/JSONHandlerTest.json").writeToFile(Arrays.asList(user));
  }

  @Test
  public void saveUserNotInDatabase() {
    try {
      handler.saveActiveUser("NotInDatabase", "src/main/resources/quizapp/json/JSONHandlerTest.json");
      fail("Not Actual user");

    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void noErrorSavingUser() {
    // checks for no errors during the saving prosess
    handler.saveActiveUser(name, "src/main/resources/quizapp/json/JSONHandlerTest.json");
  }

  @Test
  public void checkCorrectName() {
    // Checks if the same name is loaded as saved
    loadedName = handler.loadActiveUser();
    assertEquals(name, loadedName);
  }

}