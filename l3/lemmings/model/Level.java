package l3.lemmings.model;

import java.awt.*;
import java.util.ArrayList;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.*;

/**
 * Model of a level.
 * Could be used to implement levels from json files.
 */
public class Level {

    private boolean levelCompleted = false;
    private ArrayList<Lemming> lemmings = new ArrayList<>();
    private ArrayList<Block> blocks = new ArrayList<>();
    private ArrayList<Entrance> entrances = new ArrayList<>();
    private ArrayList<Escape> escapes = new ArrayList<>();
    private int safe = 0;
    private int objective = 0;  // Number of lemmings to save for win
    private int flow = 2;

    public Level(){
        for(int i = 10; i<25;i++)
        {
            blocks.add(new Block(i,16));
        }
        blocks.add(new Block(10,14));
        blocks.add(new Block(10,15));
        blocks.add(new Block(17,15));
        blocks.add(new Block(18,14));
        blocks.add(new Block(19,13));
        blocks.add(new Block(24,14));
        blocks.add(new Block(24,15));
        entrances.add(new Entrance(this, 2, 10, 15));
        entrances.add(new Entrance(this, 2, 18, 15));

        // Don't remove, entrance bug fix
        if(entrances.size()%2==0){
            entrances.add(new Entrance(this, 0, -1, -1));
        }
        escapes.add(new Escape(15,15));
        escapes.add(new Escape(20,15));
        objective = 10;
    }

    public boolean lemmingPresent(Point cell) {
        Lemming currentLemming;
        for (int i=0; i<lemmings.size(); i++) {
            currentLemming = lemmings.get(i);
            if (currentLemming.getX() == cell.x && currentLemming.getY() == cell.y) {
                return true;
            }
        }
        return false;
    }

    public Lemming getLemming(Point cell) {
        Lemming currentLemming;
        for (int i=0; i<lemmings.size(); i++) {
            currentLemming = lemmings.get(i);
            if (currentLemming.getX() == cell.x && currentLemming.getY() == cell.y) {
                return currentLemming;
            }
        }
        return null;
    }

    public void killAll(){
        lemmings.clear();
    }

    /**
     * Removes destroyed blocks and dead lemmings from lists.
     */
    public void update(){
        if (won()) {
            // Do something
        }
        for(Escape escape: escapes){
            escape.reach(this);
        }
        for (int i=0; i<blocks.size(); i++) {
            if(blocks.get(i).destroyed()) {
                blocks.remove(i);
            }
        }
        for (int i=0; i<lemmings.size(); i++) {
            if(!lemmings.get(i).alive()) {
                lemmings.remove(i);
            }
        }
    }

    public void incSafe(){
        safe++;
    }

    public boolean won(){
        return objective==safe;
    }

    public ArrayList<Lemming> getLemmings() {
        return lemmings;
    }

    public void setLemmings(ArrayList<Lemming> lemmings) {
        this.lemmings = lemmings;
    }

    public void addLemmings(Lemming lemming){
        lemmings.add(lemming);
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Entrance getEntrance(int index){
        return entrances.get(index);
    }

    public ArrayList<Entrance> getEntrances(){
        return entrances;
    }

    public void increaseFlow(){
        if (flow > 1){
            flow--;
        }
    }

    public void decreaseFlow(){
        if (flow < 5){
            flow++;
        }
    }

    public int getFlow(){
        return flow;
    }

}
