package application.useCaseNewGame;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class NewGameInstance {
	private String[] playerNames = new String[4];
	private Color[] playerColors = new Color[4];
	Image[] playerImages = new Image[4];
	
	public void setPlayer1(String name, Color color, Image image) {
		this.playerNames[0] = name;
		this.playerColors[0] = color;
		this.playerImages[0] = image;
	}
	
	public void setPlayer2(String name, Color color, Image image) {
		this.playerNames[0] = name;
		this.playerColors[0] = color;
		this.playerImages[0] = image;
	}
	public void setPlayer3(String name, Color color, Image image) {
		this.playerNames[0] = name;
		this.playerColors[0] = color;
		this.playerImages[0] = image;
	}
	
	public void setPlayer4(String name, Color color, Image image) {
		this.playerNames[0] = name;
		this.playerColors[0] = color;
		this.playerImages[0] = image;
	}

	public String[] getPlayerNames() {
		return playerNames;
	}

	public Color[] getPlayerColors() {
		return playerColors;
	}

	public Image[] getPlayerImages() {
		return playerImages;
	}
}
