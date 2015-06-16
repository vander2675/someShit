package application.useCaseNewGame;

import application.api.IUseCaseNewGame;
import application.logic.IAPIFactory;
import application.state.GameState;
public class UseCaseNewGame implements IUseCaseNewGame {

	@Override
	public void clickNewGame(NewGameInstance instance) {
		IAPIFactory.factory.getSpielbrett().newGame(instance);
		IAPIFactory.factory.getPlayers().newGame(instance);
		IAPIFactory.factory.getGameModel().setState(GameState.NEW_GAME);
	}
}
