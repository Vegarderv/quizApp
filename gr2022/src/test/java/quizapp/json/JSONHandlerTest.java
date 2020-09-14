package quizapp.json;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class JSONHandlerTest {
	
	static JSONHandler handler;
	static HashMap<String, String> usernames = new HashMap<>();
	HashMap<String, String> loadedUsernames;
	
	@BeforeAll
	public static void setUp(){
		handler = new JSONHandler("src/main/resources/quizapp/json/JSONHandler.json");
		usernames.put("gr2022", "gitlab");
	}
	
	@Test
	public void saveAndLoad() {
		handler.writeToFile(usernames);
		loadedUsernames = handler.loadFromFile();
		assertEquals(usernames, loadedUsernames);
	}
}
