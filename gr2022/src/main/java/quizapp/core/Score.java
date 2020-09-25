package quizapp.core;

import java.util.List;

import quizapp.json.JSONHandler;
import quizapp.json.UsernameHandler;

public class Score {
	/*
	 This is a scoring class which handles the scores from different quizzes and
	 sends the score to the user, along with the quiz ID
	 
	 Needs to interact with quizapp.ui.HistoryQuizController and quizapp.json.JSONHandler
	 */
	
	public void scoreQuiz(int score, int nQuestions, String quiz){
        //Send Score to user
        UsernameHandler userNameHandler = new UsernameHandler("src/main/resources/quizapp/json/activeUser.json");
        JSONHandler allUSers = new JSONHandler("src/main/resources/quizapp/json/JSONHandler.json");
        List<User> userList = allUSers.loadFromFile(); 
        String userName = userNameHandler.loadActiveUser();
        User user = userList.stream().filter(p -> p.getUsername().equals(userName)).findAny().get();
        if(user.quizTaken(quiz)){
            return; //stops method if quiz is already taken
        }
        user.addQuiz(quiz, (score * 1.0)/(nQuestions*1.0));
        allUSers.writeToFile(userList); //Writes users back to file
        
    }
	
	
}
