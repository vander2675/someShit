package application.coreElements;

import java.util.HashSet;

import application.api.IPlayer;
import application.api.IPlayers;
import application.logic.IAPIFactory;
import application.useCaseNewGame.NewGameInstance;


public class Players implements IPlayers{
	HashSet<IPlayer> players;

	public Players() {
	}

	@Override
	public HashSet<IPlayer> getPlayers() {
		return players;
	}

	@Override
	public int count() {
		return players.size();
	}

	@Override
	public void newGame(NewGameInstance instance) {
		players.clear();
		for (int i = 0; i < instance.getPlayerNames().length; i++) {
			players.add(IAPIFactory.factory.makePlayer(i, instance.getPlayerNames()[i], instance.getPlayerImages()[i], instance.getPlayerColors()[i]));
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
}
