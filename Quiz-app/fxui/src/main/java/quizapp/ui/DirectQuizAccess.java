package quizapp.ui;

import quizapp.core.Quiz;
import java.nio.file.Paths;
import java.util.List;
import quizapp.json.QuizHandler;

public class DirectQuizAccess implements QuizAccess {

  private final String pathStarter = "../core/src/main/resources/quizapp/json/";
  private final String pathQuizzes = Paths.get(pathStarter + "quizzes.json").toString();
  private QuizHandler quizHandler = new QuizHandler(this.pathQuizzes);

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