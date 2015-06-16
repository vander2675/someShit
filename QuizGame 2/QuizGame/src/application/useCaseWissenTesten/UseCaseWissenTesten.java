package application.useCaseWissenTesten;

import sun.security.jca.GetInstance.Instance;
import application.api.ICategory;
import application.api.IGameModel;
import application.api.IPlayer;
import application.api.IQuestion;
import application.api.ISpielbrett;
import application.api.IUseCaseWissenTesten;
import application.logic.APIFactory;
import application.logic.IAPIFactory;
import application.state.GameState;

public class UseCaseWissenTesten implements IUseCaseWissenTesten {
	private ICategory choosenCategory;
	private IQuestion choosenQuestion;
	private boolean answerCorrect;
	private IGameModel gameModel;
	private boolean isOponentTurn;
	
	public UseCaseWissenTesten() {
		this.gameModel = IAPIFactory.factory.getGameModel();
	}
	
	@Override
	public void chooseCategory(ICategory category) {
		isOponentTurn = true;
		this.choosenCategory = category;
		choosenQuestion = choosenCategory.getQuestions().getQuestions().get((int) (Math.random() * choosenCategory.getQuestions().count()));
		gameModel.setState(GameState.SHOW_QUESTION);
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
		gameModel.setState(GameState.SHOW_ANSWER);
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
		gameModel.setState(GameState.WAIT_FOR_DONE);
	}

	@Override
	public void clickDone() {
		if(isOponentTurn) {
			gameModel.setState(GameState.SHOW_QUESTION);
			isOponentTurn = false;
		} else {
			gameModel.setState(GameState.END_TURN_TEST);			
		}
	}

	@Override
	public void clickEndTurn() {
		gameModel.setState(GameState.NEW_TURN);
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
