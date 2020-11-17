package quizapp.json;

import org.junit.jupiter.api.Test;

import quizapp.core.Question;
import quizapp.core.Quiz;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class QuizHandlerTest {

  private static QuizHandler handler;
  private static Quiz quiz1;
  private static Quiz quiz2;
  private static Quiz quiz3;
  
  @BeforeEach
  public void setUp() {
    handler = new QuizHandler("src/main/resources/quizapp/json/quizzesTest.json");
    Question q1 = new Question("question1", "q1a1", "q1a2", "q1a3", "q1a4", 1);
    Question q2 = new Question("question2", "q2a1", "q2a2", "q2a3", "q2a4", 2);
    Question q3 = new Question("question3", "q3a1", "q3a2", "q3a3", "q3a4", 3);
    quiz1 = new Quiz("test quiz1", q1, q2, q3);

    Question q4 = new Question("question4", "q4a1", "q4a2", "q4a3", "q4a4", 1);
    Question q5 = new Question("question5", "q5a1", "q5a2", "q5a3", "q5a4", 2);
    Question q6 = new Question("question6", "q6a1", "q6a2", "q6a3", "q6a4", 3);
    quiz2 = new Quiz("test quiz2", q4, q5, q6);

    Question q7 = new Question("questio7", "q7a1", "q7a2", "q7a3", "q7a4", 1);
    Question q8 = new Question("question8", "q8a1", "q8a2", "q8a3", "q8a4", 2);
    Question q9 = new Question("question9", "q9a1", "q9a2", "q9a3", "q9a4", 3);
    quiz3 = new Quiz("test quiz3", q7, q8, q9);

    Collection<Quiz> quizzes = new ArrayList<>(List.of(quiz1, quiz2));
    handler.writeToFile(quizzes);
  }

  @Test
  public void loadFromFileTest() {
    Collection<Quiz> loadedQuizzes = handler.loadFromFile();
    assertTrue(loadedQuizzes.contains(quiz1));
    assertTrue(loadedQuizzes.contains(quiz2));
    assertFalse(loadedQuizzes.contains(quiz3));
  }

  @Test
  public void getQuizByIdTest() {
    Quiz quiz = handler.getQuizById("test-quiz1");
    assertTrue(quiz1.equals(quiz));
  }

  @Test
  public void addQuizTest() {
    handler.addQuiz(quiz3);
    Collection<Quiz> loadedQuizzes = handler.loadFromFile();
    assertTrue(loadedQuizzes.contains(quiz1));
    assertTrue(loadedQuizzes.contains(quiz2));
    assertTrue(loadedQuizzes.contains(quiz3));
  }

  @Test
  public void deleteQuizTest() {
    handler.deleteQuiz("test-quiz1");
    Collection<Quiz> loadedQuizzes = handler.loadFromFile();
    assertFalse(loadedQuizzes.contains(quiz1));
    assertTrue(loadedQuizzes.contains(quiz2));
    assertFalse(loadedQuizzes.contains(quiz3));
  }





}