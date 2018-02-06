package game.strategy;

import java.awt.Color;


import game.shapes.IShapeFactory;
import game.shapes.Shape;

public class StrategyFactory implements IStrategyFactory {
	
	public StrategyFactory() {
		
	}
	
	@Override
	public GameStrategy getStrategy(String difficultyLevel) {
		if (difficultyLevel.equals("Easy"))
            return new Easy();
        if (difficultyLevel.equals("Medium"))
        	return new Medium();
        if (difficultyLevel.equals("Hard"))
        	return new Hard();
        return null;
	}
}
