package l3.lemmings.model.props;

import l3.lemmings.model.Element;
import l3.lemmings.model.lemming.Lemming;
import l3.lemmings.model.lemming.Normal;

import java.util.ArrayList;

public class Staircase implements Element {

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public boolean interfact(Element element) {
        return false;
    }

    public class Step {
        private int x, y;
        public Step(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
    }

    ArrayList<Step> steps = new ArrayList<>();

    private int x, y;
    private Lemming lemming;
    private int stepCount = 4;
    private boolean done = false;

    public Staircase(Lemming lemming){
        this.x = lemming.getX();
        this.y = lemming.getY();
        this.lemming = lemming;
        steps.add(new Step(x,y));
    }

    // ToDo Prevent changing direction in the middle of building staircase and prevent
    //  building against structure
    public void build(){
        if(stepCount>0) {
            Step lastStep = steps.get(steps.size()-1);
            if(lemming.getDirectionAxisX()>0){
                lemming.setX(lastStep.getX());
                lemming.setY(lastStep.getY()-1);
                lemming.resetFallCount();
                steps.add(new Step(lastStep.getX()+1,lastStep.getY()-1));
            }else{
                lemming.setX(lastStep.getX());
                lemming.setY(lastStep.getY()-1);
                lemming.resetFallCount();
                steps.add(new Step(lastStep.getX()-1,lastStep.getY()-1));
            }
            stepCount--;
        }else {
            done = true;
            lemming.toggleBusy();
            lemming.setState(new Normal());
            lemming.resetFallCount();
        }
    }

    public boolean completed(){
        return done;
    }

    public ArrayList<Step> getSteps(){
        return steps;
    }
}
