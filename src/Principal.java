import Controller.GameController;

import View.GameView;

public class Principal {
	public static void main(String[] args) throws InterruptedException {

		GameView view = new GameView();

		GameController controller = new GameController(view);

	}
}
