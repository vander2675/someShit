package application.api;

import java.util.List;

public interface IField {
	List<IWissensstreiter> getWissensstreiter();
	void addWissensstreiter(IWissensstreiter ws);
	void removeWissensstreiter(IWissensstreiter ws);
}
