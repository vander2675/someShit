package application.api;

import java.util.HashSet;
import java.util.List;

import application.coreElements.Category;

public interface ICategories extends ICollection {
	List<ICategory> getCategories();
}
