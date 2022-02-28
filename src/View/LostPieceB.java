package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Model.GameModel;

import Model.Pieces.Alliance;
import Model.Pieces.Piece;

import UtilObservable.OtherEvent;

public class LostPieceB extends LostPiece{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<JLabel> l = new ArrayList<>();
	
	public LostPieceB(){
		super();

		this.setBackground(new Color(113, 164, 211));
		this.setBounds(new Rectangle(0, 50 ,GameView.WIDTH, 100));
		this.setLayout(new FlowLayout());
		
	}
	
	@Override
	public void update(OtherEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getData2() == null) {
			if (evt.getData() instanceof List<?>) {
				l.clear();
				this.removeAll();
				for (Object o : (List<?>) evt.getData()) {

					if (o instanceof Piece) {
						if (((Piece) o).getPieceAlliance() == Alliance.WHITE) {
							JLabel b = new JLabel();
							b.setIcon(new ImageIcon("res/im/" + ((Piece) o).getNom().charAt(0)
									+ ((Piece) o).getPieceAlliance() + ".png"));
							l.add(b);
							for (JLabel label : l) 
								this.add(label);
						}

					}
				}
			}
		}
		
	}

	@Override
	protected void initLost(GameModel gameModel) {
		// TODO Auto-generated method stub
		if(gameModel.getLostWhite().size() != 0) {
			l.clear();
			this.repaint();
			for(Piece p : gameModel.getLostWhite()) {
				JLabel b = new JLabel();
				b.setIcon(new ImageIcon("res/img/" + p.getNom().charAt(0)
						+ p.getPieceAlliance() + ".png"));
				l.add(b);
				for (JLabel label : l) 
					this.add(label);
			}
		}
		
		
	}

}
