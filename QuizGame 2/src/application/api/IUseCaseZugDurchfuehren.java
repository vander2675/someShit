package application.api;

import java.util.List;

public interface IUseCaseZugDurchfuehren {
	void throwDice();
	void chooseWS(IWissensstreiter ws);
	void clickEndTurn();
	List<IWissensstreiter> getDrawableWissensstreiter();
	int getNumberDiced();
}