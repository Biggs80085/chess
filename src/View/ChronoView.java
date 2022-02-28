package View;

import java.awt.Font;

import javax.swing.JLabel;

import Model.GameModel;

import UtilObservable.EventListener;
import UtilObservable.OtherEvent;

public class ChronoView extends JLabel implements EventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChronoView() {
		super();

		this.setBounds(255, 0, 100, 50);
		this.setFont(new Font("Serif", Font.PLAIN, 24));
		this.setText("00:00");
	}

	public void initModel(GameModel gameModel) {
		gameModel.addEventListener(this);
	}

	@Override
	public void update(OtherEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getSource() instanceof GameModel && evt.getData() instanceof Integer && evt.getData2() == null) {

			int minute = (int) Math.floor(((Integer) evt.getData()) / 60);
			int second = (int) Math.floor(((Integer) evt.getData()) % 60);

			this.setText("" + (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second));

		}

	}

}
