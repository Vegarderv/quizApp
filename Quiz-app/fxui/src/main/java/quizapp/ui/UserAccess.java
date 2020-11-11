package quizapp.ui;

import java.util.List;
import quizapp.core.User;

public interface UserAccess {


  /**
   * Method for getting spesific user by username of user.

   * @param name username of wanted user
   * @return returns user of said username
   */
  public User getUser(String name);

  /**
   * Method for updating existing user. Must be used after userdata is changed.
   *   
   * @param user updated user

   */
  public void putUser(User user);

  /**
   * Method for returning all users in database. 
   * 
   * @return a list of all users in database

   */
  public List<User> getUsers();

  /**
   * Method for getting user which is currently active.
   * 
   * @return active User

   */
  public User getActiveUser();

  /**
   * Method for updating active user.
   * 
   
   * 
   * @param name Username of active user
   * 
   */
  public void putActiveUser(String name);

  /**
   * Method for sending new user to database.
   * 

   * @param user new user

   */
  public void postUser(User user); 

}