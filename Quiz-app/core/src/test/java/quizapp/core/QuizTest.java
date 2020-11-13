package quizapp.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuizTest {
  
  private Quiz quiz;


  @BeforeEach
  public void setQuiz() {
    Question q1 = new Question("question1", "q1a1", "q1a2", "q1a3", "q1a4", 1);
    Question q2 = new Question("question2", "q2a1", "q2a2", "q2a3", "q2a4", 2);
    Question q3 = new Question("question3", "q3a1", "q3a2", "q3a3", "q3a4", 3);
    quiz = new Quiz("test quiz", q1, q2, q3);
  }
  @Test
  public void equalsTest() {
    assertTrue(quiz.equals(quiz));
    Question q1 = new Question("question1", "q1a1", "q1a2", "q1a3", "q1a4", 1);
    Question q2 = new Question("question2", "q2a1", "q2a2", "q2a3", "q2a4", 2);
    Question q3 = new Question("question3", "q3a1", "q3a2", "q3a3", "q3a4", 3);
    Quiz newQuiz = new Quiz("test quiz", q1, q2, q3);  
    assertTrue(quiz.equals(newQuiz));
    Question q4 = new Question("question4", "q3a1", "q3a2", "q3a3", "q3a4", 3);
    Quiz secondQuiz = new Quiz("test quiz", q1, q2, q4);  
    assertFalse(quiz.equals(secondQuiz));
    Question question = new Question();
    assertFalse(quiz.equals(question));
  }

  @Test
  public void correctNameTest() {
    assertEquals("test quiz", quiz.getName());
    quiz.setName("test quiz2");
    assertEquals("test quiz2", quiz.getName());
  }

  @Test
  public void correctId() {
    assertEquals("test-quiz", quiz.getId());
  }

  @Test
  public void getQuestionsTest() {
    assertEquals("question1", quiz.getQuestion(0).getQuestion());
    try {
      quiz.getQuestion(4);
      fail();
    } catch (IllegalArgumentException e) {
      //Illegal argument exceptionshould be caused as index is out of bound
    }
  }

  @Test
  public void makeQuizFromQuizTest() {
    Quiz newQuiz = new Quiz(quiz);
    assertTrue(quiz.equals(newQuiz));
  }


}