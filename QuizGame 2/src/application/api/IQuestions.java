package application.api;

import java.util.List;

public interface IQuestions extends ICollection {
	List<IQuestion> getQuestions();
	ICategory getCategroy();
	public static interface IWissenstandsanzeiger {
	}
}
