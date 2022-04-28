import java.awt.*;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Window extends Canvas {
    private static Graphics g;

    private static final long serialVersionUID = -7259108873705494293L;


    public Window (int x, int y, String title, Game ga){
        JFrame frame = new JFrame (title);
        frame.setSize(x,y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(ga);
        frame.setResizable(false);
        frame.setVisible(true);
        g = frame.getGraphics();
        ga.start(g);

    }

    public Graphics getG(){
        return g;
    }
}


