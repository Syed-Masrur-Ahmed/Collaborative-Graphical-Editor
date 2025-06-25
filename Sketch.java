import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Sketch class
 */
public class Sketch {
    private int id;
    private TreeMap<Integer, Shape> shapes;

    /**
     * Constructor
     */
    public Sketch(){
        id = 0;
        shapes = new TreeMap<>();
    }

    /**
     * Draws all the shapes in the sketch
     */
    public synchronized void draw(Graphics g){
        for(Integer k : shapes.keySet()){
           shapes.get(k).draw(g);
        }
    }

    /**
     * Contains method
     * @param x x coordinate of mouse press
     * @param y y coordinate of mouse press
     * @return shape id if shape contains the press location, null otherwise
     */
    public synchronized Integer contains(int x, int y){
        for(Integer k: shapes.descendingKeySet()){
            if(shapes.get(k).contains(x,y)){
                return k;
            }
        }
        return null; //if no shape has point
    }


    /**
     * Move shape based on id
     * @param id shape id of shape to be moved
     * @param dx change in x coordinate
     * @param dy change in y coordinate
     */
    public synchronized void moving(int id, int dx, int dy){
        if(shapes.get(id)!= null)  shapes.get(id).moveBy(dx, dy);
    }

    /**
     *
     * @param id id of shape to be recolored
     * @param color the new color of the shape
     */
    public synchronized void recolor(int id, Color color){
        shapes.get(id).setColor(color);
    }

    /**
     * delete shape
     * @param id id of shape to be deleted
     */
    public synchronized void delete(int id){
        shapes.remove(id);
    }

    /**
     * Add shape nd return id
     * @param s
     * @return id of shape
     */
    public synchronized int addShape (Shape s) {
        id++;
        shapes.put(id, s);
        return id;
    }

    /**
     * Gets shapes
     * @return shapes TreeMap
     */
    public TreeMap<Integer, Shape> getShapes() {
        return shapes;
    }
}
