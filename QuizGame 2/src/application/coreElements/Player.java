package application.coreElements;

import java.util.LinkedList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import application.api.IAllPlayerWissensstreiter;
import application.api.IPlayer;
import application.api.IWissenstandsanzeiger;
import application.logic.IAPIFactory;


public class Player implements IPlayer {
	private IWissenstandsanzeiger wsa;
	private int playerNumber;
	private String playerName;
	private Image image;
	private Paint color;
	private LinkedList<Field> ownedFields;
	private IAllPlayerWissensstreiter allPlayerWissensstreiter;
	
	public Player(int playerNumber, String playerName, Image image, Paint color) {
		this.playerName = playerName;
		this.playerNumber = playerNumber;
		this.image = image;
		this.color = color;
		ownedFields = new LinkedList<Field>();
		wsa = IAPIFactory.factory.makeWissenstandsanzeiger();
		allPlayerWissensstreiter = IAPIFactory.factory.makeAllPlayerWissensstreiter(this);
	}
	
	public int getPlayerNumber(){
		return playerNumber;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public Image getPlayerImage() {
		return image;
	}

	@Override
	public Paint getPlayerColor() {
		return color;
	}

	@Override
	public LinkedList<Field> getOwnedFields() {
		return ownedFields;
	}
	
	@Override
	public IWissenstandsanzeiger getWissenstandsanzeiger() {
		return wsa;
	}
		
	@Override
	public IAllPlayerWissensstreiter getAllPlayerWissensstreiter() {
		return allPlayerWissensstreiter;
	};
}
