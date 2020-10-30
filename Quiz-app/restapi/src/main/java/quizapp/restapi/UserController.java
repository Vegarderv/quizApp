package quizapp.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import quizapp.core.User;
import quizapp.json.JsonHandler;

import java.util.List;


@RestController
public class UserController {


  @GetMapping("/users")
  public List<User> users(){
    return new JsonHandler("/core/src/main/resources/quizapp/json/JSONHandler.json").loadFromFile();
  }
}
