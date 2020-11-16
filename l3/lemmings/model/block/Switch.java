package l3.lemmings.model.block;

import l3.lemmings.model.Element;
import l3.lemmings.model.Level;
import l3.lemmings.model.Type;
import l3.lemmings.model.block.Block;

import java.awt.*;
import java.util.ArrayList;

public class Switch implements Element {

    private Point point = new Point();
    ArrayList<Block> blocks;

    public Switch(int x, int y, ArrayList<Block> b){
        point.x = x;
        point.y = y;
        blocks = b;
    }

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    @Override
    public Point getPosition() {
        return new Point(point);
    }

    @Override
    public Point getSecondPosition() {
        return null;
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public boolean interact(Element element, Level level) {
        return false;
    }

    @Override
    public String getType(){
        return "switch";
    }

    @Override
    public boolean compare(Type type) {
        return type == Type.SWITCH;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
