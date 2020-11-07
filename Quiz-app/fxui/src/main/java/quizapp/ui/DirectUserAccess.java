package quizapp.ui;

import java.util.List;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

public class DirectUserAccess implements UserAccess{

  private JsonHandler jsonHandler = new JsonHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
  private UsernameHandler userHandler = new UsernameHandler("/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");



  @Override
  public User getUser(String name) {
    return jsonHandler.loadUserFromString(name);
  }

  @Override
  public void putUser(User user) {
    jsonHandler.updateUser(user);
  }

  @Override
  public List<User> getUsers() {
    return jsonHandler.loadFromFile();
  }

  @Override
  public User getActiveUser() {
    return jsonHandler.loadActiveUser();
  }

  @Override
  public void putActiveUser(String name) {
    userHandler.saveActiveUser(name, "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
  }

  @Override
  public void postUser(User user) {
    jsonHandler.addUser(user);
  }
}
