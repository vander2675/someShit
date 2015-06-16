package javaFxQuizGame;

import javafx.scene.Node;

public interface IOverlayable {

	public void overlayWithNode(Node node, boolean yieldUserInteraction);
	
	public void dismissOverlay(Node node);
	public void dismissAllOverlays();
}
