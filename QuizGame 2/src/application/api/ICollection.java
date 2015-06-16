package application.api;

import application.useCaseNewGame.NewGameInstance;

public interface ICollection {
	int playerNumber = 3;
	
	int count();
	void newGame(NewGameInstance instance);
}
