package l3.lemmings.observable.lemming;

import l3.lemmings.observable.IDrawable;
import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Level;
import l3.lemmings.observable.Type;
import l3.lemmings.observable.lemming.direction.Direction;
import l3.lemmings.observable.lemming.state.Normal;
import l3.lemmings.observable.lemming.state.State;
import l3.lemmings.observer.IObserver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LemmingObservable implements ILemming, IElement {

    private Lemming lemming;
    private List<IObserver> observers;

    public LemmingObservable(Level level, int x, int y){
        lemming = new Lemming(level, x, y);
        lemming.setState(new Normal(this));
        observers = new ArrayList<IObserver>();
    }

    @Override
    public Point getPosition() {
        return lemming.getPosition();
    }

    @Override
    public Point getSecondPosition() {
        return null;
    }

    @Override
    public Direction getDirection() {
        return lemming.getDirection();
    }

    public void setPosition(Point point){
        lemming.setPosition(point);
    }

    @Override
    public void move() {
        lemming.move();
        notifyObservers();
    }

    public void kill(){
        lemming.getStats().kill();
    }

    @Override
    public boolean isAlive() {
        return lemming.isAlive();
    }

    @Override
    public boolean compare(Type type) {
        return lemming.compare(type);
    }

    @Override
    public boolean destroy() {
        return false;
    }

    public void register(IObserver o) {
        observers.add(o);
    }

    public void unregister(IObserver o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (int i=0; i< observers.size(); i++) {
            observers.get(i).update();
        }
    }

    public State state(){
        return lemming.state();
    }

    public void setState(State state){
        lemming.setState(state);
    }

    public Surrounding surrounding(){
        return lemming.surrounding();
    }

    public boolean isAt(Point point){
        return lemming.isAt(point);
    }

    public Stats getStats(){
        return lemming.getStats();
    }

    public Lemming getLemming(){
        return lemming;
    }

    public void setX(int x){
        lemming.setX(x);
    }

    public void setY(int y){
        lemming.setY(y);
    }

    public Color getColor() {return lemming.getColor();}

    public IDrawable view(){
        return lemming.view();
    }

}
