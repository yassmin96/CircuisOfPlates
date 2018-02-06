package game.shapes;

import java.awt.Color;

import game.strategy.GameStrategy;

public abstract class IShapeFactory {
	
	public IShapeFactory() {
		
	}
	
	public abstract Shape getRandomShape(final int x, final int y, final int beltLength, final int randomshape, final Color randomColor);
	
	
}
