package application.useCaseWissenTesten;

import application.api.IPlayer;
import application.api.IWissensstreiter;

public class WissenTestenInstance implements IWissenTestenInstance {
	IPlayer currentPlayer;
	IPlayer currentOponent;
	IWissensstreiter playerWS;
	IWissensstreiter oponentWS;
	boolean playerAnswer;
	boolean oponentAnswer;
	boolean wasSwapped;
	
	@Override
	public void setUp(IPlayer player, IPlayer oponent, IWissensstreiter playerWS, IWissensstreiter oponentWS) {
		this.currentPlayer = player;
		this.currentOponent = oponent;
		this.playerWS = playerWS;
		this.oponentWS = oponentWS;
	}
	
	@Override
	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}
	@Override
	public IPlayer getCurrentOponent() {
		return currentOponent;
	}
	@Override
	public void changeWissenTestenInstance() {
		IPlayer bufferPlayer = this.currentPlayer;
		this.currentPlayer = this.currentOponent;
		this.currentOponent = bufferPlayer;
		
		IWissensstreiter bufferWS = this.playerWS;
		this.playerWS = this.oponentWS;
		this.oponentWS = bufferWS;
		
		boolean bufferAnswer = this.playerAnswer;
		this.playerAnswer = this.oponentAnswer;
		this.oponentAnswer = bufferAnswer;
		this.wasSwapped = true;
	}
	@Override
	public IWissensstreiter getPlayerWS() {
		return this.playerWS;
	}
	@Override
	public IWissensstreiter getOponentWS() {
		return this.oponentWS;
	}
	@Override
	public boolean getPlayerAnswer() {
		return this.playerAnswer;
	}
	@Override
	public boolean getOponentAnswer() {
		return this.oponentAnswer;
	}
	@Override
	public boolean getWasSwapped() {
		return this.wasSwapped;
	}
	@Override
	public void setPlayerAnswer(boolean answer) {
		this.playerAnswer = answer;
	}
}
