package application.useCaseEndGame;

import application.api.IPlayer;
import application.api.IUseCaseEndGame;
import application.logic.IAPIFactory;

public class UseCaseEndGame implements IUseCaseEndGame {
	
	@Override
	public String getWinner() {
		for (IPlayer player : IAPIFactory.factory.getPlayers().getPlayers()) {
			if(player.getWissenstandsanzeiger().isGameOver()) {
				return player.getPlayerName();
			}
		}
		return null;
	}
}
