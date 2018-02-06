package plateGenerator;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import game.shapes.Shape;

public class RightBelt extends Belt {

	public RightBelt(final int x, final int y, final int length) {
		super();
		this.x = x;
		this.y = y;
		this.length = length;
	}

	@Override
	public Shape addShape() {
		Shape shape = randomGenerator.getRandomShape(x + 40, y - 11, length);
		shape.setSpeed(-randomGenerator.getRandomSpeed());
		return shape;
	}

	@Override
	public void drawBelt(Graphics2D g) {
		g.setColor(color);
		g.setStroke(thickness);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawLine(x - length, y, x, y);
	}

}
