package application.api;
public interface IWissenstandsanzeiger {
	boolean isGameOver();
	void inkrementACategory(ICategory category);
	void dekrementACategory(ICategory category);
	int getIndexForCategory(ICategory category);
	int getIndexForCategroyByCateNo(int cateNo);
}
