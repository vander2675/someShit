package application.useCaseNewGame;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class NewGameInstance {
	
	private List<String> playerNames = new ArrayList<>();
	private List<Paint> playerColors = new ArrayList<>();
	private List<Image> playerImages = new ArrayList<>();
	
	public void addPlayer(String name, Paint color, Image image) {
		this.playerNames.add(name);
		this.playerColors.add(color);
		this.playerImages.add(image);
	}
	
	public String getPlayerName(int playerNoIndex) {
		return playerNames.get(playerNoIndex);
	}
	
	public Paint getPlayerColor(int playerNoIndex) {
		return playerColors.get(playerNoIndex);
	}
	
	public Image getPlayerImage(int playerNoIndex) {
		return playerImages.get(playerNoIndex);
	}
	
	public int playerCount() {
		return playerNames.size();
	}
	
	@Override
	public String toString() {
		String string = playerNames.toString() + playerColors.toString() + playerImages.toString();
		return string;
	}

}
