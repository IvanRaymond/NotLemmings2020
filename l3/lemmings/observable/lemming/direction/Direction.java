package l3.lemmings.observable.lemming.direction;

public class Direction {
    private DirHorizontal x;
    private DirVertical y;

    private DirHorizontal oldX = DirHorizontal.RIGHT;

    public Direction(DirHorizontal x, DirVertical y) {
        this.x = x;
        this.y = y;
    }

    public Direction(Direction direction) {
        this.x = direction.getX();
        this.y = direction.getY();
    }

    public int getIntX(){
        if (x == DirHorizontal.RIGHT) return 1;
        else if (x == DirHorizontal.LEFT) return -1;
        else return 0;
    }

    public DirHorizontal getX() {
        return x;
    }

    public DirVertical getY() {
        return y;
    }

    public boolean isGoing(DirHorizontal dir) {
        return x == dir;
    }

    public boolean isGoing(DirVertical dir) {
        return y == dir;
    }

    /**
     * Checks if lemming is leveled to the ground aka not falling
     *
     * @return true if y-axis is STILL
     */
    public boolean isleveled() {
        return getY() == DirVertical.STILL;
    }

    public boolean isStill() {
        return getX() == DirHorizontal.STILL;
    }

    public boolean isClimbing() {
        return getY() == DirVertical.UP;
    }

    public boolean isFalling() {
        return getY() == DirVertical.DOWN;
    }

    public boolean wasGoing(DirHorizontal x){
        return oldX == x;
    }

    public void turnAround() {
        x = x.getOppositeDirection();
    }

    public void stop() {
        if (x != DirHorizontal.STILL) {
            oldX = x;
        }
        x = DirHorizontal.STILL;
    }

    public void march() {
        x = oldX;
    }

    public void level() {
        y = DirVertical.STILL;
    }

    public void fall() {
        y = DirVertical.DOWN;
    }

    public void climb() {
        y = DirVertical.UP;
    }
}
