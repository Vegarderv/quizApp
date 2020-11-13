package quizapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
  private User user;

  @BeforeEach
  public void setUser() {
    user = new User("Hallvard", "Trætteberg");
    user.addQuiz("quiz1", 1);
    user.addQuiz("quiz2", 0);
    user.setCurrentQuiz(new Quiz("test quiz", new Question(), new Question(), new Question()));
  }

  @Test
  public void darkmodeTest() {
    assertFalse(user.getDarkMode());
    user.setDarkMode(true);
    assertTrue(user.getDarkMode());
  }

  @Test
  public void currentQuizTest() {
    assertEquals("test quiz", user.getCurrentQuiz().getName());
    user.setCurrentQuiz(new Quiz("test quiz2", new Question(), new Question(), new Question()));
    assertEquals("test quiz2", user.getCurrentQuiz().getName());
  }

  @Test
  public void equalsTest() {
    assertTrue(user.equals(user));
    User newUser = new User();
    newUser.setUsername("Hallvard");
    newUser.setPassword("Trætteberg");
    assertTrue(user.equals(newUser));
    User secondUser = new User("Hallvard2", "Trætteberg");
    assertFalse(user.equals(secondUser));
    Quiz quiz = new Quiz();
    assertFalse(user.equals(quiz));
  }

  @Test
  public void makeUserfromUserTest() {
    User newUser = new User(user);
    assertEquals("Hallvard", newUser.getUsername());
    assertEquals("Trætteberg", newUser.getPassword());
    assertEquals(user.getDarkMode(), newUser.getDarkMode());
    assertEquals(user.getCurrentQuiz(), newUser.getCurrentQuiz());
    assertEquals(user.getQuizzesTaken(), newUser.getQuizzesTaken());
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

  @Test
  public void retakeQuiz() {
    user.addQuiz("quiz1", 0);
    assertEquals(0, user.getScore("quiz1"));
  } 

  @Test
  public void toStringTest() {
    assertEquals("Hallvard Trætteberg", user.toString());
  }

  
}