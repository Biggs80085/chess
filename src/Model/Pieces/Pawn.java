package Model.Pieces;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import Model.GameModel;
import Model.Move;


public class Pawn extends Piece{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int CANDIDATE_MOVE_COORDINATES_LINE = 1;
	
	public Pawn(Alliance alliance) {
		super("Pawn", alliance);
		// TODO Auto-generated constructor stub
	}

	public Collection<Move> validateMove(GameModel gameModel, int x, int y){
		
		List<Move> legalMoves = new ArrayList<>();
		if(x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE>=0 &&
			x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE<7 && y>=0 && y<8) {
			if(!gameModel.getTile(x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE, y).isTileOccupied())
				legalMoves.add(new Move(x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE, y));
			
			if(x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE>=0 &&
				x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE<7 && y+1>=0 && y+1<8 && 
				gameModel.getTile(x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE, y+1).isTileOccupied()
				&& gameModel.getTile(x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE, y+1).getPiece().pieceAlliance != this.getPieceAlliance())
					legalMoves.add(new Move(x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE, y+1));
			
			if(x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE>=0 &&
				x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE<7 && y-1>=0 && y-1<8 && 
				gameModel.getTile(x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE, y-1).isTileOccupied()
				&& gameModel.getTile(x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE, y-1).getPiece().pieceAlliance != this.getPieceAlliance())
					legalMoves.add(new Move(x+this.pieceAlliance.getDirection() * CANDIDATE_MOVE_COORDINATES_LINE, y-1));
		}
			
		return legalMoves;
	}

}
