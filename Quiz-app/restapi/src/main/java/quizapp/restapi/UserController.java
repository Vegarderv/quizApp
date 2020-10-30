package quizapp.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import quizapp.core.User;
import quizapp.json.JsonHandler;

import java.util.List;

@RestController
public class UserController {

  @GetMapping("/users")
  public List<User> users() {
    return new JsonHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json")
        .loadFromFile();
  }

  @GetMapping("/user")
  public User getUser(@RequestParam String name) {
    return new JsonHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json")
        .loadUserFromString(name);
  }

  @PutMapping("/update")
  public void updateUser(@RequestBody User user) {
    new JsonHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json")
        .updateUser(user);
  }

}
