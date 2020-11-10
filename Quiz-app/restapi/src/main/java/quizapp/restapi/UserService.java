package quizapp.restapi;

import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserService{

  private List<User> users;
  private User activeUser;

  private JsonHandler jsonHandler;
  private UsernameHandler usernameHandler;
  private final String pathStarter = "../core/src/main/resources/quizapp/json/";
  private final String jsonPath = Paths.get(pathStarter + "JSONHandler.json").toString();
  private final String activeUserPath = Paths.get(pathStarter + "activeUser.json").toString();

  public UserService(){
    jsonHandler = new JsonHandler(this.jsonPath);
    usernameHandler = new UsernameHandler(this.activeUserPath);
    users = jsonHandler.loadFromFile();
    activeUser = jsonHandler.loadActiveUser();
  }


  public List<User> getUsers() {
    users = jsonHandler.loadFromFile();
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public User getActiveUser() {
    activeUser = jsonHandler.loadActiveUser();
    return activeUser;
  }

  //public void setActiveUser(User activeUser) {
  //  this.activeUser = activeUser;
  //}

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