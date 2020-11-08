package quizapp.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import quizapp.core.Quiz;
import java.util.List;

public interface QuizAccess {


  /**
   * Gets quiz by ID.
  
   * @param id id of wanted quiz
   * @return returns Quiz with id: id
   */
  public Quiz getQuiz(String id);

  /**
   * getQuizzes is a method that returns all quizzes.
  
   * @return returns all quizzes in database.
   */
  public List<Quiz> getQuizzes();

  /**
   * Method for sending new Quiz to database.
   
   * @param quiz the new quiz created
   */
  public void postQuiz(Quiz quiz);
}