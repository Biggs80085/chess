package Model.Pieces;

import java.io.Serializable;
import java.util.Collection;

import Model.GameModel;
import Model.Move;

public abstract class Piece implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final String nom;
	protected final Alliance pieceAlliance;
	
	public Piece(String nom, Alliance pieceAlliance) {
		
		this.nom = nom;
		this.pieceAlliance = pieceAlliance;
		
	}
	
	public abstract Collection<Move> validateMove(GameModel gameModel, int x, int y);
	
	public String getNom() {
		return this.nom;
	}
	
	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}
	
	
	
}
