package l3.lemmings.model;

import java.util.ArrayList;

public class Staircase {

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
    private int stepCount = 5;
    private boolean done = false;

    public Staircase(Lemming lemming){
        this.x = (int) lemming.getX();
        this.y = (int) lemming.getY();
        this.lemming = lemming;
        steps.add(new Step(x,y));
    }

    // ToDo Prevent death by fall & changing direction in the middle of building staircase
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
        }
    }

    public boolean completed(){
        return done;
    }
}
