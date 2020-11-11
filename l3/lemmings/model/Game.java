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
        int gg=0;
        level.update();

        // ToDo: Entrance problem is here, don't know why for loop don't reach the second entrance.
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
            // Mur à gauche ou a droite de 2 blocks ou plus
            if(surrounding[0][0] && surrounding[0][1] && l.getDirectionAxisX() == -1 || surrounding[2][0] && surrounding[2][1] && l.getDirectionAxisX() == 1)
            {
                // ToDo: Add exceptions in other cases to cater for climber
                if(l.isState(Lemming.LemmingState.CLIMBER)){
                    l.saveDirectionX();
                    l.setDirectionAxisX(0);
                    l.setDirectionAxisY(-1);
                }else{
                    l.changeDirectionX();
                }
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
                }
            }

            // Escaliers
            if(surrounding[2][1] && !surrounding[2][0] && l.getDirectionAxisX() == 1 || surrounding[0][1] && !surrounding[0][0] && l.getDirectionAxisX() == -1)
            {
                if(!surrounding[1][0])
                    l.setDirectionAxisY(-1);
                else {
                    l.setDirectionAxisX(l.getDirectionAxisX() * -1);
                }
            }

            // Digger
            if(l.isState(Lemming.LemmingState.DIGGER)){
                if(surrounding[1][2]) // [1][2] bugs
                {
                    Block belowBlock = null;
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
                if(lem.isState(Lemming.LemmingState.BLOCKER) && lem.getX() == l.getX() && lem.getY() == l.getY()){
                    l.changeDirectionX();
                }
            }

            // Tunnelier
            if(l.isState(Lemming.LemmingState.BASHER)){
                if(surrounding[2][1]) // [1][2] bugs
                {
                    Block belowBlock = null;
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
                    for(Block b : blocks) {
                        if(b.getX() == l.getX() - 1 && b.getY() == l.getY() || b.getX() == l.getX() + 1 && b.getY() == l.getY() || b.getX() == l.getX() && b.getY() == l.getY() - 1  || b.getX() == l.getX() && b.getY() == l.getY() + 1  ) {
                            surroundingBlocks.add(b);
                        }
                    }
                    for(Block b : surroundingBlocks)
                        blocks.remove(b);
                        l.kill();
                }




            for(int i =0;i<3;i++)
            {
                for(int j =0;j<3;j++)
                    System.out.print(" "+surrounding[i][j]);
                System.out.println();
            }
            System.out.println();
            l.move();
        }

    }
}
