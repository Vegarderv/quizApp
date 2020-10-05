package quizapp.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import quizapp.core.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UsernameHandler{
    private static FileWriter file;
    private String path;

    public UsernameHandler(String path){
        this.path = path;
    }
    /*
        Method for saving active user to file.
        Use an empty string for reseting the user
        */
    public void saveActiveUser(String username, String databasePath) throws IllegalArgumentException{
        JSONHandler jsonHandler = new JSONHandler(databasePath);
        List<User> users = jsonHandler.loadFromFile();
        if (!users.stream().map(User::getUsername).collect(Collectors.toList()).contains(username)){
            throw new IllegalArgumentException("username not in database");
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String javaObjectString = gson.toJson(username); // converts to json
		try {

			file = new FileWriter(path);
			file.write(javaObjectString);

			
		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			try {
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    public String loadActiveUser(){
        try {
			FileReader reader = new FileReader(path);
			String user = new Gson().fromJson(reader, new TypeToken<String>(){}.getType());
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}