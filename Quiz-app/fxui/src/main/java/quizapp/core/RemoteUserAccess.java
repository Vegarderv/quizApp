package quizapp.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.lang.InterruptedException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import quizapp.json.CryptoUtil;

public class RemoteUserAccess implements UserAccess {

  User user;

  private final URI endpointBaseUri;
  private String secretKey = "ssshhhhhhhhhhh!!!!";
  private CryptoUtil cryptoUtil = new CryptoUtil();

  /**
   * RemoteUserAccess constructor.
   * 

   * @param endpointBaseUri the param
   * @throws IOException throws an exception if necessary
   */
  public RemoteUserAccess(URI endpointBaseUri) throws IOException {
    // Checks if server is running

    try {
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
          .header("Accept", "application/json").GET().build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      final String responseString = response.body();
      System.out.println(responseString);
    } catch ( IOException | InterruptedException e) {
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
        HttpRequest request = HttpRequest.newBuilder(userUri(name))
            .header("Accept", "application/json").GET().build();
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
    this.user.setPassword(cryptoUtil.decrypt(user.getPassword(), secretKey));
    return this.user;
  }

  /**
   * Puts user.
   * 

   * @param newUser the user we want to put
   */
  @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
  public void putUser(User newUser) {
    User user = new User(newUser);
    user.setPassword(cryptoUtil.encrypt(user.getPassword(), secretKey));
    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(user);
      HttpRequest request = HttpRequest.newBuilder(
          userUri(user.getUsername())).header("Accept", "application/json")
          .header("Content-Type", "application/json").PUT(BodyPublishers
          .ofString(json)).build();
      System.out.println(request);
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
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
  public Collection<User> getUsers() {
    Collection<User> users = new ArrayList<>();
    try {
      if (user == null) {
        HttpRequest request = HttpRequest
            .newBuilder(userUri("users"))
            .header("Accept", "application/json").GET()
            .build();
        System.out.println(request);
        try {
          final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
              HttpResponse.BodyHandlers.ofString());
          final String responseString = response.body();
          System.out.println(responseString);
          users = new Gson().fromJson(responseString, new TypeToken<Collection<User>>() {
          }.getType());
          System.out.println("Users: " + users);
        } catch (IOException | InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    users.stream().forEach(user -> user.setPassword(
        cryptoUtil.decrypt(user.getPassword(), secretKey)));
    return users;
  }

  /**
   * Get active user.
   */
  public User getActiveUser() {
    try {
      if (user == null) {
        HttpRequest request = HttpRequest.newBuilder(
            userUri("active")).header("Accept", "application/json")
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
    this.user.setPassword(cryptoUtil.decrypt(user.getPassword(), secretKey));
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
      HttpRequest request = HttpRequest
          .newBuilder(userUri(name))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .PUT(BodyPublishers.ofString(json)).build();
      System.out.println(request);
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
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

   * @param newUser the user we want to post
   */
  @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
  public void postUser(User newUser) {
    User user = new User(newUser);
    user.setPassword(cryptoUtil.encrypt(user.getPassword(), secretKey));

    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(user);
      System.out.println(user.getUsername());
      HttpRequest request = HttpRequest.newBuilder(
          userUri(user.getUsername())).header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(json)).build();
      System.out.println(request);
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      String responseString = response.body();
      System.out.println(responseString);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}