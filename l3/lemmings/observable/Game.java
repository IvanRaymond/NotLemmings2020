package l3.lemmings.observable;

/**
 * Contains game variables
 */
public class Game {

    private boolean pause = false;
    private final Level level;
    private Physics gamePhysics;

    public Game() {
        level = new Level();
        gamePhysics = new Physics(level);
    }

    public void step() {
        level.step();
        gamePhysics.step();
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
