package application.api;

import java.util.HashSet;
import java.util.List;

import javafx.scene.paint.Paint;

public interface IPlayers extends ICollection {
	List<IPlayer> getPlayers();
	IPlayer getNextPlayer(IPlayer current);
	String getNameForPlayerNo(int playerNo);
	Paint getColorForPlayerNo(int playerNo);
	List<IPlayer> getPlayerOrderByCurrentPlayer(IPlayer current);
}
