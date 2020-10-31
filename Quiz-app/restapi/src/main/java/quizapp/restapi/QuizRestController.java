package quizapp.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import quizapp.core.Quiz;
import quizapp.json.QuizHandler;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizRestController {

  @Autowired
  private QuizService quizService;

  @GetMapping
  public List<Quiz> getQuizzes() {
    return quizService.getQuizzes();
  }


  @GetMapping("/quizzes")
  public List<Quiz> quizzz() {
    return getQuizzes();
  }

  @GetMapping("/quiz")
  public Quiz getQuiz(@RequestParam String Id) {
    return getQuizzes().stream().filter(q -> q.getId().equals(Id)).findFirst().orElse(null);
  }


  @PostMapping("/new")
  public void newQuiz(@RequestBody Quiz quiz) {
    quizService.addQuiz(quiz);
  }

}
