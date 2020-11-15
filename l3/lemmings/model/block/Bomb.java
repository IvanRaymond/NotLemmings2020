package l3.lemmings.model.block;

import l3.lemmings.model.Element;
import l3.lemmings.model.Level;
import l3.lemmings.model.trap.Trap;

public class Bomb implements Element, Trap  {
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

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public boolean interfact(Element element) {
        return false;
    }
}
