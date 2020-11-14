package quizapp.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import quizapp.core.User;


public class JSONHandlerTest {

  static JsonHandler handler;
  static List<User> usernames = new ArrayList<>();
  List<User> loadedUsernames;

  // Sets up usernames, Users and quizzes
  @BeforeAll
  public static void setUp() {
    handler = new JsonHandler("src/main/resources/quizapp/json/JSONHandlerTest.json");
    User user1 = new User();
    User user2 = new User();
    user1.setUsername("Hallvard");
    user1.setPassword("Trætteberg");
    user2.setUsername("George");
    user2.setPassword("Stoica");
    usernames.add(user1);
    usernames.add(user2);
    user1.addQuiz("testquiz123", 0.69);
    handler.writeToFile(usernames);
  }

  @Test
  public void firstUsername() {
    loadedUsernames = handler.loadFromFile();
    assertEquals("Hallvard", loadedUsernames.get(0).getUsername());
  }

  @Test
  public void firstPassword() {
    loadedUsernames = handler.loadFromFile();
    assertEquals("Trætteberg", loadedUsernames.get(0).getPassword());
  }

  @Test
  public void secondUsername() {
    loadedUsernames = handler.loadFromFile();
    assertEquals("George", loadedUsernames.get(1).getUsername());
  }

  @Test
  public void secondPassword() {
    loadedUsernames = handler.loadFromFile();
    assertEquals("Stoica", loadedUsernames.get(1).getPassword());
  }

  @Test
  public void correctQuiz() {
    // Checks if correct quiz is loaded
    loadedUsernames = handler.loadFromFile();
    assertTrue(loadedUsernames.get(0).quizTaken("testquiz123"));
  }

  @AfterAll
  public static void after() {
    //empties testdoc
    JsonHandler jsonHandler = new JsonHandler("src/main/resources/quizapp/json/JSONHandlerTest.json");
    jsonHandler.writeToFile(new ArrayList<User>());
  }

}
