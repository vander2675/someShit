package application.api;
public interface IQuestion {
	String getQuestion();
	String[] getAnswers();
	boolean isCorrectAnswer(String answer);
}
