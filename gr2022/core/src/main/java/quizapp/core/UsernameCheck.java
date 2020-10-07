package quizapp.core;

import java.util.List;
import quizapp.json.JsonHandler;

public class UsernameCheck {
  
  /**
  * Function checks if username and password is valid.
  */
  public boolean checkUsername(String username, String password) {
    JsonHandler handler = new JsonHandler("/workspace/gr2022/gr2022/core/src/main/resources/quizapp/json/JSONHandler.json");
    List<User> userPasswords = handler.loadFromFile();
    if (!userPasswords.stream().anyMatch(a -> a.getUsername().equals(username))) {
      return false;
    } else if (!userPasswords.stream()
        .filter(p -> p.getUsername().equals(username))
        .findFirst().get()
        .getPassword().equals(password)) { //Chekcs if passwords match
      return false;
    }
    return true;

  }
}