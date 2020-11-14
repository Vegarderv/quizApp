package quizapp.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.lang.InterruptedException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RemoteQuizAccess implements QuizAccess {

  Quiz quiz;

  private final URI endpointBaseUri;

  /**
   * Checks if server is running.
   * 

   * @param endpointBaseUri takes this parameter
   * @throws IOException throws exception if necessary
   */
  public RemoteQuizAccess(URI endpointBaseUri) throws IOException {
    // Checks if server is running
    try {
      HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080").resolve(""))
          .header("Accept", "application/json").GET().build();
      System.out.println(request);
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      final String responseString = response.body();
      if (!responseString.equals("OK")) {
        throw new IOException("Server Not Running");
      }
    } catch (URISyntaxException | IOException | InterruptedException e) {
      throw new IOException("Server Not Running");
    }

    this.endpointBaseUri = endpointBaseUri;
  }

  private URI quizUri(String name) {
    return endpointBaseUri.resolve(uriParam(name));
  }

  private String uriParam(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

  /**
   * Returns a quiz based in an ID.
   * 

   * @param id the id the quiz is identified by
   */
  public Quiz getQuiz(String id) {
    System.out.println("remote: " + id);
    try {
      if (quiz == null) {
        HttpRequest request = HttpRequest
            .newBuilder(quizUri(id))
            .header("Accept", "application/json")
            .GET().build();
        System.out.println(request);
        try {
          final HttpResponse<String> response = 
              HttpClient.newBuilder().build().send(request,
              HttpResponse.BodyHandlers.ofString());
          final String responseString = response.body();
          System.out.println(responseString);
          this.quiz = new Gson().fromJson(responseString, new TypeToken<Quiz>() {
          }.getType());
          System.out.println("Quiz: " + this.quiz);
        } catch (IOException | InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return this.quiz;
  }

  /**
   * Returns a list of quizzes.
   */
  public List<Quiz> getQuizzes() {
    List<Quiz> quizzes = null;
    try {
      if (quiz == null) {
        HttpRequest request = HttpRequest
            .newBuilder(quizUri("quizzes"))
            .header("Accept", "application/json").GET()
            .build();
        System.out.println(request);
        try {
          final HttpResponse<String> response = 
              HttpClient.newBuilder().build().send(request,
              HttpResponse.BodyHandlers.ofString());
          final String responseString = response.body();
          quizzes = new Gson().fromJson(responseString, new TypeToken<List<Quiz>>() {
          }.getType());
          System.out.println("Quizzes: " + quizzes);
        } catch (IOException | InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return quizzes;
  }

  /**
   * post quiz.
   * 

   * @param quiz the quiz that is getting posted
   */
  @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
  public void postQuiz(Quiz quiz) {

    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(quiz);
      System.out.println(json);
      HttpRequest request = HttpRequest
          .newBuilder(quizUri(quiz.getId()))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(json)).build();
      System.out.println(request);
      final HttpResponse<String> response = 
          HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      String responseString = response.body();
      System.out.println(responseString);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}