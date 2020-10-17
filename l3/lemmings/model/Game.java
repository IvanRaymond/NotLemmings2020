package l3.lemmings.model;

import java.util.ArrayList;

/**
 * Contains game variables
 */
public class Game {

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

        if(flowCounter == 0){
            level.getEntrance().addLemming();
            flowCounter = level.getEntrance().getFlow();
        }
        flowCounter--;

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
            if(surrounding[0][0] && surrounding[0][1] && l.getDirectionAxisX() == -1 || surrounding[2][0] && surrounding[2][1] && l.getDirectionAxisX() == 1)
            {
                l.setDirectionAxisX(l.getDirectionAxisX()*-1);
            }

            if(!surrounding[1][2])
            {
                l.setDirectionAxisY(1);
            }

            if(surrounding[1][2]){
                l.setDirectionAxisY(0);
            }

            if(surrounding[2][1] && !surrounding[2][0] && l.getDirectionAxisX() == 1 || surrounding[0][1] && !surrounding[0][0] && l.getDirectionAxisX() == -1)
            {
                l.setDirectionAxisY(-1);
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
