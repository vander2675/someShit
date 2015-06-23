package application.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import application.coreElements.Category;
import application.coreElements.Question;

public class QuestionsDAO {
	
	public List<Question> loadQuestions(Category category){
		Scanner scanner = IFileLoader.fileLoader.loadCSVtoScanner("resources/" + category.getName() + ".csv");
		List<Question> result = new ArrayList<Question>();
		while(scanner.hasNext()) {
			String question = scanner.next();
			String rightAnswer = scanner.next();
			String[] falseAnswers = {scanner.next(), scanner.next(), scanner.next()};
			result.add(new Question(question, rightAnswer, falseAnswers));
		}
		return result;
	}
}
