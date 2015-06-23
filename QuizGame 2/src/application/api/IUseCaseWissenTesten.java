package application.api;

public interface IUseCaseWissenTesten {
	void chooseCategory(ICategory category);
	void chooseAnswer(String answer);
	void timeUp();
	void clickDone();
	void clickEndTurn();
	boolean isAnswerCorrect();
	IQuestion getQuestion();
	ICategory getChoosenCategory();
}
