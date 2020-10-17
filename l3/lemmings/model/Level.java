package l3.lemmings.model;

import java.awt.*;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * Model of a level.
 * Could be used to implement levels from json files.
 */
public class Level {

    private ArrayList<Lemming> lemmings = new ArrayList<>();
    private ArrayList<Block> blocks = new ArrayList<>();
    private Entrance entrance;

    public Level(){
        // Could be placed in the Model in a class Level.
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
        entrance = new Entrance(this, 3, 11, 16);
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
        for (int i=0; i<lemmings.size(); i++) {
            lemmings.remove(i);
        }
    }

    // Add destroyed blocks removal
    public void update(){
        for (int i=0; i<lemmings.size(); i++) {
            if(!lemmings.get(i).alive()) {
                lemmings.remove(i);
            }
        }
    }

    public ArrayList<Lemming> getLemmings() {
        return lemmings;
    }

    public void setLemmings(ArrayList<Lemming> lemmings) {
        this.lemmings = lemmings;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public Entrance getEntrance(){
        return entrance;
    }
}
