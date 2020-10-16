package quizapp.core;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

  private List<Question> questions;
  private String name;

  public Quiz(String name, Question question1, Question question2, Question question3) {
    this.name = name;
    this.questions = new ArrayList<>();
    this.questions.add(question1);
    this.questions.add(question2);
    this.questions.add(question3);
  }

  public Question getQuestion(int num) {
    if (num >= 1 && num <= 3) {
      return questions.get(num-1);
    }
    throw new IllegalArgumentException("Number must be between 1 and 4");
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  
}
