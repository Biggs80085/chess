package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import Model.BlackPlayer;
import Model.GameModel;
import Model.Move;
import Model.ResourceManager;
import Model.Pieces.Alliance;
import Model.Pieces.Bishop;
import Model.Pieces.Knight;
import Model.Pieces.Piece;
import Model.Pieces.Queen;
import Model.Pieces.Rook;

import View.GameView;

public class GameController implements MouseListener, ActionListener, Runnable {

	private Piece lastPiece = null;

	private GameModel gameModel;
	private GameView gameView;
	private int x, y, dx, dy;
	private List<Move> valideMove;
	private List<Color> lastColors = new ArrayList<>();
	private Color lastPieceColor;

	public GameController(GameView gameView) {
		this.gameView = gameView;
		addActionListeners();
	}

	private void initModel(GameModel model) {
		this.gameModel = model;
		this.gameView.initModel(model);
		addMouseListeners();
	}

	private void addMouseListeners() {
		for (int i = 0; i < GameModel.LINE; i++)
			for (int j = 0; j < GameModel.COLUMN; j++)
				gameView.getViewBoard().getJLabel(i, j).addMouseListener(this);
	}

	private void addActionListeners() {
		gameView.getMenuSave().addActionListener(this);
		gameView.getMenuLoad().addActionListener(this);
		gameView.getMenuGame().addActionListener(this);
		for (int i = 0; i < 4; i++)
			gameView.getPromoted().getOption(i).addActionListener(this);

		for (int i = 0; i < 2; i++)
			gameView.getEndGame().getOption(i).addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() instanceof JLabel) {

			for (int i = 0; i < GameModel.LINE; i++)
				for (int j = 0; j < GameModel.COLUMN; j++) {

					if (e.getSource() == this.gameView.getViewBoard().getJLabel(i, j)) {

						if (gameModel.getTile(i, j).getPiece() != null || lastPiece != null) {

							if (lastPiece == null) {
								if (gameModel.getTile(i, j).getPiece().getPieceAlliance() == Alliance.WHITE) {

									Piece piece = gameModel.getTile(i, j).getPiece();
									x = i;
									y = j;

									valideMove = (List<Move>) piece.validateMove(gameModel, i, j);
									lastPiece = gameModel.getTile(i, j).getPiece();

									for (Move move : valideMove) {
										lastColors.add(
												this.gameView.getViewBoard().getJLabel(move.getDestinationCoordinateX(),
														move.getDestinationCoordinateY()).getBackground());
										this.gameView.getViewBoard().getJLabel(move.getDestinationCoordinateX(),
												move.getDestinationCoordinateY()).setBackground(Color.gray);

									}
									lastPieceColor = this.gameView.getViewBoard().getJLabel(x, y).getBackground();
									this.gameView.getViewBoard().getJLabel(x, y).setBackground(new Color(243, 31, 31));
								}

							} else {
								this.gameView.getViewBoard().getJLabel(x, y).setBackground(lastPieceColor);
								for (Move move : valideMove) {

									this.gameView.getViewBoard().getJLabel(move.getDestinationCoordinateX(),
											move.getDestinationCoordinateY()).setBackground(lastColors.get(0));
									lastColors.remove(0);

									if (e.getSource() == this.gameView.getViewBoard().getJLabel(
											move.getDestinationCoordinateX(), move.getDestinationCoordinateY())) {

										gameModel.move(gameModel.getTile(x, y), gameModel.getTile(
												move.getDestinationCoordinateX(), move.getDestinationCoordinateY()));

										if (gameModel
												.getTile(move.getDestinationCoordinateX(),
														move.getDestinationCoordinateY())
												.getPiece().getNom() == "Pawn"
												&& gameModel
														.getTile(move.getDestinationCoordinateX(),
																move.getDestinationCoordinateY())
														.getPiece().getPieceAlliance() == Alliance.WHITE) {
											if (move.getDestinationCoordinateX() == 0) {

												dx = move.getDestinationCoordinateX();
												dy = move.getDestinationCoordinateY();
												this.gameView.getPromoted().setVisible(true);

											}

										}
										if (gameModel.getCurrentPlayer().getDeadKing() == false) {
											((BlackPlayer) gameModel.getCurrentPlayer()).randomMove();
										}

										lastPiece = null;
										gameModel.display();
									}

								}
								lastPiece = null;

							}
						}
					}
				}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JMenuItem) {
			if (e.getSource() == this.gameView.getMenuSave()) {
				try {
					ResourceManager.save(gameModel, "model");

				} catch (Exception ex) {
					System.out.println("Couldn't save: " + ex.getMessage());
				}
			} else if (e.getSource() == this.gameView.getMenuLoad()) {
				try {
					GameModel game = (GameModel) ResourceManager.load("model");
					initModel(game);
				} catch (Exception ex) {
					System.out.println("Couldn't load: " + ex.getMessage());
				}
			} else if (e.getSource() == this.gameView.getMenuGame()) {
				initModel(new GameModel());
				Thread thread = new Thread(this);
				thread.start();
				gameModel.display();
			}
		}

		switch (e.getActionCommand()) {
		case "Rook":
			gameModel.promoted(gameModel.getTile(dx, dy), new Rook(Alliance.WHITE));
			this.gameView.getPromoted().setVisible(false);
			break;

		case "Knight":
			gameModel.promoted(gameModel.getTile(dx, dy), new Knight(Alliance.WHITE));
			this.gameView.getPromoted().setVisible(false);
			break;
		case "Queen":
			gameModel.promoted(gameModel.getTile(dx, dy), new Queen(Alliance.WHITE));
			this.gameView.getPromoted().setVisible(false);
			break;
		case "Bishop":
			gameModel.promoted(gameModel.getTile(dx, dy), new Bishop(Alliance.WHITE));
			this.gameView.getPromoted().setVisible(false);
			break;
		case "New Game":
			initModel(new GameModel());
			Thread thread = new Thread(this);
			thread.start();
			gameModel.display();
			gameView.getEndGame().setVisible(false);
			break;
		case "Quit":
			System.exit(0);
		}

	}

	@Override
	public void run() {

		while (gameModel.getChrono().timeChrono > 0 && !gameModel.getCurrentPlayer().getDeadKing()) {

			gameModel.getChrono().timeChrono--;

			gameModel.timer(gameModel.getChrono());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (!gameModel.getCurrentPlayer().getDeadKing())
			gameModel.endTimer();
	}

}
