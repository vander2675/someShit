package application.api;

import java.util.HashMap;
import java.util.HashSet;

public interface IAllPlayerWissensstreiter extends ICollection {
	HashSet<IWissensstreiter> getWissenstreiter();
	HashMap<IWissensstreiter,IField> getDrawnWissensstreiter(int toDraw);
}
