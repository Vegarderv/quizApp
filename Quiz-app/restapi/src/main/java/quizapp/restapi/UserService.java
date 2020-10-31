package quizapp.restapi;

import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserService{

  private List<User> users;
  private User activeUser;

  private JsonHandler jsonHandler;
  private UsernameHandler usernameHandler;

  public UserService(){
    jsonHandler = new JsonHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
    usernameHandler = new UsernameHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");
    users = jsonHandler.loadFromFile();
    activeUser = jsonHandler.loadActiveUser();
  }


  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public User getActiveUser() {
    return activeUser;
  }

  public void setActiveUser(User activeUser) {
    this.activeUser = activeUser;
  }

  public void addUser(User user) {
    jsonHandler.addUser(user);
  }

  public void updateUser(User user) {
    jsonHandler.updateUser(user);
  }

  public void updateActiveUser(String name) {
    usernameHandler.saveActiveUser(name, "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
  }


}