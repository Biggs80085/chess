package Model;

import java.io.Serializable;

import Model.Pieces.Alliance;

public abstract class Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Alliance playerAlliance;
	public boolean deadKing = false;
	
	public Player(Alliance playerAlliance) {
		this.playerAlliance = playerAlliance;	
	}
	
	public Alliance getAlliance() {
		return playerAlliance;
	}
	public void setDeadKing(boolean deadKing) {
		this.deadKing = deadKing;
	}
	public boolean getDeadKing() {
		return this.deadKing;
	}

	
	


	
	
}
