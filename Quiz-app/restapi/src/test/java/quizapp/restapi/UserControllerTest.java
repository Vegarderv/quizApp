package quizapp.restapi;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { UserController.class, UserService.class })
@WebMvcTest
public class UserControllerTest {

  private final static String TEST_USER_ID = "user-id-123";

  @Mock
  UserService service = mock(UserService.class);

  @Autowired
  MockMvc mockMvc;

  private User user1 = new User("Test", "Testville");
  private User user2 = new User("Post", "User");
  private JsonHandler jsonHandler = new JsonHandler(
      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json");
  private UsernameHandler usernameHandler = new UsernameHandler(
      "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/activeUser.json");

  // Adds User before each test to ensure independent testing
  @BeforeEach
  public void setUp() {
    user1 = new User("Test", "Testville");
    jsonHandler.addUser(user1);
  }

  @Test
  public void postUser() {
    try {
      testPostUser(user2);
      jsonHandler.loadUserFromString("Post");
    } catch (Exception e) {
      fail("Couldn't post user");
      e.printStackTrace();
    }

    // Removing the user
    List<User> users = jsonHandler.loadFromFile();
    users.remove(users.stream().filter(user -> user.equals(user2)).findAny().orElse(null));
    jsonHandler.writeToFile(users);

  }

  @Test
  public void correctUserIsRetrieved() {
    try {
      testGetUser(user1, "Test");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Couldn't recieve user Test");
    }
  }

  @Test
  public void putChangedTestUser() {
    try {
      testPutUser(user1);
    } catch (Exception e) {
      fail("Could not change User");
    }
  }

  @Test
  public void getAllUsers() {
    try {
      assertEquals(testGetUsers(), jsonHandler.loadFromFile());
    } catch (Exception e) {
      fail("Could not change User");
    }
  }

  @Test
  public void putNewActiveUsername() {
    try {
      testPutUsername(user1.getUsername());
    } catch (Exception e) {
      fail("could not put username");
    }

  }

  @Test
  public void serviceIsCalledAtGetUsers() {

    List<User> list = Arrays.asList(user1);
    when(service.getUsers()).thenReturn(list);
    service.getUsers();
    verify(service).getUsers();
  }


  @Test
  public void serviceIsCalledAtAddUser() {
    service.addUser(user1);
    verify(service).addUser(user1);
  }

  @Test
  public void serviceIsCalledAtUpdateUser() {
    service.updateUser(user1);
    verify(service).updateUser(user1);
  }

  @Test
  public void serviceIsCalledAtUpdateActiveUser() {
    service.updateActiveUser("test");
    verify(service).updateActiveUser("test");
  }

  @Test
  public void serviceIsCalledAtGetActiveUser() {
    service.getActiveUser();
    verify(service).getActiveUser();
  }

  //Removes user created before each test
  @AfterEach
  public void deleteTestUser() {
    List<User> users = jsonHandler.loadFromFile();
    users.remove(users.stream().filter(user -> user.getUsername().equals(user1.getUsername())).findAny().orElse(null));
    jsonHandler.writeToFile(users);
  }

  private void testGetUser(User user, String username) throws Exception {
    Gson gson = new Gson();
    MvcResult result = mockMvc
        .perform(MockMvcRequestBuilders
        .get("/api/user/" + username)
        .with(user(TEST_USER_ID))
        .with(csrf())
        .content(username).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    byte[] resultUserByte = result.getResponse().getContentAsByteArray();
    String resultUser = new String(resultUserByte, StandardCharsets.UTF_8);
    assertNotNull(resultUser);
    assertEquals(user, gson.fromJson(resultUser, new TypeToken<User>(){}.getType()));
  }

  private List<User> testGetUsers() throws Exception {
    Gson gson = new Gson();
    MvcResult result = mockMvc
        .perform(MockMvcRequestBuilders
        .get("/api/user/users")
        .with(user(TEST_USER_ID))
        .with(csrf())
        .content("users").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    byte[] resultUserByte = result.getResponse().getContentAsByteArray();
    String resultUser = new String(resultUserByte, StandardCharsets.UTF_8);
    assertNotNull(resultUser);
    return gson.fromJson(resultUser, new TypeToken<List<User>>(){}.getType());
  }

  private void testPostUser(User user) throws Exception {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    MvcResult result = mockMvc
        .perform(MockMvcRequestBuilders
        .post("/api/user/" + user.getUsername())
        .with(user(TEST_USER_ID))
        .with(csrf())
        .content(gson.toJson(user)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

  }

  private void testPutUser(User user) throws Exception {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    MvcResult result = mockMvc
        .perform(MockMvcRequestBuilders
        .put("/api/user/" + user.getUsername())
        .with(user(TEST_USER_ID))
        .with(csrf())
        .content(gson.toJson(user)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("RESULT:"+ result.toString());

  }
  
  private void testPutUsername(String username) throws Exception {
    MvcResult result = mockMvc
        .perform(MockMvcRequestBuilders
        .put("/api/user/updateActive/" + username)
        .with(user(TEST_USER_ID))
        .with(csrf())
        .content(username).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("RESULT:"+ result.toString());
  }

}