package l3.lemmings.model;

import java.util.ArrayList;

/**
 * Represents the point of creation of lemmings
 */
public class Entrance {

    private int x, y;
    private Level level;
    private int numberOfLemmings;

    public Entrance(Level level, int numberOfLemmings, int x, int y){
        this.x = x;
        this.y = y;
        this.level = level;
        this.numberOfLemmings = numberOfLemmings;
    }

    public void addLemming(){
        if (numberOfLemmings>0) {
            ArrayList<Lemming> lemmings = level.getLemmings();
            lemmings.add(new Lemming(x, y));
            level.setLemmings(lemmings);
            numberOfLemmings--;
        }
    }

}
