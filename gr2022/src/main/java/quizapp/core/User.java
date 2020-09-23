package quizapp.core;

import java.util.HashMap;

public class User {
/*
This class is used for easier access and saving of user data. Also much easier to expand
*/

    private String username;
    private String password;

    //Keeps control of all quizzes taken
    private HashMap<String, Double> quizzesTaken = new HashMap<>();

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addQuiz(String quiz, double score){
        //Add quiz to user when finished with said quiz
        quizzesTaken.put(quiz, score);
    }

    public Double meanScore(){
        //Returns mean score of all quizzes taken
        return quizzesTaken.values().stream().reduce(0.0, (a,b) -> a+b)/quizzesTaken.size();
    }


}