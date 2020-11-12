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
    private ArrayList<Teleporter> teleporters = new ArrayList<>();
    private ArrayList<Lava> lava = new ArrayList<>();
    private ArrayList<Staircase> staircases = new ArrayList<>();
    private ArrayList<Trap> traps = new ArrayList<Trap>();
    private int safe = 0;
    private int objective = 0;  // Number of lemmings to save for win
    private int flow = 2;

    public Level(){
        objective = 2;
        for(int i = 10; i<25;i++)
        {
//            blocks.add(new Block(i,16));
            traps.add(new Bomb(i,16));
        }
        blocks.add(new Block(10,14));
        blocks.add(new Block(10,15));
        blocks.add(new Block(17,15));
        blocks.add(new Block(18,14));
        blocks.add(new Block(19,13));
        blocks.add(new Block(24,14));
        blocks.add(new Block(24,15));
//        blocks.add(new Block(24,13));
//        blocks.add(new Block(24,12));
//        blocks.add(new Block(24,11));
        entrances.add(new Entrance(this, 2, 11, 15));
        escapes.add(new Escape(25 ,13));
//        entrances.add(new Entrance(this, 2, 10, 15));
//        entrances.add(new Entrance(this, 2, 18, 15));
//        escapes.add(new Escape(15,15));
//        escapes.add(new Escape(20,15));
//        teleporters.add(new Teleporter(15, 15, 21,15));
//        lava.add(new Lava(22,15));
        lava.add(new Lava(25,16));
        teleporters.add(new Teleporter(36,8,10,11));
        teleporters.add(new Teleporter(10,10,38,8));
        traps.add(new Bomb(20,15));

        // Testing of Bombs
        for(int i = 33; i < 40; i++)
            for(int j = 0; j < 7;j++)
                if(!(i == 36 && j == 3))
                    blocks.add(new Block(i,j));
        entrances.add(new Entrance(this, 1, 36, 2));
        //

        // Don't remove, entrance bug fix
        if(entrances.size()%2==0){
            entrances.add(new Entrance(this, 0, -1, -1));
        }
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
            // Attendre que tous les lemmings soit mort et ensuite afficher un message
            System.out.println("You won");
        }
        for(Escape escape: escapes){
            escape.reach(this);
        }
        for(Teleporter teleporter: teleporters){
            teleporter.reach(this);
        }
        for(Lava lava: lava){
            lava.reach(this);
        }
        for(Staircase staircase: staircases){
            if(!staircase.completed()) {
                staircase.build();
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

    public Staircase buildStaircase(Lemming lemming){
        Staircase staircase = new Staircase(lemming);
        staircases.add(staircase);
        return staircase;
    }

    public boolean won(){
        return objective==safe;
    }

    public void setBlocks(ArrayList<Block> blocks){
        this.blocks = blocks;
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

    public ArrayList<Trap> getTraps(){
        return traps;
    }

    public ArrayList<Staircase> getStaircases(){
        return staircases;
    }

    public ArrayList<Lava> getLava(){
        return lava;
    }

    public Entrance getEntrance(int index){
        return entrances.get(index);
    }

    public ArrayList<Entrance> getEntrances(){
        return entrances;
    }

    public ArrayList<Escape> getEscapes(){
        return escapes;
    }

    public ArrayList<Teleporter> getTeleporters(){
        return teleporters;
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

    public void printFlow(){
        System.out.println(""+flow);
    }

    public int getFlow(){
        return flow;
    }

}
