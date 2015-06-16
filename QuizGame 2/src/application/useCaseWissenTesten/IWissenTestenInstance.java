package application.useCaseWissenTesten;

import application.api.IPlayer;

public interface IWissenTestenInstance {
	IPlayer getCurrentPlayer();
	IPlayer getCurrentOponent();
	void changeWissenTestenInstance(IPlayer currentPlayer, IPlayer currentOponent);
}
