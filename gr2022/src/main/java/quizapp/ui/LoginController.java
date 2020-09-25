package quizapp.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import quizapp.core.UsernameCheck;
public class LoginController implements Initializable{
	
	@FXML
	TextField username;
	@FXML
	TextField password;
	@FXML
	Label errorMessage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
    //Checks if username and password is valid. Error message will occur if not. If valid the user will be redirected to another fxml file.
    @FXML
	public void toMainMenu(ActionEvent event) throws Exception {
        UsernameCheck chk = new UsernameCheck();
		if(!chk.checkUsername(username.getText(), password.getText())){
			username.clear();
			password.clear();
			errorMessage.setText("Wrong username or password");
			return;
        }
        
        //Gets the stage information and sets the scene
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	@FXML
	public void toSignupPage(ActionEvent event) throws Exception {
		//Gets the stage information and sets the scene
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("Signup.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}
}