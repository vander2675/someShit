package application.useCaseWissenTesten;

import application.api.IPlayer;

public class WissenTestenInstance implements IWissenTestenInstance {
	IPlayer currentPlayer;
	IPlayer currentOponent;
	
	@Override
	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}
	@Override
	public IPlayer getCurrentOponent() {
		return currentOponent;
	}
	@Override
	public void changeWissenTestenInstance(IPlayer currentPlayer, IPlayer currentOponent) {
		this.currentPlayer = currentPlayer;
		this.currentOponent = currentOponent;
	}	
}
