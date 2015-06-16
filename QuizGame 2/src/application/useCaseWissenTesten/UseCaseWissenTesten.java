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
		choosenQuestion = choosenCategory.getQuestions().getQuestions().get((int) (Math.random() * choosenCategory.getQuestions().count()));
		IAPIFactory.factory.getGameModel().setState(GameState.SHOW_QUESTION);
	}

	@Override
	public void chooseAnswer(String answer) {
		if(choosenQuestion.isCorrectAnswer(answer)) {
			answerCorrect = true;
			isOponentTurn = false;
			IAPIFactory.factory.getGameModel().getCurrentPlayer().getWissenstandsanzeiger().inkrementACategory(choosenCategory);
		} else {
			answerCorrect = false;
			IAPIFactory.factory.getGameModel().getCurrentPlayer().getWissenstandsanzeiger().dekrementACategory(choosenCategory);
			IWissenTestenInstance instance = IAPIFactory.factory.getWissenTestenInstance();
			instance.changeWissenTestenInstance(instance.getCurrentOponent(), instance.getCurrentPlayer());
		}
		perfomDrawAfterAnswer();
		IAPIFactory.factory.getGameModel().setState(GameState.SHOW_ANSWER);
	}

	private void perfomDrawAfterAnswer() {
		IPlayer currentPlayer = IAPIFactory.factory.getGameModel().getCurrentPlayer();
		ISpielbrett spielbrett = IAPIFactory.factory.getSpielbrett();
		if(answerCorrect && spielbrett.getStartFieldByPlayer(currentPlayer).getWissensstreiter().size() > 0) {
			spielbrett.drawToHomeBaseFromOccupiedField(currentPlayer);
		} else {
			spielbrett.drawToStartFieldFromOccupiedField(currentPlayer);
		}
	}

	@Override
	public void timeUp() {
		IAPIFactory.factory.getGameModel().getCurrentPlayer().getWissenstandsanzeiger().dekrementACategory(choosenCategory);
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
}
