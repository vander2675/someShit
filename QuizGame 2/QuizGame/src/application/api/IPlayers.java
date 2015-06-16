package application.api;

import java.util.HashSet;

public interface IPlayers extends ICollection {
	HashSet<IPlayer> getPlayers();
	IPlayer getNextPlayer(IPlayer current);
}
