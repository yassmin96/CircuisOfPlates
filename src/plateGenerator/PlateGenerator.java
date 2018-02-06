package plateGenerator;
 
import java.awt.Color;


import java.util.HashMap;

import game.shapes.CorruptPool;
import game.shapes.CorruptShape;
import game.shapes.IShapeFactory;
import game.shapes.Shape;
import game.shapes.ShapeFactory;
import game.shapes.ShapePool;
import game.strategy.GameStrategy;
import game.strategy.IStrategyFactory;
import game.strategy.StrategyFactory;
 
public class PlateGenerator {
 
    private IShapeFactory shapeFactory;
    private IStrategyFactory strategyFactory;
    private GameStrategy strategy;
    private ShapePool corruptPool;
    private HashMap<Integer, Color> colors;
    // private String difficultyLevel;
    private int minSpeed;
    private int maxSpeed;
    private int colorLimit;
    private int limitCorrupt;
    private int shapeCounter=0;
    
    public PlateGenerator() {
        intializeColors();
        shapeFactory = new ShapeFactory();
        strategyFactory = new StrategyFactory();
        corruptPool = CorruptPool.getInstance();
    }
   
    private void intializeColors() {
        colors = new HashMap<Integer, Color>();
        colors.put(0, new Color (40, 53, 147));  // blue
        colors.put(1, new Color (174, 79, 107)); // brown
        colors.put(2, new Color (178, 135, 0));  // almostgrey
        colors.put(3, new Color  (47, 79, 79));  // dark lategrey
        colors.put(4, new Color (255, 235, 59)); // yellow
        colors.put(5, new Color (56, 142, 60));  // green
        colors.put(6, new Color (74, 35, 90));   // violet
        colors.put(7, new Color (255, 140, 0));  // dark orange
        
            
    }
    
   public void setDifficultyLevel(String difficultyLevel) {
        strategy = strategyFactory.getStrategy(difficultyLevel);
        strategy.setGameStrategy(this);
    }
   
    public void setColorLimit(int colorLimit, int limitCorrupt) {
        this.colorLimit = colorLimit;
        this.limitCorrupt = limitCorrupt;
    }
 
    public int getColorsRange() {
    	return colors.size();
    }
    
    private Color getRandomColor() {
        int randomNum = getRandomNumber();
        randomNum %= colorLimit;
        return colors.get(randomNum);
    }
 
    public int getRandomNumber() {
        int randomNum = (int) Math.round((((float) Math.random()) * 452521));
        randomNum &= (int) Math.round((((float) Math.random()) * 321654));
        randomNum ^= (int) Math.round((((float) Math.random()) * 987456));
        return randomNum;
    }
 
	public Shape getRandomShape(final int x, final int y, final int beltLength) {
		shapeCounter++;
		int randomNum = getRandomNumber();
		Color shapeColor = getRandomColor();
		if (shapeCounter == limitCorrupt) {
			Shape temp = corruptPool.pull(x, y, beltLength);
			if (temp != null) {
				shapeCounter = 0;
				return temp;
			}
		}
        return shapeFactory.getRandomShape(x, y, beltLength, randomNum, shapeColor);
	}
 
    public int getRandomSpeed() {
        int randomNum = 10;
        
        while (randomNum <= 10) {
            randomNum = getRandomNumber();
            randomNum %= 40;
        }
        
        return randomNum;
    }
}