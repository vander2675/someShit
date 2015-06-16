package application.logic;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import application.api.IAllPlayerWissensstreiter;
import application.api.ICategories;
import application.api.IDice;
import application.api.IField;
import application.api.IGameModel;
import application.api.IPlayer;
import application.api.IPlayers;
import application.api.ISpielbrett;
import application.api.IUseCaseNewGame;
import application.api.IUseCaseWissenTesten;
import application.api.IUseCaseZugDurchfuehren;
import application.api.IWissensstreiter;
import application.api.IWissenstandsanzeiger;
import application.useCaseWissenTesten.IWissenTestenInstance;

public interface IAPIFactory {
	IAPIFactory factory = new APIFactory();
	
	IGameModel getGameModel();
	IUseCaseWissenTesten getUseCaseWissenTesten();
	IUseCaseZugDurchfuehren getUseCaseSpielzugDurchfuehren();
	IUseCaseNewGame getUseCaseNewGame();
	IPlayers getPlayers();
	ISpielbrett getSpielbrett();
	IDice getDice();
	ICategories getCategories();
	IWissenstandsanzeiger makeWissenstandsanzeiger();
	IPlayer makePlayer(int number, String name, Image image, Color color);
	IWissensstreiter makeWissensstreiter(IPlayer player);
	IAllPlayerWissensstreiter makeAllPlayerWissensstreiter(IPlayer player);
	IField makeField();
	IWissenTestenInstance getWissenTestenInstance();
}
