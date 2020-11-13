package quizapp.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsernameCheckTest {

  // Declaring variables
  static UsernameCheck nameCheck;
  UserAccess userAccess = new DirectUserAccess();

  // Setting up the tests
  @BeforeAll
  public static void setUp() {
    UserAccess userAccess = new DirectUserAccess(true);
    User user1 = new User();
    user1.setPassword("gitlab");
    user1.setUsername("gr2022");
    nameCheck = new UsernameCheck();
    userAccess.postUser(user1);
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
