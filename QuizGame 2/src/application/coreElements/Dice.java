package application.coreElements;

import application.api.IDice;

public class Dice implements IDice {

	public Dice() {
	}

	@Override
	public int throwDice() {
//		return (int)Math.ceil(Math.random() * 6);
		return 6;
	}
}
