package quizapp.restapi;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quizapp.core.Quiz;

@RestController
@RequestMapping("/api/quiz")
public class QuizRestController {

  @Autowired
  private QuizService quizService;

  @GetMapping
  public Collection<Quiz> getQuizzes() {
    return quizService.getQuizzes();
  }


  @GetMapping("/quizzes")
  public Collection<Quiz> quizzz() {
    return getQuizzes();
  }

  @GetMapping("/{id1}")
  public Quiz getQuiz(@PathVariable("id1") String id1) {
    System.out.println("Url id: " + id1);
    return getQuizzes().stream().filter(q -> q.getId().equals(id1)).findFirst().orElse(null);
  }


  @PostMapping("/{id}")
  public void newUser(@PathVariable("id") String id, @RequestBody Quiz quiz) {
    quizService.addQuiz(quiz);
  }

}
