package quizapp.ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import quizapp.core.User;

public class RemoteUserAccess {

  User user;

  private final URI endpointBaseUri;
  

  public RemoteUserAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
  }

  private User getUser(String name) {
    if (user == null) {
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
          .header("Accept", "api/user/user?name=" + name)
          .GET()
          .build();
      try {
        final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        final String responseString = response.body();
        this.user =  new Gson().fromJson(responseString, new TypeToken<User>(){}.getType());
        System.out.println("User: " + this.user);
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return this.user;
  }

  
}