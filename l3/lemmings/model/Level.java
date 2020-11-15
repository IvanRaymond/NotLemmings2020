package l3.lemmings.model;

import l3.lemmings.model.block.Block;
import l3.lemmings.model.block.Bomb;
import l3.lemmings.model.block.Switch;
import l3.lemmings.model.lemming.Lemming;
import l3.lemmings.model.props.Entrance;
import l3.lemmings.model.props.Escape;
import l3.lemmings.model.props.Staircase;
import l3.lemmings.model.props.Teleporter;
import l3.lemmings.model.trap.Lava;
import l3.lemmings.model.trap.Trap;

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
    private ArrayList<Switch> switches = new ArrayList<Switch>();

    private int safe = 0;
    private int objective = 0;  // Number of lemmings to save for win
    private int flow = 2;

    public Level(){
        objective = 2;

        // Test grimper un block, retour si deux blocks, lemming grimpeur
        // 1: have first lemming become un grimpeur; The second and third lemming hit the wall and go back; The now first lemming go to the exit while the second lemming blows himself up to activate the switch
        for(int i = 1; i<21;i++)
        {
            blocks.add(new Block(i,6));
        }
        for(int i = 7; i<30; i++){
            blocks.add(new Block(i,9));
        }
        blocks.add(new Block(28,8));
        blocks.add(new Block(28,7));
        blocks.add(new Block(26,8));
        blocks.add(new Block(26,7));
        blocks.add(new Block(20,8));

        blocks.add(new Block(9,5));
        blocks.add(new Block(6,5));
        blocks.add(new Block(7,5));
        blocks.add(new Block(6,4));
        blocks.add(new Block(5,3));
        blocks.add(new Block(1,4));
        blocks.add(new Block(1,5));

        blocks.add(new Block(19,15));
        blocks.add(new Block(19,14));

        blocks.add(new Block(15,5));
        blocks.add(new Block(16,5));
        blocks.add(new Block(17,5));
        blocks.add(new Block(18,5));
        blocks.add(new Block(19,5));
        blocks.add(new Block(15,4));
        blocks.add(new Block(16,4));
        blocks.add(new Block(17,4));
        blocks.add(new Block(18,4));
        blocks.add(new Block(19,4));
        blocks.add(new Block(24,4));
        blocks.add(new Block(24,5));
        entrances.add(new Entrance(this, 2, 9, 5));

        blocks.add(new Block(11,5));
        blocks.add(new Block(11,4));

        entrances.add(new Entrance(this, 1, 12,4));

        escapes.add(new Escape(25 ,3));

//        escapes.add(new Escape(10 ,5));
        switches.add(new Switch(27, 9, new ArrayList<Block>(){{add(new Block(9,16));add(new Block(8,16));add(new Block(7,16));add(new Block(6,16));add(new Block(5,16));add(new Block(4,16)); add(new Block(9,14));add(new Block(8,14));add(new Block(7,14));add(new Block(6,14));add(new Block(5,14));add(new Block(4,14));add(new Block(9,15));add(new Block(8,15));add(new Block(7,15));add(new Block(6,15));add(new Block(5,15));}}));
        /////



        // Test lava, generation escalier, lemming blocker, lemming tunnelier
        //1: put a blocker on the last dirt; 2: put a ladder right before the stop with the last lemming coming right, make the lemming going left bash the wall on the left to go the tunnel

        for(int i = 10; i<20;i++)
        {
            blocks.add(new Block(i,16));
        }
        for(int i = 20; i< 25;i++)
        {
            lava.add(new Lava(i,16));
        }
        blocks.add(new Block(10,14));
        blocks.add(new Block(24,14));
        blocks.add(new Block(24,15));
        blocks.add(new Block(10,15));
        entrances.add(new Entrance(this, 2, 12, 15));
        escapes.add(new Escape(25 ,13));


        teleporters.add(new Teleporter(7,11,22,13));
        teleporters.add(new Teleporter(22,12,3,14));

        for(int i=  2; i <= 14; i++){
            blocks.add(new Block(i,21));
        }
        entrances.add(new Entrance(this, 5, 2,20));
        escapes.add(new Escape(14,20));
        entrances.add(new Entrance(this, 5, 1,15));

        for(int i=  2; i <= 14; i++){
            blocks.add(new Block(i,18));
        }
        entrances.add(new Entrance(this, 30, 2,17));
        escapes.add(new Escape(14,17));

        for(int i = 0; i <= 7; i++){
            blocks.add(new Block(i,16));
        }
        blocks.add(new Block(0,14));
        blocks.add(new Block(0,15));
//        blocks.add(new Block(3,15));
        blocks.add(new Block(6,14));
        blocks.add(new Block(6,15));



//        entrances.add(new Entrance(this, 2, 10, 15));
//        entrances.add(new Entrance(this, 2, 18, 15));
//        escapes.add(new Escape(15,15));
//        escapes.add(new Escape(20,15));
//        teleporters.add(new Teleporter(15, 15, 21,15));
//        lava.add(new Lava(22,15));
//         lava.add(new Lava(25,16));
//         teleporters.add(new Teleporter(36,8,10,11));
//         teleporters.add(new Teleporter(10,10,38,8));
//         traps.add(new Bomb(20,15));

//         switches.add(new Switch(36,7, new ArrayList<Block>(){{add(new Block(9,16));add(new Block(8,16));add(new Block(7,16));add(new Block(6,16));}}));


        // Testing of Bombs
        for(int i = 33; i < 40; i++)
            for(int j = 0; j < 7;j++)
                if(!(i == 36 && j == 3))
                    blocks.add(new Block(i,j));
        entrances.add(new Entrance(this, 1, 36, 3));
        //

        //Testing digger & parachute
        for(int i = 29; i < 32; i++)
            for(int j = -1; j < 7;j++)
                if(!(i == 30 && j == 0))
                    blocks.add(new Block(i,j));
        entrances.add(new Entrance(this, 1, 30, 0));
        escapes.add(new Escape(30,15));
        //

        //Test lemming big fall death
        for(int i = 26; i < 29; i++)
            for(int j = -1; j < 2;j++)
                if(!(i == 27 && j == 0))
                    blocks.add(new Block(i,j));
        entrances.add(new Entrance(this, 1, 27, 0));

        //Testing traps and possible 2 blocks wall
        for(int i = 33; i < 40; i++)
                if(!(i == 36 ))
                    blocks.add(new Block(i,15));
        blocks.add(new Block(33,14));
        blocks.add(new Block(33,13));
        blocks.add(new Block(39,14));
        blocks.add(new Block(39,13));
        traps.add(new Bomb(36,15));
        entrances.add(new Entrance(this, 2, 36, 10));
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

    public ArrayList<Switch> getSwitches() {
        return switches;
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
