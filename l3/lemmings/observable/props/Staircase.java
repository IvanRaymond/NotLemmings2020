package l3.lemmings.observable.props;

import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Level;
import l3.lemmings.observable.Type;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;
import l3.lemmings.observable.lemming.direction.DirHorizontal;
import l3.lemmings.observable.lemming.state.Normal;
import l3.lemmings.observer.IObserver;

import java.awt.*;
import java.util.ArrayList;

public class Staircase implements IElement, IObserver {

    public class Step implements IElement, IObserver {
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
        public boolean compare(Type type) {
            return type == Type.STEP;
        }

        @Override
        public void update() {

        }
    }

    ArrayList<Step> steps = new ArrayList<>();

    private final Point point = new Point();
    private final LemmingObservable lemming;
    private int stepCount = 4;
    private boolean done = false;

    public Staircase(LemmingObservable lemming) {
        point.x = (int) lemming.getPosition().getX();
        point.y = (int) lemming.getPosition().getY();
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
    public void update() {
        build();
    }

    @Override
    public boolean compare(Type type) {
        return type == Type.STAIRCASE;
    }
}
