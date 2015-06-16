package javaFxQuizGame;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import application.api.ICategory;
import application.api.IField;
import application.api.IGameModel;
import application.api.IObserver;
import application.api.IPlayer;
import application.api.IPlayers;
import application.api.ISpielbrett;
import application.api.IWissensstreiter;
import application.coreElements.Category;
import application.logic.APIFactory;
import application.logic.IAPIFactory;
import application.state.GameState;
import application.useCaseNewGame.NewGameInstance;
import application.useCaseNewGame.NewGameInstance;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class QuizGameBoardViewController implements Initializable, IOverlayable, IObserver<GameState> {
	
	// Keys for Player-Map
	private static final String PLAYER1_KEY = "PLAYER1";
	private static final String PLAYER2_KEY = "PLAYER2";
	private static final String PLAYER3_KEY = "PLAYER3";
	private static final String PLAYER4_KEY = "PLAYER4";
	// sub player level
	private static final String BASE_FIELDS_KEY = "BASE_FIELDS";
	private static final String BASE_LABEL_KEY = "BASE_LABEL";
	private static final String START_LABEL_KEY = "START_LABEL";
	private static final String WA_KEY = "WA";
	
	@FXML
	private StackPane rootPane;
	
	@FXML
	private BorderPane quizBoardBorderPane;
	
	@FXML
	private MenuBar menuBar;
	
    @FXML
    private Circle field19;

    @FXML
    private Circle field17;

    @FXML
    private Circle field18;

    @FXML
    private Circle field22;

    @FXML
    private Circle field23;

    @FXML
    private Circle field20;

    @FXML
    private Circle field21;

    @FXML
    private Circle field26;

    @FXML
    private Circle field27;

    @FXML
    private Circle field24;

    @FXML
    private Circle waPlayer1Cat1Field1;

    @FXML
    private Circle field25;

    @FXML
    private Circle waPlayer1Cat1Field2;

    @FXML
    private Circle waPlayer1Cat1Field0;

    @FXML
    private Circle waPlayer1Cat4Field1;

    @FXML
    private Circle waPlayer1Cat4Field0;

    @FXML
    private Circle waPlayer1Cat4Field2;

    @FXML
    private Circle waPlayer2Cat1Field2;

    @FXML
    private Circle waPlayer2Cat1Field1;

    @FXML
    private Circle waPlayer2Cat1Field0;

    @FXML
    private Circle p4Circle;

    @FXML
    private Circle field11;

    @FXML
    private Circle field12;

    @FXML
    private Circle field10;

    @FXML
    private Circle field15;

    @FXML
    private Circle field16;

    @FXML
    private Circle field13;

    @FXML
    private Circle field14;

    @FXML
    private Circle waPlayer4Cat2Field0;

    @FXML
    private Label p2Label;

    @FXML
    private Circle waPlayer2Cat4Field1;

    @FXML
    private Circle waPlayer2Cat4Field2;

    @FXML
    private Circle waPlayer2Cat4Field0;

    @FXML
    private Circle waPlayer4Cat2Field2;

    @FXML
    private Rectangle category4Rect;

    @FXML
    private Circle waPlayer4Cat2Field1;

    @FXML
    private Circle waPlayer2Cat2Field0;

    @FXML
    private Label p4Label;

    @FXML
    private Circle waPlayer2Cat2Field2;

    @FXML
    private Circle waPlayer2Cat2Field1;

    @FXML
    private Circle field39;

    @FXML
    private Circle field44;

    @FXML
    private Rectangle category2Rect;

    @FXML
    private Circle field45;

    @FXML
    private Circle field42;

    @FXML
    private Circle field43;

    @FXML
    private Circle waPlayer3Cat4Field2;

    @FXML
    private Circle field46;

    @FXML
    private Circle waPlayer3Cat4Field0;

    @FXML
    private Circle field47;

    @FXML
    private Circle waPlayer3Cat4Field1;

    @FXML
    private Circle base0Player4;

    @FXML
    private Circle field40;

    @FXML
    private Circle base1Player2;

    @FXML
    private Circle field41;

    @FXML
    private Circle base0Player1;

    @FXML
    private Circle base1Player1;

    @FXML
    private Circle base1Player4;

    @FXML
    private Circle base0Player2;

    @FXML
    private Circle base0Player3;

    @FXML
    private Circle base1Player3;

    @FXML
    private Circle waPlayer4Cat1Field2;

    @FXML
    private Circle waPlayer2Cat3Field2;

    @FXML
    private Circle waPlayer2Cat3Field1;

    @FXML
    private Circle waPlayer2Cat3Field0;

    @FXML
    private Circle waPlayer4Cat1Field0;

    @FXML
    private Circle waPlayer4Cat1Field1;

    @FXML
    private Circle currentPlayerCircle;

    @FXML
    private Circle field28;

    @FXML
    private Circle field29;

    @FXML
    private Circle field33;

    @FXML
    private Circle field34;

    @FXML
    private Circle field1;

    @FXML
    private Circle field31;

    @FXML
    private Circle field0;

    @FXML
    private Circle field32;

    @FXML
    private Circle field37;

    @FXML
    private Circle field38;

    @FXML
    private Circle field35;

    @FXML
    private Circle field36;

    @FXML
    private Circle field7;

    @FXML
    private Circle field6;

    @FXML
    private Circle field9;

    @FXML
    private Circle field8;

    @FXML
    private Circle field3;

    @FXML
    private Circle field2;

    @FXML
    private Circle field30;

    @FXML
    private Circle field5;

    @FXML
    private Circle field4;

    @FXML
    private Button mainButton;

    @FXML
    private Label startLabelPlayer4;

    @FXML
    private Label startLabelPlayer3;

    @FXML
    private Label startLabelPlayer2;

    @FXML
    private Label startLabelPlayer1;

    @FXML
    private Circle waPlayer1Cat2Field1;

    @FXML
    private Circle waPlayer1Cat2Field0;

    @FXML
    private Circle waPlayer1Cat2Field2;

    @FXML
    private Circle waPlayer3Cat3Field1;

    @FXML
    private Circle waPlayer3Cat3Field2;

    @FXML
    private Circle waPlayer3Cat3Field0;

    @FXML
    private Circle p3Circle;

    @FXML
    private Circle waPlayer1Cat3Field2;

    @FXML
    private Circle waPlayer1Cat3Field1;

    @FXML
    private Circle waPlayer3Cat1Field0;

    @FXML
    private Circle base2Player1;

    @FXML
    private Circle waPlayer1Cat3Field0;

    @FXML
    private Circle waPlayer3Cat1Field1;

    @FXML
    private Circle waPlayer3Cat1Field2;

    @FXML
    private Label baseLabelPlayer4;

    @FXML
    private Rectangle category3Rect;

    @FXML
    private Circle waPlayer4Cat3Field1;

    @FXML
    private Label baseLabelPlayer3;

    @FXML
    private Circle waPlayer4Cat3Field0;

    @FXML
    private Label baseLabelPlayer2;

    @FXML
    private Label baseLabelPlayer1;

    @FXML
    private Label currentPlayerLabel;

    @FXML
    private Circle waPlayer4Cat3Field2;

    @FXML
    private Circle base2Player4;

    @FXML
    private Circle base2Player2;

    @FXML
    private Circle base2Player3;

    @FXML
    private Circle waPlayer3Cat2Field2;

    @FXML
    private Circle waPlayer3Cat2Field0;

    @FXML
    private Circle waPlayer3Cat2Field1;

    @FXML
    private Label p3Label;

    @FXML
    private Rectangle category1Rect;

    @FXML
    private Circle waPlayer4Cat4Field2;

    @FXML
    private Circle waPlayer4Cat4Field1;

    @FXML
    private Circle waPlayer4Cat4Field0;

    @FXML
    private Circle p2Circle;
    
    @FXML
    private GridPane quizBoardGridPane;
    
    // ********************************************************
    // own variables
    // ********************************************************
    
    // Model
    private IGameModel gameModel = null;
    
    private int playerCount = 4;
    
    // General
    private List<Circle> fields;
    private List<Rectangle> categories;
    
    private MainButtonState mainButtonState;
    private int selectedCategoryNo;
    
    private List<Circle> turnOrderCircles;
    private List<Label> turnOrderLabels;
    // Player-owned elements
    private Map<String, Map<String, Object>> playerElements;
    
    private List<Node> highlightedNodes;
    
    private List<ImageView> wissensstreiterz = new ArrayList<>();
    
    // UI Helpers
    private List<Node> overlayViews;
    private Map<Node, Object> viewControllerForOverlayViews;
    
	@Override
	public void initialize(URL location, ResourceBundle arg1) {
		// wire up to Model
		gameModel = IAPIFactory.factory.getGameModel();
		gameModel.attach(this);
		
		this.initReferenceCollections();
		this.initClickHandlers();
		
		if (Main.DEBUG) {
			setupDebugMode();
		}
		
		viewControllerForOverlayViews = new HashMap<>();
		
		mainButton.setMaxWidth(58);
		// add start images
		for (int i = 0; i < 4; i++) {
			ImageView startImgView = addImageToNode(getStartField(i), new Image(getClass().getResourceAsStream("resources/Assets/arrow.png")), false);
			startImgView.setRotate(90*i);
		}
	}
	
	private void setupDebugMode() {
		Menu debugMenu = new Menu("Debug");
		menuBar.getMenus().add(debugMenu);
		
		MenuItem neuesSpiel = new MenuItem("Neues Spiel starten");
		neuesSpiel.setOnAction(new EventHandler<ActionEvent> (){
			   @Override public void handle(ActionEvent e) {
				   overlayWithNodeFromFXML("resources/FXML/newGame.fxml");
			   }
			});
		debugMenu.getItems().add(neuesSpiel);
		
		MenuItem frageStellen = new MenuItem("Frage stellen");
		frageStellen.setOnAction(new EventHandler<ActionEvent> (){
			   @Override public void handle(ActionEvent e) {
				   overlayWithNodeFromFXML("resources/FXML/Frage.fxml");
			   }
			});
		debugMenu.getItems().add(frageStellen);
		
		MenuItem winnerScreen = new MenuItem("Winnerscreen");
		winnerScreen.setOnAction(new EventHandler<ActionEvent> (){
			   @Override public void handle(ActionEvent e) {
				   overlayWithNodeFromFXML("resources/FXML/winnerScreen.fxml");
			   }
			});
		debugMenu.getItems().add(winnerScreen);
		
		MenuItem dismissOverlays = new MenuItem("Overlays beenden");
		dismissOverlays.setOnAction(new EventHandler<ActionEvent> (){
			   @Override public void handle(ActionEvent e) {
				   dismissAllOverlays();
			   }
			});
		debugMenu.getItems().add(dismissOverlays);
		
		MenuItem highlightCategories = new MenuItem("Kategorien highlighten");
		highlightCategories.setOnAction(new EventHandler<ActionEvent> (){
			   @Override public void handle(ActionEvent e) {
				   highlightNodes(new ArrayList<Node>(categories));
			   }
			});
		debugMenu.getItems().add(highlightCategories);
		
		MenuItem removeHighlights = new MenuItem("Alle Highlights entfernen");
		removeHighlights.setOnAction(new EventHandler<ActionEvent> (){
			   @Override public void handle(ActionEvent e) {
				   removeHighlightFromAllNodes();
			   }
			});
		debugMenu.getItems().add(removeHighlights);
	}
	
	private void test() {
		
//		for (Circle field : fields) {
//			field.setFill(Color.BLACK);
//		}
		
		List<Circle> list = getBaseFields(0);
		for (Circle circle : list) {
			circle.setFill(Color.BISQUE);
		}
		
		for (Rectangle category : categories) {
			category.setFill(Color.AZURE);
		}
		
		addImageToNode(currentPlayerCircle, new Image(getClass().getResourceAsStream("resources/Assets/Fab.png")), true);
	}
	
	private ImageView addWissensstreiterToField(Circle field, int playerNo) {
		Image wiImage = null;
		
		switch (playerNo) {
		case 0:
			wiImage = new Image(getClass().getResourceAsStream("resources/Assets/Wissensstreiter/white.png"));
			break;
			
		case 1:
			wiImage = new Image(getClass().getResourceAsStream("resources/Assets/Wissensstreiter/red.png"));
			break;
			
		case 2:
			wiImage = new Image(getClass().getResourceAsStream("resources/Assets/Wissensstreiter/purple.png"));
			break;

		default:
			wiImage = new Image(getClass().getResourceAsStream("resources/Assets/Wissensstreiter/green.png"));
			break;
		}
		
		ImageView wissensstreiter = addImageToNode(field, wiImage, true);
		wissensstreiterz.add(wissensstreiter);
		return wissensstreiter;
	}
	
	private void removeWissensstreiterz() {
		for (ImageView wissensstreiter : wissensstreiterz) {
			((Pane)wissensstreiter.getParent()).getChildren().remove(wissensstreiter);
		}
		
		wissensstreiterz.clear();
	}
	
	private ImageView addImageToNode(Node node, Image img, boolean fitSizeToParent) {
		ImageView imgView = null;
		Parent parent = node.getParent();
		
		if (parent instanceof GridPane) {
			GridPane parentPane = (GridPane)node.getParent();
			Integer fieldRow = GridPane.getRowIndex(node);
			Integer fieldColumn = GridPane.getColumnIndex(node);
			
			// exchange field with new stackpane in parent
			StackPane sp = new StackPane();
			int indexOfField = parentPane.getChildren().indexOf(node);
			if (fieldRow != null) {
				GridPane.setRowIndex(sp, fieldRow.intValue());
			}
			if (fieldColumn != null) {
				GridPane.setColumnIndex(sp, fieldColumn.intValue());
			}
			parentPane.getChildren().set(indexOfField, sp);
			// re-add field to the new stackpane
			sp.getChildren().add(node);
		}
		
		if (parent instanceof VBox) {
			VBox parentPane = (VBox)node.getParent();
			
			// exchange field with new stackpane in parent
			StackPane sp = new StackPane();
			int indexOfField = parentPane.getChildren().indexOf(node);
			parentPane.getChildren().set(indexOfField, sp);
			// re-add field to the new stackpane
			sp.getChildren().add(node);
		}
		
		// add image
		parent = node.getParent();
		if (parent instanceof StackPane) {
			StackPane parentStackPane = (StackPane)node.getParent();
			
		    imgView = new ImageView(img);
		    int indexOfField = parentStackPane.getChildren().indexOf(node);
		    parentStackPane.getChildren().add(indexOfField+1, imgView);
		    
		    // resize
		    if (fitSizeToParent) {
		    	imgView.setFitWidth(node.getLayoutBounds().getWidth());
		    	imgView.setFitHeight(node.getLayoutBounds().getHeight());
		    }
		    
		    // configure imgView
		    imgView.setMouseTransparent(true);
		}
		
		return imgView;
	}
	
	private ImageView getImageViewForNode(Node node) {
		ImageView imgView = null;
		
		Parent parent = node.getParent();
		
		if (parent instanceof StackPane) {
			StackPane parentStackPane = (StackPane)node.getParent();
			
			for (Node childrenNode : parentStackPane.getChildren()) {
				if (childrenNode instanceof ImageView) {
					imgView = (ImageView)childrenNode;
				}
			}
		}
		
		return imgView;
	}
	
	private void fitImageViewToNodeSize(ImageView imgView, Node node) {
		imgView.setFitWidth(node.getLayoutBounds().getWidth());
    	imgView.setFitHeight(node.getLayoutBounds().getHeight());
	}
	
	private void highlightNodes(List<Node> nodes) {
		if (highlightedNodes == null) {
			highlightedNodes = new ArrayList<>();
		}
		
		for (Node node : nodes) {
			addGlowEffectToNode(node, Color.web("#00FC00"));
			highlightedNodes.add(node);
		}
	}
	
	private void highlightCircles(List<Circle> circles) {
		List<Node> nodes = new ArrayList<>(circles);
		highlightNodes(nodes);
	}
	
	private void highlightRectangles(List<Rectangle> rectangles) {
		List<Node> nodes = new ArrayList<>(rectangles);
		highlightNodes(nodes);
	}
	
	private void removeHighlightFromAllNodes() {
		if (highlightedNodes != null) {
			ArrayList<Node> highlightedNodesCopy = new ArrayList<>(highlightedNodes);
			for (Node node : highlightedNodesCopy) {
				removeGlowEffectFromNode(node);
				highlightedNodes.remove(node);
			}
		}
	}
	
	private void addGlowEffectToNode(Node node, Color color) {
		double depth = 70; //Setting the uniform variable for the glow width and height
		 
		DropShadow borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(color);
//		borderGlow.setWidth(depth);
//		borderGlow.setHeight(depth);
		 
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
		
		timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0
                new KeyValue(borderGlow.widthProperty(), depth/2.0, Interpolator.EASE_OUT),
                new KeyValue(borderGlow.heightProperty(), depth/2.0, Interpolator.EASE_OUT)),
                new KeyFrame(new Duration(750), // set end position at 40s
                new KeyValue(borderGlow.widthProperty(), depth, Interpolator.EASE_OUT),
                new KeyValue(borderGlow.heightProperty(), depth, Interpolator.EASE_OUT)));
		
		timeline.play();
		
		node.setEffect(borderGlow); //Apply the borderGlow effect to the JavaFX node
	}
	
	private void emphasizeGlowEffectFromNode(Node node, Color color, Color emphasizeColor) {
		for (Node otherNode : highlightedNodes) {
			((DropShadow)otherNode.getEffect()).setColor(color);
		}
		
		DropShadow borderGlow = (DropShadow)node.getEffect();
		
		borderGlow.setColor(emphasizeColor);
	}
	
	private void removeGlowEffectFromNode(Node node) {
		node.setEffect(null);
	}
	
	private void initReferenceCollections() {
		// start with the fields
		Circle[] fieldArray = {field0, field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11, field12, field13, field14, field15, field16, field17, field18, field19, field20, field21, field22, field23, field24, field25, field26, field27, field28, field29, field30, field31, field32, field33, field34, field35, field36, field37, field38, field39, field40, field41, field42, field43, field44, field45, field46, field47};
		this.fields = Arrays.asList(fieldArray);
		
		// categories
		Rectangle[] categoryArray = {category1Rect, category2Rect, category3Rect, category4Rect};
		this.categories = Arrays.asList(categoryArray);
		
		// turn-order-HUD
		Circle[] turnOrderCircleArray = {currentPlayerCircle, p2Circle, p3Circle, p4Circle};
		this.turnOrderCircles = Arrays.asList(turnOrderCircleArray);
		Label[] turnOrderLabelArray = {currentPlayerLabel, p2Label, p3Label, p4Label};
		this.turnOrderLabels = Arrays.asList(turnOrderLabelArray);
		
		// player-owned-elements
		this.playerElements = new HashMap<>();
		this.addPlayerElementsForPlayer1();
		this.addPlayerElementsForPlayer2();
		this.addPlayerElementsForPlayer3();
		this.addPlayerElementsForPlayer4();
	}
	
	private void addPlayerElementsForPlayer1() {
		Map<String, Object> playerMap = new HashMap<>();
		
		// Wissenstandsanzeiger
		List<Circle> cat1Fields = Arrays.asList(new Circle[] {waPlayer1Cat1Field0, waPlayer1Cat1Field1, waPlayer1Cat1Field2});
		List<Circle> cat2Fields = Arrays.asList(new Circle[] {waPlayer1Cat2Field0, waPlayer1Cat2Field1, waPlayer1Cat2Field2});
		List<Circle> cat3Fields = Arrays.asList(new Circle[] {waPlayer1Cat3Field0, waPlayer1Cat3Field1, waPlayer1Cat3Field2});
		List<Circle> cat4Fields = Arrays.asList(new Circle[] {waPlayer1Cat4Field0, waPlayer1Cat4Field1, waPlayer1Cat4Field2});
		// bundle into one array, which will later reside under the 'WA'-key
		List<List<Circle>> waFields = new ArrayList<List<Circle>>();
		waFields.add(cat1Fields);
		waFields.add(cat2Fields);
		waFields.add(cat3Fields);
		waFields.add(cat4Fields);
		// add to playerMap
		playerMap.put(WA_KEY, waFields);
		
		// Start Label
		playerMap.put(START_LABEL_KEY, startLabelPlayer1);
		
		// Base Label
		playerMap.put(BASE_LABEL_KEY, baseLabelPlayer1);
		
		// Base Fields
		List<Circle> baseFields = Arrays.asList(new Circle[] {base0Player1, base1Player1, base2Player1});
		playerMap.put(BASE_FIELDS_KEY, baseFields);
		
		// add playerMap to playerElements-dict, that collects player-elements from ALL players.
		this.playerElements.put(PLAYER1_KEY, playerMap);
	}
	
	private void addPlayerElementsForPlayer2() {
		Map<String, Object> playerMap = new HashMap<>();
		
		// Wissenstandsanzeiger
		List<Circle> cat1Fields = Arrays.asList(new Circle[] {waPlayer2Cat1Field0, waPlayer2Cat1Field1, waPlayer2Cat1Field2});
		List<Circle> cat2Fields = Arrays.asList(new Circle[] {waPlayer2Cat2Field0, waPlayer2Cat2Field1, waPlayer2Cat2Field2});
		List<Circle> cat3Fields = Arrays.asList(new Circle[] {waPlayer2Cat3Field0, waPlayer2Cat3Field1, waPlayer2Cat3Field2});
		List<Circle> cat4Fields = Arrays.asList(new Circle[] {waPlayer2Cat4Field0, waPlayer2Cat4Field1, waPlayer2Cat4Field2});
		// bundle into one array, which will later reside under the 'WA'-key
		List<List<Circle>> waFields = new ArrayList<List<Circle>>();
		waFields.add(cat1Fields);
		waFields.add(cat2Fields);
		waFields.add(cat3Fields);
		waFields.add(cat4Fields);
		// add to playerMap
		playerMap.put(WA_KEY, waFields);
		
		// Start Label
		playerMap.put(START_LABEL_KEY, startLabelPlayer2);
		
		// Base Label
		playerMap.put(BASE_LABEL_KEY, baseLabelPlayer2);
		
		// Base Fields
		List<Circle> baseFields = Arrays.asList(new Circle[] {base0Player2, base1Player2, base2Player2});
		playerMap.put(BASE_FIELDS_KEY, baseFields);
		
		// add playerMap to playerElements-dict, that collects player-elements from ALL players.
		this.playerElements.put(PLAYER2_KEY, playerMap);
	}
	
	private void addPlayerElementsForPlayer3() {
		Map<String, Object> playerMap = new HashMap<>();
		
		// Wissenstandsanzeiger
		List<Circle> cat1Fields = Arrays.asList(new Circle[] {waPlayer3Cat1Field0, waPlayer3Cat1Field1, waPlayer3Cat1Field2});
		List<Circle> cat2Fields = Arrays.asList(new Circle[] {waPlayer3Cat2Field0, waPlayer3Cat2Field1, waPlayer3Cat2Field2});
		List<Circle> cat3Fields = Arrays.asList(new Circle[] {waPlayer3Cat3Field0, waPlayer3Cat3Field1, waPlayer3Cat3Field2});
		List<Circle> cat4Fields = Arrays.asList(new Circle[] {waPlayer3Cat4Field0, waPlayer3Cat4Field1, waPlayer3Cat4Field2});
		// bundle into one array, which will later reside under the 'WA'-key
		List<List<Circle>> waFields = new ArrayList<List<Circle>>();
		waFields.add(cat1Fields);
		waFields.add(cat2Fields);
		waFields.add(cat3Fields);
		waFields.add(cat4Fields);
		// add to playerMap
		playerMap.put(WA_KEY, waFields);
		
		// Start Label
		playerMap.put(START_LABEL_KEY, startLabelPlayer3);
		
		// Base Label
		playerMap.put(BASE_LABEL_KEY, baseLabelPlayer3);
		
		// Base Fields
		List<Circle> baseFields = Arrays.asList(new Circle[] {base0Player3, base1Player3, base2Player3});
		playerMap.put(BASE_FIELDS_KEY, baseFields);
		
		// add playerMap to playerElements-dict, that collects player-elements from ALL players.
		this.playerElements.put(PLAYER3_KEY, playerMap);
	}
	
	private void addPlayerElementsForPlayer4() {
		Map<String, Object> playerMap = new HashMap<>();
		
		// Wissenstandsanzeiger
		List<Circle> cat1Fields = Arrays.asList(new Circle[] {waPlayer4Cat1Field0, waPlayer4Cat1Field1, waPlayer4Cat1Field2});
		List<Circle> cat2Fields = Arrays.asList(new Circle[] {waPlayer4Cat2Field0, waPlayer4Cat2Field1, waPlayer4Cat2Field2});
		List<Circle> cat3Fields = Arrays.asList(new Circle[] {waPlayer4Cat3Field0, waPlayer4Cat3Field1, waPlayer4Cat3Field2});
		List<Circle> cat4Fields = Arrays.asList(new Circle[] {waPlayer4Cat4Field0, waPlayer4Cat4Field1, waPlayer4Cat4Field2});
		// bundle into one array, which will later reside under the 'WA'-key
		List<List<Circle>> waFields = new ArrayList<List<Circle>>();
		waFields.add(cat1Fields);
		waFields.add(cat2Fields);
		waFields.add(cat3Fields);
		waFields.add(cat4Fields);
		// add to playerMap
		playerMap.put(WA_KEY, waFields);
		
		// Start Label
		playerMap.put(START_LABEL_KEY, startLabelPlayer4);
		
		// Base Label
		playerMap.put(BASE_LABEL_KEY, baseLabelPlayer4);
		
		// Base Fields
		List<Circle> baseFields = Arrays.asList(new Circle[] {base0Player4, base1Player4, base2Player4});
		playerMap.put(BASE_FIELDS_KEY, baseFields);
		
		// add playerMap to playerElements-dict, that collects player-elements from ALL players.
		this.playerElements.put(PLAYER4_KEY, playerMap);
	}
	
	private void initClickHandlers() {
		// for the fields
		for (Circle field : fields) {
			field.setOnMouseClicked(new EventHandler<MouseEvent>()
			        {
			            @Override
			            public void handle(MouseEvent t) {
			                handleClickOnField(field);
			            }
			        });
		}
		
		// for the categories
		for (Rectangle categoryField : categories) {
			categoryField.setOnMouseClicked(new EventHandler<MouseEvent>()
			        {
			            @Override
			            public void handle(MouseEvent t) {
			                handleClickOnCategoryField(categoryField);
			            }
			        });
		}
		
		// for the player's elements
		for (Map.Entry<String, Map<String, Object>> entry : playerElements.entrySet()) {
			int playerNo = getPlayerNo(entry.getKey());
			
			// for the base-fields
			List<Circle> baseFields = getBaseFields(playerNo);
			for (Circle baseField : baseFields) {
				baseField.setOnMouseClicked(new EventHandler<MouseEvent>()
				        {
				            @Override
				            public void handle(MouseEvent t) {
				                handleClickOnBaseField(baseField, playerNo);
				            }
				        });
			}
			
			// for the WA-fields
			for (int i = 0; i < categories.size(); i++) {
				List<Circle> waFields = getWAFields(playerNo, i);
				for (Circle waField : waFields) {
					waField.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent t) {
							handleClickOnWAField(waField, playerNo, getWAFields(playerNo).indexOf(waFields));
						}
					});
				}
			}
			
			// for the (main) interaction button (throw dice | next turn | and so on...)
			mainButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					handleClickOnMainButton();
				}
			});
		}
	}
	
	// ********************************************************
	// Target-Action
	// ********************************************************
	
	private void handleClickOnField(Circle field)
	{
		int indexOfField = fields.indexOf(field);
		System.out.println("field["+indexOfField+"] clicked!");
		
		IWissensstreiter ws = IAPIFactory.factory.getSpielbrett().getFieldAtIndex(indexOfField).getWissensstreiter().get(0);
		if(ws != null && ws.getOwner().equals(gameModel.getCurrentPlayer())) {
			gameModel.chooseWS(ws);
		}
		
//		field.setFill(Color.RED);
//		highlightNodes(Arrays.asList(new Circle[] {field}));
	}
	
	private void handleClickOnCategoryField(Rectangle categoryField)
	{
		int indexOfField = categories.indexOf(categoryField);
		System.out.println("categoryField["+indexOfField+"] clicked!");
		
		if (categoryField.getEffect() == null) {
			return;
		}
		
		emphasizeGlowEffectFromNode(categoryField, Color.web("#00FC00"), Color.RED);
		selectedCategoryNo = this.categories.indexOf(categoryField);
		
		setMainButtonToPromptForAcceptCategoryDecision(selectedCategoryNo);
	}
	
	private void handleClickOnBaseField(Circle baseField, int playerNo)
	{
		int indexOfField = this.getBaseFields(playerNo).indexOf(baseField);
		System.out.println("baseField["+indexOfField+"] of Player["+playerNo+"] clicked!");
		int numberOfWSInHomeBase = IAPIFactory.factory.getPlayers().getCountOfWissensstreiterInHomeBase(gameModel.getCurrentPlayer());
		if (indexOfField <= numberOfWSInHomeBase) {
			for (IWissensstreiter ws : gameModel.getCurrentPlayer().getAllPlayerWissensstreiter().getWissenstreiter()) {
				if(IAPIFactory.factory.getSpielbrett().getFieldOfWissensstreiter(ws) == null) {
					gameModel.chooseWS(ws);
				}
			}
		}
		
//		baseField.setFill(Color.RED);
	}
	
	private void handleClickOnWAField(Circle waField, int playerNo, int categoryNo)
	{
		int indexOfField = this.getWAFields(playerNo, categoryNo).indexOf(waField);
		System.out.println("waField["+indexOfField+"] of Player["+playerNo+"] for Category["+categoryNo+"] clicked!");
		
		waField.setFill(Color.RED);
	}

	private void handleClickOnMainButton() {
		System.out.println("Main Button clicked!");
		
		switch (mainButtonState) {
		case PROMPT_FOR_DICE_THROW:
			gameModel.throwDice();
			break;
			
		case DICE_THROWN:
			
			break;
			
		case PROMPT_FOR_NEXT_TURN:
			gameModel.clickEndTurn();
			break;

		case PROMPT_FOR_ACCEPT_CATEGORY:
			System.out.println("Chose category no:" + selectedCategoryNo);
			ICategory selectedCategory = IAPIFactory.factory.getCategories().getCategories().get(selectedCategoryNo);
			gameModel.chooseCategory(selectedCategory);
			break;

		default:
			break;
		}
	}

	private Parent loadParentFromFXML(String resourceName) {
		URL resource = getClass().getResource(resourceName);
		FXMLLoader fxmlLoader = new FXMLLoader(resource);
		
		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
            e.printStackTrace();
        }
		return root;
	}
	
	// ********************************************************
	// SETTER
	// ********************************************************
	
	private void setPlayerName(int playerNo, String name) {
		this.getBaseLabel(playerNo).setText(name);
		this.getStartLabel(playerNo).setText(name);
	}
	
	private void setPlayerColor(int playerNo, Paint color) {
		getStartField(playerNo).setFill(color);
		
		for (List<Circle> waFieldsForCategory : getWAFields(playerNo)) {
			for (Circle waField : waFieldsForCategory) {
				waField.setFill(color);
			}
		}
		
		for (Circle baseField: getBaseFields(playerNo)) {
			baseField.setFill(color);
		}
	}
	
	private void setMainButtonToPromptForDiceThrow() {
		mainButton.setText("Würfeln");
		mainButton.setGraphic(getThrowDicePromptImageView());
		mainButtonState = MainButtonState.PROMPT_FOR_DICE_THROW;
	}
	
	private void setMainButtonToDiceThrown(int facesNo, boolean shouldRethrow) {
		if (!shouldRethrow) {
			mainButton.setText("Wähle Wissensstreiter");
			mainButtonState = MainButtonState.DICE_THROWN;
		} else {
			mainButton.setText("Würfeln");
			mainButtonState = MainButtonState.PROMPT_FOR_DICE_THROW;
		}
		mainButton.setGraphic(getDiceImageView(facesNo));
	}
	
	private void setMainButtonToPromptForNextTurn() {
		mainButton.setText("Spielzug beenden");
//		mainButton.setGraphic(getNextTurnPromptImageView());
		mainButtonState = MainButtonState.PROMPT_FOR_NEXT_TURN;
	}
	
	private void setMainButtonToPromptForAcceptCategoryDecision(int categoryNo) {
		mainButton.setText("Kategorie " + Integer.toString(categoryNo) + " bestätigen");
		mainButton.setGraphic(getAcceptPromptImageView());
		mainButtonState = MainButtonState.PROMPT_FOR_ACCEPT_CATEGORY;
	}
	
	private void updateTurnOrder(List<String> playerNames, List<Paint> playerColors, List<Image> playerImages) {
		for (int i = 0; i < this.playerCount; i++) {
			if (playerNames != null && i < playerNames.size()) {
				this.turnOrderLabels.get(i).setText(playerNames.get(i));
			}
			if (playerColors != null && i < playerColors.size()) {
				this.turnOrderCircles.get(i).setFill(playerColors.get(i));
			}
			if (playerImages != null && i < playerImages.size()) {
				ImageView imgView = this.getImageViewForNode(this.turnOrderCircles.get(i));
				
				if (imgView != null) {
					imgView.setImage(playerImages.get(i));
					this.fitImageViewToNodeSize(imgView, this.turnOrderCircles.get(i));
				} else {
					addImageToNode(this.turnOrderCircles.get(i), playerImages.get(i), true);
				}
			}
		}
	}
	
	private void updateGameBoard() {
		removeWissensstreiterz();
		
		List<IField> fields = IAPIFactory.factory.getSpielbrett().getFields();
		
		for(int i = 0; i < fields.size(); i++) {
			IField field = fields.get(i);
			if (field.getWissensstreiter().size()>0) {
				int playerNo = field.getWissensstreiter().get(0).getOwner().getPlayerNumber();
				addWissensstreiterToField(this.fields.get(i), playerNo);
			}
		}
		ISpielbrett gameBoard = IAPIFactory.factory.getSpielbrett();
		for (IPlayer player : IAPIFactory.factory.getPlayers().getPlayers()) {
			int basefieldIndex = 0;
			for (IWissensstreiter ws : player.getAllPlayerWissensstreiter().getWissenstreiter()) {
				if(gameBoard.getFieldOfWissensstreiter(ws) == null) {
					addWissensstreiterToField(this.getBaseFields(player.getPlayerNumber()).get(basefieldIndex), player.getPlayerNumber());
					basefieldIndex++;
				}
			}
		}
		
		
	}
	
	// ********************************************************
	// GETTER
	// ********************************************************
	
	private String getPlayerKey(int playerNo) {
		String playerKey = null;
		
		switch (playerNo) {
		case 0:
			playerKey = PLAYER1_KEY;
			break;
			
		case 1:
			playerKey = PLAYER2_KEY;
			break;
			
		case 2:
			playerKey = PLAYER3_KEY;
			break;
			
		case 3:
			playerKey = PLAYER4_KEY;
			break;

		default:
			break;
		}
		
		return playerKey;
	}
	
	private int getPlayerNo(String playerKey) {
		int playerNo = -1;
		
		if (playerKey.equals(PLAYER1_KEY)) {
			playerNo = 0;
		}
		if (playerKey.equals(PLAYER2_KEY)) {
			playerNo = 1;
		}
		if (playerKey.equals(PLAYER3_KEY)) {
			playerNo = 2;
		}
		if (playerKey.equals(PLAYER4_KEY)) {
			playerNo = 3;
		}
		
		return playerNo;
	}
	
	private Map<String, Object> getPlayerMap(int playerNo) {
		return this.playerElements.get(getPlayerKey(playerNo));
	}
	
	@SuppressWarnings("unchecked")
	private List<Circle> getBaseFields(int playerNo) {
		return (List<Circle>)getPlayerMap(playerNo).get(BASE_FIELDS_KEY);
	}

	private Label getBaseLabel(int playerNo) {
		return (Label)getPlayerMap(playerNo).get(BASE_LABEL_KEY);
	}
	
	private Circle getStartField(int playerNo) {
		return fields.get(playerNo*(fields.size()/playerCount));
	}
	
	private Label getStartLabel(int playerNo) {
		return (Label)getPlayerMap(playerNo).get(START_LABEL_KEY);
	}
	
	private List<List<Circle>> getWAFields(int playerNo) {
		@SuppressWarnings("unchecked")
		List<List<Circle>> waCategories = (List<List<Circle>>)getPlayerMap(playerNo).get(WA_KEY);
		return waCategories;
	}
	
	private List<Circle> getWAFields(int playerNo, int categoryNo) {
		List<List<Circle>> waCategories = this.getWAFields(playerNo);
		return waCategories.get(categoryNo);
	}
	
	private ImageView getDiceImageView(int facesNo) {
		String facesNoString = Integer.toString(facesNo);
		
		String imgPath = "resources/Assets/dice_" + facesNoString + ".png";
		Image img = new Image(getClass().getResourceAsStream(imgPath));
		
		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(25);
    	imgView.setFitHeight(25);
    	
    	return imgView;
	}
	
	private ImageView getThrowDicePromptImageView() {
		Image img = new Image(getClass().getResourceAsStream("resources/Assets/throwDicePrompt.png"));
		
		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(25);
    	imgView.setFitHeight(25);
    	
    	return imgView;
	}
	
	private ImageView getNextTurnPromptImageView() {
		Image img = new Image(getClass().getResourceAsStream("resources/Assets/nextTurn.png"));
		
		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(25);
    	imgView.setFitHeight(25);
    	
    	return imgView;
	}
	
	private ImageView getAcceptPromptImageView() {
		Image img = new Image(getClass().getResourceAsStream("resources/Assets/checkmark.png"));
		
		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(25);
    	imgView.setFitHeight(25);
    	
    	return imgView;
	}
	
	private void willStartNewGame(NewGameInstance newGameInstance) {		
		gameModel.clickNewGame(newGameInstance);
	}
	
	private void overlayWithNodeFromFXML(String resourceName) {
		URL resource = getClass().getResource(resourceName);
		FXMLLoader fxmlLoader = new FXMLLoader(resource);
		
		Parent rootFromFXML = null;
		try {
			rootFromFXML = (Parent)fxmlLoader.load();
			
			if (fxmlLoader.getController() != null) {
				viewControllerForOverlayViews.put(rootFromFXML, fxmlLoader.getController());
			}
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		// optionally further configurations following (if class is of type IPresentable-interface)
		boolean yieldUserInteraction = false;
		if (fxmlLoader.getController() instanceof IPresentable) {
			IPresentable controller = (IPresentable)fxmlLoader.getController();
			controller.setPresenterView(this);
			
			if (controller.shouldPresenterViewYieldUserInteraction()) {
				yieldUserInteraction = true;
			}
		}
		if (fxmlLoader.getController() != null) {
			this.willPresentViewController(fxmlLoader.getController());
		}
		
		this.overlayWithNode(rootFromFXML, yieldUserInteraction);
	}
	
	private void willPresentViewController(Object viewController) {
		System.out.println("Will present viewController: " + viewController);
		
		if (viewController instanceof QuizGameQuestionViewController) {
			// Frage Parameter holen und hier setzen
			ICategory selectedCategory = IAPIFactory.factory.getCategories().getCategories().get(selectedCategoryNo);
			((QuizGameQuestionViewController) viewController).setupWithParameters("Kategorie I", "Vander", "Frage1:?!", Arrays.asList(new String[] {"Antwort1", "Antwort2", "Antwort3", "Antwort4"}), 0);
		}
		
		if (viewController instanceof QuizGameWinnerScreenViewController) {
			((QuizGameWinnerScreenViewController) viewController).setupWithParameters("FappelDieFap");
		}
	}
	
	private void willDismissOfViewController(Object viewController) {
		System.out.println("Dismiss of viewController: " + viewController);
		
		if (viewController instanceof QuizGameNewGameViewController) {
			willStartNewGame(((QuizGameNewGameViewController) viewController).result);
		}
	}
	
	// ********************************************************
	// OVERLAYABLE
	// ********************************************************
	
	@Override
	public void overlayWithNode(Node node, boolean yieldUserInteraction) {
		if (node instanceof Region) {
			Region region = (Region)node;
			region.setBackground(rootPane.getBackground());
			region.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), null)));
		}
		
		// optionally change mouse click listening behavior
		if (yieldUserInteraction) {
			for (Node prevNode : rootPane.getChildren()) {
				// if a node is the borderPane, we want to ensure, that its menuBar stays enabled
				if (prevNode == quizBoardBorderPane) {
					quizBoardGridPane.setMouseTransparent(true);
				} else {
					prevNode.setMouseTransparent(true);
				}
			}
		}
		
		rootPane.getChildren().add(node);
		
		// add to overlayViews
		if (overlayViews == null) {
			overlayViews = new LinkedList<Node>();
		}
		overlayViews.add(node);
	}
	
	@Override
	public void dismissOverlay(Node node) {
		rootPane.getChildren().remove(node);
		if (viewControllerForOverlayViews.get(node) != null) {
			willDismissOfViewController(viewControllerForOverlayViews.get(node));
			viewControllerForOverlayViews.remove(node);
		}
		overlayViews.remove(node);
		
		// change mouse click listening behavior
		Node children = rootPane.getChildren().get(rootPane.getChildren().size() - 1);
		if (children == quizBoardBorderPane) {
			quizBoardGridPane.setMouseTransparent(false);
		} else {
			children.setMouseTransparent(false);
		}
	}
	
	@Override
	public void dismissAllOverlays() {
		if (overlayViews != null) {
			List<Node> overlayViewsCopy = new ArrayList<Node>(overlayViews);
			for (Node overlayView : overlayViewsCopy) {
				dismissOverlay(overlayView);
			}
		}
	}
	
	// ********************************************************
	// OBSERVER
	// ********************************************************

	@Override
	public void update(GameState info) {
		System.out.println("Update called with :" + info);
		
		removeHighlightFromAllNodes();
		
		switch (info) {
		case NEW_GAME:
			IPlayers players = APIFactory.factory.getPlayers();
			for (int playerNo = 0; playerNo < players.count(); playerNo++) {
				setPlayerName(playerNo, players.getNameForPlayerNo(playerNo));
				setPlayerColor(playerNo, players.getColorForPlayerNo(playerNo));
			}
			
			gameModel.clickEndTurn();
			break;
			
		case NEW_TURN:
			List<IPlayer> playerOrder = IAPIFactory.factory.getPlayers().getPlayerOrderByCurrentPlayer(gameModel.getCurrentPlayer());
			List<String> playerNames = new LinkedList<>();
			List<Paint> playerColors = new LinkedList<>();
			List<Image> playerImages = new LinkedList<>();
			
			for(int i = 0; i < playerOrder.size(); i++) {
				IPlayer player = playerOrder.get(i);
				playerNames.add(player.getPlayerName());
				playerColors.add(player.getPlayerColor());
				playerImages.add(player.getPlayerImage());
			}
			
			updateTurnOrder(playerNames, playerColors, playerImages);
			updateGameBoard();
			
			setMainButtonToPromptForDiceThrow();
			break;
			
		case DICED_DRAWABLE:
			setMainButtonToDiceThrown(gameModel.getNumberDiced(), false);
			
			ISpielbrett gameBoard = IAPIFactory.factory.getSpielbrett();
			List<IWissensstreiter> drawableWS = gameModel.getDrawableWissensstreiter();
			for (IWissensstreiter wissensstreiter : drawableWS) {
				IField field = gameBoard.getFieldOfWissensstreiter(wissensstreiter);
				
				if (field == null) {
					System.out.println("playerno:"+gameModel.getCurrentPlayer().getPlayerNumber());
					for (int i = 0; i < IAPIFactory.factory.getPlayers().getCountOfWissensstreiterInHomeBase(IAPIFactory.factory.getGameModel().getCurrentPlayer()); i++) {
						List<Circle> circles = new ArrayList<Circle>();
						circles.add(getBaseFields(gameModel.getCurrentPlayer().getPlayerNumber()).get(i));
						highlightCircles(circles);						
					}
				}
				else {
					int fieldIndex = gameBoard.getFields().indexOf(field);
					highlightNodes(Arrays.asList(new Circle[] {this.fields.get(fieldIndex)}));
				}
			}
			
		case DICED_NOT_DRAWABLE_THROWABLE:
			setMainButtonToDiceThrown(gameModel.getNumberDiced(), true);
			break;
			
		case DICED_NOT_DRAWABLE_NOT_THROWABLE:
			setMainButtonToPromptForNextTurn();
			break;
			
		case DRAWN_NOT_OCCUPIED:
			updateGameBoard();
			setMainButtonToPromptForNextTurn();
			break;
			
		case DRAWN_OCCUPIED:
			highlightRectangles(this.categories);
			break;
			
		case SHOW_QUESTION:
			overlayWithNodeFromFXML("resources/FXML/Frage.fxml");
			
			break;
			
		case END_TURN_TEST:
			
//			break;
			
		case END_TURN_NORMAL:
			updateGameBoard();
			break;
			
		case END_GAME:
		
			break;

		default:
			break;
		}
	}
}

enum MainButtonState {
	PROMPT_FOR_DICE_THROW,
	DICE_THROWN,
	PROMPT_FOR_NEXT_TURN,
	PROMPT_FOR_ACCEPT_CATEGORY
}