package plateGenerator;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import game.shapes.Shape;

public class LeftBelt extends Belt {

	public LeftBelt(final int x, final int y, final int length) {
		super();
		super.x = x;
		super.y = y;
		this.length = length;
	}

	@Override
	public Shape addShape() {
		Shape shape = randomGenerator.getRandomShape(x - 40, y - 11, length);
		shape.setSpeed(randomGenerator.getRandomSpeed());
		return shape;
	}

	@Override
	public void drawBelt(Graphics2D g) {
		g.setColor(color);
		g.setStroke(thickness);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawLine(x, y, x + length, y);
	}

}
