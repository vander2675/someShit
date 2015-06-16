package application.coreElements;

import application.api.IField;
import application.api.IPlayer;
import application.api.ISpielbrett;
import application.api.IWissensstreiter;
import application.logic.IAPIFactory;
public class Wissensstreiter implements IWissensstreiter {
	private IPlayer owner;
	
	public Wissensstreiter(IPlayer owner) {
		this.owner = owner;
	}
	
	@Override
	public IField getResultForDraw(int toDraw) {
		ISpielbrett spielbrett = IAPIFactory.factory.getSpielbrett();
		return spielbrett.getResultForDraw(toDraw, this);
	}

	@Override
	public void performDraw(int toDraw) {
		ISpielbrett spielbrett = IAPIFactory.factory.getSpielbrett();
		spielbrett.perfomDraw(toDraw, this);
	}

	@Override
	public void drawFromHomebase() {
		
	}

	@Override
	public IPlayer getOwner() {
		return owner;
	}
}
