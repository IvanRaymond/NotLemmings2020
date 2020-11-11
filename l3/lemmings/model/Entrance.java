package l3.lemmings.model;

import java.util.ArrayList;

/**
 * Represents the point of creation of lemmings
 */
public class Entrance {

    private int x, y;
    private Level level;
    private int numberOfLemmings;
    private boolean done = false;
    int n;

    public Entrance(Level level, int numberOfLemmings, int x, int y){
        this.x = x;
        this.y = y;
        this.level = level;
        this.numberOfLemmings = numberOfLemmings;
        n = numberOfLemmings;
    }

    public void addLemming(){
        if (numberOfLemmings>0) {
            level.addLemmings(new Lemming(x, y));
            numberOfLemmings--;
        }else{
            done = true;
        }
    }

    public void printNumberOfLemmings(){
        System.out.println(""+n);
    }

    public boolean getDone(){
        return done;
    }

}
