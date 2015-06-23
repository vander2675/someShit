package application.useCaseWissenTesten;

import application.api.ICategory;
import application.api.IGameModel;
import application.api.IPlayer;
import application.api.IQuestion;
import application.api.ISpielbrett;
import application.api.IUseCaseWissenTesten;
import application.logic.IAPIFactory;
import application.state.GameState;

public class UseCaseWissenTesten implements IUseCaseWissenTesten {
	private ICategory choosenCategory;
	private IQuestion choosenQuestion;
	private boolean answerCorrect;
	private boolean isOponentTurn;
	
	public UseCaseWissenTesten() {
	}
	
	@Override
	public void chooseCategory(ICategory category) {
		isOponentTurn = true;
		this.choosenCategory = category;
		choosenQuestion = choosenCategory.getQuestions().getQuestions().get((int)Math.ceil((Math.random() * choosenCategory.getQuestions().count()) - 1));
		IAPIFactory.factory.getGameModel().setState(GameState.SHOW_QUESTION);
	}

	@Override
	public void chooseAnswer(String answer) {
		IPlayer currentPlayer = IAPIFactory.factory.getWissenTestenInstance().getCurrentPlayer();
		IWissenTestenInstance instance = IAPIFactory.factory.getWissenTestenInstance();

		if(choosenQuestion.isCorrectAnswer(answer)) {
			answerCorrect = true;
			instance.setPlayerAnswer(true);
			isOponentTurn = false;
			currentPlayer.getWissenstandsanzeiger().inkrementACategory(choosenCategory);
		} else {
			answerCorrect = false;
			currentPlayer.getWissenstandsanzeiger().dekrementACategory(choosenCategory);
			instance.setPlayerAnswer(false);
			instance.changeWissenTestenInstance();
			choosenQuestion = choosenCategory.getQuestions().getQuestions().get((int)Math.ceil((Math.random() * choosenCategory.getQuestions().count()) - 1));
		}
		perfomDrawAfterAnswer();
		if(instance.getCurrentPlayer().getWissenstandsanzeiger().isGameOver()) {
			IAPIFactory.factory.getGameModel().setState(GameState.END_GAME);
		} else {
			IAPIFactory.factory.getGameModel().setState(GameState.SHOW_ANSWER);			
		}
	}

	private void perfomDrawAfterAnswer() {
		ISpielbrett board = IAPIFactory.factory.getSpielbrett();
		IWissenTestenInstance instance = IAPIFactory.factory.getWissenTestenInstance();
		if (instance.getPlayerAnswer()) {
			board.drawToStartFieldFromOccupiedField(instance.getPlayerWS());
		} else {
			board.drawToHomeBaseFromOccupiedField(instance.getPlayerWS());
		}
	}

	@Override
	public void timeUp() {
		IWissenTestenInstance instance = IAPIFactory.factory.getWissenTestenInstance();
		ISpielbrett board = IAPIFactory.factory.getSpielbrett();
		
		instance.getCurrentPlayer().getWissenstandsanzeiger().dekrementACategory(choosenCategory);
		board.drawToHomeBaseFromOccupiedField(instance.getPlayerWS());
		instance.changeWissenTestenInstance();
		
		IAPIFactory.factory.getGameModel().setState(GameState.WAIT_FOR_DONE);
	}

	@Override
	public void clickDone() {
		IGameModel gameModel = IAPIFactory.factory.getGameModel();
		if(isOponentTurn) {
			gameModel.setState(GameState.SHOW_QUESTION);
			isOponentTurn = false;
		} else {
			gameModel.setState(GameState.END_TURN_TEST);			
		}
	}

	@Override
	public void clickEndTurn() {
		IAPIFactory.factory.getGameModel().setState(GameState.NEW_TURN);
	}

	@Override
	public boolean isAnswerCorrect() {
		return answerCorrect;
	}

	@Override
	public IQuestion getQuestion() {
		return choosenQuestion;
	}

	@Override
	public ICategory getChoosenCategory() {
		return choosenCategory;
	}
}
