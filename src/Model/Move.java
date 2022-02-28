package Model;

import java.io.Serializable;

public class Move implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int destinationCoordinateX, destinationCoordinateY;
	
	public Move(int destinationCoordinateX, int destinationCoordinateY) {
		this.destinationCoordinateX = destinationCoordinateX;
		this.destinationCoordinateY = destinationCoordinateY;
	}

	public int getDestinationCoordinateX() {
		return destinationCoordinateX;
	}

	public int getDestinationCoordinateY() {
		return destinationCoordinateY;
	}
	
	public void deplacer(Tile depart, Tile arrive) {
		arrive.setPiece(depart.getPiece());
		depart.setPiece(null);
	}
	
}
