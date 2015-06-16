package application.api;

import java.util.List;

import application.state.GameState;
import application.state.ICallback;


public interface IGameModel extends ISubject<GameState>, IUseCaseZugDurchfuehren, IUseCaseWissenTesten, IUseCaseNewGame, IUseCaseEndGame, ICallback{
	IPlayer getCurrentPlayer();
	GameState getState();
	List<IWissensstreiter> getDrawableWissensstreiter();
	IPlayer getCurrentOponent();
}
