package l3.lemmings.model.lemming;

public class Stats {
    private boolean alive;
    private boolean busy;
    private FallSpeed fallSpeed;
    private boolean kill;

    public Stats() {
        alive = true;
        busy = false;
        fallSpeed = FallSpeed.FAST;
    }

    public void toggleBusy() {
        busy = !busy;
    }

    public boolean getIfBusy() {
        return busy;
    }

    public void kill() {
        alive = false;
    }

    public void toKill(){
        kill = true;
    }

    public boolean toKillVal(){
        return kill;
    }

    public boolean alive() {
        return alive;
    }

    public boolean isFallingFast() {
        return fallSpeed == FallSpeed.FAST;
    }

    public void fallFast() {
        fallSpeed = FallSpeed.FAST;
    }

    public void fallSlow() {
        fallSpeed = FallSpeed.SLOW;
    }
}
