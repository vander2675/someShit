package application.useCaseNewGame;

import application.api.IUseCaseNewGame;
import application.logic.IAPIFactory;
public class UseCaseNewGame implements IUseCaseNewGame {

	@Override
	public void clickNewGame(NewGameInstance instance) {
		IAPIFactory.factory.getSpielbrett().newGame(instance);
		IAPIFactory.factory.getPlayers().newGame(instance);
	}
}
