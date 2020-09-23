package quizapp.json;

import org.json.simple.parser.JSONParser;

import quizapp.core.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONHandler {
	private static FileWriter file;
	private String path;
	
	
	public JSONHandler(String path) {
		this.path = path;
	}
    
    //Function writes a hashmap as a JSON object to a JSON file
	public void writeToFile(List<User> users) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String javaObjectString = gson.toJson(users); // converts to json
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
    
    //Function reads a JSON file and returns a hashmap
	public List<User> loadFromFile(){
		JSONParser parser = new JSONParser();
		try {
			FileReader reader = new FileReader(path);
			List<User> user = new Gson().fromJson(reader, new TypeToken<List<User>>(){}.getType());
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
    }

    
    /*
        Method for saving active user to file.
        Use an empty string for reseting the user
        */
    public void saveActiveUser(String username){
        

    }

    public static void main(String[] args) {
        User user1 = new User();
        user1.setUsername("Vegard");
        user1.setPassword("Halla");
        List<User> users = new ArrayList<>();
        users.add(user1);
        JSONHandler handler = new JSONHandler("quizapp.json.JSONHandler.json");
        handler.writeToFile(users);
        List<User> usersLoaded = handler.loadFromFile();
        System.out.println(usersLoaded.get(0).getUsername());
    }

	
}
