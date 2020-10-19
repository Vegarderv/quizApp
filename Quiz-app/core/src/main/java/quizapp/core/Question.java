package quizapp.core;

import java.util.Arrays;
import java.util.List;

public class Question {

  private String question, alternative1, alternative2, alternative3, alternative4;
  private int correct_alternative;
  private List<String> alternatives = Arrays.asList(alternative1, alternative2, alternative3, alternative4);


  /** 
   * This class is used for representing a question, with alternatives and the correct answer.
  */
  public Question(String question, String alternative1, String alternative2, String alternative3, String alternative4, int correct_alternative){
    this.question = question;
    this.alternative1 = alternative1;
    this.alternative2 = alternative2;
    this.alternative3 = alternative3;
    this.alternative4 = alternative4;
    if (correct_alternative <= 4 && correct_alternative >= 1) {
      this.correct_alternative = correct_alternative;
    }
    else {
      throw new IllegalArgumentException("The correct alternative must be a number between 1 and 4.");
    }
  }

  /**
   * @return the question
   */
  public String getQuestion() {
    return question;
  }

  /**
   * 
   * @param num
   * @return alternative num 
   */
  public String getAlternative(int num) {
    if (!(num >= 1 && num <= 4)) {
      throw new IllegalArgumentException("num must be between 1 and 4");
    }
    return alternatives.get(num-1); 
  }

  /**
   * @return the correct_alternative
   */
  public int getCorrect_alternative() {
    return correct_alternative;
  }

}