package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import game.shapes.Shape;
public class Shapes  {

	private ArrayList<Shape> shapes;
	
	public Shapes (){
		shapes = new ArrayList<Shape>();
	}

	public boolean add(Shape shape) {
		
		return shapes.add((Shape) shape);
		
	}
	public Iterator<Shape> iterator() {
		return new IteratorShapes();
	}

	
	public boolean remove(Shape shape) {
		return shapes.remove((Shape)shape);
	}

	public void clear() {
		shapes.clear();
		
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
	private class IteratorShapes implements Iterator<Shape> {
		
    int pointer =0;
	@Override
	public boolean hasNext() {
		if (pointer< shapes.size()){
			return true;
			
		}
		return false;
	}

	@Override
	public Shape next() {
		if (hasNext()){
			Shape shape =shapes.get(pointer);
			pointer++;
			return shape;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	}
}
