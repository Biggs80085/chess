package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.GameModel;
import Model.Player;
import Model.Pieces.Alliance;
import UtilObservable.EventListener;
import UtilObservable.OtherEvent;

public class EndGame extends JDialog implements EventListener {

	private static final long serialVersionUID = 1L;
	JButton[] options;
	JLabel label;

	public EndGame(JFrame frame) {
		super(frame);

		this.setTitle("Game Over");
		this.setModal(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel panel = new JPanel();
		label = new JLabel("gameOver");
		panel.setLayout(new FlowLayout());
		options = new JButton[2];

		String[] choose = { "New Game", "Quit" };

		for (int i = 0; i < 2; i++) {
			options[i] = new JButton("" + choose[i]);
			options[i].setActionCommand(choose[i]);
			panel.add(options[i]);
		}
		this.add(label, BorderLayout.NORTH);
		this.add(panel);
		this.pack();
	}

	public void initModel(GameModel gameModel) {
		gameModel.addEventListener(this);
	}

	public JButton getOption(int i) {
		return this.options[i];
	}

	@Override
	public void update(OtherEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getSource() instanceof GameModel && evt.getData() == null && evt.getData2() == null) {
			this.label.setText("Out of Time You Loose");
			this.setVisible(true);
		}

		if (evt.getSource() instanceof GameModel && evt.getData() instanceof Player && evt.getData2() == null) {

			if (((Player) evt.getData()).getAlliance() == Alliance.BLACK) {
				this.label.setText("You Win");
			}

			else if (((Player) evt.getData()).getAlliance() == Alliance.WHITE) {
				this.label.setText("You Loose");
			}

			this.setVisible(true);

		}

	}
}
