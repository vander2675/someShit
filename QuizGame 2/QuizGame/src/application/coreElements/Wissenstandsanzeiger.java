package application.coreElements;

import application.api.ICategories;
import application.api.ICategory;
import application.api.IWissenstandsanzeiger;
import application.logic.IAPIFactory;



public class Wissenstandsanzeiger implements IWissenstandsanzeiger {
	
	ICategory[] categories;
	int[] anzeiger;

	public Wissenstandsanzeiger() {
		loadCategories();
		anzeiger = new int[categories.length];
	}
	
	public boolean isGameOver() {
		for (int anzeige : anzeiger) {
			if(anzeige < 2) {
				return false;
			}
		}
		return true;
	}
	
	private void loadCategories() {
		ICategories categories = IAPIFactory.factory.getCategories();
		this.categories = new Category[categories.count()];
		this.categories = categories.getCategories().toArray(this.categories);
	}

	@Override
	public void inkrementACategory(ICategory category) {
		int i = 0;
		for (ICategory categoryItorator : categories) {
			if(categoryItorator.equals(category)) {
				if (anzeiger[i] < 2) {
					anzeiger[i]++; 
				} else {
					inkRandomCategory();
				}
			}
			i++;
		}
	}
	
	private void inkRandomCategory() {
		for (int i : anzeiger) {
			if(i < 2) {
				i++;
				break;
			}
		}
	}

	private void dekRandomCategory() {
		for (int i : anzeiger) {
			if(i > 0) {
				i--;
				break;
			}
		}
	}
	
	@Override
	public void dekrementACategory(ICategory category) {
		int i = 0;
		for (ICategory categoryItorator : categories) {
			if(categoryItorator.equals(category)) {
				if (anzeiger[i] < 0) {
					anzeiger[i]--; 
				} else {
					dekRandomCategory();
				}
			}
			i++;
		}
	}
}
