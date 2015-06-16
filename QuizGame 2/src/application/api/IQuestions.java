package application.api;

import java.util.List;

import application.coreElements.Category;
import application.coreElements.Question;

public interface IQuestions extends ICollection {
	List<Question> getQuestions();
	Category getCategroy();
	public static interface IWissenstandsanzeiger {
	}
}
