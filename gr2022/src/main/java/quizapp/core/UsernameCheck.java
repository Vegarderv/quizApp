package quizapp.core;

import java.util.HashMap;

import quizapp.json.JSONHandler;
public class UsernameCheck {

    //Function checks if username and password is valid.
	public boolean checkUsername(String username, String password) {
		JSONHandler handler = new JSONHandler("src/main/resources/quizapp/json/JSONHandler.json");
		HashMap<String, String> userPasswords = handler.loadFromFile();
		if (!userPasswords.containsKey(username)) {
			return false;
		} else if (!userPasswords.get(username).equals(password)){
			return false;
		}
		return true;
	}
}