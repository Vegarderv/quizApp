package quizapp.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuizTest {
  
  private Quiz quiz;


  @BeforeEach
  public void setQuiz() {
    quiz = new Quiz("Test quiz", new Question("Test question", "test1", "test2", "test3", "test4", 0),
     new Question(), new Question());
  }

  @Test
  public void correctNameTest() {
    assertEquals("Test quiz", quiz.getName());
    quiz.setName("Test quiz2");
    assertEquals("Test quiz2", quiz.getName());
  }

  @Test
  public void correctId() {
    assertEquals("Test-quiz", quiz.getId());
  }

  @Test
  public void getQuestionsTest() {
    assertEquals("Test question", quiz.getQuestion(0).getQuestion());
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
    assertEquals("Test quiz", newQuiz.getName());
    assertEquals("Test question", newQuiz.getQuestion(0).getQuestion());
  }


}