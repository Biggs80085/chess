package View;

import javax.swing.JPanel;

import Model.GameModel;
import UtilObservable.EventListener;

public abstract class LostPiece extends JPanel implements EventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GameModel gameModel;
	
	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
		this.removeAll();
		this.repaint();
		this.gameModel.addEventListener(this);
	}
	
	protected abstract void initLost(GameModel gameModel);
}
