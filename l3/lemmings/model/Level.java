package l3.lemmings.model;

import l3.lemmings.model.block.Block;
import l3.lemmings.model.lemming.Lemming;
import l3.lemmings.model.props.Entrance;
import l3.lemmings.model.props.Staircase;

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

    private final boolean levelCompleted = false;

    private final ArrayList<Element> elements = new ArrayList<>();

    private final Physics gamePhysics = new Physics(this);

    private int safe = 0;
    private int objective = 0;  // Number of lemmings to save for win
    private int flow = 2;

    public Level() {
        objective = 2;

        for (int i = 1; i < 23; i++) {
            elements.add(new Block(i, 6));
        }
        for (int i = 7; i < 17; i++) {
            elements.add(new Block(i, 9));
        }
        elements.add(new Block(10, 8));

//        elements.add(new Lemming(this, 10,8));
        elements.add(new Lemming(this, 20, 11));

        for (int i = 17; i < 30; i++) {
            elements.add(new Block(i, 12));
        }
//        elements.add(new Block(29, 11));
//        elements.add(new Block(30,10));
//        elements.add(new Block(29, 10));
        elements.add(new Block(28, 4));
        for(int i = 11; i>=4; i--){
            elements.add(new Block(29,i));
        }

        elements.add(new Entrance(this, 5,20,11));
//        elements.add(new Block(28,8));
//        elements.add(new Block(28,7));
//        elements.add(new Block(26,8));
//        elements.add(new Block(26,7));
//        elements.add(new Block(20,8));
//
//        elements.add(new Block(9,5));
//        elements.add(new Block(6,5));
//        elements.add(new Block(7,5));
//        elements.add(new Block(6,4));
//        elements.add(new Block(5,3));
//        elements.add(new Block(1,4));
//        elements.add(new Block(1,5));
//
//        elements.add(new Block(19,15));
//        elements.add(new Block(19,14));
//
//        elements.add(new Block(15,5));
//        elements.add(new Block(16,5));
//        elements.add(new Block(17,5));
//        elements.add(new Block(18,5));
//        elements.add(new Block(19,5));
//        elements.add(new Block(15,4));
//        elements.add(new Block(16,4));
//        elements.add(new Block(17,4));
//        elements.add(new Block(18,4));
//        elements.add(new Block(19,4));
//        elements.add(new Block(24,4));
//        elements.add(new Block(24,5));
//        elements.add(new Entrance(this, 2, 9, 5));
//
//        elements.add(new Block(11,5));
//        elements.add(new Block(11,4));
//
//        elements.add(new Entrance(this, 1, 12,4));
//
//        elements.add(new Escape(25 ,3));
//
//        elements.add(new Switch(27, 9, new ArrayList<Block>(){{add(new Block(9,16));add(new Block(8,16));add(new Block(7,16));add(new Block(6,16));add(new Block(5,16));add(new Block(4,16)); add(new Block(9,14));add(new Block(8,14));add(new Block(7,14));add(new Block(6,14));add(new Block(5,14));add(new Block(4,14));add(new Block(9,15));add(new Block(8,15));add(new Block(7,15));add(new Block(6,15));add(new Block(5,15));}}));
//
//        for(int i = 10; i<20;i++)
//        {
//            elements.add(new Block(i,16));
//        }
//        for(int i = 20; i< 25;i++)
//        {
//            elements.add(new Lava(i,16));
//        }
//        elements.add(new Block(10,14));
//        elements.add(new Block(24,14));
//        elements.add(new Block(24,15));
//        elements.add(new Block(10,15));
//        elements.add(new Entrance(this, 2, 12, 15));
//        elements.add(new Escape(25 ,13));
//
//
//        elements.add(new Teleporter(7,11,22,13));
//        elements.add(new Teleporter(22,12,3,14));
//
//        for(int i=  2; i <= 14; i++){
//            elements.add(new Block(i,21));
//        }
//        elements.add(new Entrance(this, 5, 2,20));
//        elements.add(new Escape(14,20));
//        elements.add(new Entrance(this, 5, 1,15));
//
//        for(int i=  2; i <= 14; i++){
//            elements.add(new Block(i,18));
//        }
//        elements.add(new Entrance(this, 30, 2,17));
//        elements.add(new Escape(14,17));
//
//        for(int i = 0; i <= 7; i++){
//            elements.add(new Block(i,16));
//        }
//        elements.add(new Block(0,14));
//        elements.add(new Block(0,15));
//        elements.add(new Block(6,14));
//        elements.add(new Block(6,15));
//
//
//        // Testing of Bombs
//        for(int i = 33; i < 40; i++)
//            for(int j = 0; j < 7;j++)
//                if(!(i == 36 && j == 3))
//                    elements.add(new Block(i,j));
//        elements.add(new Entrance(this, 1, 36, 3));
//        //
//
//        //Testing digger & parachute
//        for(int i = 29; i < 32; i++)
//            for(int j = -1; j < 7;j++)
//                if(!(i == 30 && j == 0))
//                    elements.add(new Block(i,j));
//        elements.add(new Entrance(this, 1, 30, 0));
//        elements.add(new Escape(30,15));
//        //
//
//        //Test lemming big fall death
//        for(int i = 26; i < 29; i++)
//            for(int j = -1; j < 2;j++)
//                if(!(i == 27 && j == 0))
//                    elements.add(new Block(i,j));
//        elements.add(new Entrance(this, 1, 27, 0));
//
//        //Testing traps and possible 2 blocks wall
//        for(int i = 33; i < 40; i++)
//                if(!(i == 36 ))
//                    elements.add(new Block(i,15));
//        elements.add(new Block(33,14));
//        elements.add(new Block(33,13));
//        elements.add(new Block(39,14));
//        elements.add(new Block(39,13));
////        traps.add(new Bomb(36,15));
//        elements.add(new Entrance(this, 2, 36, 10));
//        //

        // Don't remove, entrance bug fix
        if (elements.size() % 2 == 0) {
            elements.add(new Entrance(this, 0, -1, -1));
        }
    }


    public boolean lemmingPresent(Point cell) {
        Lemming currentLemming;
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).compare(Type.LEMMING)) {
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
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).compare(Type.LEMMING)) {
                currentLemming = (Lemming) elements.get(i);
                if (currentLemming.isAt(cell)) {
                    return currentLemming;
                }
            }
        }
        return null;
    }

    public void killAll() {
        Lemming currentLemming;
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).compare(Type.LEMMING)) {
                currentLemming = (Lemming) elements.get(i);
                currentLemming.getStats().kill();
            }
        }
    }

    /**
     * Removes destroyed blocks and dead lemmings from lists.
     */
    public void update() {
        if (won()) {
            // Attendre que tous les lemmings soit mort et ensuite afficher un message
            System.out.println("You won");
        }

        for(int i=0; i < elements.size(); i++) {
            elements.get(i).update();
        }
        gamePhysics.update();

    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void incSafe() {
        safe++;
    }

    public Staircase buildStaircase(Lemming lemming) {
        Staircase staircase = new Staircase(lemming);
        elements.add(staircase);
        return staircase;
    }

    public void killSurrounding(Lemming l){
        ArrayList<Element> toRemove = new ArrayList<>();
        for(Element e : elements){
            if(e.compare(Type.LEMMING)) {
                Point p = e.getPosition();
                if (p.getX() <= l.getX() + 5 && p.getX() >= l.getX() - 5 &&
                        p.getY() >= l.getY() - 5 && p.getY() <= l.getY()) {
                    toRemove.add(e);
                }
            }
        }
        elements.remove(toRemove);
    }

    public boolean won() {
        return objective == safe;
    }

    public void addLemmings(Lemming lemming) {
        elements.add(lemming);
    }


    public void increaseFlow() {
        if (flow > 1) {
            flow--;
        }
    }

    public void decreaseFlow() {
        if (flow < 5) {
            flow++;
        }
    }

    public void printFlow() {
        System.out.println("" + flow);
    }

    public int getFlow() {
        return flow;
    }

    /**
     * Remove an element from the list, called by a lemming doing an action.
     *
     * @param element
     */
    public void remove(Element element) {
        elements.remove(element);
    }
}
