package l3.lemmings.observable.lemming.direction;

/**
 * Enum that represents 4 directions and can return the opposite direction
 */
public enum DirVertical {

    DOWN, UP, STILL;

    private DirVertical opposite;

    static {
        DOWN.opposite = UP;
        UP.opposite = DOWN;
        STILL.opposite = STILL;
    }

    public DirVertical getOppositeDirection() {
        return opposite;
    }

}
