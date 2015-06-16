package application.useCaseZugDurchfuehren;

import java.util.List;
import java.util.Map.Entry;

import application.api.IField;
import application.api.IGameModel;
import application.api.IPlayer;
import application.api.IUseCaseZugDurchfuehren;
import application.api.IWissensstreiter;
import application.logic.IAPIFactory;
import application.state.GameState;

public class UseCaseZugDurchfuehren implements IUseCaseZugDurchfuehren{

	private int numberDiced;	
	private IGameModel gameModel;
	private int countDicedInARow;
	private List<IWissensstreiter> drawableWissensstreiter;
	
	public UseCaseZugDurchfuehren() {
		gameModel = IAPIFactory.factory.getGameModel();
		countDicedInARow = 0; // unsch�n!!!! 
	}
	
	@Override
	public void throwDice() {
		numberDiced = IAPIFactory.factory.getDice().throwDice();
		countDicedInARow++;
		IPlayer currentPlayer = gameModel.getCurrentPlayer();
		drawableWissensstreiter.clear();

		for (Entry<IWissensstreiter, IField> entry: currentPlayer.getAllPlayerWissensstreiter().getDrawnWissensstreiter(numberDiced).entrySet()) {
			boolean drawable = true;
			
			if(entry.getValue() == null && numberDiced != 6) {
				drawable = false;
			} 
			
			else if(entry.getValue() != null) {
				for (IWissensstreiter ws : entry.getValue().getWissensstreiter()) {
					if (ws.equals(entry.getKey())) {
						drawable = false;
					}
				}
				if (drawable) {
					drawableWissensstreiter.add(entry.getKey());
				}
			}
		}
		if (drawableWissensstreiter.size() > 0) {
			gameModel.setState(GameState.DICED_DRAWABLE);
		} else if (countDicedInARow < 2) {
			gameModel.setState(GameState.DICED_NOT_DRAWABLE_THROWABLE);
		} else {
			gameModel.setState(GameState.DICED_NOT_DRAWABLE_NOT_THROWABLE);
		}
	}

	@Override
	public void chooseWS(IWissensstreiter ws) {
		if(drawableWissensstreiter.contains(ws)) {
			ws.performDraw(numberDiced);			
		}
		IField fieldAfterDraw = IAPIFactory.factory.getSpielbrett().getFieldOfWissensstreiter(ws);
		boolean occupied = false;
		if(fieldAfterDraw.getWissensstreiter().size() > 1) {
				occupied = true;
		}
		
		if(occupied) {
			gameModel.setState(GameState.DRAWN_OCCUPIED);
			for (IWissensstreiter iWissensstreiter : fieldAfterDraw.getWissensstreiter()) {
				if(!iWissensstreiter.getOwner().equals(IAPIFactory.factory.getGameModel().getCurrentPlayer())) {
					IAPIFactory.factory.getWissenTestenInstance().changeWissenTestenInstance(
							IAPIFactory.factory.getGameModel().getCurrentPlayer(), iWissensstreiter.getOwner());
				}
			}
		} else {
			gameModel.setState(GameState.DRAWN_NOT_OCCUPIED);
		}
	}

	@Override
	public void clickEndTurn() {
		gameModel.setState(GameState.END_TURN_NORMAL);
		countDicedInARow = 0;
	}

	@Override
	public List<IWissensstreiter> getDrawableWissensstreiter() {
		return drawableWissensstreiter;
	}
	
	@Override
	public int getNumberDiced() {
		return numberDiced;
	}
}
