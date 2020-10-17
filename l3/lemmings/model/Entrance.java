package l3.lemmings.model;

import java.util.ArrayList;

/**
 * Represents the point of creation of lemmings
 */
public class Entrance {

    // Number of frames between addition of a lemming.
    private int flow = 2;
    private int x;
    private int y;
    private Level level;

    public Entrance(Level level, int numberOfLemmings, int x, int y){
        this.x = x;
        this.y = y;
        this.level = level;
        letTheLemmingsOut(numberOfLemmings);
    }

    private void letTheLemmingsOut(int numberOfLemmings){
        ArrayList<Lemming> lemmings = new ArrayList<>();
        for(int i=0; i<=numberOfLemmings; i++){
            lemmings.add(new Lemming(x,y));
        }
        level.setLemmings(lemmings);
    }

    public void increaseFlow(){
        if (flow < 5){
            flow++;
        }
    }

    public void decreaseFlow(){
        if (flow > 1){
            flow--;
        }
    }

    // Used to test flow change button
    public void printFlow(){
        System.out.println("flow = "+flow);
    }

}
