package Model.Pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Model.GameModel;
import Model.Move;

public class Queen extends Piece{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int[] CANDIDATE_MOVE_COORDINATES_LINE = {-1, 0, 1};
	private final static int[] CANDIDATE_MOVE_COORDINATES_COLUMN = {-1, 0, 1};

	public Queen(Alliance alliance) {
		super("Queen", alliance);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Collection<Move> validateMove(GameModel gameModel, int x, int y) {
		// TODO Auto-generated method stub
		List<Move> legalMoves = new ArrayList<>();
		
		
		for(int line = 0; line < CANDIDATE_MOVE_COORDINATES_LINE.length; line++)
			for(int column = 0; column < CANDIDATE_MOVE_COORDINATES_COLUMN.length; column++) {
				for(int i=1;i<8;i++) {
					if(x+i*CANDIDATE_MOVE_COORDINATES_LINE[line]>=0 && x+i*CANDIDATE_MOVE_COORDINATES_LINE[line]<7 && 
						y+i*CANDIDATE_MOVE_COORDINATES_COLUMN[column]>=0 && y+i*CANDIDATE_MOVE_COORDINATES_COLUMN[column]<8) {
						if(!gameModel.getTile(x+i*CANDIDATE_MOVE_COORDINATES_LINE[line], y+i*CANDIDATE_MOVE_COORDINATES_COLUMN[column]).isTileOccupied())
							legalMoves.add(new Move(x+i*CANDIDATE_MOVE_COORDINATES_LINE[line], y+i*CANDIDATE_MOVE_COORDINATES_COLUMN[column]));
						
						else if(gameModel.getTile(x+i*CANDIDATE_MOVE_COORDINATES_LINE[line], y+i*CANDIDATE_MOVE_COORDINATES_COLUMN[column]).isTileOccupied() &&
								gameModel.getTile(x+i*CANDIDATE_MOVE_COORDINATES_LINE[line], y+i*CANDIDATE_MOVE_COORDINATES_COLUMN[column]).getPiece().getPieceAlliance() != this.getPieceAlliance()) {
							legalMoves.add(new Move(x+i*CANDIDATE_MOVE_COORDINATES_LINE[line], y+i*CANDIDATE_MOVE_COORDINATES_COLUMN[column]));	
							break;
							
						}else
							break;
					}
				}	
			}
	
		
		
		return legalMoves;
	}

}
