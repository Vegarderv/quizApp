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
	
	public void writeToFile(HashMap<String, String> map) {
		JSONObject obj = new JSONObject(map);
		try {

			// Constructs a FileWriter given a file name, using the platform's default charset
			file = new FileWriter(path);
			file.write(obj.toJSONString());

			//TODO: Create log
			//QuizLog("Successfully Copied JSON Object to File...");
			//QuizLog("\nJSON Object: " + obj);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			try {
				file.flush();
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public HashMap<String,String> loadFromFile(){
		
		
		JSONParser parser = new JSONParser();
		try {
			FileReader reader = new FileReader(path);
			Object obj = parser.parse(reader);

			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;
			HashMap<String, String> yourHashMap = new Gson().fromJson(jsonObject.toString(), HashMap.class);
			return yourHashMap;

			// A JSON array. JSONObject supports java.util.List interface.
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	
}
