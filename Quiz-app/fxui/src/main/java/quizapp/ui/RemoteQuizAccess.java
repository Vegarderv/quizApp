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

public class RemoteQuizAccess {

  Quiz quiz;

  private final URI endpointBaseUri;

  public RemoteQuizAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
  }

  private URI quizUri(String name) {
    return endpointBaseUri.resolve(uriParam(name));
  }

  private String uriParam(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }
  
  public Quiz getQuiz(String id) {
    try {
      if (quiz == null) {
      HttpRequest request = HttpRequest.newBuilder(quizUri(id))
          .header("Accept", "application/json")
          .GET()
          .build();
      System.out.println(request);
      try {
        final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        final String responseString = response.body();
        this.quiz =  new Gson().fromJson(responseString, new TypeToken<Quiz>(){}.getType());
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

  public List<Quiz> getQuizzes() {
    List<Quiz> quizzes = null;
    try {
      if (quiz == null) {
      HttpRequest request = HttpRequest.newBuilder(quizUri("quizzes"))
          .header("Accept", "application/json")
          .GET()
          .build();
      System.out.println(request);
      try {
        final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        final String responseString = response.body();
        quizzes =  new Gson().fromJson(responseString, new TypeToken<List<Quiz>>(){}.getType());
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

  @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
  public void postQuiz(Quiz quiz) {
  
    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(quiz);
      System.out.println(json);
      HttpRequest request = HttpRequest.newBuilder(quizUri(quiz.getId()))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(json))
          .build();
      System.out.println(request);
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      
      String responseString = response.body();
      System.out.println(responseString);
      //Boolean added = new Gson().fromJson(responseString, new TypeToken<Boolean>(){}.getType());
      //if (added != null) {
      //  System.out.println("Was not added, sad");
      //}
  
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}