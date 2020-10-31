package l3.lemmings.model;

import java.util.ArrayList;

public class Escape {
    // ToDo: Make block, escape and entrance inherit from the same abstract class and instantiate a reach method to
    //  define what happens when a lemming reach the block

    private int x, y;

    public Escape(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void reach(Level level){
        ArrayList<Lemming> lemmings = level.getLemmings();
        for (Lemming lemming : lemmings){
            if (lemming.getX() == x && lemming.getY() == y){
                lemming.kill();
                level.incSafe();
            }
        }
        level.setLemmings(lemmings);
    }




}
