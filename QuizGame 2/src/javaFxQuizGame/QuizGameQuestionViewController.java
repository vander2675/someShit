package javaFxQuizGame;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.reactfx.util.FxTimer;
import org.reactfx.util.Timer;

import application.api.IGameModel;
import application.api.IObserver;
import application.logic.IAPIFactory;
import application.state.GameState;

public class QuizGameQuestionViewController implements Initializable, IPresentable, IObserver<GameState> {

    @FXML
    private Rectangle answer1Rect;

    @FXML
    private Label answer0Label;

    @FXML
    private Label questionToLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label answer1Label;

    @FXML
    private Rectangle answer3Rect;

    @FXML
    private ProgressBar timeUpProgressBar;
    
    @FXML
    private Label timeUpLabel;

    @FXML
    private Rectangle answer2Rect;

    @FXML
    private Label answer2Label;

    @FXML
    private Label answer3Label;

    @FXML
    private Rectangle answer0Rect;

    @FXML
    private Label questionLabel;
    
    @FXML
    private GridPane rootPane;
    
    // ********************************************************
    // own variables
    // ********************************************************
    
    private static final int kAnswerTimeInMillis = 1000 * 10;
    private static final int kTimerUpdateRateInMillis = 10;
    
    private IOverlayable presenterViewController;
    
    private List<Rectangle> answerRects;
    private List<Label> answerLabels;
    
    int remainingTimeInMillis = -1;
    Timer timer = null;
    
    int indexOfRightAnswer = -1;
    
    IGameModel gameModel;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	gameModel = IAPIFactory.factory.getGameModel();
    	gameModel.attach(this);
    	initReferenceCollections();
    	initClickHandlers();
    	
    	timer = setupTimer();
	}
    
	private void initReferenceCollections() {
		// start with the answer rects
		Rectangle[] answerRectsArray = { answer0Rect, answer1Rect, answer2Rect, answer3Rect };
		this.answerRects = Arrays.asList(answerRectsArray);
		
		this.answerLabels = Arrays.asList(new Label[] { answer0Label, answer1Label, answer2Label, answer3Label });
	}
    
    private void initClickHandlers() {    	
		// for the answerRects
		for (Rectangle answerRect : answerRects) {
			answerRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					handleClickOnAnswerRect(answerRect);
				}
			});
		}
    }
    
	public void setupWithParameters(String category, String questionGoesTo,
			String question, List<String> answers, int indexOfRightAnswer) {
		categoryLabel.setText(category);
		questionToLabel.setText("Frage an: "+questionGoesTo);
		questionLabel.setText(question);
		
		for (String answer : answers) {
			answerLabels.get(answers.indexOf(answer)).setText(answer);
		}
		
		this.indexOfRightAnswer = indexOfRightAnswer;
	}
    
    private Timer setupTimer() {
    	remainingTimeInMillis = kAnswerTimeInMillis;
    	
    	Timer timer = FxTimer.runPeriodically(
    	        Duration.ofMillis(kTimerUpdateRateInMillis),
    	        () -> handleTimerTick());
    	
    	return timer;
    }
    
    private void handleClickOnAnswerRect(Rectangle answerRect) {
		boolean result = IAPIFactory.factory.getGameModel().getQuestion().isCorrectAnswer(gameModel.getQuestion().getAnswers()[answerRects.indexOf(answerRect)]);
		System.out.println(result);
		gameModel.chooseAnswer(gameModel.getQuestion().getAnswers()[answerRects.indexOf(answerRect)]);
		answerRect.setFill((result) ? Color.LIGHTGREEN : Color.RED);

		setupEndState();
	}
    
    private void setupEndState() {
    	this.timer.stop();
    	rootPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				dismiss();
				IAPIFactory.factory.getGameModel().clickDone();
			}
		});
    }
    
    private boolean checkAnswerForClickedRectangle(Rectangle clickedRect) {
    	int indexOfRect = answerRects.indexOf(clickedRect);

    	if (indexOfRect == this.indexOfRightAnswer) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    private void handleTimerTick() {
    	remainingTimeInMillis -= kTimerUpdateRateInMillis;
    	
    	// update progress bar
    	double remainingProgress = (double)remainingTimeInMillis / (double)kAnswerTimeInMillis;
//    	System.out.println("progress: "+remainingProgress*100.0 + "%");
    	timeUpProgressBar.setProgress(remainingProgress);
    	
    	if (remainingProgress<=0.0) {
    		remainingProgress = 0.0;
    		timer.stop();
    	}
    	
    	// update progress label
    	if (remainingTimeInMillis > 0) {
    		String text = "Zeit verbleibend: " + remainingTimeInMillis / 1000;
    		timeUpLabel.setText(text);
    	} else {
    		
    		timeUpLabel.setText("Zeit abgelaufen!");
    		IAPIFactory.factory.getGameModel().timeUp();
//    		timeUpLabel.setTextFill(Color.RED);
//    		
//    		// set all answer rects to red
//    		for (Rectangle rectangle : answerRects) {
//				rectangle.setFill(Color.RED);
//			}
    		
    		setupEndState();
    	}
    }
    
    @Override
    public void dismiss() {
    	presenterViewController.dismissOverlay(rootPane);
    }

	@Override
	public void setPresenterView(IOverlayable presenterViewController) {
		this.presenterViewController = presenterViewController;
	}

	@Override
	public boolean shouldPresenterViewYieldUserInteraction() { return true; }

	@Override
	public void update(GameState info) {

		switch(info) {
		case SHOW_ANSWER:
			
		}
	}

}
