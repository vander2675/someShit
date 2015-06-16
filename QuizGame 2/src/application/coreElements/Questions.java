package application.coreElements;

import java.util.ArrayList;
import java.util.List;

import application.api.IQuestions;
import application.persistence.QuestionsDAO;
import application.useCaseNewGame.NewGameInstance;

public class Questions implements IQuestions {
	private List<Question> questions;
	private Category category;
	
	public Questions(Category category) {
		QuestionsDAO dao = new QuestionsDAO();
		questions = new ArrayList<Question>();
		dao.loadQuestions(category);
		this.category = category;
	}
	
	@Override
	public List<Question> getQuestions() {
		return questions;
	}

	@Override
	public Category getCategroy() {
		return this.category;
	}

	@Override
	public int count() {
		return questions.size();
	}

	@Override
	public void newGame(NewGameInstance instance) {
		
	}
}
