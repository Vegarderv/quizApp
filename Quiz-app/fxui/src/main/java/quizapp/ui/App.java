package quizapp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(final Stage primaryStage) throws Exception {
    final Parent parent = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
    Scene scene = new Scene(parent);
    scene.getStylesheets().add(App.class.getResource("lightmode.css")
            .toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(final String[] args) {
    launch(App.class, args);
  }
}
