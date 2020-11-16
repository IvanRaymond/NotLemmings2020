package l3.lemmings.model.lemming.direction;

// ToDo implement an update method to translate direction into movement.
public class Direction {
    private DirHorizontal x;
    private DirVertical y;

    private DirHorizontal oldX;

    public Direction(DirHorizontal x, DirVertical y){
        this.x=x;
        this.y=y;
    }

    public Direction(Direction direction){
        this.x = direction.getX();
        this.y = direction.getY();
    }

    public DirHorizontal getX() {
        return x;
    }

    public DirVertical getY() {
        return y;
    }

    public boolean isGoing(DirHorizontal dir){
        return x == dir;
    }

    public boolean isGoing(DirVertical dir){
        return y == dir;
    }

    /**
     * Checks if lemming is leveled to the ground aka not falling
     * @return true if y-axis is STILL
     */
    public boolean isleveled(){
        return getY() == DirVertical.STILL;
    }

    public boolean isStill(){
        return getX() == DirHorizontal.STILL;
    }

    public boolean isClimbing(){
        return getY() == DirVertical.UP;
    }

    public boolean isFalling(){
        return getY() == DirVertical.DOWN;
    }

    public void turnAround(){
        x = x.getOppositeDirection();
    }

    public void stop(){
        oldX = x;
        x = DirHorizontal.STILL;
    }

    public void march(){
        x = oldX;
    }

    public void level(){
        y = DirVertical.STILL;
    }

    public void fall(){
        y = DirVertical.DOWN;
    }

    public void climb(){
        y = DirVertical.UP;
    }
}
