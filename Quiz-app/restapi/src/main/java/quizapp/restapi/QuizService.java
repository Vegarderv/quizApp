package quizapp.restapi;

import java.nio.file.Paths;
import java.util.List;
import org.springframework.stereotype.Service;
import quizapp.core.Quiz;
import quizapp.json.QuizHandler;

@Service
public class QuizService {

  private List<Quiz> quizzes;
  private static String pathStarter = "../core/src/main/resources/quizapp/json/";
  private final String quizPath = Paths.get(pathStarter + "quizzes.json").toString();
  private QuizHandler quizHandler = new QuizHandler(this.quizPath);
  
  public List<Quiz> getQuizzes() {
    quizzes = quizHandler.loadFromFile();
    return quizzes;
  }

  public void setQuizzes(List<Quiz> quizzes) {
    this.quizzes = quizzes;
  }

  public QuizService() {
    quizzes = quizHandler.loadFromFile();
  }

  public void addQuiz(Quiz quiz) {
    quizHandler.addQuiz(quiz);
  }
}