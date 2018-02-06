package game.shapes;
 
import java.util.ArrayList;
 
public class CorruptPool implements ShapePool{
 
    private final static int LIMIT = 5;
    private static ArrayList<CorruptShape> corruptPool = new ArrayList<>();
    private static CorruptPool corruptPoolIns;
    private static int shapesCounter=0;
    private CorruptPool(){
       
    }
    public static CorruptPool getInstance(){
        if (corruptPoolIns == null){
            corruptPoolIns = new CorruptPool();
        }
        return corruptPoolIns;
    }
    @Override
    public Shape pull(final int x, final int y, final int beltLength){     
        if (corruptPool.size()!= 0){
            CorruptShape old =corruptPool.get(0);
            old.setObject(x, y, beltLength);
            shapesCounter++;
            return old;        
        }
        if (shapesCounter < LIMIT){
            shapesCounter++;
            return new CorruptShape(x, y, beltLength);
        }
        return null;
    }
   
    @Override
    public void returntoPoll (Shape endedShape){
        shapesCounter--;
        corruptPool.add((CorruptShape)endedShape);     
    }
}