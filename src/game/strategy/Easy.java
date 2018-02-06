package game.strategy;

import static utilities.Properties.*;


import plateGenerator.PlateGenerator;

public class Easy implements GameStrategy {
	
	private static final int CORRUPTED_LIMIT = 40;
	private static final int NEWSHAPE_SPEED = 2700;
	private static final int UPDATE_SPEED = 120;
	
	@Override
	public void setGameStrategy(PlateGenerator randomGenerator) {
		randomGenerator.setColorLimit(randomGenerator.getColorsRange() / 2, CORRUPTED_LIMIT);
		GENERATION_SHAPES_SPEED = NEWSHAPE_SPEED;
		UPDATED_SHAPES_SPEED = UPDATE_SPEED;
	}

}
