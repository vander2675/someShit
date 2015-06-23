package application.coreElements;

import java.util.LinkedList;
import java.util.List;

import application.api.IField;
import application.api.IWissensstreiter;


public class Field implements IField {
	List<IWissensstreiter> ws;
	
	public Field() {
		this.ws = new LinkedList<IWissensstreiter>();
	}

	@Override
	public List<IWissensstreiter> getWissensstreiter() {
		return ws;
	}

	@Override
	public void addWissensstreiter(IWissensstreiter ws) {
		this.ws.add(ws);
	}

	@Override
	public void removeWissensstreiter(IWissensstreiter ws) {
		this.ws.remove(ws);
	}
}
