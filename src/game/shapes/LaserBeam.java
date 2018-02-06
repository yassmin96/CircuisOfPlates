package game.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import static utilities.Properties.frameWidth;

public class LaserBeam {


	public LaserBeam() {

	}

	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.red.darker());
		g.setStroke(new BasicStroke(5));
		g.drawLine(0, 140, frameWidth(), 140); // remove static dimensions!!
	}

}
