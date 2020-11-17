package quizapp.restapi;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quizapp.core.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public Collection<User> getUsers() {
    return userService.getUsers();
  }

  @GetMapping("/users")
  public Collection<User> users() {
    return getUsers();
  }

  @GetMapping("/{name}")
  public User getUser(@PathVariable("name") String name) {
    return getUsers().stream().filter(u -> u.getUsername().equals(name)).findFirst().orElse(null);
  }

  @PutMapping("/{name}")
  public void updateUser(@PathVariable("name") String name, @RequestBody User user) {
    userService.updateUser(user);
  }



  @PostMapping("/{name}")
  public void newUser(@PathVariable("name") String name, @RequestBody User user) {
    userService.addUser(user);
  }

  @GetMapping("/active")
  public User getActiveUser() {
    return userService.getActiveUser();
  }

  @PutMapping("/updateActive/{name1}")
  public void updateUsername(@PathVariable("name1") String name1) {
    userService.updateActiveUser(name1);
  }

}
