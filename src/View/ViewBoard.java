package View;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import Model.GameModel;
import Model.Tile;
import UtilObservable.OtherEvent;


public class ViewBoard extends JPanel implements UtilObservable.EventListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[][] caseBoard;
	private GameModel gameModel;
	
	public ViewBoard(){
		super();
		
		
		this.setLayout(new GridLayout(GameModel.LINE, GameModel.COLUMN));
		this.setSize(600, 525);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		caseBoard = new JLabel[GameModel.LINE][GameModel.COLUMN];
		initView();
		
	}
	
	private void initView() {
		boolean flag=true;
		for(int i=0; i<GameModel.LINE; i++) {
			
			flag = flag ? false : true;
			
			for(int j=0; j<GameModel.COLUMN;j++) {
				caseBoard[i][j] = new JLabel();

				caseBoard[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				
				if(flag) {
					caseBoard[i][j].setBackground(new Color(113, 164, 211));
					caseBoard[i][j].setOpaque(true);
					
					this.add(caseBoard[i][j]);
					flag=false;
				}else {
					caseBoard[i][j].setBackground(new Color(240, 242, 251));
					caseBoard[i][j].setOpaque(true);
					this.add(caseBoard[i][j]);
					flag=true;
				}
			}
		}
				
	}
	
	private void initPiece(GameModel gameModel) {
		for(int i=0; i<GameModel.LINE; i++) {
			for(int j=0; j<GameModel.COLUMN;j++) {
				if(gameModel.getTile(i,j).isTileOccupied()) {
			
					caseBoard[i][j].setIcon(new ImageIcon("res/im/"+
					gameModel.getTile(i, j).getPiece().getNom().charAt(0)+
					gameModel.getTile(i, j).getPiece().getPieceAlliance()+".png"));
				}
			}
		}
	}
	
	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
		this.removeAll();
		initView();
		initPiece(gameModel);
		this.gameModel.addEventListener(this);
	}
	public GameModel getModel() {
		return this.gameModel;
	}
	
	public JLabel getJLabel(int line, int column) {
		return this.caseBoard[line][column];
	}
	
	@Override
	public void update(OtherEvent evt) {
		// TODO Auto-generated method stub
		
		if(evt.getSource() instanceof GameModel && evt.getData2() == null) {
			if(evt.getData() instanceof Tile) {
			getJLabel(((Tile) evt.getData()).getX(), ((Tile) evt.getData()).getY()).setIcon(
					new ImageIcon("res/im/"+((Tile) evt.getData()).getPiece().getNom().charAt(0) +
							((Tile) evt.getData()).getPiece().getPieceAlliance()+".png"));
			}
		}
		else if(evt.getSource() instanceof GameModel && evt.getData() instanceof Tile && evt.getData2() instanceof Tile) {
			
			getJLabel(((Tile) evt.getData2()).getX(), ((Tile) evt.getData2()).getY()).setIcon(
				getJLabel(((Tile) evt.getData()).getX(), ((Tile) evt.getData()).getY()).getIcon());
			getJLabel((((Tile) evt.getData())).getX(), ((Tile) evt.getData()).getY()).setIcon(null);
			
		}
		
	}
}
