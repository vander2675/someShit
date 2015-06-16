package javaFxQuizGame;

public interface IPresentable {

	// mode-setting
	public void setPresenterView(IOverlayable presenterViewController);
	public boolean shouldPresenterViewYieldUserInteraction();
	
	public void dismiss();
}
