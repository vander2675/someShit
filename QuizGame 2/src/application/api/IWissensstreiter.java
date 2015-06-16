package application.api;

public interface IWissensstreiter {
	IField getResultForDraw(int toDraw);
	void performDraw(int toDraw);
	void drawFromHomebase();
	IPlayer getOwner();
}
