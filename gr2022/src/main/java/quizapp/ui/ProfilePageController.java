package quizapp.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import quizapp.core.User;
import quizapp.json.JSONHandler;
import quizapp.json.UsernameHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ProfilePageController implements Initializable{

    @FXML
    MenuBar menuBar;

    @FXML
    MenuItem mainPageID;

    @FXML
    MenuItem loginID;

    @FXML
    Label nameID;

    @FXML
    Label scoreID;

    @FXML
    public void goToMainMenu(ActionEvent event) {
        this.switchScene("MainPage.fxml");
    }

    @FXML
    public void goLogOut(ActionEvent event) {
        this.switchScene("Login.fxml");
    }

    private User getActiveUser(){
        JSONHandler jsonHandler = new JSONHandler("src/main/resources/quizapp/json/JSONHandler.json");
        UsernameHandler usernameHandler = new UsernameHandler("src/main/resources/quizapp/json/activeUser.json");
        return jsonHandler.
                loadFromFile().
                stream().
                filter(user -> user.getUsername().equals(usernameHandler.loadActiveUser())).
                findFirst().get();
    }

    public void switchScene(String fxmlFile) {
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
  
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UsernameHandler userHandler = new UsernameHandler("src/main/resources/quizapp/json/activeUser.json");

        String userName=userHandler.loadActiveUser();
        String score=getActiveUser().meanScore().toString();
        nameID.setText(userName);
        scoreID.setText(score);

    }


}
