package graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by josh_000 on 13/02/2016.
 */
public class DijkstraProgramGUI {
    public final int WINDOW_WIDTH;
    public final int WINDOW_HIGHT;

    public DijkstraProgramGUI(int width, int hight) {
        WINDOW_WIDTH = width;
        WINDOW_HIGHT = hight;
        init();
    }

    private JPanel jp_canvas;

    private void init() {
        JFrame jf_main = new JFrame("Dijkstra Algorithm Program");
        jf_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf_main.setResizable(false);

        jp_canvas = new JPanel();
        jp_canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HIGHT));
        jp_canvas.setBackground(Color.WHITE);
        jf_main.add(jp_canvas);

        jf_main.pack();
        jf_main.setVisible(true);


    }
    public Graphics createBrush(){
        return jp_canvas.getGraphics();
    }
}


