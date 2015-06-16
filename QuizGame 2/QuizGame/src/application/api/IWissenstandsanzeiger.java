package application.api;
public interface IWissenstandsanzeiger {
	boolean isGameOver();
	void inkrementACategory(ICategory category);
	void dekrementACategory(ICategory category);
}
