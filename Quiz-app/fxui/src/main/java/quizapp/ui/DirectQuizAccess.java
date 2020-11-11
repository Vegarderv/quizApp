package quizapp.ui;

import java.nio.file.Paths;
import java.util.List;
import quizapp.core.Quiz;
import quizapp.json.QuizHandler;

public class DirectQuizAccess implements QuizAccess {

  private static String pathStarter = "../core/src/main/resources/quizapp/json/";
  private final String pathQuizzes = Paths.get(pathStarter + "quizzes.json").toString();
  private QuizHandler quizHandler = new QuizHandler(this.pathQuizzes);

  public Quiz getQuiz(String id) {
    return quizHandler.getQuizById(id);
  }

  public List<Quiz> getQuizzes() {
    return quizHandler.loadFromFile();
  }

  public void postQuiz(Quiz quiz) {
    quizHandler.addQuiz(quiz);
  }
}