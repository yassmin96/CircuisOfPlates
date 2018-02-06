package game.shapes;

public interface ShapePool  {
 
    public Shape pull(final int x, final int y, final int beltLength);
    public void returntoPoll(Shape shape);
    }
