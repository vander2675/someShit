package application.coreElements;

import application.api.ICategory;
public class Category implements ICategory {
	private String name;
	private Questions questions;
	
	public Category(String name) {
		this.name = name;	
		questions = new Questions(this);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Questions getQuestions() {
		return questions;
	}
}
