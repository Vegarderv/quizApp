package quizapp.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import quizapp.core.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

public class UsernameHandler{
    private static Writer file;
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

			FileOutputStream fileStream = new FileOutputStream(path);
            file = new OutputStreamWriter(fileStream, "UTF-8");
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
			InputStream inputStream = new FileInputStream(path);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
			String user = new Gson().fromJson(reader, new TypeToken<String>(){}.getType());
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}