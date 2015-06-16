package application.logic;
import java.util.LinkedList;
import java.util.List;

import application.api.*;
import application.state.*;
import application.useCaseNewGame.NewGameInstance;

public class GameModel implements IGameModel {
	
	private IUseCaseNewGame useCaseNewGame;
	private IUseCaseZugDurchfuehren useCaseZugDurchfuehren;
	private IUseCaseWissenTesten useCaseWissenTesten;
	private IUseCaseEndGame useCaseEndGame;
	private GameState state;
	private LinkedList<IObserver<GameState>> lnkObserver;
	private IPlayer currentPlayer;
	private IPlayer currentOponent;
	
	public GameModel() {
		useCaseZugDurchfuehren = IAPIFactory.factory.getUseCaseSpielzugDurchfuehren();
		useCaseWissenTesten = IAPIFactory.factory.getUseCaseWissenTesten();
		useCaseNewGame = IAPIFactory.factory.getUseCaseNewGame();
		state = GameState.NEW_GAME;
		this.lnkObserver = new LinkedList<IObserver<GameState>>();
	}

	@Override
	public void detach(IObserver<GameState> obs) {
		if(this.lnkObserver.contains(obs)) {
			this.lnkObserver.remove(obs);
		}
	}

	@Override
	public void attach(IObserver<GameState> obs) {
		this.lnkObserver.add(obs);
	}

	@Override
	public void throwDice() {
		if(this.state == GameState.DICED_NOT_DRAWABLE_THROWABLE || state == GameState.NEW_TURN) {
			useCaseZugDurchfuehren.throwDice();
			updateObservers(); 
		}
	}

	@Override
	public void chooseWS(IWissensstreiter ws) {
		if (this.state == GameState.DICED_DRAWABLE) {
			useCaseZugDurchfuehren.chooseWS(ws);
			if(state == GameState.DRAWN_OCCUPIED) {
				
			}
			updateObservers();			
		}
	}

	@Override
	public void clickEndTurn() {
		if (this.state == GameState.END_TURN_NORMAL) {
			useCaseZugDurchfuehren.clickEndTurn();
			setCurrentPlayerToNextPlayer();
		} else if (state == GameState.END_TURN_TEST) {
			useCaseWissenTesten.clickEndTurn();
			setCurrentPlayerToNextPlayer();
		} else if (state == GameState.NEW_GAME) {
			this.state = GameState.NEW_TURN;
		}
		currentPlayer = IAPIFactory.factory.getPlayers().getNextPlayer(currentPlayer);
		updateObservers();
	}

	@Override
	public void chooseCategory(ICategory category) {
		if (this.state == GameState.DRAWN_OCCUPIED) {
			useCaseWissenTesten.chooseCategory(category);
			updateObservers();
		}
	}

	@Override
	public void chooseAnswer(String answer) {
		if (this.state == GameState.SHOW_QUESTION) {
			useCaseWissenTesten.chooseAnswer(answer);
			updateObservers();
		}
	}

	@Override
	public void timeUp() {
		if(this.state ==GameState.SHOW_QUESTION) {
			useCaseWissenTesten.timeUp();
			updateObservers();
		}
	}

	@Override
	public void clickDone() {
		if(this.state == GameState.SHOW_ANSWER || this.state == GameState.WAIT_FOR_DONE) {
			useCaseWissenTesten.clickDone();
			updateObservers();
		}
	}

	@Override
	public void setState(GameState state) {
		this.state = state;
	}

	@Override
	public GameState getState() {
		return state;
	}
	
	private void updateObservers() {
		for (IObserver<GameState> item : this.lnkObserver) {
			item.update(state);
		}
	}

	@Override
	public void clickNewGame(NewGameInstance instance) {
		useCaseNewGame.clickNewGame(instance);
		currentPlayer = IAPIFactory.factory.getPlayers().getPlayers().get(0);
		updateObservers();
	}

	@Override
	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}
	
	@Override
	public List<IWissensstreiter> getDrawableWissensstreiter() {
		return useCaseZugDurchfuehren.getDrawableWissensstreiter();			
	}
	
	@Override
	public int getNumberDiced() {
		return useCaseZugDurchfuehren.getNumberDiced();
	}
		
	private void setCurrentPlayerToNextPlayer() {
		currentPlayer = IAPIFactory.factory.getPlayers().getNextPlayer(currentPlayer);
	}

	@Override
	public boolean isAnswerCorrect() {
		return useCaseWissenTesten.isAnswerCorrect();
	}

	@Override
	public IQuestion getQuestion() {
		return useCaseWissenTesten.getQuestion();
	}

	@Override
	public String getWinner() {
		return useCaseEndGame.getWinner();
	}

	@Override
	public IPlayer getCurrentOponent() {
		return currentOponent;
	}
}
