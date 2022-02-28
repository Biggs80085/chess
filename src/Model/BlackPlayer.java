package Model;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.Pieces.Alliance;
import Model.Pieces.Bishop;
import Model.Pieces.Knight;
import Model.Pieces.Piece;
import Model.Pieces.Queen;
import Model.Pieces.Rook;


public class BlackPlayer extends Player{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Move> valideMove;
	private List<Tile> validePiece = new ArrayList<>();
	private GameModel gameModel;
	public BlackPlayer(Alliance playerAlliance, GameModel gameModel){
		super(playerAlliance);
		this.gameModel=gameModel;
		// TODO Auto-generated constructor stub
	}
	
	public Piece choosePiece(int choose) {
		switch(choose) {
		case 0:
			return new Rook(Alliance.BLACK);
		case 1:
			return new Knight(Alliance.BLACK);
		case 2:
			return new Queen(Alliance.BLACK);
		case 3:
			return new Bishop(Alliance.BLACK);
		default :
			return null;
		}
	}
	
	public void randomMove() {

		for (int i = 0; i < GameModel.LINE; i++)
			for (int j = 0; j < GameModel.COLUMN; j++) {
				if (gameModel.getTile(i, j).getPiece() != null
						&& gameModel.getTile(i, j).getPiece().getPieceAlliance() == Alliance.BLACK
						&& gameModel.getTile(i, j).getPiece().validateMove(gameModel, i, j).size() > 0) {
					validePiece.add(gameModel.getTile(i, j));
				}
			}

		Random randomizer = new Random();

		Tile randomPiece = validePiece.get(randomizer.nextInt(validePiece.size()));
		valideMove = (List<Move>) randomPiece.getPiece().validateMove(gameModel, randomPiece.getX(),
				randomPiece.getY());
		Move randomMove = valideMove.get(randomizer.nextInt(valideMove.size()));

		gameModel.move(gameModel.getTile(randomPiece.getX(), randomPiece.getY()),
				gameModel.getTile(randomMove.getDestinationCoordinateX(), randomMove.getDestinationCoordinateY()));

		if (gameModel.getTile(randomMove.getDestinationCoordinateX(), randomMove.getDestinationCoordinateY()).getPiece()
				.getNom() == "Pawn"
				&& gameModel.getTile(randomMove.getDestinationCoordinateX(), randomMove.getDestinationCoordinateY())
						.getPiece().getPieceAlliance() == Alliance.BLACK) {
			if (randomMove.getDestinationCoordinateX() == 6) {
				gameModel.promoted(
						gameModel.getTile(randomMove.getDestinationCoordinateX(),
								randomMove.getDestinationCoordinateY()),
						((BlackPlayer) gameModel.getCurrentPlayer()).choosePiece(randomizer.nextInt(4)));
			}
		}

		validePiece = new ArrayList<>();

	}

}
