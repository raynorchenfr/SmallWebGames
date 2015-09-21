package controllers;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@SessionScoped
public class PlayerBean implements Serializable{
	public PlayerBean(){}
	
	private String headline = "Welcome to Ray's Mini Games!";
	private String player1;
	private String player2;
	
	private boolean multiplayer;
	
	private ArrayList<SelectItem> games;
	
	private String gameName;
	
	@PostConstruct
	public void init(){
		games = new ArrayList<SelectItem>();
		games.add(new SelectItem("SentenceGuessingGame/guessSentence.xhtml","Guess Sentence"));
	}
	
	public String startGame(){
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		if(this.gameName != null && this.gameName.length() > 0){
			return this.gameName+"?faces-redirect=true";
		}
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Game Choice!","");
		fc.addMessage(null, msg);
		
		return "index.xhtml";
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public boolean isMultiplayer() {
		return multiplayer;
	}

	public void setMultiplayer(boolean multiplayer) {
		this.multiplayer = multiplayer;
	}
	
	public String getHeadline(){
		return headline;
	}

	public ArrayList<SelectItem> getGames() {
		return games;
	}

	public void setGames(ArrayList<SelectItem> games) {
		this.games = games;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
}
