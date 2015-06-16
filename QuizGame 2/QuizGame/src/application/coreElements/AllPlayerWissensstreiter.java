package application.coreElements;

import java.util.HashMap;
import java.util.HashSet;

import application.api.IAllPlayerWissensstreiter;
import application.api.IField;
import application.api.IPlayer;
import application.api.IWissensstreiter;
import application.logic.IAPIFactory;
import application.useCaseNewGame.NewGameInstance;
public class AllPlayerWissensstreiter implements IAllPlayerWissensstreiter {

	HashSet<IWissensstreiter> allPlayerWissensstreiter;
	IPlayer owner;
	
	public AllPlayerWissensstreiter(IPlayer player){
		allPlayerWissensstreiter = new HashSet<IWissensstreiter>();
		this.owner = player;
	}
	
	@Override
	public int count() {
		return allPlayerWissensstreiter.size();
	}

	@Override
	public HashSet<IWissensstreiter> getWissenstreiter() {
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
