package application.api;

import java.util.LinkedList;

import application.coreElements.Field;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface IPlayer {
	
	int getPlayerNumber();
	String getPlayerName();
	Image getPlayerImage();
	Color getPlayerColor();
	IWissenstandsanzeiger getWissenstandsanzeiger();
	LinkedList<Field> getOwnedFields();
	IAllPlayerWissensstreiter getAllPlayerWissensstreiter();
}
