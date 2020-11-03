package quizapp.core;

import quizapp.json.JsonHandler;
import java.net.URI;
import java.util.List;
import quizapp.ui.RemoteUserAccess;

public class UsernameCheck {

  RemoteUserAccess remoteUserAccess;
  /**
   * Function checks if username and password is valid.
   */
  public boolean checkUsername(String username, String password) {
    remoteUserAccess = new RemoteUserAccess(new URI("http://localhost:8080/api/user/"));
    List<User> userPasswords = remoteUserAcess.getUsers();
    if (!userPasswords.stream().anyMatch(a -> a.getUsername().equals(username))) {
      return false;
    } else if (!userPasswords.stream().filter(p -> p.getUsername().equals(username))
        .findFirst().get().getPassword().equals(password)) { // Chekcs if passwords match
      return false;
    }
    return true;

  }
}