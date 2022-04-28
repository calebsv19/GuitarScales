import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.util.List;

public class Handler {
    private int width;                 // width of frame
    private int height;                // height of frame
    private Set<Integer> pattern;
    private ArrayList<ArrayList<Integer>> strings;


    public static final int FRETS = 15, LEFT_EDGE = 150, RIGHT_EDGE = 1250, STRETCH = 200, FRET_WIDTH = 8;
    public static final int STR_BUFFER = 15, STRINGS = 6, STRING_WIDTH = 3, NECK_MARGIN = 0, SIZE = 20;

    public static final int START_PITCH = 0;

    public static final double RATIO = 7E4;      // modifiable ratio to change pixel distance value




    // creates list of strings and pitch associated with each fret
    // also creates standard model of scales with pitch gaps
    public Handler(int width, int height, Graphics g){

        this.width = width;
        this.height = height;
        strings = new ArrayList<>();
        addStrings();
        pattern = new HashSet<>();
        pattern.add(0);
        pattern.add(2);
        pattern.add(4);
        pattern.add(5);
        pattern.add(7);
        pattern.add(9);
        pattern.add(11);

        render(g);
    }


    // adds string and each strings given starting pitch
    private void addStrings(){
        int numPitch = 0;
        for (int i = 0; i < STRINGS; i++){
            strings.add(new ArrayList<>());

            for (int j = 0; j < FRETS; j++){
                strings.get(i).add(numPitch + j);
            }

            if (i == STRINGS - 3){
                numPitch += 4;
            } else {
                numPitch += 5;
            }
        }
    }

    // creates string and pitch setup
    public void render(Graphics g) {
        g.setColor(new Color(0,0,0));
        g.fillRect(0, 0, width, height);

        // creates neck of guitar
        g.setColor(new Color(45, 20, 5));
        g.fillRect(LEFT_EDGE - NECK_MARGIN, (height - STRETCH) / 2 - NECK_MARGIN / 2,
                (RIGHT_EDGE - LEFT_EDGE) + 2 * NECK_MARGIN, STRETCH + NECK_MARGIN);

        // creates top and bottom fret, for this I chose to only span 12 frets, (complete octave)
        g.setColor(Color.WHITE);
        g.fillRect(LEFT_EDGE, (height - STRETCH) / 2, FRET_WIDTH, STRETCH);
        g.fillRect(RIGHT_EDGE, (height - STRETCH) / 2, FRET_WIDTH, STRETCH);

        // fills frets in between first and last
        for (int i = 1; i < FRETS; i++){
            g.fillRect(LEFT_EDGE + (i * (RIGHT_EDGE - LEFT_EDGE) / FRETS), (height - STRETCH) / 2, FRET_WIDTH / 3, STRETCH);
        }

        //
        for (int i = 0; i < STRINGS; i++){
            int stringStart = (STR_BUFFER + (height - STRETCH) / 2) + ((STRETCH - 2 * STR_BUFFER) / (STRINGS - 1) * i);
            g.fillRect(LEFT_EDGE, stringStart, RIGHT_EDGE - LEFT_EDGE, STRING_WIDTH);
        }


        int fretDistance = (RIGHT_EDGE - LEFT_EDGE) / FRETS;
        for (int i = 0; i < strings.size(); i++){
            for (int j = -1; j < strings.get(i).size(); j++){
                Color c;
                int value;
                if (j == -1){
                    value = (strings.get(i).get(j + 1) - START_PITCH + 36) % 12;
                } else {
                    value = (strings.get(i).get(j) + 1 - START_PITCH + 36) % 12;
                }

                if (pattern.contains(value)){
                    c = new Color(100, 180, 100);
                } else {
                    c = new Color(180, 100, 100);
                }

                int stringStart = ((height - STRETCH) / 2) + STRETCH - STR_BUFFER - ((STRETCH - 2 * STR_BUFFER) / (STRINGS - 1) * i);
                g.setColor(c);
                g.fillOval(LEFT_EDGE + (int) (fretDistance * (.5 + j)) - SIZE/2, stringStart - SIZE / 2, SIZE, SIZE);
            }
        }

    }
}