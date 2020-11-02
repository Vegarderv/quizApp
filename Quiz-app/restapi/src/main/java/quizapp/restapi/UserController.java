package quizapp.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import quizapp.core.User;
import quizapp.json.JsonHandler;
import quizapp.json.UsernameHandler;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @GetMapping("/users")
  public List<User> users() {
    return getUsers();
  }

  @GetMapping("/user")
  public User getUser(@RequestParam String name) {
    return getUsers().stream().filter(u -> u.getUsername().equals(name)).findFirst().orElse(null);
  }

  @PutMapping("/update/{name}")
  public void updateUser(@PathVariable("name") String name, @RequestBody User user) {
    userService.updateUser(user);
  }



  @PostMapping("/new")
  public void newUser(@RequestBody User user) {
    userService.addUser(user);
  }

  @GetMapping("/active")
  public User getActiveUser(){
    return userService.getActiveUser();
  }

  @PutMapping("/updateActive")
  public void updateUsername(String name) {
    userService.updateActiveUser(name);
  }

}
