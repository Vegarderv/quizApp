package quizapp.core;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RemoteUserAccessTest {

  private WireMockConfiguration config;
  private WireMockServer wireMockServer;

  private RemoteUserAccess userAccess;

  @BeforeEach
  public void startWireMockServerAndSetup() throws URISyntaxException, IOException {
    config = WireMockConfiguration.wireMockConfig().port(8089);
    wireMockServer = new WireMockServer(config.portNumber());
    wireMockServer.start();
    WireMock.configureFor("localhost", config.portNumber());
    try {
      userAccess = new RemoteUserAccess(new URI("http://localhost:" + wireMockServer.port() + "/api/user/"));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  public void testGetUsernames() {
    User user = new User("user1", "fRiAyB68D83OvcnbsoKIeA\u003d\u003d");
    User user2 = new User("user3", "fRiAyB68D83OvcnbsoKIeA\u003d\u003d");
    Collection<User> col = new ArrayList<>();
    col.add(user);
    col.add(user2);
    stubFor(get(urlEqualTo("/api/user/users"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(new Gson().toJson(col))));
    Collection<User> names = userAccess.getUsers();
    System.out.println(names);
    assertEquals(2, names.size());
    assertTrue(names.stream().map(u -> u.getUsername()).collect(Collectors.toList()).containsAll(List.of("user1", "user3")));
  }

  @AfterEach
  public void stopWireMockServer() {
    wireMockServer.stop();
  }
}
