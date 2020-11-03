package quizapp.core;

import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

import java.util.List;

/**
* This is a scoring class which handles the scores from different quizzes and
 sends the score to the user, along with the quiz ID.
 
 Needs to interact with quizapp.ui.HistoryQuizController and quizapp.json.JSONHandler.
*/
public class Score {

  private String jsonPath;
  private String usernamePath;


  public Score(String jsonPath, String usernamePath) {
    this.usernamePath = usernamePath;
    this.jsonPath = jsonPath;
  }
  
  /**
  * Send Score to user.
  */
  public void scoreQuiz(int score, int n, String quiz) {
    UsernameHandler userNameHandler = new UsernameHandler(usernamePath);
    JsonHandler allUsers = new JsonHandler(jsonPath);
    List<User> userList = allUsers.loadFromFile();
    String userName = userNameHandler.loadActiveUser();
    User user = userList.stream().filter(p -> p.getUsername().equals(userName)).findAny().get();
    if (user.quizTaken(quiz)) {
      return; //stops method if quiz is already taken
    }
    user.addQuiz(quiz, (score * 1.0) / (n * 1.0));
    allUsers.writeToFile(userList); //Writes users back to file
  }


}
