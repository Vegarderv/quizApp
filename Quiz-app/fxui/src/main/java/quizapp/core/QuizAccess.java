package quizapp.core;

import java.util.Collection;

public interface QuizAccess {


  /**
   * Gets quiz by ID.

   * @param id id of wanted quiz
   * @return returns Quiz with id: id
   */
  public Quiz getQuiz(String id);

  /**
   * getQuizzes is a method that returns all quizzes.

   * @return returns all quizzes in database.
   */
  public Collection<Quiz> getQuizzes();

  /**
   * Method for sending new Quiz to database.

   * @param quiz the new quiz created
   */
  public void postQuiz(Quiz quiz);
}