package l3.lemmings.model;

import java.util.ArrayList;

public class Switch {
    private int x, y;
    ArrayList<Block> blocks;

    public Switch(int x, int y, ArrayList<Block> b){
        this.x = x;
        this.y = y;
        blocks = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
