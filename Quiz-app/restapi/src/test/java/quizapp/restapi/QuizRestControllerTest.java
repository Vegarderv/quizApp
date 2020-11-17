package quizapp.restapi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quizapp.core.Question;
import quizapp.core.Quiz;
import quizapp.core.User;
import quizapp.json.QuizHandler;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { QuizRestController.class, QuizService.class })
@WebMvcTest
public class QuizRestControllerTest {
  @Autowired
  MockMvc mockMvc;

  private final static String TEST_QUIZ_ID = "quiz-id-123";
  private final static String pathStarter = "../core/src/main/resources/quizapp/json/";
  private final String quizPath = Paths.get(pathStarter + "quizzes.json").toString();
  private QuizHandler quizHandler = new QuizHandler(this.quizPath);

  private Quiz quiz = new Quiz();

  @BeforeEach
  public void setUp() {
    quiz.setId("Test-Quiz");
    quiz.setName("Test Quiz");
    Question q1 = new Question("q1", "a1", "a2", "a3", "a4", 3);
    Question q2 = new Question("q2", "a1", "a2", "a3", "a4", 3);
    Question q3 = new Question("q3", "a1", "a2", "a3", "a4", 3);
    List<Question> questions = new ArrayList<>();
    questions.add(q1);
    questions.add(q2);
    questions.add(q3);
    quiz.setQuestions(questions);
    quizHandler.addQuiz(quiz);
    
  }

  @Test
  public void postQuiz() {
    try {
      testPostQuiz(quiz);
    } catch (Exception e) {
      e.printStackTrace();
      fail("could not post quiz");
    }

    //removes quiz
    Collection<Quiz> quizzes = quizHandler.loadFromFile();
    quizzes.remove(quizzes.stream().filter(quiz -> quiz.getId().equals(this.quiz.getId())).findAny().orElse(null));
    quizHandler.writeToFile(quizzes);
  }

  @Test
  public void getQuizzes() {
    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      //Testing with JsonStrings as assertEquals would not accept two identical objects
      assertEquals(gson.toJson(testGetQuizzes()), gson.toJson(quizHandler.loadFromFile()));
    } catch (Exception e) {
      fail("Could not load all quizzes");
      e.printStackTrace();
    }
  }

  @Test
  public void getQuiz() {
    try {
      testGetQuiz(quiz);
    } catch (Exception e) {
      fail("Could not get quiz: quiz");
      e.printStackTrace();
    }
  }
 
  @AfterEach
  public void after() {
    Collection<Quiz> quizzes = quizHandler.loadFromFile();
    quizzes.remove(quizzes.stream().filter(quiz -> quiz.getId().equals(this.quiz.getId())).findAny().orElse(null));
    quizHandler.writeToFile(quizzes);
  }


  private void testPostQuiz(Quiz quiz) throws Exception {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    MvcResult result = mockMvc
        .perform(MockMvcRequestBuilders.post("/api/quiz/" + quiz.getId())
        .with(user(TEST_QUIZ_ID))
        .with(csrf())
        .content(gson.toJson(quiz))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println(result);
  }

  private List<User> testGetQuizzes() throws Exception {
    Gson gson = new Gson();
    MvcResult result = mockMvc
        .perform(MockMvcRequestBuilders.get("/api/quiz").with(user(TEST_QUIZ_ID)).with(csrf()).content("")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    byte[] resultQuizByte = result.getResponse().getContentAsByteArray();
    String resultQuiz = new String(resultQuizByte, StandardCharsets.UTF_8);
    assertNotNull(resultQuiz);
    return gson.fromJson(resultQuiz, new TypeToken<List<Quiz>>() {
    }.getType());
  }

  private void testGetQuiz(Quiz quiz) throws Exception {
    Gson gson = new Gson();
    MvcResult result = mockMvc
        .perform(MockMvcRequestBuilders.get("/api/quiz/" + quiz.getId()).with(user(TEST_QUIZ_ID)).with(csrf())
            .content(quiz.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    byte[] resultQuizByte = result.getResponse().getContentAsByteArray();
    String resultQuiz = new String(resultQuizByte, StandardCharsets.UTF_8);
    assertNotNull(resultQuiz);
    System.out.println(resultQuiz);
    assertEquals(quiz.getId() , ((Quiz) gson.fromJson(resultQuiz, new TypeToken<Quiz>() {
    }.getType())).getId());
  }
}