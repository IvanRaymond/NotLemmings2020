package l3.lemmings.model;

import java.util.ArrayList;

// ToDo Create an abstract class or interface Trap as they will all
//  behave the same but just look different
public class  Lava {

    private int x, y;

    public Lava(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void reach(Level level){
        ArrayList<Lemming> lemmings = level.getLemmings();
        for (Lemming lemming : lemmings){
            if (lemming.getX() == x && lemming.getY() == y){
                lemming.kill();
            }
        }
        level.setLemmings(lemmings);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
