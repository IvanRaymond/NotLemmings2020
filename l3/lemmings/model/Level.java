package l3.lemmings.model;

import l3.lemmings.model.block.Block;
import l3.lemmings.model.block.Switch;
import l3.lemmings.model.lemming.Lemming;
import l3.lemmings.model.props.Entrance;
import l3.lemmings.model.props.Escape;
import l3.lemmings.model.props.Staircase;
import l3.lemmings.model.props.Teleporter;
import l3.lemmings.model.trap.Lava;

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
//    private ArrayList<Lemming> lemmings = new ArrayList<>();
//    private ArrayList<Block> blocks = new ArrayList<>();
//    private ArrayList<Entrance> entrances = new ArrayList<>();
//    private ArrayList<Escape> escapes = new ArrayList<>();
//    private ArrayList<Teleporter> teleporters = new ArrayList<>();
//    private ArrayList<Lava> lava = new ArrayList<>();
//    private ArrayList<Staircase> staircases = new ArrayList<>();
//    private ArrayList<Switch> switches = new ArrayList<Switch>();

    private ArrayList<Element> elements = new ArrayList<>();

    private int safe = 0;
    private int objective = 0;  // Number of lemmings to save for win
    private int flow = 2;

    public Level(){
        objective = 2;

        // Test grimper un block, retour si deux blocks, lemming grimpeur
        // 1: have first lemming become un grimpeur; The second and third lemming hit the wall and go back; The now first lemming go to the exit while the second lemming blows himself up to activate the switch
        for(int i = 1; i<21;i++)
        {
            elements.add(new Block(i,6));
        }
        for(int i = 7; i<30; i++){
            elements.add(new Block(i,9));
        }
        elements.add(new Block(28,8));
        elements.add(new Block(28,7));
        elements.add(new Block(26,8));
        elements.add(new Block(26,7));
        elements.add(new Block(20,8));

        elements.add(new Block(9,5));
        elements.add(new Block(6,5));
        elements.add(new Block(7,5));
        elements.add(new Block(6,4));
        elements.add(new Block(5,3));
        elements.add(new Block(1,4));
        elements.add(new Block(1,5));

        elements.add(new Block(19,15));
        elements.add(new Block(19,14));

        elements.add(new Block(15,5));
        elements.add(new Block(16,5));
        elements.add(new Block(17,5));
        elements.add(new Block(18,5));
        elements.add(new Block(19,5));
        elements.add(new Block(15,4));
        elements.add(new Block(16,4));
        elements.add(new Block(17,4));
        elements.add(new Block(18,4));
        elements.add(new Block(19,4));
        elements.add(new Block(24,4));
        elements.add(new Block(24,5));
        elements.add(new Entrance(this, 2, 9, 5));

        elements.add(new Block(11,5));
        elements.add(new Block(11,4));

        elements.add(new Entrance(this, 1, 12,4));

        elements.add(new Escape(25 ,3));

//        escapes.add(new Escape(10 ,5));
        elements.add(new Switch(27, 9, new ArrayList<Block>(){{add(new Block(9,16));add(new Block(8,16));add(new Block(7,16));add(new Block(6,16));add(new Block(5,16));add(new Block(4,16)); add(new Block(9,14));add(new Block(8,14));add(new Block(7,14));add(new Block(6,14));add(new Block(5,14));add(new Block(4,14));add(new Block(9,15));add(new Block(8,15));add(new Block(7,15));add(new Block(6,15));add(new Block(5,15));}}));
        /////



        // Test lava, generation escalier, lemming blocker, lemming tunnelier
        //1: put a blocker on the last dirt; 2: put a ladder right before the stop with the last lemming coming right, make the lemming going left bash the wall on the left to go the tunnel

        for(int i = 10; i<20;i++)
        {
            elements.add(new Block(i,16));
        }
        for(int i = 20; i< 25;i++)
        {
            elements.add(new Lava(i,16));
        }
        elements.add(new Block(10,14));
        elements.add(new Block(24,14));
        elements.add(new Block(24,15));
        elements.add(new Block(10,15));
        elements.add(new Entrance(this, 2, 12, 15));
        elements.add(new Escape(25 ,13));


        elements.add(new Teleporter(7,11,22,13));
        elements.add(new Teleporter(22,12,3,14));

        for(int i=  2; i <= 14; i++){
            elements.add(new Block(i,21));
        }
        elements.add(new Entrance(this, 5, 2,20));
        elements.add(new Escape(14,20));
        elements.add(new Entrance(this, 5, 1,15));

        for(int i=  2; i <= 14; i++){
            elements.add(new Block(i,18));
        }
        elements.add(new Entrance(this, 30, 2,17));
        elements.add(new Escape(14,17));

        for(int i = 0; i <= 7; i++){
            elements.add(new Block(i,16));
        }
        elements.add(new Block(0,14));
        elements.add(new Block(0,15));
//        blocks.add(new Block(3,15));
        elements.add(new Block(6,14));
        elements.add(new Block(6,15));


        // Testing of Bombs
        for(int i = 33; i < 40; i++)
            for(int j = 0; j < 7;j++)
                if(!(i == 36 && j == 3))
                    elements.add(new Block(i,j));
        elements.add(new Entrance(this, 1, 36, 3));
        //

        //Testing digger & parachute
        for(int i = 29; i < 32; i++)
            for(int j = -1; j < 7;j++)
                if(!(i == 30 && j == 0))
                    elements.add(new Block(i,j));
        elements.add(new Entrance(this, 1, 30, 0));
        elements.add(new Escape(30,15));
        //

        //Test lemming big fall death
        for(int i = 26; i < 29; i++)
            for(int j = -1; j < 2;j++)
                if(!(i == 27 && j == 0))
                    elements.add(new Block(i,j));
        elements.add(new Entrance(this, 1, 27, 0));

        //Testing traps and possible 2 blocks wall
        for(int i = 33; i < 40; i++)
                if(!(i == 36 ))
                    elements.add(new Block(i,15));
        elements.add(new Block(33,14));
        elements.add(new Block(33,13));
        elements.add(new Block(39,14));
        elements.add(new Block(39,13));
//        traps.add(new Bomb(36,15));
        elements.add(new Entrance(this, 2, 36, 10));
        //

        // Don't remove, entrance bug fix
        if(elements.size()%2==0){
            elements.add(new Entrance(this, 0, -1, -1));
        }
    }


    public boolean lemmingPresent(Point cell) {
        Lemming currentLemming;
        for (int i=0; i<elements.size(); i++) {
            if ((elements.get(i).toString()).equals("L")) {
                currentLemming = (Lemming) elements.get(i);
                if (currentLemming.isAt(cell)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Lemming getLemming(Point cell) {
        Lemming currentLemming;
        for (int i=0; i<elements.size(); i++) {
            if ((elements.get(i).toString()).equals("L")) {
                currentLemming = (Lemming) elements.get(i);
                if (currentLemming.isAt(cell)) {
                    return currentLemming;
                }
            }
        }
        return null;
    }

    public void killAll(){
        Lemming currentLemming;
        for (int i=0; i<elements.size(); i++) {
            if ((elements.get(i).toString()).equals("L")) {
                currentLemming = (Lemming) elements.get(i);
                currentLemming.kill();
            }
        }
    }

    /**
     * Removes destroyed blocks and dead lemmings from lists.
     */
    public void update(){
        if (won()) {
            // Attendre que tous les lemmings soit mort et ensuite afficher un message
            System.out.println("You won");
        }
//        for(Escape escape: escapes){
//            escape.reach(this);
//        }
//        for(Teleporter teleporter: teleporters){
//            teleporter.reach(this);
//        }
//        for(Lava lava: lava){
//            lava.reach(this);
//        }
//        for(Staircase staircase: staircases){
//            if(!staircase.completed()) {
//                staircase.build();
//            }
//        }
//        for (int i=0; i<lemmings.size(); i++) {
//            if(!lemmings.get(i).alive()) {
//                lemmings.remove(i);
//            }
//        }
    }

    public void incSafe(){
        safe++;
    }

    public Staircase buildStaircase(Lemming lemming){
        Staircase staircase = new Staircase(lemming);
        elements.add(staircase);
        return staircase;
    }

    public boolean won(){
        return objective==safe;
    }

    public void addLemmings(Lemming lemming){
        elements.add(lemming);
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

    public void remove(Element element){
        // Remove element from element list
        // Must be called by a lemming
    }
}
