package quizapp.core;

import java.util.HashMap;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class User {
  /*
   * This class is used for easier access and saving of user data. Also much
   * easier to expand
   */

  private String username;
  private String password;
  private Boolean darkMode = false;
  private Quiz currentQuiz;

  // Keeps control of all quizzes taken
  private HashMap<String, Double> quizzesTaken = new HashMap<>();

  public User() {
  }

  public User(User user) {
    this.username = user.username;
    this.password = user.password;
    this.darkMode = user.darkMode;
    this.currentQuiz = user.currentQuiz;
    this.quizzesTaken = user.quizzesTaken;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public boolean quizTaken(String quiz) {
    return quizzesTaken.containsKey(quiz);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void addQuiz(String quiz, double score) {

    // Add quiz to user when finished with said quiz, or updates score to latest
    // result
    if (quizzesTaken.containsKey(quiz)) {
      quizzesTaken.replace(quiz, score);
    } else {
      quizzesTaken.put(quiz, score);
    }
  }

  public Double meanScore() {
    //Returns mean score of all quizzes taken
    return quizzesTaken.values().stream().mapToDouble(d->d).sum() / quizzesTaken.size();
  }

  public Double getScore(String quiz) {
    return quizzesTaken.get(quiz);
  }

  public String toString() {
    return username + " " + password;
  }

  public Boolean getDarkMode() {
    return darkMode;
  }

  public void setDarkMode(Boolean darkMode) {
    this.darkMode = darkMode;
  }

  /**
   * return the currentQuiz.
   */
  public Quiz getCurrentQuiz() {
    return currentQuiz;
  }

  /**
   * currentQuiz the currentQuiz to set.
   */
  public void setCurrentQuiz(Quiz currentQuiz) {
    this.currentQuiz = currentQuiz;
  }

  public void setQuizzesTaken(HashMap<String, Double> quizzesTaken) {
    this.quizzesTaken = quizzesTaken;
  }

  public HashMap<String, Double> getQuizzesTaken() {
    return new HashMap<String, Double>(quizzesTaken);
  }

  @Override
  public int hashCode() {
  assert false : "hashCode not designed";
  return 42; // any arbitrary constant will do
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return this.getUsername().equals(user.getUsername()) && this.getPassword().equals(user.getPassword());
  }
}