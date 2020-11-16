package l3.lemmings.model.lemming.state;

import l3.lemmings.model.Game;
import l3.lemmings.model.lemming.Lemming;

import java.lang.reflect.GenericArrayType;

public class Digger implements State, Behaviour {

    Lemming lemming;
    Game game;

    int digCount = 0;

    public Digger(Lemming lemming, Game context){
        this.lemming = lemming;
        game = context;
    }

    public void incDigCount(){
        digCount++;
    }

    public int getDigCount(){
        return digCount;
    }

    public void resetDigCount(){
        digCount=0;
    }

    @Override
    public boolean doAction() {



        return true;
    }

    @Override
    public boolean walk() {
        return false;
    }

    @Override
    public boolean reachWall() {
        return false;
    }

    @Override
    public boolean reachBlock() {
        return false;
    }

    @Override
    public boolean fallingLow() {
        return false;
    }

    @Override
    public boolean fallingHigh() {
        return false;
    }

}
