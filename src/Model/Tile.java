package Model;

import java.io.Serializable;

import Model.Pieces.Piece;

public class Tile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int tileCoordinateX, tileCoordinateY;
	Piece piece;
	
	
	public Tile(int tileCoordinateX, int tileCoordinateY) {
		this.tileCoordinateX = tileCoordinateX;
		this.tileCoordinateY = tileCoordinateY;
	}
	
	public boolean isTileOccupied() {
		if(piece != null)
			return true;
		else
			return false;
	}

	public Piece getPiece() {
		if(piece != null)
			return this.piece;
		else
			return null;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public int getX() {
		return this.tileCoordinateX;
	}
	
	public int getY() {
		return this.tileCoordinateY;
	}
	
}
