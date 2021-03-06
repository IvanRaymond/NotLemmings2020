package l3.lemmings;

import l3.lemmings.observable.Game;
import l3.lemmings.observer.Views;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

//ToDo: Implement all states...sike
//      Remove sprites
//      Implement special blocks

public class App {

    public final static int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public final static int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final static int MAX_FPS = 50;
    // maximum number of frames to be skipped
    private final static int MAX_FRAME_SKIPS = 5;
    // the frame period
    private final static int FRAME_PERIOD = 30000 / MAX_FPS;

    public static void main(String[] args) throws InterruptedException, IOException {

        int numCaseX = 40;
        int numCaseY = 24;
        Game game = new Game();

        Views view = new Views(game, WIDTH - 200, HEIGHT - 100, numCaseX, numCaseY);
        JFrame frame = new JFrame("Lemmingway");
        frame.add(view);
        frame.setSize(view.getSize());
        frame.setLocation(WIDTH / 2 - view.getSize().width / 2, HEIGHT / 2 - view.getSize().height / 2);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        while (true) {
            if (!game.pause()) {
                game.step();
                view.repaint(); //ToDo replace with observable in view
                Thread.sleep(700);
            }
            // Necessary to make pause work.
            else {
                Thread.sleep(50);
            }
        }
    }

}
