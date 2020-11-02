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
import quizapp.core.User;

public class RemoteUserAccess {

  User user;

  private final URI endpointBaseUri;
  

  public RemoteUserAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
  }

  private URI userUri(String name) {
    return endpointBaseUri.resolve(uriParam(name));
  }

  private String uriParam(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }



  private User getUser(String name) {
    if (user == null) {
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
          .header("Accept", "application/json")
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

  @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
  public void putUser(User user) {
  
    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(user);
      System.out.println(json);
      System.out.println(user.getUsername());
      HttpRequest request = HttpRequest.newBuilder(userUri(user.getUsername()))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .PUT(BodyPublishers.ofString(json))
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