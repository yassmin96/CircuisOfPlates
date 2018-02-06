package game.shapes.states;

import game.shapes.Shape;

import static utilities.Properties.frameWidth;

public class OnBelt extends State {
	
	private int xCenter;
	private int yCenter;
	
	public OnBelt() {
		super();
		state = "OnBelt";
	}

	@Override
	public int getUpdatedX() {
		return xCenter;
	}

	@Override
	public int getUpdatedY() {
		return yCenter;
	}
	
	@Override
	public boolean updateCoor(int moveSpeed, int x, int y, int beltLength) {
		this.xCenter = x + moveSpeed;
		this.yCenter = y;
		if (moveSpeed > 0)
			return this.xCenter >= beltLength;
		else
			return this.xCenter <= frameWidth() - beltLength;
	}

	@Override
	public void updateSate(Shape shape) {
		boolean stateChanged = updateCoor(shape.getSpeed(), shape.getX(), shape.getY(), shape.getBeltLength());
		shape.setCenter(xCenter, yCenter);
		if (stateChanged)
			shape.setState(new Falling(xCenter, yCenter));
	}
}
