package application.coreElements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.paint.Paint;
import application.api.IPlayer;
import application.api.IPlayers;
import application.logic.IAPIFactory;
import application.useCaseNewGame.NewGameInstance;


public class Players implements IPlayers{
	List<IPlayer> players;

	public Players() {
		players = new ArrayList<IPlayer>();
	}

	@Override
	public List<IPlayer> getPlayers() {
		return players;
	}

	@Override
	public int count() {
		return players.size();
	}

	@Override
	public void newGame(NewGameInstance instance) {
		players.clear();
		for (int i = 0; i < instance.playerCount(); i++) {
			players.add(IAPIFactory.factory.makePlayer(i, instance.getPlayerName(i), instance.getPlayerImage(i), instance.getPlayerColor(i)));
		}
	}
	
	@Override
	public IPlayer getNextPlayer(IPlayer current) {
		int currentPlayerNumber = current.getPlayerNumber();
		int nextPlayerNumber;
		if(currentPlayerNumber == 3) {
			nextPlayerNumber = 0;
		} else {
			nextPlayerNumber = currentPlayerNumber++;
		}
		
		for (IPlayer player : players) {
			if(player.getPlayerNumber() == nextPlayerNumber) {
				return player;
			}
		}
		return null;
	}

	@Override
	public String getNameForPlayerNo(int playerNo) {
		return players.get(playerNo).getPlayerName();
	}

	@Override
	public Paint getColorForPlayerNo(int playerNo) {
		return players.get(playerNo).getPlayerColor();
	}

	@Override
	public List<IPlayer> getPlayerOrderByCurrentPlayer(IPlayer current) {
		LinkedList<IPlayer> result = new LinkedList<>();
//		result.add(current);
		int currentIndex = current.getPlayerNumber();
		for (int i = 0; i < 4; i++) {
			currentIndex = getNextPlayerIndexWithoutOverflow(currentIndex);
			result.add(players.get(getNextPlayerIndexWithoutOverflow(currentIndex)));
		}
		return result;
	}
	
	private int getNextPlayerIndexWithoutOverflow(int current) {
		if(current == 3) {
			return 0;
		}else {
			current++;
		}
		return current;
	}
}
