package View;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Model.GameModel;

public class GameView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static int WIDTH = 600, HEIGHT = 825;

	LostPiece lostWhite, lostBlack;
	ViewBoard viewBoard;
	ChronoView chrono;
	private JMenuItem item;
	private JMenuItem load;
	private JMenuItem game;

	private PromotedDialog promot;
	private EndGame endGame;

	public GameView() {
		super();

		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("file");
		item = new JMenuItem("Save");
		load = new JMenuItem("Load");
		game = new JMenuItem("New");
		chrono = new ChronoView();
		menu.add(item);

		menu.add(load);
		menu.add(game);
		menuBar.add(menu);

		this.setJMenuBar(menuBar);

		lostBlack = new LostPieceB();
		lostWhite = new LostPieceW();

		viewBoard = new ViewBoard();
		viewBoard.setBounds(0, 150, WIDTH, 525);

		this.getContentPane().add(chrono, null);
		this.getContentPane().add(lostBlack, null);
		this.getContentPane().add(viewBoard, null);
		this.getContentPane().add(lostWhite, null);

		this.promot = new PromotedDialog(this);
		this.endGame = new EndGame(this);

		this.setVisible(true);

	}

	public void initModel(GameModel model) {
		this.viewBoard.setGameModel(model);
		this.lostBlack.setGameModel(model);
		this.lostBlack.initLost(model);
		this.lostWhite.setGameModel(model);
		this.lostWhite.initLost(model);
		this.chrono.initModel(model);
		this.endGame.initModel(model);
	}

	public ViewBoard getViewBoard() {
		return viewBoard;
	}

	public PromotedDialog getPromoted() {
		return this.promot;
	}
	
	public EndGame getEndGame() {
		return this.endGame;
	}

	public JMenuItem getMenuSave() {
		return this.item;
	}

	public JMenuItem getMenuLoad() {
		return this.load;
	}

	public JMenuItem getMenuGame() {
		return this.game;
	}

}
