import java.awt.*;

/**
 * Parser class takes a String-based command (sent via writer to reader) and parses it
 * to determine what action to perform and update the sketch accordingly
 */
public class Parser {
    private Sketch sketch;
    public Parser(Sketch sketch) {
        this.sketch = sketch;
    }
    /**
     * @param line the line to be parsed
     */
    public void parse(String line) {
        String[] words = line.split(" ");
        String command = words[0];
        if (command.equals("move")) {
            sketch.moving(Integer.parseInt(words[1]), (int)Double.parseDouble(words[2]), (int)Double.parseDouble(words[3]));
        }
        else if (command.equals("recolor")) {
            Color c = new Color((int)Double.parseDouble(words[2]));
            sketch.recolor(Integer.parseInt(words[1]), c);
        }
        else if (command.equals("delete")) {
            sketch.delete(Integer.parseInt(words[1]));
        }
        else if (command.equals("add")) {
            String type = words[1];
            int x1 = (int)Double.parseDouble(words[2]);
            int y1 = (int)Double.parseDouble(words[3]);
            int x2 = (int)Double.parseDouble(words[4]);
            int y2 = (int)Double.parseDouble(words[5]);
            Color c = new Color((int)Double.parseDouble(words[6]));
            if (type.equals("rectangle")) {
                sketch.addShape(new Rectangle(x1, y1, x2, y2, c));
            }
            else if (type.equals("ellipse")) {
                sketch.addShape(new Ellipse(x1, y1, x2, y2, c));
            }
            else if (type.equals("segment")) {
                sketch.addShape(new Segment(x1, y1, x2, y2, c));
            }
            else if (type.equals("polyline")) {
                c = new Color((int)Double.parseDouble(words[words.length-1]));
                Polyline p = new Polyline(c);
                for (int i = 2; i < words.length - 2; i+=2) {
                    int x = (int)Double.parseDouble(words[i]);
                    int y = (int)Double.parseDouble(words[i+1]);
                    p.addPoint(new Point(x, y));
                }
                sketch.addShape(p);
            }
        }
    }
}
