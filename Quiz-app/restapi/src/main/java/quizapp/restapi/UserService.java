package quizapp.restapi;

import java.nio.file.Paths;
import java.util.List;
import org.springframework.stereotype.Service;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

@Service
public class UserService {

  private List<User> users;
  private User activeUser;

  private JsonHandler jsonHandler;
  private UsernameHandler usernameHandler;
  private static String pathStarter = "../core/src/main/resources/quizapp/json/";
  private final String jsonPath = Paths.get(pathStarter + "JSONHandler.json").toString();
  private final String activeUserPath = Paths.get(pathStarter + "activeUser.json").toString();

  /**
   * UserService constructor.
   */
  public UserService() {
    jsonHandler = new JsonHandler(this.jsonPath);
    usernameHandler = new UsernameHandler(this.activeUserPath);
    users = jsonHandler.loadFromFile();
    activeUser = jsonHandler.loadActiveUser();
  }


  public List<User> getUsers() {
    users = jsonHandler.loadFromFile();
    return users;
  }

  public User getActiveUser() {
    activeUser = jsonHandler.loadActiveUser();
    return activeUser;
  }

  public void addUser(User user) {
    jsonHandler.addUser(user);
  }

  public void updateUser(User user) {
    jsonHandler.updateUser(user);
  }

  public void updateActiveUser(String name) {
    usernameHandler.saveActiveUser(name, this.jsonPath);
  }


}