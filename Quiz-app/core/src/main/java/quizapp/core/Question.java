package quizapp.core;

public class Question {

  private String question, alternative1, alternative2, alternative3, alternative4;
  private int correct_alternative;


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
   * @return the alternative1
   */
  public String getAlternative1() {
    return alternative1;
  }

  /**
   * @return the alternative2
   */
  public String getAlternative2() {
    return alternative2;
  }

  /**
   * @return the alternative3
   */
  public String getAlternative3() {
    return alternative3;
  }

  /**
   * @return the alternative4
   */
  public String getAlternative4() {
    return alternative4;
  }

  /**
   * @return the correct_alternative
   */
  public int getCorrect_alternative() {
    return correct_alternative;
  }

}