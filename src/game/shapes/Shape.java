package game.shapes;

import game.shapes.states.OnBelt;
import game.shapes.states.State;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

import static utilities.Properties.SHAPE_HEIGHT;
import static utilities.Properties.SHAPE_WIDTH;

public abstract class Shape implements Serializable {

    protected int xCenter;
    protected int yCenter;
    protected int width;
    protected int height;
    protected int beltLength;
    protected transient Color color;
    private int moveSpeed;
    private State state;


    public Shape() {
        state = new OnBelt();
        width = SHAPE_WIDTH;
        height = SHAPE_HEIGHT;
    }

    public void setCenter(int x, int y) {
        this.xCenter = x;
        this.yCenter = y;
    }

    public int getSpeed() {
        return moveSpeed;
    }

    public void setSpeed(int randomSpeed) {
        this.moveSpeed = randomSpeed;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getBeltLength() {
        return beltLength;
    } // remove!!

    public int getX() {
        return xCenter;
    }

    public int getY() {
        return yCenter;
    }

    public Color getColor() {
        return color;
    }

    public void update() {
        state.updateSate(this);
        setCenter(state.getUpdatedX(), state.getUpdatedY());
    }

    protected void setShape(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
    }

    public abstract void draw(Graphics2D g);

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(color.getRGB());
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        color = new Color(stream.readInt());
    }
}
