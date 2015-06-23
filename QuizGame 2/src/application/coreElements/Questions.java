package application.coreElements;

import java.util.ArrayList;
import java.util.List;

import application.api.ICategory;
import application.api.IQuestion;
import application.api.IQuestions;
import application.persistence.QuestionsDAO;
import application.useCaseNewGame.NewGameInstance;

public class Questions implements IQuestions {
	private List<IQuestion> questions;
	private ICategory category;
	
	public Questions(ICategory category2) {
		QuestionsDAO dao = new QuestionsDAO();
		questions = new ArrayList<IQuestion>();
		questions = dao.loadQuestions(category2);
		this.category = category2;
	}
	
	@Override
	public List<IQuestion> getQuestions() {
		return questions;
	}

	@Override
	public ICategory getCategroy() {
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
