package l3.lemmings.model.trap;

import l3.lemmings.model.Element;
import l3.lemmings.model.Level;
import l3.lemmings.model.lemming.Lemming;

import java.util.ArrayList;

// ToDo Create an abstract class or interface Trap as they will all
//  behave the same but just look different
public class Lava implements Trap, Element {

    private int x, y;

    public Lava(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void reach(Level level){
        ArrayList<Lemming> lemmings = level.getLemmings();
        for (Lemming lemming : lemmings){
            if (lemming.getX() == x && lemming.getY() == y-1){
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

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public boolean interfact(Element element) {
        return false;
    }

    @Override
    public void activate(Level level) {

    }
}
