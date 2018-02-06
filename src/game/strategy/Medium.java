package game.strategy;

import static utilities.Properties.*;
import plateGenerator.PlateGenerator;

public class Medium implements GameStrategy {
	
	public static final int CORRUPTED_LIMIT = 20;
	private static final int NEWSHAPE_SPEED = 2500;
	private static final int UPDATE_SPEED = 100;

	@Override
	public void setGameStrategy(PlateGenerator randomGenerator) {
		randomGenerator.setColorLimit(randomGenerator.getColorsRange() * 3 / 4, CORRUPTED_LIMIT);
		GENERATION_SHAPES_SPEED = NEWSHAPE_SPEED;
		UPDATED_SHAPES_SPEED = UPDATE_SPEED;
	}

}
