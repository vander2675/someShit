package application.coreElements;

import java.util.ArrayList;
import java.util.List;

import application.api.IField;
import application.api.IPlayer;
import application.api.ISpielbrett;
import application.api.IWissensstreiter;
import application.logic.IAPIFactory;
import application.useCaseNewGame.NewGameInstance;
import application.useCaseWissenTesten.IWissenTestenInstance;


public class Spielbrett implements ISpielbrett {

	List<IField> fields;
	
	public Spielbrett() {
		fields = new ArrayList<IField>();
	}	
	
	@Override
	public void newGame(NewGameInstance instance) {
		fields.clear();
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
		cleanUp();
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
		if (getFieldOfWissensstreiter(ws) != null) {
			fields.get(fields.indexOf(getFieldOfWissensstreiter(ws))).removeWissensstreiter(ws);
		}
		nextField.addWissensstreiter(ws);
		return nextField;
	}

	@Override
	public IField getFieldOfWissensstreiter(IWissensstreiter ws) {
		for (IField field : fields) {
			for (IWissensstreiter wsIndex : field.getWissensstreiter()) {
				if (wsIndex != null) {
					if(wsIndex.equals(ws)) {
						return field;
					}
				}
			}
		}
		return null;
	}

	private int getStartIndexByPlayer(IPlayer player) {
		int result = (this.fields.size() / 4) * player.getPlayerNumber();
		return result;
	}
	
	@Override
	public IField getStartFieldByPlayer(IPlayer player) {
		return this.fields.get(getStartIndexByPlayer(player));
	}

	@Override
	public IField getOccupiedField() {
		cleanUp();
		for (IField iField : fields) {
			if (iField.getWissensstreiter().size() > 1) {
				return iField;
			}
		}
		return null;
	}

	@Override
	public void drawToStartFieldFromOccupiedField(IWissensstreiter ws) {
		IWissenTestenInstance instance = IAPIFactory.factory.getWissenTestenInstance();
		if (getStartFieldByPlayer(ws.getOwner()).getWissensstreiter().size() > 0 &&
				instance.getPlayerWS().equals(getStartFieldByPlayer(instance.getCurrentPlayer()).getWissensstreiter().get(0))) {
			drawToHomeBaseFromOccupiedField(ws);
		} else {
			getFieldOfWissensstreiter(ws).removeWissensstreiter(ws);
			getStartFieldByPlayer(ws.getOwner()).addWissensstreiter(ws);	
		}
	}

	@Override
	public void drawToHomeBaseFromOccupiedField(IWissensstreiter ws) {
		IField fieldOfWissensstreiter = getFieldOfWissensstreiter(ws);
		System.out.println("fieldOfWissensstreiter: " + "\t" + fieldOfWissensstreiter + "ws:" + ws);
		fieldOfWissensstreiter.removeWissensstreiter(ws);
	}

	@Override
	public void drawToStartFieldFromHomeBase(IWissensstreiter ws) {
		getStartFieldByPlayer(IAPIFactory.factory.getGameModel().getCurrentPlayer()).addWissensstreiter(ws);
	}
		
	private void cleanUp() {
		for (IField iField : fields) {
			List<IWissensstreiter> ws = new ArrayList<IWissensstreiter>();
			for (IWissensstreiter iWissensstreiter : iField.getWissensstreiter()) {
				if (iWissensstreiter != null) {
					ws.add(iWissensstreiter);
				}
			}
			iField.getWissensstreiter().clear();
			for (IWissensstreiter iWissensstreiter : ws) {
				iField.addWissensstreiter(iWissensstreiter);
			}
		}
	}
}
