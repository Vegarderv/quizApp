package quizapp.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import quizapp.core.Quiz;

public class QuizHandler {
  private String path;
  private Writer file;

  public QuizHandler(String path) {
    this.path = path;
  }

  /**
   * Function writes a hashmap as a JSON object to a JSON file.
   */
  public void writeToFile(Collection<Quiz> quizList) {
    Collection<Quiz> quizzes = new ArrayList<Quiz>(quizList);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String javaObjectString = gson.toJson(quizzes); // converts to json
    try {

      FileOutputStream fileStream = new FileOutputStream(path);
      file = new OutputStreamWriter(fileStream, "UTF-8");
      file.write(javaObjectString);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {

      try {
        file.flush();
        file.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Function reads a JSON file and returns a list of quizzes.
   */
  public Collection<Quiz> loadFromFile() {
    try {
      InputStream inputStream = new FileInputStream(path);
      Reader fileReader = new InputStreamReader(inputStream, "UTF-8");
      Collection<Quiz> quizzes = new Gson().fromJson(fileReader, new TypeToken<Collection<Quiz>>() {
      }.getType());
      return quizzes;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  /**
   * Method for getting Quiz by id.
   * 

   * @param id id of the quiz you want
   * @return returns Quiz with id
   */
  public Quiz getQuizById(String id) {
    String name = id.replace("-", " ");
    Collection<Quiz> quizzes = this.loadFromFile();
    return quizzes.stream().filter(q -> q.getName().equals(name)).findFirst().orElse(null);
  }

  /**
   * Adds quiz to file.
   * 

   * @param quiz the quiz object
   */
  public void addQuiz(Quiz quiz) {
    Collection<Quiz> quizzes = loadFromFile();
    quizzes.add(new Quiz(quiz));
    writeToFile(quizzes);
  }

  /**
   * Deletes quiz from file.
   * 

   * @param quizId quiz we want to delete
   */
  public void deleteQuiz(String quizId) {
    Collection<Quiz> quizzes = loadFromFile();
    quizzes = quizzes.stream().filter(q -> !q.getId().equals(quizId)).collect(Collectors.toList());
    writeToFile(quizzes);
  }

}