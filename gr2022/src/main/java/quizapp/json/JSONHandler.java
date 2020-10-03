package quizapp.json;


import quizapp.core.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    
    //Function reads a JSON file and returns a list of users
	public List<User> loadFromFile(){
		try {
            FileReader reader = new FileReader(path);
			List<User> user = new Gson().fromJson(reader, new TypeToken<List<User>>(){}.getType());
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
    }

    
    

	
}
