package application.gui;

import javafx.event.EventType;
import javafx.scene.input.InputEvent;
import javafx.scene.shape.Circle;
import application.api.IGameModel;
import application.logic.GameModel;
import application.logic.IAPIFactory;
public class Controller {

	
	private Views lnkView;
	private IGameModel lnkModel;
	
	public Controller(Views views) {
		this.lnkView = views;
		lnkModel = IAPIFactory.factory.getGameModel();
	}
	
	
	public void handleClickThrowDice(InputEvent event) {
		lnkModel.throwDice();
	}
	
	
}
