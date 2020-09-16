package quizapp.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class JSONHandler {
	private static FileWriter file;
	private String path;
	
	
	public JSONHandler(String path) {
		this.path = path;
	}
    
    //Function writes a hashmap as a JSON object to a JSON file
	public void writeToFile(HashMap<String, String> map) {
		JSONObject obj = new JSONObject(map);
		try {

			file = new FileWriter(path);
			file.write(obj.toJSONString());

			
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
	public HashMap<String,String> loadFromFile(){
		
		
		JSONParser parser = new JSONParser();
		try {
			FileReader reader = new FileReader(path);
			Object obj = parser.parse(reader);

			JSONObject jsonObject = (JSONObject) obj;
			HashMap<String, String> yourHashMap = new Gson().fromJson(jsonObject.toString(), HashMap.class);
			return yourHashMap;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	
}
