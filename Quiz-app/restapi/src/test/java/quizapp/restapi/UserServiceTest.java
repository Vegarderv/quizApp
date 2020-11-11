package quizapp.restapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import quizapp.json.JsonHandler;
import quizapp.core.User;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;


/**
 * All methods returns directly from JsonHandler, not a lot of testing is
 * needed, just some tests that verify that the paths are correct.
 */
public class UserServiceTest {
  @Mock
  UserService service = mock(UserService.class);
  private String jsonPath = "/workspace/gr2022/Quiz-app/core/src/main/resources/quizapp/json/JSONHandler.json";
  JsonHandler jsonHandler = new JsonHandler(jsonPath);
  User user1 = new User("Test", "Testville");
    



  @Test
  public void serviceIsCalledAtGetUsers() {

    List<User> list = Arrays.asList(user1);
    when(service.getUsers()).thenReturn(list);
    service.getUsers();
    
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

}