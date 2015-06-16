package application.coreElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import application.api.IAllPlayerWissensstreiter;
import application.api.IField;
import application.api.IPlayer;
import application.api.IWissensstreiter;
import application.logic.IAPIFactory;
import application.useCaseNewGame.NewGameInstance;
public class AllPlayerWissensstreiter implements IAllPlayerWissensstreiter {

	List<IWissensstreiter> allPlayerWissensstreiter;
	IPlayer owner;
	
	public AllPlayerWissensstreiter(IPlayer player){
		allPlayerWissensstreiter = new ArrayList<IWissensstreiter>();
		this.owner = player;
		for(int i = 0; i < 3; i++) {
			allPlayerWissensstreiter.add(IAPIFactory.factory.makeWissensstreiter(owner));
		}
	}
	
	@Override
	public int count() {
		return allPlayerWissensstreiter.size();
	}

	@Override
	public List<IWissensstreiter> getWissenstreiter() {
		return allPlayerWissensstreiter;
	}
	
	@Override
	public void newGame(NewGameInstance instance) {
		allPlayerWissensstreiter.clear();
		for(int i = 0; i < 3; i++) {
			allPlayerWissensstreiter.add(IAPIFactory.factory.makeWissensstreiter(owner));
		}
	}

	@Override
	public HashMap<IWissensstreiter, IField> getDrawnWissensstreiter(int toDraw) {
		HashMap<IWissensstreiter,IField> result = new HashMap<IWissensstreiter, IField>();
		for (IWissensstreiter wissensstreiter : allPlayerWissensstreiter) {
			result.put(wissensstreiter, wissensstreiter.getResultForDraw(toDraw));
		}
		return result;
	}
}
