package quizapp.ui;

import java.nio.file.Paths;
import java.util.List;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

public class DirectUserAccess implements UserAccess{

  private final static String pathStarter = "../core/src/main/resources/quizapp/json/";
  private final String jsonPath = Paths.get(pathStarter + "JSONHandler.json").toString();
  private JsonHandler jsonHandler = new JsonHandler(this.jsonPath);
  private final String activeUserPath = Paths.get(pathStarter + "activeUser.json").toString();
  private UsernameHandler userHandler = new UsernameHandler(this.activeUserPath);



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
    userHandler.saveActiveUser(name, this.jsonPath);
  }

  @Override
  public void postUser(User user) {
    jsonHandler.addUser(user);
  }
}
