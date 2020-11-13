package quizapp.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

  @GetMapping
  public String health() {
    return "OK";
  }
}