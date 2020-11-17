package l3.lemmings.model.props;

import l3.lemmings.model.Element;
import l3.lemmings.model.Level;
import l3.lemmings.model.Type;
import l3.lemmings.model.lemming.Lemming;
import l3.lemmings.model.lemming.direction.DirHorizontal;
import l3.lemmings.model.lemming.state.Normal;

import java.awt.*;
import java.util.ArrayList;

public class Staircase implements Element {

    public class Step implements Element {
        private final Point point = new Point();

        public Step(int x, int y) {
            point.x = x;
            point.y = y;
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
        public boolean update() {
            return false;
        }

        @Override
        public boolean interact(Element element, Level level) {
            return true;
        }

        @Override
        public String getType() {
            return "step";
        }

        @Override
        public String toString() {
            return "s";
        }

        @Override
        public boolean compare(Type type) {
            return type == Type.STEP;
        }
    }

    ArrayList<Step> steps = new ArrayList<>();

    private final Point point = new Point();
    private final Lemming lemming;
    private int stepCount = 4;
    private boolean done = false;

    public Staircase(Lemming lemming) {
        point.x = lemming.getX();
        point.y = lemming.getY();
        this.lemming = lemming;
        steps.add(new Step(point.x, point.y));
    }

    // ToDo Prevent building against structure
    public void build() {
        if (stepCount > 0) {
            Step lastStep = steps.get(steps.size() - 1);
            if (lemming.getDirection().isGoing(DirHorizontal.RIGHT)) {
                lemming.setX(lastStep.getX());
                lemming.setY(lastStep.getY() - 1);
                steps.add(new Step(lastStep.getX() + 1, lastStep.getY() - 1));
            } else {
                lemming.setX(lastStep.getX());
                lemming.setY(lastStep.getY() - 1);
                steps.add(new Step(lastStep.getX() - 1, lastStep.getY() - 1));
            }
            stepCount--;
        } else {
            done = true;
            lemming.getStats().toggleBusy();
            lemming.setState(new Normal(lemming));
        }
    }

    public ArrayList<Step> getSteps() {
        return steps;
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
    public boolean update() {
        build();
        return false;
    }

    // The interaction is done with the steps
    @Override
    public boolean interact(Element element, Level level) {
        return false;
    }

    @Override
    public String getType() {
        return "staircase";
    }

    @Override
    public boolean compare(Type type) {
        return type == Type.STAIRCASE;
    }
}
