package application.api;

import java.util.HashSet;

import application.coreElements.Category;

public interface ICategories extends ICollection {
	HashSet<Category> getCategories();
}
