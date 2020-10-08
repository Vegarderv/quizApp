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
import java.util.List;
import quizapp.core.User;

public class JsonHandler {
  private String path;
  private Writer file;


  public JsonHandler(String path) {
    this.path = path;
  }

  /**
  * Function writes a hashmap as a JSON object to a JSON file.
  */
  public void writeToFile(List<User> users) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String javaObjectString = gson.toJson(users); // converts to json
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
  * Function reads a JSON file and returns a list of users.
  */
  public List<User> loadFromFile() {
    try {
      InputStream inputStream = new FileInputStream(path);
      Reader fileReader = new InputStreamReader(inputStream, "UTF-8");
      List<User> user = new Gson().fromJson(fileReader, new TypeToken<List<User>>() {
      }.getType());
      return user;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }


}
