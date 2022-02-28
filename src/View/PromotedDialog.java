package View;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PromotedDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton[] options;

	public PromotedDialog(JFrame frame) {
		super(frame);

		this.setTitle("You have to promot your pawn ...");
		this.setModal(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		options = new JButton[4];

		String[] choose = { "Rook", "Knight", "Queen", "Bishop" };

		for (int i = 0; i < 4; i++) {
			options[i] = new JButton("" + choose[i]);
			options[i].setActionCommand(choose[i]);
			panel.add(options[i]);
		}

		this.add(panel);
		this.pack();
	}

	public JButton getOption(int i) {
		return this.options[i];
	}

}
