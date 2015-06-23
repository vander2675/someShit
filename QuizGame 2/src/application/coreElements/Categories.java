package application.coreElements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import application.api.ICategories;
import application.api.ICategory;
import application.persistence.CategoriesDAO;
import application.useCaseNewGame.NewGameInstance;

public class Categories implements ICategories {

	private List<ICategory> categories = new ArrayList<>();
	
	public Categories() {
		CategoriesDAO dao = new CategoriesDAO();
		for (Category catagory : dao.loadCategories()) {
			categories.add(catagory);
		}
	}
	
	@Override
	public List<ICategory> getCategories() {
		return categories;
	}

	@Override
	public int count() {
		return categories.size();
	}

	@Override
	public void newGame(NewGameInstance instance) {
		// TODO Auto-generated method stub
		
	}
}
