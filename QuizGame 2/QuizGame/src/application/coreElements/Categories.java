package application.coreElements;

import java.util.HashSet;

import application.api.ICategories;
import application.persistence.CategoriesDAO;
import application.useCaseNewGame.NewGameInstance;



public class Categories implements ICategories {

	private HashSet<Category> categories;
	
	public Categories() {
		CategoriesDAO dao = new CategoriesDAO();
		for (Category catagory : dao.loadCategories()) {
			categories.add(catagory);
		}
	}
	
	@Override
	public HashSet<Category> getCategories() {
		return categories;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void newGame(NewGameInstance instance) {
		// TODO Auto-generated method stub
		
	}
}
