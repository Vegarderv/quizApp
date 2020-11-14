package quizapp.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class QuestionTest {

  private Question question;

  @BeforeEach
  public void setQuestion() {
    question = new Question("Is coding fun?", "No", "Yes", "Sometimes", "What is coding?", 1);
  }

  @Test
  public void equalsTest() {
    assertTrue(question.equals(question));
    Question newQuestion = new Question("Is coding fun?", "No", "Yes", "Sometimes", "What is coding?", 1);
    assertTrue(question.equals(newQuestion));
    Question secondQuestion = new Question("Is coding fun?", "No", "Yes", "Never", "What is coding?", 1);
    assertFalse(question.equals(secondQuestion));
    Quiz quiz = new Quiz();
    assertFalse(question.equals(quiz));
  }

  @Test
  public void getAlternativeTest() {
    assertEquals("Sometimes", question.getAlternative(2));
    try {
      question.getAlternative(4);
      fail();
    } catch (IllegalArgumentException e) {
      //Illegal argument exceptionshould be caused as index is out of bound
    }
  }

  @Test
  public void correctAlternativeTest() {
    assertEquals(1, question.getCorrectAlternative());
  }

  @Test
  public void correctQuestionTest() {
    assertEquals("Is coding fun?", question.getQuestion());
    question.setQuestion("Is coding fun2?");
    assertEquals("Is coding fun2?", question.getQuestion());
  }

  @Test
  public void alternativesTest() {
    List<String> alternatives = new ArrayList<>(List.of("No", "Yes", "Sometimes", "What is coding?"));
    assertTrue(alternatives.stream().allMatch(a -> question.getAlternatives().contains(a)));
  }

  @Test
  public void setWrongCorrectAlternativeTest() {
    try {
      question.setCorrectAlternative(4);
      fail();
    } catch (IllegalArgumentException e) {
      //Illegal argument exceptionshould be caused as index is out of bound
    }
  }

  
}