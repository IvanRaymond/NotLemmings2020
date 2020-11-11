package l3.lemmings.model;

import java.util.ArrayList;

public class Bomb {
    private int x, y;

    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }

    //ToDo Maybe use the same logic as the exploding lemming
    public void reach(Level level){
        ArrayList<Lemming> lemmings = level.getLemmings();
        for (Lemming lemming : lemmings){
            if (lemming.getX() == x && lemming.getY() == y){
                lemming.kill();
            }
        }
        level.setLemmings(lemmings);
    }
}
