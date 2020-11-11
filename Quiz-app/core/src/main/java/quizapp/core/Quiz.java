package quizapp.core;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

  private List<Question> questions;
  private String name;
  private String id;

  /**
   * Quiz Constructor.
   * 

   * @param name name of the quiz
   * @param question0 First Question
   * @param question1 Second Question
   * @param question2 Third Question
   */
  public Quiz() {

  }

  public Quiz(Quiz other) {
    this.questions = other.questions;
    this.name = other.name;
    setId();
  }

  public Quiz(String name, Question question0, Question question1, Question question2) {
    this.name = name;
    setId();
    this.questions = new ArrayList<>();
    this.questions.add(question0);
    this.questions.add(question1);
    this.questions.add(question2);
  }

  /**
   * Returns Question using num as parameter.
   * 

   * @param num Integer between 0 and 2. Inclusive
   * @return the question with index num-1. Use num=1 to get question the first
   *         question.
   */

  public Question getQuestion(int num) {
    if (num >= 0 && num <= 2) {
      return questions.get(num);
    }
    throw new IllegalArgumentException("Number must be between 0 and 2");
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setId() {
    this.id = getName().replace(" ", "-");
  }

  public String toString(){
    return this.getName();
  }

  
}
