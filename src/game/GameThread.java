package game;

import mvc.Model;
import static utilities.Properties.*;

public class GameThread implements Runnable {

	private Model game;

	public GameThread(Model game) {
		this.game = game;
	}

	@Override
	public void run() {
		while(true){
			try {
				if (game.isRunning())
						game.updateGameItems();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
						Thread.sleep(UPDATED_SHAPES_SPEED);
			} catch (InterruptedException e) {
						e.printStackTrace();
			}
		} 
	}
		
	}