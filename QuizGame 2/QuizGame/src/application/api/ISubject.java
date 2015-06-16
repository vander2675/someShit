package application.api;
public interface ISubject<GameState> {

//	IObserver lnkObserver = null;

	public void detach(IObserver<GameState> obs);

	public void attach(IObserver<GameState> obs);
}
