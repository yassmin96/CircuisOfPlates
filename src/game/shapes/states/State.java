package game.shapes.states;

import java.io.IOException;
import java.io.Serializable;

import game.shapes.Shape;

public abstract class State implements Serializable{

	protected String state;

	public State () {
		state = "";
	}
	
	public abstract int getUpdatedX();

	public abstract int getUpdatedY();

	public abstract void updateSate(Shape shape);

	public abstract boolean updateCoor(int moveSpeed, int x, int y, int beltLength); // read belt length from class

	private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}

	@Override
	public String toString() {
		return state;
	}
}
