package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Model.Pieces.Alliance;
import Model.Pieces.Bishop;
import Model.Pieces.King;
import Model.Pieces.Knight;
import Model.Pieces.Pawn;
import Model.Pieces.Piece;
import Model.Pieces.Queen;
import Model.Pieces.Rook;
import UtilObservable.Chrono;
import UtilObservable.Notifier;
import UtilObservable.OtherEvent;

public class GameModel extends Notifier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static int LINE = 7, COLUMN = 8;

	public Tile[][] tiles;
	private List<Piece> lostBlack = new ArrayList<>();
	private List<Piece> lostWhite = new ArrayList<>();

	public Player currentPlayer;
	public BlackPlayer blackPlayer = new BlackPlayer(Alliance.BLACK, this);
	public WhitePlayer whitePlayer = new WhitePlayer(Alliance.WHITE);

	public Chrono chrono;

	public GameModel() {
		tiles = new Tile[7][8];
		builderPiece();
		this.currentPlayer = whitePlayer;
		chrono = new Chrono();

	}

	private void initTiles() {
		for (int i = 0; i < LINE; i++)
			for (int j = 0; j < COLUMN; j++)
				this.tiles[i][j] = new Tile(i, j);
	}

	private void builderPiece() {

		char[] ordrePiece = { 'R', 'K', 'B', 'Q', 'G', 'B', 'K', 'R' };
		int increment = 1;
		int line = 0;

		initTiles();
		while (increment >= -1) {

			for (int column = 0; column < 8; column++) {

				switch (ordrePiece[column]) {
				case 'R':
					this.tiles[line][column].setPiece(new Rook(line == 0 ? Alliance.BLACK : Alliance.WHITE));
					break;

				case 'K':
					this.tiles[line][column].setPiece(new Knight(line == 0 ? Alliance.BLACK : Alliance.WHITE));
					break;

				case 'B':
					this.tiles[line][column].setPiece(new Bishop(line == 0 ? Alliance.BLACK : Alliance.WHITE));
					break;

				case 'Q':
					this.tiles[line][column].setPiece(new Queen(line == 0 ? Alliance.BLACK : Alliance.WHITE));
					break;

				case 'G':
					this.tiles[line][column].setPiece(new King(line == 0 ? Alliance.BLACK : Alliance.WHITE));
					break;
				}

				this.tiles[line + increment][column].setPiece(new Pawn(line == 0 ? Alliance.BLACK : Alliance.WHITE));

			}

			increment -= 2;
			line = 6;
		}
	}

	public void move(Tile beginning, Tile arrive) {

		if (arrive.isTileOccupied()) {
			if (arrive.getPiece().getPieceAlliance() == Alliance.BLACK) {
				this.lostBlack.add(arrive.getPiece());
				
				super.notify(new OtherEvent(this, lostBlack, null));
			} else {
				this.lostWhite.add(arrive.getPiece());
				
				super.notify(new OtherEvent(this, lostWhite, null));
			}
		}

		if (beginning.getPiece().getPieceAlliance() == Alliance.BLACK)
			setPlayer(whitePlayer);
		else
			setPlayer(blackPlayer);

		notify(new OtherEvent(this, beginning, arrive));
		if (arrive.isTileOccupied() && arrive.getPiece().getNom() == "Ging") {
			currentPlayer.setDeadKing(true);
			
			notify(new OtherEvent(this, currentPlayer));
		}

		arrive.setPiece(beginning.getPiece());
		beginning.setPiece(null);
		

	}

	public void promoted(Tile promoting, Piece piece) {
		promoting.setPiece(piece);
		super.notify(new OtherEvent(this, promoting));
	}

	public void display() {
		for (int i = 0; i < LINE; i++) {
			for (int j = 0; j < COLUMN; j++) {
				if (this.tiles[i][j].isTileOccupied())
					System.out.print(this.tiles[i][j].getPiece().getNom().charAt(0) + " ");
				else
					System.out.print("- ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private void setPlayer(Player player) {

		this.currentPlayer = player;
		renitChrono(chrono);

	}

	public void renitChrono(Chrono chrono) {

		chrono.setTimeChrono(Chrono.TIMER);

		notify(new OtherEvent(this, chrono.getTimeChrono()));

	}

	public void timer(Chrono chrono) {

		notify(new OtherEvent(this, chrono.getTimeChrono()));
	}

	public void endTimer() {
		notify(new OtherEvent(this, null));
	}
	public Tile getTile(int line, int column) {
		return this.tiles[line][column];
	}

	public Chrono getChrono() {
		return this.chrono;
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public List<Piece> getLostBlack() {
		return lostBlack;
	}

	public List<Piece> getLostWhite() {
		return lostWhite;
	}

}
