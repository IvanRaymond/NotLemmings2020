package l3.lemmings.model;

/**
 * Contains game variables
 */
public class Game {

    private boolean pause = false;
    private final Level level;

    public Game() {
        level = new Level();
    }

    public void update() {
        level.update();
    }

    public void togglePause() {
        pause = !pause;
    }

    public boolean pause() {
        return pause;
    }

    public Level getLevel() {
        return level;
    }

}
