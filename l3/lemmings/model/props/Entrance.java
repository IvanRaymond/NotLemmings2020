package l3.lemmings.model.props;

import l3.lemmings.model.Element;
import l3.lemmings.model.Level;
import l3.lemmings.model.lemming.Lemming;

/**
 * Represents the point of creation of lemmings
 */
public class Entrance implements Element {

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

    public int getX() {
        return x;
    }

    public int getY() {
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
}
