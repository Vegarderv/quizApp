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
	
	public void scoreQuiz(int score, int nQuestions){
        UsernameHandler userName = new UsernameHandler("src/main/java/resources/quizapp/json/activeUser.json");
        JSONHandler allUSers = new JSONHandler("src/main/java/resources/quizapp/json/JSONHandler.json");
        List<User> userList = allUSers.loadFromFile(); 
        User user = userList.stream().filter(p -> p.getUsername().equals(userName.loadActiveUser())).findAny().get();
        user.addQuiz(this.toString(), (score * 1.0)/(nQuestions*1.0));
        allUSers.writeToFile(userList);
        
    }
	
	
}
