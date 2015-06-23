package application.useCaseWissenTesten;

import application.api.IPlayer;
import application.api.IWissensstreiter;

public interface IWissenTestenInstance {
	IPlayer getCurrentPlayer();
	IPlayer getCurrentOponent();
	IWissensstreiter getPlayerWS();
	IWissensstreiter getOponentWS();
	boolean getPlayerAnswer();
	boolean getOponentAnswer();
	void changeWissenTestenInstance();
	boolean getWasSwapped();
	void setPlayerAnswer(boolean answer);
	void setUp(IPlayer player, IPlayer oponent, IWissensstreiter playerWS, IWissensstreiter oponentWS);
}
