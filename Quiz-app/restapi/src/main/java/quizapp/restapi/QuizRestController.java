package quizapp.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/{id1}")
  public Quiz getQuiz(@PathVariable("id1") String id1, @RequestParam String id) {
    System.out.println("Url id: " + id);
    return getQuizzes().stream().filter(q -> q.getId().equals(id)).findFirst().orElse(null);
  }


  @PostMapping("/new/{Id}")
  public void newUser(@PathVariable("Id") String Id, @RequestBody Quiz quiz) {
    quizService.addQuiz(quiz);
  }

}
