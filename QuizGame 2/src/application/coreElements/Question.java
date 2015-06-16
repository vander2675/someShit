package application.coreElements;

import java.util.LinkedList;

import application.api.IQuestion;
public class Question implements IQuestion {
	private String question;
	private String trueAnswer;
	private String[] falseAnswers;
	
	public Question(String question, String rightAnswer, String[] falseAnswers){
		this.question = question;
		this.trueAnswer = rightAnswer;
		this.falseAnswers = falseAnswers;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String[] getAnswers(){
		LinkedList<String> list = new LinkedList<String>();
		list.add(trueAnswer);
		for (String string : falseAnswers) {
			list.add(string);
		}
		
		String[] result = new String[4];
		int i = 0;
		int limit = 3;
		while(!list.isEmpty()){
			double rand = Math.random() * limit;
			int index = Math.round((float)rand);
			result[i] = list.remove(index);
			i++;
			limit--;
		}
		return result;		
	}
	
	public boolean isCorrectAnswer(String answer) {
		return trueAnswer.equals(answer);
	}
}
