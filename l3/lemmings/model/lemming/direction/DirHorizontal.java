package l3.lemmings.model.lemming.direction;

public enum DirHorizontal {

    LEFT, RIGHT, STILL;

    private DirHorizontal opposite;

    static {
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
        STILL.opposite = STILL;
    }

    public DirHorizontal getOppositeDirection() {
        return opposite;
    }

}
