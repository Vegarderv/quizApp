package quizapp.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
	List<RadioButton> buttons = new ArrayList<>();
	
	
	
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
        Score scoreCard = new Score();
        scoreCard.scoreQuiz(sum, 3, this.toString());
	}
	
	@Override
    public String toString() {
        return "HistoryQuiz";
    }
	
	
	
}
