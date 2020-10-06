package quizapp.core;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
  private User user = new User();

  @BeforeEach
  public void setUser() {
    // Using private method instead of @BeforeAll to avoid static method

    user.setUsername("Hallvard");
    user.setPassword("Trætteberg");
    user.addQuiz("quiz1", 1);
    user.addQuiz("quiz2", 0);
  }

  @Test
  public void correctUsernameAndPassword() {
    // Tests the getter methods

    assertEquals(user.getUsername(), "Hallvard");
    assertEquals(user.getPassword(), "Trætteberg");
  }

  @Test
  public void correctMeanScore() {
    // Checks User.meanScore() method
    assertEquals(user.meanScore(), 0.5);
  }

  @Test
  public void hasTakenQuiz() {
    // Checks if User.quizTaken() works properly

    assertTrue(user.quizTaken("quiz1"));
  }

  @Test
  public void hasNotTakenQuiz() {
    // Checks if User.quizTaken() works properly

    assertFalse(user.quizTaken("quiz3"));
  }

  @Test
  public void getCorrectScore() {
    // Checks if User.getScore() works properly

    assertEquals(1.0, user.getScore("quiz1"));
  }
}