package quizapp.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import javafx.scene.Node;


public class MainPageController {
	
	@FXML MenuBar menuBar;
	

    @FXML
    void GoToHistoryQuiz(ActionEvent event) {
    	this.switchSceneWithButton(event, "HistoryQuiz.fxml");
    }
    

    @FXML
    void GoToProfile(ActionEvent event) {
    	this.switchSceneWithMenuItem("ProfilePage.fxml");
    }

    @FXML
    void LogOut(ActionEvent event) {
    	this.switchSceneWithMenuItem("Login.fxml");
    }
    
    void switchSceneWithButton(ActionEvent event, String fxmlFile) {
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
			Scene scene = new Scene(parent);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    void switchSceneWithMenuItem(String fxmlFile) {
    	try {
        	Stage stage = (Stage) menuBar.getScene().getWindow();
			Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
	        Scene scene = new Scene(parent);  
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
   

}
