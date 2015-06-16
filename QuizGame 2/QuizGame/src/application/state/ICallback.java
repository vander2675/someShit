package application.state;
public interface ICallback {
	void setState(GameState state);
	GameState getState();
}
