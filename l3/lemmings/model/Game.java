package l3.lemmings.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

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
        ArrayList<Switch> switches = level.getSwitches();
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
            for(Switch currentSwitch : switches)
            {
                if(l.getY() == currentSwitch.getY())
                {	if(l.getX() == currentSwitch.getX())
                    surrounding[1][1] = true;
                else if(l.getX() - 1 == currentSwitch.getX() )
                    surrounding[0][1] = true;
                else if(l.getX() + 1 == currentSwitch.getX())
                    surrounding[2][1] = true;}
                else if(l.getY() - 1 == currentSwitch.getY())
                {
                    if(l.getX() == currentSwitch.getX())
                        surrounding[1][0] = true;
                    else if(l.getX() - 1 == currentSwitch.getX())
                        surrounding[0][0] = true;
                    else if(l.getX() + 1 == currentSwitch.getX())
                        surrounding[2][0] = true;
                }
                else if(l.getY() + 1 == currentSwitch.getY())
                {
                    if(l.getX() == currentSwitch.getX())
                        surrounding[1][2] = true;
                    else if(l.getX() - 1 == currentSwitch.getX())
                        surrounding[0][2] = true;
                    else if(l.getX() + 1 == currentSwitch.getX())
                        surrounding[2][2] = true;
                }
            }

            // Mur à gauche ou a droite de 2 blocks ou plus
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

            // Bloqueur
            if(l.isState(Lemming.LemmingState.BLOCKER)){
                l.setDirectionAxisX(0);
            }
            for (Lemming lem: lemmings){
                if(lem.isState(Lemming.LemmingState.BLOCKER) && ((l.getDirectionAxisX()==1 && lem.getX() == l.getX()+1) || (l.getDirectionAxisX()==-1 && lem.getX() == l.getX()-1)) && lem.getY() == l.getY()){
                    l.changeDirectionX();
                }
            }


            // Digger
            if(l.isState(Lemming.LemmingState.DIGGER)){
                ArrayList<Block> surroundingBlocks = new ArrayList<>();
                ArrayList<Trap> usedTraps = new ArrayList<>();
                ArrayList<Switch> usedSwitch = new ArrayList<>();

                if(surrounding[1][2])
                {
                    Block belowBlock = null;

                    for(Switch currentSwitch :  switches) {
                        if(currentSwitch.getX() == l.getX() && currentSwitch.getY() == l.getY()+1) {
                            usedSwitch.add(currentSwitch);
                            blocks.addAll(currentSwitch.getBlocks());
                        }
                    }
                    for(Switch currentSwitch : usedSwitch)
                        switches.remove(currentSwitch);

                    for(Trap trap: traps){
                        if(trap.getX() == l.getX() && trap.getY() == l.getY()+1) {
                            for(Block b : blocks){
                                if(b.getX() == trap.getX() - 1 && b.getY() == trap.getY() || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() || b.getX() == trap.getX() && b.getY() == trap.getY() - 1  || b.getX() == trap.getX() && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() || b.getX() == trap.getX() && b.getY() == trap.getY() - 2  || b.getX() == trap.getX() && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() -1 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() +1 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() - 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() - 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() - 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() + 2) {
                                    surroundingBlocks.add(b);
                                }
                            }
                            for(Lemming lemming : lemmings){
                                if(lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() - 1  || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() - 2  || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() -1 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() +1 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() - 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() - 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() - 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() + 2) {
                                    lemming.kill();
                                }
                            }
                            for(Block b : surroundingBlocks) {
                                blocks.remove(b);
                            }
                        }
                        usedTraps.add(trap);
                    }
                    for(Trap usedTrap : usedTraps){
                        traps.remove(usedTrap);
                    }
                    for(Block b : blocks) {
                        if(b.getX() == l.getX() && b.getY() == l.getY()+1) {
                            belowBlock = b;
                        }
                    }
                    if(belowBlock != null) {
                        blocks.remove(belowBlock);
                        l.incDigCount();
                        l.setX(l.getX()-l.getDirectionAxisX());
                        if(l.getDigCount()==5){
                            l.resetDigCount();
                            l.setState(Lemming.LemmingState.NORMAL);
                        }
                    }
                }
            }


            // Tunnelier
            if(l.isState(Lemming.LemmingState.BASHER)){
                ArrayList<Block> surroundingBlocks = new ArrayList<>();
                ArrayList<Trap> usedTraps = new ArrayList<>();
                ArrayList<Switch> usedSwitch = new ArrayList<>();
                if(surrounding[2][1] || surrounding[0][1]) // [1][2] bugs
                {
                    Block adjacentBlock = null;

                    for(Switch currentSwitch :  switches) {
                        if(currentSwitch.getX() == l.getX() + l.getDirectionAxisX() && currentSwitch.getY() == l.getY() || currentSwitch.getX() == l.getX() - l.getDirectionAxisX() && currentSwitch.getY() == l.getY()) {
                            usedSwitch.add(currentSwitch);
                            blocks.addAll(currentSwitch.getBlocks());
                        }
                    }
                    for(Switch currentSwitch : usedSwitch)
                        switches.remove(currentSwitch);

                    for(Trap trap: traps){
                        if(trap.getX() == l.getX() + l.getDirectionAxisX() && trap.getY() == l.getY() || trap.getX() == l.getX() - l.getDirectionAxisX() && trap.getY() == l.getY()) {
                            for(Block b : blocks){
                                if(b.getX() == trap.getX() - 1 && b.getY() == trap.getY() || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() || b.getX() == trap.getX() && b.getY() == trap.getY() - 1  || b.getX() == trap.getX() && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() || b.getX() == trap.getX() && b.getY() == trap.getY() - 2  || b.getX() == trap.getX() && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() -1 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() +1 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() - 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() - 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() - 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() + 2) {
                                    surroundingBlocks.add(b);
                                }
                            }
                            for(Lemming lemming : lemmings){
                                if(lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() - 1  || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() - 2  || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() -1 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() +1 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() - 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() - 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() - 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() + 2) {
                                    lemming.kill();
                                }
                            }
                            for(Block b : surroundingBlocks) {
                                blocks.remove(b);
                            }
                        }
                        usedTraps.add(trap);
                    }
                    for(Trap usedTrap : usedTraps){
                        traps.remove(usedTrap);
                    }
                    for(Block b : blocks) {
                        // Bug: le fait de devoir avoir deux fois la condition avec un + et un -
                        if(b.getX() == l.getX() + l.getDirectionAxisX() && b.getY() == l.getY() || b.getX() == l.getX() - l.getDirectionAxisX() && b.getY() == l.getY()) {
                            adjacentBlock = b;
                        }
                    }
                    if(adjacentBlock != null)
                    {
                        l.changeDirectionX();
                        blocks.remove(adjacentBlock);
                    }else
                        l.setState(Lemming.LemmingState.NORMAL);
                }
            }

            // Bomb
            if(l.isState(Lemming.LemmingState.BOMB)){
                ArrayList<Block> surroundingBlocks = new ArrayList<>();
                ArrayList<Trap> usedTraps = new ArrayList<>();
                ArrayList<Switch> usedSwitch = new ArrayList<>();
                for(Switch currentSwitch :  switches) {
                    if(l.getX() == currentSwitch.getX() - 1 && l.getY() == currentSwitch.getY() || l.getX() == currentSwitch.getX() + 1 && l.getY() == currentSwitch.getY() || l.getX() == currentSwitch.getX() && l.getY() == currentSwitch.getY() - 1  || l.getX() == currentSwitch.getX() && l.getY() == currentSwitch.getY() + 1 || l.getX() == currentSwitch.getX() - 2 && l.getY() == currentSwitch.getY() || l.getX() == currentSwitch.getX() + 2 && l.getY() == currentSwitch.getY() || l.getX() == currentSwitch.getX() && l.getY() == currentSwitch.getY() - 2  || l.getX() == currentSwitch.getX() && l.getY() == currentSwitch.getY() + 2 || l.getX() == currentSwitch.getX() - 1 && l.getY() == currentSwitch.getY() -1 || l.getX() == currentSwitch.getX() + 1 && l.getY() == currentSwitch.getY() +1 || l.getX() == currentSwitch.getX() + 1 && l.getY() == currentSwitch.getY() - 1 || l.getX() == currentSwitch.getX() - 2 && l.getY() == currentSwitch.getY() - 2 || l.getX() == currentSwitch.getX() + 2 && l.getY() == currentSwitch.getY() + 2 || l.getX() == currentSwitch.getX() + 2 && l.getY() == currentSwitch.getY() - 2 || l.getX() == currentSwitch.getX() - 1 && l.getY() == currentSwitch.getY() + 1 || l.getX() == currentSwitch.getX() - 2 && l.getY() == currentSwitch.getY() + 2 || l.getX() == currentSwitch.getX() - 1 && l.getY() == currentSwitch.getY() - 2 || l.getX() == currentSwitch.getX() + 1 && l.getY() == currentSwitch.getY() - 2 || l.getX() == currentSwitch.getX() + 2 && l.getY() == currentSwitch.getY() - 1 || l.getX() == currentSwitch.getX() - 2 && l.getY() == currentSwitch.getY() - 1 || l.getX() == currentSwitch.getX() - 2 && l.getY() == currentSwitch.getY() + 1 || l.getX() == currentSwitch.getX() + 2 && l.getY() == currentSwitch.getY() + 1 || l.getX() == currentSwitch.getX() + 1 && l.getY() == currentSwitch.getY() + 2 || l.getX() == currentSwitch.getX() - 1 && l.getY() == currentSwitch.getY() + 2) {
                        usedSwitch.add(currentSwitch);
                        blocks.addAll(currentSwitch.getBlocks());
                    }
                }
                for(Switch currentSwitch : usedSwitch)
                    switches.remove(currentSwitch);

                for(Trap trap: traps){
                    for(Block b : blocks){
                        if(b.getX() == trap.getX() - 1 && b.getY() == trap.getY() || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() || b.getX() == trap.getX() && b.getY() == trap.getY() - 1  || b.getX() == trap.getX() && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() || b.getX() == trap.getX() && b.getY() == trap.getY() - 2  || b.getX() == trap.getX() && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() -1 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() +1 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() - 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() - 2 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() - 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() - 1 || b.getX() == trap.getX() - 2 && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() + 2 && b.getY() == trap.getY() + 1 || b.getX() == trap.getX() + 1 && b.getY() == trap.getY() + 2 || b.getX() == trap.getX() - 1 && b.getY() == trap.getY() + 2) {
                            surroundingBlocks.add(b);
                        }
                    }
                    for(Lemming lemming : lemmings){
                        if(lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() - 1  || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() - 2  || lemming.getX() == trap.getX() && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() -1 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() +1 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() - 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() - 2 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() - 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() - 1 || lemming.getX() == trap.getX() - 2 && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() + 2 && lemming.getY() == trap.getY() + 1 || lemming.getX() == trap.getX() + 1 && lemming.getY() == trap.getY() + 2 || lemming.getX() == trap.getX() - 1 && lemming.getY() == trap.getY() + 2) {
                            lemming.kill();
                        }
                    }
                    usedTraps.add(trap);
                }
                for(Block b : blocks) {
                    if(b.getX() == l.getX() - 1 && b.getY() == l.getY() || b.getX() == l.getX() + 1 && b.getY() == l.getY() || b.getX() == l.getX() && b.getY() == l.getY() - 1  || b.getX() == l.getX() && b.getY() == l.getY() + 1 || b.getX() == l.getX() - 2 && b.getY() == l.getY() || b.getX() == l.getX() + 2 && b.getY() == l.getY() || b.getX() == l.getX() && b.getY() == l.getY() - 2  || b.getX() == l.getX() && b.getY() == l.getY() + 2 || b.getX() == l.getX() - 1 && b.getY() == l.getY() -1 || b.getX() == l.getX() + 1 && b.getY() == l.getY() +1 || b.getX() == l.getX() + 1 && b.getY() == l.getY() - 1 || b.getX() == l.getX() - 2 && b.getY() == l.getY() - 2 || b.getX() == l.getX() + 2 && b.getY() == l.getY() + 2 || b.getX() == l.getX() + 2 && b.getY() == l.getY() - 2 || b.getX() == l.getX() - 1 && b.getY() == l.getY() + 1 || b.getX() == l.getX() - 2 && b.getY() == l.getY() + 2 || b.getX() == l.getX() - 1 && b.getY() == l.getY() - 2 || b.getX() == l.getX() + 1 && b.getY() == l.getY() - 2 || b.getX() == l.getX() + 2 && b.getY() == l.getY() - 1 || b.getX() == l.getX() - 2 && b.getY() == l.getY() - 1 || b.getX() == l.getX() - 2 && b.getY() == l.getY() + 1 || b.getX() == l.getX() + 2 && b.getY() == l.getY() + 1 || b.getX() == l.getX() + 1 && b.getY() == l.getY() + 2 || b.getX() == l.getX() - 1 && b.getY() == l.getY() + 2) {
                        surroundingBlocks.add(b);
                    }
                }
                surroundingBlocks = removeDuplicates(surroundingBlocks);
                for(Block b : surroundingBlocks) {
                    blocks.remove(b);
                }
                for(Trap trap : usedTraps){
                    traps.remove(trap);
                }
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
                    l.setDirectionAxisX(0);
                    l.setDirectionAxisY(-1);
                }
                else if(!surrounding[2][1] && l.getDirectionAxisY() == 1)
                {
                   // l.setX(l.getX() + 1);
                    l.setY(l.getY() - 1);
                    l.setState(Lemming.LemmingState.NORMAL);
                    l.setDirectionAxisX(1);
                }
                else if(!surrounding[0][1] && l.getDirectionAxisY() == 1)
                {
                    l.setX(l.getX() - 1);
                    l.setY(l.getY() - 1);
                    l.setState(Lemming.LemmingState.NORMAL);
                    l.setDirectionAxisX(1);
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

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        // Create a new LinkedHashSet
        Set<T> set = new LinkedHashSet<>();

        // Add the elements to set
        set.addAll(list);

        // Clear the list
        list.clear();

        // add the elements of set
        // with no duplicates to the list
        list.addAll(set);

        // return the list
        return list;
    }
}
