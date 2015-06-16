package application.persistence;
import java.util.LinkedList;
import java.util.Scanner;

import application.coreElements.*;


public class CategoriesDAO {
	
	public LinkedList<Category> loadCategories() {
		Scanner scanner = IFileLoader.fileLoader.loadCSVtoScanner("resources/Categories.csv");
		LinkedList<Category> result = new LinkedList<Category>();
		while(scanner.hasNext()) {
			result.add(new Category(scanner.next()));
		}
		return result;
	}
}
