package application.logic;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
import application.coreElements.AllPlayerWissensstreiter;
import application.coreElements.Categories;
import application.coreElements.Dice;
import application.coreElements.Field;
import application.coreElements.Player;
import application.coreElements.Players;
import application.coreElements.Spielbrett;
import application.coreElements.Wissensstreiter;
import application.coreElements.Wissenstandsanzeiger;
import application.useCaseNewGame.UseCaseNewGame;
import application.useCaseWissenTesten.IWissenTestenInstance;
import application.useCaseWissenTesten.UseCaseWissenTesten;
import application.useCaseWissenTesten.WissenTestenInstance;
import application.useCaseZugDurchfuehren.UseCaseZugDurchfuehren;

public class APIFactory implements IAPIFactory {
	
	private IGameModel gameModel;
	private IUseCaseNewGame useCaseNewGame;
	private IUseCaseZugDurchfuehren useCaseSpielzugDurchfuehren;
	private IUseCaseWissenTesten useCaseWissenTesten;
	private ISpielbrett spielbrett;
	private IDice dice;
	private ICategories categories;
	private IPlayers players;
	private IWissenTestenInstance instance;

	@Override
	public IGameModel getGameModel() { return getGameModelImpl(); }	@Override

	public IUseCaseNewGame getUseCaseNewGame() { return getUseCaseNewGameImpl(); }

	@Override
	public IUseCaseWissenTesten getUseCaseWissenTesten() { return getUseCaseWissenTestenImpl(); }

	@Override
	public IUseCaseZugDurchfuehren getUseCaseSpielzugDurchfuehren() { return getUseCaseSpielzugDurchfuehrenImpl(); }
	
	@Override
	public ISpielbrett getSpielbrett() { return getSpielbrettImpl(); }

	@Override
	public IDice getDice() { return getDiceImpl(); }

	@Override
	public ICategories getCategories() { return getCategoriesImpl(); }
	
	@Override
	public IPlayers getPlayers() { return getPlayersImpl(); }

	@Override
	public IWissenstandsanzeiger makeWissenstandsanzeiger() { return makeWissenstandsanzeigerImpl(); }
	
	@Override
	public IPlayer makePlayer(int number, String name, Image image, Paint color) { return makePlayerImpl(number, name, image, color); }
	
	@Override
	public IWissensstreiter makeWissensstreiter(IPlayer player) { return makeWissenstreiterImpl(player); }
	
	@Override
	public IField makeField() { return makeFieldImpl(); }
	
	@Override
	public IAllPlayerWissensstreiter makeAllPlayerWissensstreiter(IPlayer player) { return makeAllPlayerWissensstreiterImpl(player); }
	
	@Override
	public IWissenTestenInstance getWissenTestenInstance() { return getWissenTestenInstanceImpl(); }
	
	// private initializers
	
	private IGameModel getGameModelImpl() {
		if (this.gameModel == null) {
			this.gameModel = new GameModel();
		}
		
		return this.gameModel;
	}

	private IUseCaseWissenTesten getUseCaseWissenTestenImpl() {
		if (this.useCaseWissenTesten == null) {
			this.useCaseWissenTesten = new UseCaseWissenTesten();
		}
		
		return this.useCaseWissenTesten;
	}
	
	private IUseCaseZugDurchfuehren getUseCaseSpielzugDurchfuehrenImpl() {
		if (this.useCaseSpielzugDurchfuehren == null) {
			this.useCaseSpielzugDurchfuehren = new UseCaseZugDurchfuehren();
		}
		
		return this.useCaseSpielzugDurchfuehren;
	}
	
	private ISpielbrett getSpielbrettImpl() {
		if (this.spielbrett == null) {
			this.spielbrett = new Spielbrett();
		}
		return this.spielbrett;
	}
	
	private IDice getDiceImpl() {
		if (this.dice == null) {
			this.dice = new Dice();
		}
		return this.dice;
	}
	
	private ICategories getCategoriesImpl() {
		if (this.categories == null) {
			this.categories = new Categories();
		}
		return this.categories;
	}
		
	private IUseCaseNewGame getUseCaseNewGameImpl() {
		if (this.useCaseNewGame == null) {
			this.useCaseNewGame = new UseCaseNewGame();
		}
		return this.useCaseNewGame;
	}
	
	private IPlayers getPlayersImpl() {
		if(this.players == null) {
			this.players = new Players();
		}
		return this.players;
	}
	
	private IWissenstandsanzeiger makeWissenstandsanzeigerImpl() {
		return new Wissenstandsanzeiger();
	}
	
	private IPlayer makePlayerImpl(int number, String name, Image image, Paint color)  {
		return new Player(number, name, image, color);
	}
	
	private IWissensstreiter makeWissenstreiterImpl(IPlayer player) {
		return new Wissensstreiter(player);
	}
	
	private IField makeFieldImpl() {
		return new Field();
	}
	
	private IAllPlayerWissensstreiter makeAllPlayerWissensstreiterImpl(IPlayer player) {
		return new AllPlayerWissensstreiter(player);
	}
	
	private IWissenTestenInstance getWissenTestenInstanceImpl() {
		if (instance == null) {
			this.instance = new WissenTestenInstance();
		}
		return instance;
	}
}
