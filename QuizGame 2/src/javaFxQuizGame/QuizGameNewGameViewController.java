package javaFxQuizGame;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import application.useCaseNewGame.NewGameInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class QuizGameNewGameViewController implements Initializable, IPresentable {
	
	@FXML
    private ComboBox<String> player2Color;

    @FXML
    private TextField player2name;

    @FXML
    private ImageView player2pic;

    @FXML
    private ImageView player3pic;

    @FXML
    private GridPane rootPane;

    @FXML
    private ImageView player1pic;

    @FXML
    private ImageView player4pic;

    @FXML
    private TextField player4name;

    @FXML
    private ComboBox<String> player1Color;

    @FXML
    private ComboBox<String> player4Color;

    @FXML
    private TextField player1name;

    @FXML
    private ComboBox<String> player3Color;

    @FXML
    private Button buttonNext;

    @FXML
    private TextField player3name;
    
    @FXML
    private Rectangle player1ColorRect;
    
    @FXML
    private Rectangle player2ColorRect;
    
    @FXML
    private Rectangle player3ColorRect;
    
    @FXML
    private Rectangle player4ColorRect;
    
    // ********************************************************
    // own variables
    // ********************************************************
    
    // Output:
    public NewGameInstance result = null;
	
	private IOverlayable presenterViewController;
	
	List<Paint> colors;
	ObservableList<String> colorStrings;
	
	List<TextField> playerTextFields;
	List<ImageView> playerImageViews;
	List<ComboBox<String>> comboBoxes;
	List<Rectangle> playerColorIndicators;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initReferenceCollections();
		initClickHandlers();
		
		setupPlayerImages();
		setupColorTextBoxes();
		
		prepopulateColorTextBoxes();
	}
	
	private void initReferenceCollections() {
		// the color combo boxes
		this.comboBoxes = new ArrayList<>();
		this.comboBoxes.add(player1Color); this.comboBoxes.add(player2Color); this.comboBoxes.add(player3Color); this.comboBoxes.add(player4Color);
		
		// labels
		this.playerTextFields = Arrays.asList(new TextField[] {player1name, player2name, player3name, player4name});
		
		// imageViews
		this.playerImageViews = Arrays.asList(new ImageView[] {player1pic, player2pic, player3pic, player4pic});
		
		// color rects
		this.playerColorIndicators = Arrays.asList(new Rectangle[] { player1ColorRect, player2ColorRect, player3ColorRect, player4ColorRect });
	}
	
	private void initClickHandlers() {
		// for the next button
		buttonNext.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				handleClickOnButtonNext();
			}
		});
	}
	
	private void setupPlayerImages() {
		player1pic.setImage(new Image(getClass().getResourceAsStream("resources/Assets/PlayerImages/1.jpeg")));
		player2pic.setImage(new Image(getClass().getResourceAsStream("resources/Assets/PlayerImages/2.jpeg")));
		player3pic.setImage(new Image(getClass().getResourceAsStream("resources/Assets/PlayerImages/3.jpeg")));
		player4pic.setImage(new Image(getClass().getResourceAsStream("resources/Assets/PlayerImages/4.jpeg")));
	}
	
	private void setupColorTextBoxes() {
		colors = Arrays.asList(new Paint[] {Color.WHITE, Color.web("#F66809"), Color.web("#B435FF"), Color.web("#00FF22")});
		colorStrings = FXCollections.observableArrayList(Arrays.asList(new String[] {"WHITE", "ORANGE", "PURPLE", "GREEN"}));
		
		for (ComboBox<String> comboBox : comboBoxes) {
			comboBox.setItems(colorStrings);
			comboBox.setOnAction((event) -> {
				int comboBoxSelectedIndex = colorStrings.indexOf(comboBox
						.getSelectionModel().getSelectedItem());
			    Paint selectedColor = this.colors.get(comboBoxSelectedIndex);
			    
			    this.playerColorIndicators.get(comboBoxes.indexOf(comboBox)).setFill(selectedColor);
			});
		}
	}
	
	private void prepopulateColorTextBoxes() {
		for (ComboBox<String> comboBox : comboBoxes) {
			comboBox.getSelectionModel().select(comboBoxes.indexOf(comboBox));
		}
	}
	
	private void handleClickOnButtonNext() {
		this.result = new NewGameInstance();
		
		for (int i = 0; i<4; i++) {
			Paint playerColor = null;
			// get the color
			ComboBox<String> comboBox = comboBoxes.get(i);
			int comboBoxSelectedIndex = colorStrings.indexOf(comboBox
					.getSelectionModel().getSelectedItem());

			if (comboBoxSelectedIndex != -1) {
				Paint color = colors.get(comboBoxSelectedIndex);
				playerColor = color;
			}
			
			// player name
			String playerName = playerTextFields.get(i).getText();
			
			// player image
			Image playerImage = playerImageViews.get(i).getImage();
			
			// set this player in the results object
			result.addPlayer(playerName, playerColor, playerImage);
			
		}
		
		dismiss();
	}

	@Override
	public void setPresenterView(IOverlayable presenterViewController) {
		this.presenterViewController = presenterViewController;
	}

	@Override
	public boolean shouldPresenterViewYieldUserInteraction() { return true; }


	@Override
    public void dismiss() {	
    	presenterViewController.dismissOverlay(rootPane);
    }

}
