package quizapp.core;


import java.util.HashMap;

public class User {
  /*
  This class is used for easier access and saving of user data. Also much easier to expand
  */

  private String username;
  private String password;
  private Boolean darkMode = false;
  private Quiz currentQuiz;

  //Keeps control of all quizzes taken
  private HashMap<String, Double> quizzesTaken = new HashMap<>();


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
    
    //Add quiz to user when finished with said quiz, or updates score to latest result
    if (quizzesTaken.containsKey(quiz)) {
      quizzesTaken.replace(quiz, score);
    }
    else {
      quizzesTaken.put(quiz, score);
    }
  }

  public double meanScore() {
    //Returns mean score of all quizzes taken
    if (quizzesTaken.size() == 0) {
      return 0;
    }
    return quizzesTaken.values().stream().reduce(0.0, (a, b) -> a + b) / quizzesTaken.size();
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
  
  public HashMap<String, Double> getQuizzesTaken(){
    return this.quizzesTaken;
  }
}