package game.strategy;

import static utilities.Properties.*;
import plateGenerator.PlateGenerator;

public class Hard implements GameStrategy {

	private static final int CORRUPTED_LIMIT = 10;
	private static final int NEWSHAPE_SPEED = 1500;
	private static final int UPDATE_SPEED = 70;
	
	@Override
	public void setGameStrategy(PlateGenerator randomGenerator) {
		randomGenerator.setColorLimit(randomGenerator.getColorsRange(), CORRUPTED_LIMIT);
		GENERATION_SHAPES_SPEED = NEWSHAPE_SPEED;
		UPDATED_SHAPES_SPEED = UPDATE_SPEED;
	}
}
