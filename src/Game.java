import java.awt.*;

public class Game extends Canvas {
    private Handler handler;

    //FIELDS
    public static int WIDTH  = 1400;
    public static int HEIGHT = WIDTH / 16 * 9;

    public static void main(String[] args){
        Game ga = new Game();
        new Window(WIDTH, HEIGHT, "Guitar neck", ga);

    }


    //METHODS
    public void start(Graphics g){
        Dimension size = new Dimension (WIDTH, HEIGHT);
        setPreferredSize(size);
        handler = new Handler(WIDTH, HEIGHT, g);
        paint(g);


    }

    public void paint(Graphics g){
        handler.render(g);
    }
}
