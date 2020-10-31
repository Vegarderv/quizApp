package quizapp.restapi;

import quizapp.core.Quiz;
import quizapp.json.QuizHandler;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QuizService{

  private List<Quiz> quizzes;

  private QuizHandler quizHandler = new QuizHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/quizzes.json");

  
  public List<Quiz> getQuizzes() {
    return quizzes;
  }

  public void setQuizzes(List<Quiz> quizzes) {
    this.quizzes = quizzes;
  }

  public QuizService(){
    quizzes = quizHandler.loadFromFile();
  }

  public void addQuiz(Quiz quiz) {
    quizHandler.addQuiz(quiz);
  }



}