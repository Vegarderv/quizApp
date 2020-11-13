package quizapp.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
  public void getAlternativeTest() {
    assertEquals("Sometimes", question.getAlternative(2));
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
  
}