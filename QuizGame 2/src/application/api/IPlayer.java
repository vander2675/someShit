package application.api;

import java.util.LinkedList;

import application.coreElements.Field;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public interface IPlayer {
	
	int getPlayerNumber();
	String getPlayerName();
	Image getPlayerImage();
	Paint getPlayerColor();
	IWissenstandsanzeiger getWissenstandsanzeiger();
	LinkedList<Field> getOwnedFields();
	IAllPlayerWissensstreiter getAllPlayerWissensstreiter();
}
