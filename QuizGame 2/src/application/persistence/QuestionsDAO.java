package application.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.api.ICategory;
import application.api.IQuestion;
import application.logic.IAPIFactory;

public class QuestionsDAO {
	
	public List<IQuestion> loadQuestions(ICategory category){
		Scanner scanner = IFileLoader.fileLoader.loadCSVtoScanner("resources/" + category.getName() + ".csv");
		List<IQuestion> result = new ArrayList<IQuestion>();
		while(scanner.hasNext()) {
			String question = scanner.next().trim();
			String rightAnswer = scanner.next().trim();
			String[] falseAnswers = {scanner.next().trim(), scanner.next().trim(), scanner.next().trim()};
			result.add(IAPIFactory.factory.makeQuestion(question, rightAnswer, falseAnswers));
		}
		return result;
	}
}
