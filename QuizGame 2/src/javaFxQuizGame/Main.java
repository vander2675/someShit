package javaFxQuizGame;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application{
	
	public static final boolean DEBUG = true;

	@Override
	public void start(Stage primaryStage) throws Exception {
        try {
        	URL resource = getClass().getResource("resources/FXML/Quiz.fxml");
    		FXMLLoader fxmlLoader = new FXMLLoader(resource);
    		
    		Parent root = (Parent)fxmlLoader.load();
    		Scene scene = new Scene(root, 800, 630);
    		
    		primaryStage.setTitle("Quiz Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
