package l3.lemmings.model;

import java.util.ArrayList;

public class Bomb extends Trap {
    private int x, y;

    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void activate(Level level) {
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
