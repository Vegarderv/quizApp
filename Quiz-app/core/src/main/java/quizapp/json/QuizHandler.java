package quizapp.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import quizapp.core.Quiz;

import java.io.*;
import java.util.List;

public class QuizHandler {
  private String path;
  private Writer file;


  public QuizHandler(String path) {
    this.path = path;
  }

  /**
  * Function writes a hashmap as a JSON object to a JSON file.
  */
  public void writeToFile(List<Quiz> quizzes) {
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
        System.out.println(e);
      }
    }
  }

  /**
  * Function reads a JSON file and returns a list of quizzes.
  */
  public List<Quiz> loadFromFile() {
    try {
      InputStream inputStream = new FileInputStream(path);
      Reader fileReader = new InputStreamReader(inputStream, "UTF-8");
      List<Quiz> quizzes = new Gson().fromJson(fileReader, new TypeToken<List<Quiz>>() {
      }.getType());
      return quizzes;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  /**
   * Method for getting Quiz by name.

   * @param name name of the quiz you want
   * @return returns Quiz with name
   */
  public Quiz getQuizById(String name) {
    List<Quiz> quizzes = this.loadFromFile();
    return quizzes.stream().filter(q -> q.getName().equals(name)).findFirst().orElse(null);

  }

  public void addQuiz(Quiz quiz) {
    List<Quiz> quizzes = loadFromFile();
    quizzes.add(quiz);
    writeToFile(quizzes);
  }


}