package quizapp.ui;

import quizapp.core.Quiz;
import java.util.List;
import quizapp.json.QuizHandler;

public class DirectQuizAccess implements QuizAccess {

  private QuizHandler quizHandler = new QuizHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/quizzes.json");

  public Quiz getQuiz(String ID) {
    return quizHandler.getQuizById(ID);
  }

  public List<Quiz> getQuizzes() {
    return quizHandler.loadFromFile();
  }

  public void postQuiz(Quiz quiz) {
    quizHandler.addQuiz(quiz);
  }
}