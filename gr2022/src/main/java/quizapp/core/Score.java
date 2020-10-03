package quizapp.core;

import java.util.List;

import quizapp.json.JSONHandler;
import quizapp.json.UsernameHandler;

public class Score {

    private String jsonPath;
    private String usernamePath;


    public Score(String jsonPath, String usernamePath) {
        this.usernamePath = usernamePath;
        this.jsonPath = jsonPath;
    }
	
	/*
	 This is a scoring class which handles the scores from different quizzes and
	 sends the score to the user, along with the quiz ID
	 
	 Needs to interact with quizapp.ui.HistoryQuizController and quizapp.json.JSONHandler
	 */
	
	public void scoreQuiz(int score, int nQuestions, String quiz){
        //Send Score to user
        UsernameHandler userNameHandler = new UsernameHandler(usernamePath);
        JSONHandler allUSers = new JSONHandler(jsonPath);
        List<User> userList = allUSers.loadFromFile(); 
        String userName = userNameHandler.loadActiveUser();
        User user = userList.stream().filter(p -> p.getUsername().equals(userName)).findAny().get();
        if(user.quizTaken(quiz)){
            return; //stops method if quiz is already taken
        }
        user.addQuiz(quiz, (score * 1.0)/(nQuestions*1.0));
        allUSers.writeToFile(userList); //Writes users back to file
    }

    public String getUsername(){
        return new UsernameHandler(usernamePath).loadActiveUser();
    }

    
	
}
