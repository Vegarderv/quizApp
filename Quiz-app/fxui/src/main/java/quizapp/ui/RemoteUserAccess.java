package quizapp.ui;

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
import quizapp.core.User;

public class RemoteUserAccess implements UserAccess {

  User user;

  private final URI endpointBaseUri;

  /**
   * RemoteUserAccess constructor.
   * 

   * @param endpointBaseUri the param
   * @throws IOException throws an exception if necessary
   */
  public RemoteUserAccess(URI endpointBaseUri) throws IOException {
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
      System.out.println(e);
      throw new IOException("Server Not Running");
    }

    this.endpointBaseUri = endpointBaseUri;
  }

  private URI userUri(String name) {
    return endpointBaseUri.resolve(uriParam(name));
  }

  private String uriParam(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

  /**
   * Returns a user based on the name.
   * 

   * @param name the name of the user we want to find
   */
  public User getUser(String name) {
    try {
      if (user == null) {
        HttpRequest request = HttpRequest
            .newBuilder(userUri(name))
            .header("Accept", "application/json")
            .GET().build();
        System.out.println(request);
        try {
          final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
              HttpResponse.BodyHandlers.ofString());
          final String responseString = response.body();
          this.user = new Gson().fromJson(responseString, new TypeToken<User>() {
          }.getType());
          System.out.println("User: " + this.user);
        } catch (IOException | InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return this.user;
  }

  /**
   * puts user.
   * 

   * @param user the user we want to put
   */
  @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
  public void putUser(User user) {
    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(user);
      System.out.println(json);
      System.out.println(user.getUsername());
      HttpRequest request = HttpRequest
          .newBuilder(userUri(user.getUsername()))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .PUT(BodyPublishers.ofString(json)).build();
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

  /**
   * Returns a list of the users.
   */
  public List<User> getUsers() {
    List<User> users = null;
    try {
      if (user == null) {
        HttpRequest request = HttpRequest
            .newBuilder(userUri("users"))
            .header("Accept", "application/json")
            .GET()
            .build();
        System.out.println(request);
        try {
          final HttpResponse<String> response = 
              HttpClient.newBuilder().build().send(request,
              HttpResponse.BodyHandlers.ofString());
          final String responseString = response.body();
          users = new Gson().fromJson(responseString, new TypeToken<List<User>>() {
          }.getType());
          System.out.println("Users: " + users);
        } catch (IOException | InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return users;
  }

  /**
   * Get active user.
   */
  public User getActiveUser() {
    try {
      if (user == null) {
        HttpRequest request = HttpRequest
            .newBuilder(userUri("active"))
            .header("Accept", "application/json")
            .GET()
            .build();
        System.out.println(request);
        try {
          final HttpResponse<String> response = 
              HttpClient.newBuilder().build().send(request,
              HttpResponse.BodyHandlers.ofString());
          final String responseString = response.body();
          this.user = new Gson().fromJson(responseString, new TypeToken<User>() {
          }.getType());
          System.out.println("User: " + this.user);
        } catch (IOException | InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this.user;
  }

  /**
   * Put active user.
   * 

   * @param name name of the user we want to be active
   */
  @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
  public void putActiveUser(String name) {

    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(name);
      System.out.println(json);
      HttpRequest request = HttpRequest
          .newBuilder(userUri(name))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .PUT(BodyPublishers.ofString(json)).build();
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

  /**
   * Post user.
   * 

   * @param user the user we want to post
   */
  @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
  public void postUser(User user) {

    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(user);
      System.out.println(json);
      System.out.println(user.getUsername());
      HttpRequest request = HttpRequest
          .newBuilder(userUri(user.getUsername()))
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