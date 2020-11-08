package quizapp.core;

import quizapp.json.JsonHandler;
import java.net.URI;
import java.util.List;
import quizapp.ui.RemoteUserAccess;
import quizapp.ui.UserAccess;
import quizapp.ui.DirectUserAccess;

public class UsernameCheck {

  private UserAccess remoteUserAccess;
  /**
   * Function checks if username and password is valid.
   */
  public boolean checkUsername(String username, String password) {
    try {
        remoteUserAccess = new RemoteUserAccess(new URI("http://localhost:8080/api/user/"));
    } catch (Exception e) {
      remoteUserAccess = new DirectUserAccess();
    }
    List<User> userPasswords = remoteUserAccess.getUsers();
    if (!userPasswords.stream().anyMatch(a -> a.getUsername().equals(username))) {
      System.out.println("Can't find user");
      return false;
    } else if (!userPasswords.stream().filter(p -> p.getUsername().equals(username))
        .findFirst().get().getPassword().equals(password)) { // Chekcs if passwords match
      return false;
    }
    return true;

  }
}