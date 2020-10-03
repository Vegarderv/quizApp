package quizapp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import quizapp.core.Score;

public class HistoryQuizController implements Initializable{

	@FXML RadioButton q1a1;
	@FXML RadioButton q1a2;
	@FXML RadioButton q1a3;
	@FXML RadioButton q1a4;
	@FXML RadioButton q2a1;
	@FXML RadioButton q2a2;
	@FXML RadioButton q2a3;
	@FXML RadioButton q2a4;
	@FXML RadioButton q3a1;
	@FXML RadioButton q3a2;
	@FXML RadioButton q3a3;
	@FXML RadioButton q3a4;
    @FXML Button submit;
    @FXML Label score;
    @FXML ScrollPane scroll;
    @FXML MenuButton userMenu;
    @FXML MenuItem menuProfilePage;
    @FXML MenuItem menuSignOut;
    private List<RadioButton> buttons = new ArrayList<>();
    private String userName;
    private String jsonPath = "src/main/resources/quizapp/json/activeUser.json";
    private String usernamePath = "src/main/resources/quizapp/json/JSONHandler.json";
    Score scoreCard = new Score(jsonPath, usernamePath);
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buttons.add(q1a1);
		buttons.add(q1a2);
		buttons.add(q1a3);
		buttons.add(q1a4);
		buttons.add(q2a1);
		buttons.add(q2a2);
		buttons.add(q2a3);
		buttons.add(q2a4);
		buttons.add(q3a1);
		buttons.add(q3a2);
		buttons.add(q3a3);
        buttons.add(q3a4);
        userName = scoreCard.getUsername();
        userMenu.setText(userName);
	}
	
	@FXML
	public void submitAnswers() {
		int sum = 0;
		if (q1a3.isSelected()) {
			sum ++;
		}
		if (q2a4.isSelected()) {
			sum ++;
		}
		if (q3a3.isSelected()) {
			sum ++;
		}
		buttons.stream().forEach(a -> a.setDisable(true));
        submit.setDisable(true);
        scroll.setVvalue(0.01);
        score.setText("You got this Score: " + Integer.toString(Math.round(((float) sum / (float) 3)*100)) + "%");
        scoreCard.scoreQuiz(sum, 3, this.toString());
	}
	
	@Override
    public String toString() {
        return "HistoryQuiz";
    }
    
    private void switchSceneWithMenuItem(String fxmlFile) {
    	try {
        	Stage stage = (Stage) userMenu.getScene().getWindow();
			Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
	        Scene scene = new Scene(parent);  
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void GoToProfile(ActionEvent event) {
    	this.switchSceneWithMenuItem("ProfilePage.fxml");
    }

    @FXML
    void GoToLogIn(ActionEvent event) {
    	this.switchSceneWithMenuItem("Login.fxml");
    }

    @FXML
    void GoToMainMenu(ActionEvent event) {
    	this.switchSceneWithMenuItem("MainPage.fxml");
    }

	
	
}
