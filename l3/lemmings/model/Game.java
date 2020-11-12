package l3.lemmings.model;

import java.util.ArrayList;

/**
 * Contains game variables
 */
public class Game {

    private int direction = 0;
    private int flowCounter=0;
    private boolean pause = false;
    private Level level;
    private ArrayList<Lemming> lemmings;
    private ArrayList<Block> blocks;

    public Game(){
        level = new Level();
        lemmings = level.getLemmings();
        blocks = level.getBlocks();
    }

    public void togglePause(){
        pause = !pause;
    }

    public boolean pause(){
        return pause;
    }

    public Level getLevel(){
        return level;
    }

    // Could use some refactoring to implement different lemmings behaviours
    public void update() {
        level.update();
        ArrayList<Trap> traps = level.getTraps();
        ArrayList<Entrance> entrances = level.getEntrances();
        for(Entrance e : entrances) {
            if (flowCounter == 0) {
                if(!e.getDone()) {
                    e.addLemming();
                }
//                entrances.get(1).addLemming();
                flowCounter = level.getFlow();
            }
            flowCounter--;
        }

        for(Lemming l : lemmings)
        {
            boolean[][] surrounding = new boolean[3][3];
            for(Block b : blocks)
            {
                if(l.getY() == b.getY())
                {	if(l.getX() == b.getX())
                    surrounding[1][1] = true;
                else if(l.getX() - 1 == b.getX() )
                    surrounding[0][1] = true;
                else if(l.getX() + 1 == b.getX())
                    surrounding[2][1] = true;}
                else if(l.getY() - 1 == b.getY())
                {
                    if(l.getX() == b.getX())
                        surrounding[1][0] = true;
                    else if(l.getX() - 1 == b.getX())
                        surrounding[0][0] = true;
                    else if(l.getX() + 1 == b.getX())
                        surrounding[2][0] = true;
                }
                else if(l.getY() + 1 == b.getY())
                {
                    if(l.getX() == b.getX())
                        surrounding[1][2] = true;
                    else if(l.getX() - 1 == b.getX())
                        surrounding[0][2] = true;
                    else if(l.getX() + 1 == b.getX())
                        surrounding[2][2] = true;
                }
            }
            for(Trap trap : traps)
            {
                if(l.getY() == trap.getY())
                {	if(l.getX() == trap.getX())
                    surrounding[1][1] = true;
                else if(l.getX() - 1 == trap.getX() )
                    surrounding[0][1] = true;
                else if(l.getX() + 1 == trap.getX())
                    surrounding[2][1] = true;}
                else if(l.getY() - 1 == trap.getY())
                {
                    if(l.getX() == trap.getX())
                        surrounding[1][0] = true;
                    else if(l.getX() - 1 == trap.getX())
                        surrounding[0][0] = true;
                    else if(l.getX() + 1 == trap.getX())
                        surrounding[2][0] = true;
                }
                else if(l.getY() + 1 == trap.getY())
                {
                    if(l.getX() == trap.getX())
                        surrounding[1][2] = true;
                    else if(l.getX() - 1 == trap.getX())
                        surrounding[0][2] = true;
                    else if(l.getX() + 1 == trap.getX())
                        surrounding[2][2] = true;
                }
            }
            for(Staircase staircase : getLevel().getStaircases())
            {
                for(Staircase.Step step : staircase.getSteps()) {
                    if (l.getY() == step.getY()) {
                        if (l.getX() == step.getX())
                            surrounding[1][1] = true;
                        else if (l.getX() - 1 == step.getX())
                            surrounding[0][1] = true;
                        else if (l.getX() + 1 == step.getX())
                            surrounding[2][1] = true;
                    } else if (l.getY() - 1 == step.getY()) {
                        if (l.getX() == step.getX())
                            surrounding[1][0] = true;
                        else if (l.getX() - 1 == step.getX())
                            surrounding[0][0] = true;
                        else if (l.getX() + 1 == step.getX())
                            surrounding[2][0] = true;
                    } else if (l.getY() + 1 == step.getY()) {
                        if (l.getX() == step.getX())
                            surrounding[1][2] = true;
                        else if (l.getX() - 1 == step.getX())
                            surrounding[0][2] = true;
                        else if (l.getX() + 1 == step.getX())
                            surrounding[2][2] = true;
                    }
                }
            }

            // Mur à gauche ou a droite de 2 blocks ou plus
            /*if(surrounding[0][0] && surrounding[0][1] && l.getDirectionAxisX() == -1 || surrounding[2][0] && surrounding[2][1] && l.getDirectionAxisX() == 1)
            {
                // ToDo: Add exceptions in other cases to cater for climber
                if(l.isState(Lemming.LemmingState.CLIMBER)){
                    l.saveDirectionX();
                    l.setDirectionAxisX(0);
                    l.setDirectionAxisY(-1);
                }else{
                    //surrounded
                    if(surrounding[0][0] && surrounding[0][1] && surrounding[2][0] && surrounding[2][1])
                        l.setDirectionAxisX(0);
                    else
                        l.changeDirectionX();
                }
            }*/
            if(surrounding[0][0] && surrounding[0][1] && l.getDirectionAxisX() == -1 || surrounding[2][0] && surrounding[2][1] && l.getDirectionAxisX() == 1)
            {

                    //surrounded
                    if(surrounding[0][0] && surrounding[0][1] && surrounding[2][0] && surrounding[2][1])
                        l.setDirectionAxisX(0);
                    else
                        l.changeDirectionX();
            }
            // Pas de sol en dessous
            if(!surrounding[1][2])
            {
                l.setDirectionAxisY(1);
                // Death by height
                l.fellToDeath(); // Checks if lemming fell to death and kills him if true
                l.incFallCount();
                if(l.getDirectionAxisX()!=0) {
                    l.saveDirectionX();
                    l.setDirectionAxisX(0);
                }
            }

            // Sol en dessous
            if(surrounding[1][2]){
                l.setDirectionAxisY(0);
                // Death by height
                l.fellToDeath();
                l.resetFallCount();
                if (l.getDirectionAxisX()==0){
                    l.restoreDirectionAxisX();
                    //Reset Floater to normal once ground touch
                    if(l.getState() == Lemming.LemmingState.FLOATER)
                        l.setState(Lemming.LemmingState.NORMAL);
                }
            }

            // Escaliers
            if(surrounding[2][1] && !surrounding[2][0] && l.getDirectionAxisX() == 1 || surrounding[0][1] && !surrounding[0][0] && l.getDirectionAxisX() == -1)
            {
                if(!surrounding[1][0])
                    l.setDirectionAxisY(-1);
                else {
                    l.changeDirectionX();
                }
            }

            // Digger
            if(l.isState(Lemming.LemmingState.DIGGER)){
                if(surrounding[1][2]) // [1][2] bugs
                {
                    Block belowBlock = null;
                    for(Trap trap: traps){
                        if(trap.getX() == l.getX() && trap.getY() == l.getY()+1) {
                            trap.activate(level);
                        }
                    }
                    for(Block b : blocks) {
                        if(b.getX() == l.getX() && b.getY() == l.getY()+1) {
                            belowBlock = b;
                        }
                    }
                    if(belowBlock != null)
                        blocks.remove(belowBlock);
                    else
                        l.setState(Lemming.LemmingState.NORMAL);
                }
            }

            // Bloqueur
            if(l.isState(Lemming.LemmingState.BLOCKER)){
                l.setDirectionAxisX(0);
            }
            for (Lemming lem: lemmings){
                if(lem.isState(Lemming.LemmingState.BLOCKER) && ((l.getDirectionAxisX()==1 && lem.getX() == l.getX()+1) || (l.getDirectionAxisX()==-1 && lem.getX() == l.getX()-1)) && lem.getY() == l.getY()){
                    l.changeDirectionX();
                }
            }

            // Tunnelier
            if(l.isState(Lemming.LemmingState.BASHER)){
                if(surrounding[2][1]) // [1][2] bugs
                {
                    Block belowBlock = null;
                    for(Trap trap: traps){
                        if(trap.getX() == l.getX()+ l.getDirectionAxisX() && trap.getY() == l.getY()) {
                            trap.activate(level);
                        }
                    }
                    for(Block b : blocks) {
                        if(b.getX() == l.getX()+ l.getDirectionAxisX() && b.getY() == l.getY()) {
                            belowBlock = b;
                        }
                    }
                    if(belowBlock != null)
                        blocks.remove(belowBlock);
                }
            }

            // Bomb
            if(l.isState(Lemming.LemmingState.BOMB)){
                ArrayList<Block> surroundingBlocks = new ArrayList<>();
                ArrayList<Trap> usedTraps = new ArrayList<>();
                for(Trap trap: traps){
                    if(trap.getX() == l.getX() - 1 && trap.getY() == l.getY() || trap.getX() == l.getX() + 1 && trap.getY() == l.getY() || trap.getX() == l.getX() && trap.getY() == l.getY() - 1  || trap.getX() == l.getX() && trap.getY() == l.getY() + 1 || trap.getX() == l.getX() - 2 && trap.getY() == l.getY() || trap.getX() == l.getX() + 2 && trap.getY() == l.getY() || trap.getX() == l.getX() && trap.getY() == l.getY() - 2  || trap.getX() == l.getX() && trap.getY() == l.getY() + 2 || trap.getX() == l.getX() - 1 && trap.getY() == l.getY() -1 || trap.getX() == l.getX() + 1 && trap.getY() == l.getY() +1 || trap.getX() == l.getX() + 1 && trap.getY() == l.getY() - 1  || trap.getX() == l.getX() + 1 && trap.getY() == l.getY() - 1 || trap.getX() == l.getX() - 2 && trap.getY() == l.getY() - 2 || trap.getX() == l.getX() + 2 && trap.getY() == l.getY() + 2 || trap.getX() == l.getX() + 2 && trap.getY() == l.getY() - 2  || trap.getX() == l.getX() + 2 && trap.getY() == l.getY() - 2 || trap.getX() == l.getX() - 1 && trap.getY() == l.getY() + 1 || trap.getX() == l.getX() - 2 && trap.getY() == l.getY() + 2 || trap.getX() == l.getX() - 1 && trap.getY() == l.getY() - 2 || trap.getX() == l.getX() + 1 && trap.getY() == l.getY() - 2 || trap.getX() == l.getX() + 2 && trap.getY() == l.getY() - 1 || trap.getX() == l.getX() - 2 && trap.getY() == l.getY() - 1 || trap.getX() == l.getX() - 2 && trap.getY() == l.getY() + 1 || trap.getX() == l.getX() + 2 && trap.getY() == l.getY() + 1 || trap.getX() == l.getX() + 1 && trap.getY() == l.getY() + 2 || trap.getX() == l.getX() - 1 && trap.getY() == l.getY() + 2) {
                        trap.activate(level);
                        usedTraps.add(trap);
                    }
                }
                for(Block b : blocks) {
                    if(b.getX() == l.getX() - 1 && b.getY() == l.getY() || b.getX() == l.getX() + 1 && b.getY() == l.getY() || b.getX() == l.getX() && b.getY() == l.getY() - 1  || b.getX() == l.getX() && b.getY() == l.getY() + 1 || b.getX() == l.getX() - 2 && b.getY() == l.getY() || b.getX() == l.getX() + 2 && b.getY() == l.getY() || b.getX() == l.getX() && b.getY() == l.getY() - 2  || b.getX() == l.getX() && b.getY() == l.getY() + 2 || b.getX() == l.getX() - 1 && b.getY() == l.getY() -1 || b.getX() == l.getX() + 1 && b.getY() == l.getY() +1 || b.getX() == l.getX() + 1 && b.getY() == l.getY() - 1  || b.getX() == l.getX() + 1 && b.getY() == l.getY() - 1 || b.getX() == l.getX() - 2 && b.getY() == l.getY() - 2 || b.getX() == l.getX() + 2 && b.getY() == l.getY() + 2 || b.getX() == l.getX() + 2 && b.getY() == l.getY() - 2  || b.getX() == l.getX() + 2 && b.getY() == l.getY() - 2 || b.getX() == l.getX() - 1 && b.getY() == l.getY() + 1 || b.getX() == l.getX() - 2 && b.getY() == l.getY() + 2 || b.getX() == l.getX() - 1 && b.getY() == l.getY() - 2 || b.getX() == l.getX() + 1 && b.getY() == l.getY() - 2 || b.getX() == l.getX() + 2 && b.getY() == l.getY() - 1 || b.getX() == l.getX() - 2 && b.getY() == l.getY() - 1 || b.getX() == l.getX() - 2 && b.getY() == l.getY() + 1 || b.getX() == l.getX() + 2 && b.getY() == l.getY() + 1 || b.getX() == l.getX() + 1 && b.getY() == l.getY() + 2 || b.getX() == l.getX() - 1 && b.getY() == l.getY() + 2) {
                        surroundingBlocks.add(b);
                    }
                }
                for (Trap trap : usedTraps){
                    traps.remove(trap);
                    l.kill();
                }
                for(Block b : surroundingBlocks)
                    blocks.remove(b);
                    l.kill();
            }

            // Builder
            if(l.isState(Lemming.LemmingState.BRIDGE_BUILDER)){
                if (!l.getIfBusy()) {
                    Staircase staircase = level.buildStaircase(l);
                    staircase.build();
                    l.toggleBusy();
                }
            }

            if(l.isState(Lemming.LemmingState.CLIMBER)){
                if(surrounding[0][1] && l.getDirectionAxisX() == -1 || surrounding[2][1] /*&& l.getDirectionAxisX() == 1*/)
                {
                    l.saveDirectionX();
                    l.setDirectionAxisX(0);
                    l.setDirectionAxisY(-1);
                }
                else if(!surrounding[2][1] && l.getDirectionAxisY() == 1)
                {
                    l.setX(l.getX() + 1);
                    l.setY(l.getY() - 1);
                    l.setState(Lemming.LemmingState.NORMAL);
                    l.restoreDirectionAxisX();
                }
                else if(!surrounding[0][1] && l.getDirectionAxisY() == 1)
                {
                    l.setX(l.getX() - 1);
                    l.setY(l.getY() - 1);
                    l.setState(Lemming.LemmingState.NORMAL);
                    l.restoreDirectionAxisX();
                }
            }


//            for(int i =0;i<3;i++)
//            {
//                for(int j =0;j<3;j++)
//                    System.out.print(" "+surrounding[i][j]);
//                System.out.println();
//            }
//            System.out.println();
            l.move();
        }

    }
}
