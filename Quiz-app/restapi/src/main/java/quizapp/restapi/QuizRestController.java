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

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizRestController {

  @GetMapping("/quizzes")
  public List<Quiz> quizzz() {
    return new QuizHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/quizzes.json")
        .loadFromFile();
  }

  @GetMapping("/quiz")
  public Quiz getQuiz(@RequestParam String Id) {
    return new QuizHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/quizzes.json")
        .getQuizById(Id);
  }


  @PostMapping("/new")
  public void newQuiz(@RequestBody Quiz quiz) {
    new QuizHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/quizzes.json")
        .addQuiz(quiz);
  }

}
