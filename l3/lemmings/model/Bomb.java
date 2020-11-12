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
        ArrayList<Block> blocks = new ArrayList<>(level.getBlocks());
        ArrayList<Lemming> lemmings = new ArrayList<>(level.getLemmings());

        //ToDo needs testing
        blocks.removeIf(block -> block.getX() <= x + 5 && block.getX() >= x - 5 && block.getY() >= x - 5 && block.getY() <= x + 5);
        lemmings.removeIf(lemming -> lemming.getX() >= x - 5 && lemming.getX() <= x + 5 && lemming.getY() >= x - 5 && lemming.getY() <= x + 5);
        level.setLemmings(lemmings);
        level.setBlocks(blocks);
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
