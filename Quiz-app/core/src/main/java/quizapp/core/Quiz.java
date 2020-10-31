package quizapp.core;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

  private List<Question> questions;
  private String name;

  /**
   * Quiz Constructor.
   * 

   * @param name name of the quiz
   * @param question1 First Question
   * @param question2 Second Question
   * @param question3 Third Question
   */
  public Quiz(String name, Question question1, Question question2, Question question3) {
    this.name = name;
    this.questions = new ArrayList<>();
    this.questions.add(question1);
    this.questions.add(question2);
    this.questions.add(question3);
  }

  /**
   * Returns Question using num as parameter.
   * This method is indexed as one, so
   * valid params are {1,2,3,4}
   * 

   * @param num Integer between 1 and 4. Inclusive
   * @return the question with index num-1. Use num=1 to get question the first
   *         question.
   */

  public Question getQuestion(int num) {
    if (num >= 1 && num <= 3) {
      return questions.get(num - 1);
    }
    throw new IllegalArgumentException("Number must be between 1 and 4");
  }

  /**
   * Returns the name of the name of this quiz.

   * @return the name
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Question> getQuestions(){
    return this.questions;
  }
  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }
}
