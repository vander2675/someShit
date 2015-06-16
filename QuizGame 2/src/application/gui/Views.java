package application.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import application.api.IGameModel;
import application.api.IObserver;
import application.logic.IAPIFactory;
import application.state.GameState;
public class Views extends Application implements IObserver<GameState> {

	private Controller lnkController;
	private IGameModel lnkModel;
	
	public void initGame(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		
		Scene scene = new Scene(root, 300, 300);
				
		primaryStage.setScene(scene);
		
		Rectangle rect = new Rectangle(100, 100);
		rect.setFill(Color.AQUAMARINE);
		root.getChildren().add(rect);
		
		primaryStage.show();
		
		lnkController = new Controller(this);
		lnkModel = IAPIFactory.factory.getGameModel();
		lnkModel.attach(this);
	}

	@Override
	public void update(GameState state) {
		// TODO Auto-generated method stub
		
	}	
}
