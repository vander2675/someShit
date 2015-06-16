package javaFxQuizGame;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class QuizGameWinnerScreenViewController implements Initializable {
	
	@FXML
    private Button endGame;

    @FXML
    private Label winnerNameLabel;

    @FXML
    private ImageView winnerIsPic;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		winnerIsPic.setImage(new Image(getClass().getResourceAsStream("resources/Assets/Winner.jpeg")));
	}
	
	public void setupWithParameters(String winnerName) {
		winnerNameLabel.setText(winnerName);
	}

}
