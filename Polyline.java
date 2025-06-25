import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 * @author Tim Pierson Dartmouth CS 10, provided for Winter 2025
 */
public class Polyline implements Shape {
	// TODO: YOUR CODE HERE
	private Color color;
	private List<Point> polyLine;

	/**
	 * Constructor
	 */
	public Polyline(Color color){
		this.color = color;
		this.polyLine = new ArrayList<>();
	}
	@Override
	public void moveBy(int dx, int dy) {
		for (Point p : polyLine){
			int x = (int) p.getX();
			int y = (int) p.getY();
			p.setLocation(x + dx, y + dy);
		}
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public boolean contains(int x, int y) {
		for (int i = 0; i < polyLine.size() - 1; i++){
			int x1 = (int) polyLine.get(i).getX();
			int y1 = (int) polyLine.get(i).getY();
			int x2 = (int) polyLine.get(i+1).getX();
			int y2 = (int) polyLine.get(i+1).getY();
			if (Segment.pointToSegmentDistance(x, y, x1, y1, x2, y2) <= 3) return true;
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		for (int i = 0; i < polyLine.size() - 1; i++){
			int x1 = (int) polyLine.get(i).getX();
			int y1 = (int) polyLine.get(i).getY();
			int x2 = (int) polyLine.get(i+1).getX();
			int y2 = (int) polyLine.get(i+1).getY();
			g.drawLine(x1, y1, x2, y2);
		}
	}

	@Override
	public String toString() {
		String s = "polyline ";
		for (Point p : polyLine) {
			s += p.getX() + " ";
			s += p.getY() + " ";
		}
		s += color.getRGB();
		return s;
	}

	/**
	 * Adds a point 
	 * @param p
	 */
	public void addPoint(Point p) {
		polyLine.add(p);
	}
}
