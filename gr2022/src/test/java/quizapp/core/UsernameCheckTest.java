package quizapp.core;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import quizapp.json.JSONHandler;


public class UsernameCheckTest {
    
    //Declaring variables
	static JSONHandler handler;
	static HashMap<String, String> usernames = new HashMap<>();
    HashMap<String, String> loadedUsernames;
    static UsernameCheck nameCheck;
    
    //Setting up the tests
	@BeforeAll
	public static void setUp(){
		handler = new JSONHandler("src/main/resources/quizapp/json/JSONHandler.json");
        usernames.put("gr2022", "gitlab");
        nameCheck = new UsernameCheck();
        handler.writeToFile(usernames);
	}
    
    //Testing with wrong username and right password
	@Test
	public void wrongUsername() {
		assertFalse(nameCheck.checkUsername("bob", "gitlab"));
    }

    //Testing with right usernamen and wrong password
    @Test
	public void wrongPassword() {
		assertFalse(nameCheck.checkUsername("gr2022", "admin123"));
    }

    //Testing with correct username and password
    @Test
	public void correct() {
		assertTrue(nameCheck.checkUsername("gr2022", "gitlab"));
	}
}
