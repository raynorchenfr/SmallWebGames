package sentenceguessing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class SentenceGameBean implements Serializable {
	public SentenceGameBean(){}
	
	private final String gameName = "Guess a Sentence!";
	
	private String display;
	
	private String question;
	
	private String answer;
	
	private String guess;
	
	private ArrayList<String> questions;
	
	private int wrongGuesses;
	
	private boolean gameOver;
	
	@PostConstruct
	public void loadQuestions(){
		System.out.println("Guess Sentence Init");
		this.questions = new ArrayList<String>();
		
		questions.add("WHO IS YOUR DADDY");
		questions.add("I AM YOUR FATHER");
		questions.add("YOU ARE IN MY SPOT");
		questions.add("NEVER DOUBT ME");
		questions.add("MY LIFE FOR AIUR");
		questions.add("GO TUCK YOURSELF IN");
		
		startGame();
		
	}
	
	public void startGame(){
		Random rdn = new Random();
		int index = rdn.nextInt(questions.size());
		
		this.question = questions.get(index);
		display = question.replaceAll("[a-zA-Z]", "_");
		System.out.println("Current question: "+question+", display as: "+display);
		
		wrongGuesses = 5;
		gameOver = false;
	}
	
	public void guessLetter(){
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		
		if(guess == null || guess.trim().length() == 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please enter a letter","");
			fc.addMessage(null, msg);
			return;
		}
		
		if(guess.trim().length() > 1){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"One letter only","");
			fc.addMessage(null, msg);
			return;
		}
		boolean test = (guess.trim().charAt(0) <= 'z' && guess.trim().charAt(0) >= 'a')
				|| (guess.trim().charAt(0) <= 'Z' && guess.trim().charAt(0) >= 'A' );
		
		if(!test){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please choose an alphabetical letter","");
			fc.addMessage(null, msg);
			return;
		}
		
		ArrayList<Integer> li = new ArrayList<Integer>();
		char c = guess.toUpperCase().charAt(0);
		for(int i=0;i<this.question.length();i++){
			if(c == question.charAt(i)){
				li.add(i);
			}
		}
		if(li.isEmpty()){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Wrong Guess!","");
			fc.addMessage(null, msg);
			wrongGuesses--;
			if(wrongGuesses == 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"You have run out of guesses!","");
				fc.addMessage(null, msg);
				setGameOver(true);
				return;
			}
			
			return;
		}
		StringBuilder curr = new StringBuilder(display);
		for(int index : li){
			curr.setCharAt(index, c);
		}
		display = curr.toString();
	}
	
	public void guessSentence(){
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		
		if(answer == null || answer.trim().length() == 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please enter a valid answer","");
			fc.addMessage(null, msg);
			return;
		}
		String a = answer.toUpperCase().trim();
		if(a.equals(question)){
			setDisplay(question);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"You won!","");
			fc.addMessage(null, msg);
			return;
		}
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getGuess() {
		return guess;
	}

	public void setGuess(String guess) {
		this.guess = guess;
	}

	public ArrayList<String> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<String> questions) {
		this.questions = questions;
	}

	public int getWrongGuesses() {
		return wrongGuesses;
	}

	public void setWrongGuesses(int wrongGuesses) {
		this.wrongGuesses = wrongGuesses;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public String getGameName(){
		return this.gameName;
	}
}
