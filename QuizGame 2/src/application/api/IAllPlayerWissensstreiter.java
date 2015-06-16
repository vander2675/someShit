package application.api;

import java.util.HashMap;
import java.util.List;

public interface IAllPlayerWissensstreiter extends ICollection {
	List<IWissensstreiter> getWissenstreiter();
	HashMap<IWissensstreiter,IField> getDrawnWissensstreiter(int toDraw);
}
