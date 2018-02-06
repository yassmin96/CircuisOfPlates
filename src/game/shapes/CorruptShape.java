package game.shapes;

import java.awt.Graphics2D;

import game.shapes.states.OnBelt;

import static utilities.Properties.*;

public class CorruptShape extends Shape{

	
	public CorruptShape(final int x, final int y, final int beltLength) {
		super();
		super.beltLength = beltLength;
		xCenter = x;
		yCenter = y;
		color = Corrupt_Color;

	}
	
	public void setObject (final int x, final int y, final int beltLength){ // why!!!
        setState(new OnBelt());
		xCenter = x;
		yCenter = y;
		super.beltLength = beltLength;
	}

	@Override
	public void draw(Graphics2D g) {
		setShape(g);
		int topLeftX = super.xCenter - (width / 2);
		int topLeftY = super.yCenter - (height / 2);
		g.fillRect(topLeftX, topLeftY, width, height);
	}
}
