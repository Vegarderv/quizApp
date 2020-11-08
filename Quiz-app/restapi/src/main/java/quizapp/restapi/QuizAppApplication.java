package quizapp.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import quizapp.core.User;


@SpringBootApplication
public class QuizAppApplication {

  @Bean
  public User objectMapperModule() {
    return new User();
  }

  public static void main(String[] args) {
    SpringApplication.run(QuizAppApplication.class, args);
  }
}
