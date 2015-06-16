package application.coreElements;

import java.util.ArrayList;
import java.util.List;

import application.api.IField;
import application.api.IPlayer;
import application.api.ISpielbrett;
import application.api.IWissensstreiter;
import application.logic.IAPIFactory;
import application.useCaseNewGame.NewGameInstance;


public class Spielbrett implements ISpielbrett {

	List<IField> fields;
	IField occupiedField;
	
	public Spielbrett() {
		fields = new ArrayList<IField>();
	}	
	
	@Override
	public void newGame(NewGameInstance instance) {
		for (int i = 0; i < 48; i++) {
			fields.add(IAPIFactory.factory.makeField());
		}
	}

	@Override
	public int count() {
		return fields.size();
	}

	@Override
	public List<IField> getFields() {
		return fields;
	}

	@Override
	public IField getFieldAtIndex(int index) {
		return fields.get(index);
	}

	/**
	 * Gets the Fields when ws is drawn hypothetical
	 * @param toDraw the number of Fields to Draw
	 * @param ws The Wissensstreiter that is Drawn
	 * @return The field of the ws after drawing, null if the ws is not on the map
	 */
	@Override
	public IField getResultForDraw(int toDraw, IWissensstreiter ws) {
		if (getFieldOfWissensstreiter(ws) == null ) {
			return null;
		}
		int currentField = fields.indexOf(getFieldOfWissensstreiter(ws));
		return fields.get(drawWithoutOverflow(toDraw, currentField));
	}
	
	/**
	 * 
	 * @param toDraw
	 * @param currentField
	 * @return
	 */
	private int drawWithoutOverflow(int toDraw, int currentField) {
		int result = currentField + toDraw;
		if (result > 47) {
			result = result - 48;
		}
		return result;
	}

	@Override
	public IField perfomDraw(int toDraw, IWissensstreiter ws) {
		IField nextField = fields.get(drawWithoutOverflow(toDraw, fields.indexOf(getFieldOfWissensstreiter(ws))));
		fields.get(fields.indexOf(getFieldOfWissensstreiter(ws))).removeWissensstreiter(ws);
		if (nextField.getWissensstreiter().size() > 0) {
			occupiedField = nextField;
		}
		nextField.addWissensstreiter(ws);
		return nextField;
	}

	@Override
	public IField getFieldOfWissensstreiter(IWissensstreiter ws) {
		for (IField field : fields) {
			for (IWissensstreiter wsIndex : field.getWissensstreiter()) {
				if(wsIndex.equals(ws)) {
					return field;
				}
			}
		}
		return null;
	}

	private int getStartIndexByPlayer(IPlayer player) {
		return (this.fields.size() / 4) * player.getPlayerNumber();
	}
	
	@Override
	public IField getStartFieldByPlayer(IPlayer player) {
		return this.fields.get(getStartIndexByPlayer(player));
	}

	@Override
	public IField getOccupiedField() {
		return occupiedField;
	}

	@Override
	public void drawToStartFieldFromOccupiedField(IPlayer player) {
		IWissensstreiter playerWs = null;
		for (IWissensstreiter ws : occupiedField.getWissensstreiter()) {
			if (ws.getOwner().equals(player)) {
				playerWs = ws;
			}
		}
		occupiedField.removeWissensstreiter(playerWs);
		getStartFieldByPlayer(player).addWissensstreiter(playerWs);
	}

	@Override
	public void drawToHomeBaseFromOccupiedField(IPlayer player) {
		IWissensstreiter playerWs = null;
		for (IWissensstreiter ws : occupiedField.getWissensstreiter()) {
			if (ws.getOwner().equals(player)) {
				playerWs = ws;
			}
		}
		occupiedField.removeWissensstreiter(playerWs);
	}
}
